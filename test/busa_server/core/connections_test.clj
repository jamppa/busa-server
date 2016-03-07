(ns busa-server.core.connections-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.core.connections :as connections]
    [busa-server.core.utils :as utils]
    [busa-server.model.connection :as connection]
    [busa-server.model.place :as place]
    [busa-server.page.connections :as connections-page]
    [busa-server.model.fixtures :as fixtures]))

(fact "should clear all connections"
  (connections/clear-connections) => anything
  (provided
    (connection/delete-all) => anything))

(def connection-detail {:departure-time "00:00" :duration "45min" :arrival-place-id "1" :departure-place-id "2"})
(def connection {:id "123abc"})
(def page-details {})
(def today (utils/today-as-iso))

(fact "should fetch todays connections by departure and arrival place"
  (connections/fetch-connections place/nummela place/helsinki) => [connection]
  (provided
    (connections-page/new-details place/nummela place/helsinki today) => page-details
    (connections-page/connections page-details) => [connection-detail]
    (connection/new-connection connection-detail) => connection))

(fact "should load todays connections from departure place to arrival place"
  (connections/load-connections-from-to place/nummela place/helsinki) => anything
  (provided
    (connections/fetch-connections place/nummela place/helsinki) => [connection connection]
    (connection/save [connection connection]) => anything))

(fact "should load all todays connections"
  (connections/load-connections) => anything
  (provided
    (connections/clear-connections) => anything
    (connections/load-connections-from-to place/nummela place/helsinki) => anything
    (connections/load-connections-from-to place/helsinki place/nummela) => anything))

(fact "should find connection departuring next"
  (connections/find-connection-departuring-next "nummela" "helsinki") => fixtures/connection-helsinki-nummela
    (provided
      (place/find-by-name "nummela") => place/nummela
      (place/find-by-name "helsinki") => place/helsinki
      (utils/now-millis) => 1
      (connection/find-by-places-and-departuring-next place/nummela place/helsinki 1) => fixtures/connection-helsinki-nummela))
