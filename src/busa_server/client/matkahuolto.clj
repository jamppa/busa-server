(ns busa-server.client.matkahuolto
  (:require
    [busa-server.client.util :as util]
    [org.httpkit.client :as http]
    [schema.core :as s]))

(def connections-api-template
  "https://liput.matkahuolto.fi/minfo/mlippu_rest/connections?allSchedules=0&arrivalStopAreaId=:arrivalId&departureDate=:departureDate&departureStopAreaId=:departureId&ticketTravelType=0")

(s/defrecord ConnectionsParams
  [arrivalId    :- s/Str
   departureId  :- s/Str
   departureDate :- s/Str])

(defn make-connections-params [keyvals]
  (s/validate ConnectionsParams (map->ConnectionsParams keyvals)))

(defn connections-api-url [params]
  (-> connections-api-template
    (.replace ":arrivalId" (:arrivalId params))
    (.replace ":departureId" (:departureId params))
    (.replace ":departureDate" (:departureDate params))))
