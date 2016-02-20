(ns busa-server.core.connections
  (:require
    [busa-server.model.connection :as connection]
    [busa-server.page.connections :as connections-page]
    [busa-server.core.utils :as utils]))

(defn clear-connections []
  (connection/delete-all))

(defn fetch-connections [arrival-place departure-place]
  (let [page-details (connections-page/new-details arrival-place departure-place (utils/today-as-iso))
        connections (connections-page/connections page-details)]

      (map #(connection/new-connection %1) connections)))

(defn load-connections []
  nil)
