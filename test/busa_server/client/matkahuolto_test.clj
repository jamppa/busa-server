(ns busa-server.client.matkahuolto-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.client.matkahuolto :as m]))

(fact "should make new connections params"
  (m/make-connection-params
    {:arrivalId "p1001"
     :departureId "p1447"
     :departureDate "2016-4-10"}) => (busa_server.client.matkahuolto.ConnectionParams. "p1001" "p1447" "2016-4-10"))
