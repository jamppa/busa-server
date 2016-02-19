(ns busa-server.model.connection-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as connection]
    [busa-server.model.db :as db]))

(def valid-connection-map {
  :departure-time "00:00"
  :duration "45min"
  :arrival-place-id "p1447"
  :departure-place-id "p1001"})

(with-state-changes [(before :facts (db/setup-test-db))]

(fact "should make new connection with id from map"
  (connection/new-connection valid-connection-map) => (contains (merge valid-connection-map {:id anything})))

(fact "should not make new connection from map missing departure time"
  (connection/new-connection (dissoc valid-connection-map :departure-time)) => (throws Exception))

(fact "should not make new connection from map missing duration"
  (connection/new-connection (dissoc valid-connection-map :duration)) => (throws Exception))

(fact "should not make new connection from map missing arrival place id"
  (connection/new-connection (dissoc valid-connection-map :arrival-place-id)) => (throws Exception))

(fact "should not make new connection from map missing departure place id"
  (connection/new-connection (dissoc valid-connection-map :departure-place-id)) => (throws Exception))

(def first-connection (connection/new-connection valid-connection-map))
(def second-connection (connection/new-connection valid-connection-map))
(fact "should save connections to db"
  (connection/save [first-connection second-connection]) => (contains {:inserted 2}))

(fact "should delete all connections from db"
  (connection/save [first-connection second-connection])
  (connection/delete-all) => (contains {:deleted 2}))

)
