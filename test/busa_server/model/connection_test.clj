(ns busa-server.model.connection-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as connection]
    [busa-server.model.place :as place]
    [busa-server.model.db :as db]
    [busa-server.model.fixtures :as fixtures]))

(with-state-changes [(before :facts (do (db/setup-test-db) (fixtures/load-fixtures)))]

  (fact "should test"
    true => truthy)
)
