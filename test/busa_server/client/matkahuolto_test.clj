(ns busa-server.client.matkahuolto-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.client.matkahuolto :as m]))

(def connections-params (busa_server.client.matkahuolto.ConnectionsParams. "p1001" "p1447" "2016-4-10"))

(fact "should make new connections params"
  (m/make-connections-params
    {:arrivalId "p1001"
     :departureId "p1447"
     :departureDate "2016-4-10"}) => connections-params)

(fact "should format connections api url from params"
  (m/connections-api-url connections-params) =>
    "https://liput.matkahuolto.fi/minfo/mlippu_rest/connections?allSchedules=0&arrivalStopAreaId=p1001&departureDate=2016-4-10&departureStopAreaId=p1447&ticketTravelType=0")
