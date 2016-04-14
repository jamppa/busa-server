(ns busa-server.core.connections
  (:require
    [busa-server.model.connection :as connection]
    [busa-server.model.place :as place]
    [busa-server.core.utils :as utils]
    [busa-server.client.matkahuolto :as matkahuolto]))

(def places [
  [place/nummela place/helsinki]
  [place/helsinki place/nummela]])

(defn clear-connections []
  (connection/delete-all))

(defn load-connections-from-to [departure-place arrival-place]
  (let [fetched-connections (matkahuolto/fetch-todays-connections arrival-place departure-place)]
    (connection/save fetched-connections)))

(defn reload-connections []
  (clear-connections)
  (doseq [[from to] places]
    (load-connections-from-to from to)))

(defn- sorted-by-departure-time [connections]
  (sort #(compare
    (utils/date-to-millis (utils/iso-to-date (get-in %1 [:from-place :time])))
    (utils/date-to-millis (utils/iso-to-date (get-in %2 [:from-place :time])))) connections))

(defn- past-connections-dropped [connections]
  (filter #(utils/date-after-now? (utils/iso-to-date (get-in % [:from-place :time]))) connections))

(defn find-connection-departuring-next [from-place to-place]
  (let [connections (connection/find-by-from-to from-place to-place)]
    (-> connections sorted-by-departure-time past-connections-dropped first)))

(defn find-all-connections-departuring-next []
  (map #(find-connection-departuring-next (get % 0) (get % 1)) places))
