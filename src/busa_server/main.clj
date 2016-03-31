(ns busa-server.main
  (:require
    [busa-server.page.driver :as page-driver]
    [busa-server.model.db :as db]
    [busa-server.core.connections :as connections]
    [busa-server.core.scheduler :as scheduler]
    [busa-server.api.handler :as busa-api]
    [org.httpkit.server :as httpkit])
  (:gen-class))

(defn init []
  (page-driver/init)
  (println "Webdriver initialized")
  (db/setup-db)
  (println "Database initialized")
  (connections/load-connections)
  (println "Connections loaded")
  (scheduler/run-everyday-after-midnight connections/load-connections))

(defn start-server [conf]
  (httpkit/run-server #'busa-api/busa-handler conf)
  (println (str "Server started at port " (:port conf))))

(defn -main
  [& args]
  (println "Starting BUSA Server")
  (init)
  (start-server {:port 3000}))
