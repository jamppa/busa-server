(ns busa-server.api.connection-api-test
  (:require
    [midje.sweet :refer :all]
    [ring.mock.request :as mock]
    [busa-server.api.handler :as handler]
    [busa-server.core.connections :as connections]))

(fact "should find connection departuring next by departure and arrival place"
  (handler/busa-handler
    (mock/request :get "/api/connections/departuring_next?departure=nummela&arrival=helsinki")) => (contains {:body {:id "foo"} :status 200})
    (provided
      (connections/find-connection-departuring-next "nummela" "helsinki") => {:id "foo"}))

(fact "should find all connections departuring next"
  (handler/busa-handler
    (mock/request :get "/api/connections/departuring_next/all")) => (contains {:body [] :status 200})
    (provided
      (connections/find-all-connections-departuring-next) => []))