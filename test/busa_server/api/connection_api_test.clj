(ns busa-server.api.connection-api-test
  (:require
    [midje.sweet :refer :all]
    [ring.mock.request :as mock]
    [busa-server.api.handler :as handler]
    [busa-server.core.connections :as connections]))

(fact "should return HTTP status 200 when finding all connections departuring next"
  (handler/busa-handler
    (mock/request :get "/api/connections/departuring_next")) => (contains {:status 200})
    (provided
      (connections/find-all-connections-departuring-next) => []))
