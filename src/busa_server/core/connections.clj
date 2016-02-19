(ns busa-server.core.connections
  (:require
    [busa-server.model.connection :as connection]
    [busa-server.page.connections :as connections-page]
    [busa-server.core.utils :as utils]))

(defn to-page-details [a-place d-place date]
  (connections-page/new-details (:id a-place) (:id d-place) date))

(defn clear-connections []
  (connection/delete-all))

(defn fetch-connections [arrival-place departure-place]
  (let [page-details (to-page-details arrival-place departure-place (utils/today-as-iso))
        connections-details (connections-page/details page-details)]

      nil))
