(ns busa-server.client.matkahuolto
  (:require
    [busa-server.client.util :as util]
    [org.httpkit.client :as http]
    [schema.core :as s]))

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
    :departure-date departure-date
    }))

(defn connections-api-url [params]
  (-> connections-api-template
    (.replace ":arrivalId" (:arrival-id params))
    (.replace ":departureId" (:departure-id params))
    (.replace ":departureDate" (:departure-date params))))

(defn fetch-todays-connections [departure-place arrival-place]
  nil)
