(ns busa-server.api.connection-api
  (:require
    [compojure.core :refer :all]
    [ring.util.response :refer :all]
    [busa-server.api.api-utils :refer :all]
    [busa-server.core.connections :as connections]
    [busa-server.model.place :as place]))

(def prefix "/connections")

(def departuring-next
  (GET (str prefix "/departuring_next") [:as req]
    (-> (connections/find-all-connections-departuring-next) response)))

(def departuring-next-by-places
  (GET (str prefix "/departuring_next/:from/:to") [from to]
    (let [from-place (place/find-by-name from)
          to-place (place/find-by-name to)]
      (-> (connections/find-n-connections-departuring-next from-place to-place 3) response))))

(defroutes connection-api departuring-next departuring-next-by-places)
