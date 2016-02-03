(ns busa-server.model.connection-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as connection]))

(def valid-connection-map {:departure-time "00:00" :duration "45min" :arrival-place-id "p1447" :departure-place-id "p1001"})

(fact "should make new connection with id from map"
  (connection/new-connection valid-connection-map) => (contains (merge valid-connection-map {:id anything})))

(fact "should not make new connection from map missing departure time"
  (connection/new-connection (dissoc valid-connection-map :departure-time)) => (throws Exception))

(fact "should not make new connection from map missing duration"
  (connection/new-connection (dissoc valid-connection-map :duration)) => (throws Exception))
