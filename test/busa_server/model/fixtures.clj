(ns busa-server.model.fixtures
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as connection]
    [busa-server.model.db :as db]))

(def connections [])

(defn load-fixtures []
  (db/save connections connection/table))
