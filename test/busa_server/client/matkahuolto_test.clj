(ns busa-server.client.matkahuolto-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.client.matkahuolto :as m]
    [busa-server.model.place :as p]))

(def connections-params (busa_server.client.matkahuolto.ConnectionsParams. "p1001" "p1447" "2016-04-10"))

(fact "should make new connections params"
  (m/make-connections-params
    {:arrival-id "p1001"
     :departure-id "p1447"
     :departure-date "2016-04-10"}) => connections-params)

(fact "should format connections api url from params"
  (m/connections-api-url connections-params) =>
    "https://liput.matkahuolto.fi/minfo/mlippu_rest/connections?allSchedules=0&arrivalStopAreaId=p1001&departureDate=2016-04-10&departureStopAreaId=p1447&ticketTravelType=0")

(fact "should define client options"
  m/client-options => {:headers {"Accept-Language" "fi" "Accept" "application/json" "Theme" "matkahuolto"}})

(fact "should make new connections params from places and date"
  (m/to-connections-params p/helsinki p/nummela "2016-04-10") => connections-params)

(fact "should fetch todays connections by departure and arrival place"
  (m/fetch-todays-connections p/helsinki p/nummela) =>
    (contains (contains {:id anything :duration anything :from anything :to anything})))
