(ns busa-server.core.connections-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.core.connections :as connections]
    [busa-server.model.connection :as connection]))

(fact "should clear all connections"
  (connections/clear-connections) => anything
  (provided
    (connection/delete-all) => anything))
