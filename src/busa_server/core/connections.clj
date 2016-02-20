(ns busa-server.core.connections
  (:require
    [busa-server.model.connection :as connection]
    [busa-server.model.place :as place]
    [busa-server.page.connections :as connections-page]
    [busa-server.core.utils :as utils]))

(defn clear-connections []
  (connection/delete-all))

(defn fetch-connections [departure-place arrival-place]
  (let [page-details (connections-page/new-details departure-place arrival-place (utils/today-as-iso))
        connections (connections-page/connections page-details)]

      (map #(connection/new-connection %1) connections)))

(defn load-connections-from-to [departure-place arrival-place]
  (->
    (fetch-connections departure-place arrival-place)
    (connection/save)))

(defn load-connections []
  (clear-connections)
  )
