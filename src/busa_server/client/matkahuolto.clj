(ns busa-server.client.matkahuolto
  (:require
    [org.httpkit.client :as http]
    [schema.core :as s]
    [busa-server.core.utils :as util]
    [busa-server.model.connection :as c]))

(def connections-api-template
  "https://liput.matkahuolto.fi/minfo/mlippu_rest/connections?allSchedules=0&arrivalStopAreaId=:arrivalId&departureDate=:departureDate&departureStopAreaId=:departureId&ticketTravelType=0")

(def client-options {
  :headers {"Accept" "application/json"
            "Accept-Language" "fi"
            "Theme" "matkahuolto"}})

(s/defrecord ConnectionsParams
  [arrival-id    :- s/Str
   departure-id  :- s/Str
   departure-date :- s/Str])

(defn make-connections-params [keyvals]
  (s/validate ConnectionsParams (map->ConnectionsParams keyvals)))

(defn to-connections-params [arrival-place departure-place departure-date]
  (make-connections-params {
    :arrival-id (:id arrival-place)
    :departure-id (:id departure-place)
    :departure-date departure-date}))

(defn connections-api-url [params]
  (-> connections-api-template
    (.replace ":arrivalId" (:arrival-id params))
    (.replace ":departureId" (:departure-id params))
    (.replace ":departureDate" (:departure-date params))))

(defn- connections-from-resp [resp]
  (-> resp
    (util/response-body)
    (get-in [:connections])))

(defn- strip-connection-place [place]
  (let [stripped (select-keys place [:placeName :dateTime])]
    (hash-map :name (:placeName stripped), :time (:dateTime stripped))))

(defn- to-connection [keyvals]
  (let [from-place (strip-connection-place (:fromPlace keyvals))
        to-place (strip-connection-place (:toPlace keyvals))]
    (c/make-connection
      (merge (select-keys keyvals [:id :duration]) {:from from-place :to to-place}))))

(defn- to-connections [connections-from-resp]
  (->> connections-from-resp
      (map #(select-keys % [:id :duration :fromPlace :toPlace]))
      (map #(to-connection %))))

(defn fetch-todays-connections [arrival-place departure-place]
  (let [api (connections-api-url (to-connections-params arrival-place departure-place (util/today-as-iso)))
        resp (http/get api client-options)]
    (->> (connections-from-resp @resp) (to-connections))))
