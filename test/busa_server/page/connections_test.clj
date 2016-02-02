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

(fact "should return departure dates from page"
  (connections-page/departure-times connection-page-details) => (has-prefix ["00:00" "01:10" "06:45" "07:15"])
  (provided
    (driver/fetch anything) => (slurp "connections.html")))
