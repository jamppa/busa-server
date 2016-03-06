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
  (connection/new-connection
    (merge connection-map {:departure-place-id "p1001" :arrival-place-id "p1447"})))

(def connection-nummela-helsinki
  (connection/new-connection
    (merge connection-map {:departure-place-id "p1447" :arrival-place-id "p1001"})))

(def connection-helsinki-nummela-departing-later
  (connection/new-connection
    (merge connection-map {:departure-place-id "p1001" :arrival-place-id "p1447" :departure-time 1456066000000})))

(def connection-nummela-helsinki-departing-later
  (connection/new-connection
    (merge connection-map {:departure-place-id "p1447" :arrival-place-id "p1001" :departure-time 1456066000000})))

(def connection-nummela-helsinki-departing-in-far-future
  (connection/new-connection
    (merge connection-map {:departure-place-id "p1447" :arrival-place-id "p1001" :departure-time 4108031911000})))

(def connections
  [connection-nummela-helsinki
   connection-helsinki-nummela
   connection-nummela-helsinki-departing-later
   connection-helsinki-nummela-departing-later
   connection-nummela-helsinki-departing-in-far-future])

(defn load-fixtures []
  (db/save connections connection/table))
