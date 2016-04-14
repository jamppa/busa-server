(ns busa-server.api.handler
  (:require
    [compojure.core :refer :all]
    [compojure.handler :as handler]
    [ring.middleware.format :refer [wrap-restful-format]]
    [busa-server.api.connection-api :refer :all]))

(defroutes busa-routes
  (context "/api" [] connection-api))

(def busa-handler
  (->
    (handler/site busa-routes)
    (wrap-restful-format :formats [:json-kw])))
