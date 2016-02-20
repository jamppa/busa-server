(ns busa-server.page.connections-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.page.connections :as connections-page]
    [busa-server.page.driver :as driver]
    [busa-server.model.place :as place]))

(fact "should create new page details"
  (connections-page/new-details place/nummela place/helsinki "2016-02-01") =>
    {:arrival-place-id "p1447" :departure-place-id "p1001" :departure-date "2016-02-01"})

(def connection-page-details (connections-page/new-details place/nummela place/helsinki "2016-01-01"))

(fact "should format url for connections page"
  (connections-page/url connection-page-details) =>
    "https://liput.matkahuolto.fi/connectionlist?lang=fi&arrivalPlaceId=p1447&departurePlaceId=p1001&departureDate=2016-01-01")

(fact "should return departure times from page html"
  (connections-page/departure-times (slurp "test/resources/connections.html")) => (has-prefix ["00:00" "01:10" "06:45" "07:15"]))

(fact "should return durations from page html"
  (connections-page/durations (slurp "test/resources/connections.html")) => (has-prefix ["45min" "40min" "50min"]))

(def expected-connection-detail {:departure-date "2016-01-01" :departure-place-id "p1001" :arrival-place-id "p1447" :departure-time "00:00" :duration "50min"})
(def page-html "<foo></foo>")
(fact "should return connection details"
  (connections-page/details connection-page-details) => [expected-connection-detail expected-connection-detail]
  (provided
    (connections-page/page-html connection-page-details) => page-html
    (connections-page/departure-times page-html) => ["00:00" "00:00"]
    (connections-page/durations page-html) => ["50min" "50min"]))
