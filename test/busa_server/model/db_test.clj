(ns busa-server.model.db-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.db :as db]))

(fact "should set current database"
  (db/set-db!)
  @db/current-db => (db/config :db))
