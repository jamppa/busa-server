(ns busa-server.model.fixtures
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as connection]
    [busa-server.model.db :as db]))

(def connection-map {
  :departure-time 1456065000000
  :duration "45min"
  :arrival-place-id nil
  :departure-place-id nil})

(def connection-helsinki-nummela
  (connection/new-connection (merge connection-map {:departure-place-id "p1001" :arrival-place-id "p1447" })))

(def connection-nummela-helsinki
  (connection/new-connection (merge connection-map {:departure-place-id "p1447" :arrival-place-id "p1001" })))

(def connections
  [connection-nummela-helsinki
   connection-helsinki-nummela])

(defn load-fixtures []
  (db/save connections connection/table))
