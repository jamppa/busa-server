(ns busa-server.api.connection-api-test
  (:require
    [midje.sweet :refer :all]
    [ring.mock.request :as mock]
    [busa-server.api.handler :as handler]
    [busa-server.core.connections :as connections]
    [busa-server.model.place :as place]))

(fact "should return HTTP status 200 when finding all connections departuring next"
  (handler/busa-handler
    (mock/request :get "/api/connections/departuring_next")) => (contains {:status 200})
    (provided
      (connections/find-all-connections-departuring-next) => []))

(fact "should return HTTP status 200 when finding connections departuring next"
  (handler/busa-handler
    (mock/request :get "/api/connections/nummela/helsinki")) => (contains {:status 200})
    (provided
      (place/find-by-name "nummela") => place/nummela
      (place/find-by-name "helsinki") => place/helsinki
      (connections/find-n-connections-departuring-next place/nummela place/helsinki 3) => []))
