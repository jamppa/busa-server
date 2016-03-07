(ns busa-server.api.connection-api
  (:require
    [compojure.core :refer :all]
    [ring.util.response :refer :all]
    [busa-server.api.api-utils :refer :all]
    [busa-server.core.connections :as connections]))

(def prefix "/connections")

(def departuring-next
  (GET (str prefix "/departuring_next") [departure :<< required arrival :<< required :as req]
    (->
      (connections/find-connection-departuring-next departure arrival)
      response)))

(defroutes connection-api departuring-next)
