(ns busa-server.core.connections-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.core.connections :as connections]
    [busa-server.core.utils :as utils]
    [busa-server.model.connection :as connection]
    [busa-server.model.place :as place]
    [busa-server.model.fixtures :as fixtures]))

(fact "should clear all connections"
  (connections/clear-connections) => anything
  (provided
    (connection/delete-all) => anything))

(fact "should reload todays connections"
  (connections/reload-connections) => anything
  (provided
    (connections/clear-connections) => anything
    (connections/load-connections-from-to place/nummela place/helsinki) => anything
    (connections/load-connections-from-to place/helsinki place/nummela) => anything))
