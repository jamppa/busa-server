(ns busa-server.core.connections
  (:require
    [busa-server.model.connection :as connection]
    [busa-server.page.connections :as connections-page]
    [busa-server.core.utils :as utils]))

(defn clear-connections []
  (connection/delete-all))

(defn fetch-connections [departure-place arrival-place]
  (let [page-details (connections-page/new-details departure-place arrival-place (utils/today-as-iso))
        connections (connections-page/connections page-details)]

      (map #(connection/new-connection %1) connections)))

(defn load-connections []
  nil)
