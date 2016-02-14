(ns busa-server.model.db-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.db :as db]))

(fact "should set current database"
  (db/set-db!)
  @db/current-db => (db/config :db))

(fact "should set current database as test database"
  (db/set-test-db!)
  @db/current-db => (db/config :db-test))

(fact "should connect to current database"
  (db/set-test-db!)
  (db/connect)
  (instance? rethinkdb.core.Connection @db/current-db-connection) => truthy)
