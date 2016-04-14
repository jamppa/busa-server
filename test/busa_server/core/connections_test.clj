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

(def c1 fixtures/nla-hki-departuring-in-one-hour-from-now)
(def c2 fixtures/nla-hki-departuring-in-two-hours-from-now)
(def c3 fixtures/nla-hki-departured-one-hour-ago)

(fact "should find connection departuring next"
  (connections/find-connection-departuring-next place/nummela place/helsinki) => c1
  (provided
    (connection/find-by-from-to place/nummela place/helsinki) => [c1 c2 c3]))

(fact "should find all connections departuring next"
  (connections/find-all-connections-departuring-next) => [c1 c1]
  (provided
    (connections/find-connection-departuring-next place/nummela place/helsinki) => c1
    (connections/find-connection-departuring-next place/helsinki place/nummela) => c1))
