(ns busa-server.main
  (:require
    [busa-server.page.driver :as page-driver]
    [busa-server.model.db :as db]
    [busa-server.core.connections :as connections])
  (:gen-class))

(defn -main
  [& args]
  (println "Starting BUSA Server")
  (page-driver/init)
  (println "Webdriver initialized")
  (db/setup-db)
  (println "Database initialized")
  (connections/load-connections)
  (println "Todays connections loaded"))
