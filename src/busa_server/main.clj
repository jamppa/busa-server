(ns busa-server.main
  (:require
    [busa-server.page.driver :as page-driver]
    [busa-server.model.db :as db]
    [busa-server.core.connections :as connections])
  (:gen-class))

(defn init []
  (page-driver/init)
  (println "Webdriver initialized")
  (db/setup-db)
  (println "Database initialized")
  (connections/load-connections)
  (println "Todays connections loaded"))

(defn -main
  [& args]
  (println "Starting BUSA Server")
  (init))
