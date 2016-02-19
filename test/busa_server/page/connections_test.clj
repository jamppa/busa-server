(ns busa-server.page.connections-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.page.connections :as connections-page]
    [busa-server.page.driver :as driver]))

(fact "should create new page details"
  (connections-page/new-details "1" "2" "2016-02-01") =>
    {:arrival-place-id "1" :departure-place-id "2" :departure-date "2016-02-01"})

(def connection-page-details (connections-page/new-details "10" "20" "2016-01-01"))

(fact "should format url for connections page"
  (connections-page/url connection-page-details) =>
    "https://liput.matkahuolto.fi/connectionlist?lang=fi&arrivalPlaceId=10&departurePlaceId=20&departureDate=2016-01-01")

(fact "should return departure times from page html"
  (connections-page/departure-times (slurp "test/resources/connections.html")) => (has-prefix ["00:00" "01:10" "06:45" "07:15"]))

(fact "should return durations from page html"
  (connections-page/durations (slurp "test/resources/connections.html")) => (has-prefix ["45min" "40min" "50min"]))

(def expected-connection-detail {:departure-date "2016-01-01" :departure-place-id "20" :arrival-place-id "10" :departure-time "00:00" :duration "50min"})
(def page-html "<foo></foo>")
(fact "should return connection details"
  (connections-page/details connection-page-details) => [expected-connection-detail expected-connection-detail]
  (provided
    (connections-page/page-html connection-page-details) => page-html
    (connections-page/departure-times page-html) => ["00:00" "00:00"]
    (connections-page/durations page-html) => ["50min" "50min"]))
