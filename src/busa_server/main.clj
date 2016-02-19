(ns busa-server.main
  (:require
    [busa-server.page.driver :as page-driver]
    [busa-server.model.db :as db])
  (:gen-class))

(defn -main
  [& args]
  (println "Starting BUSA Server")
  (page-driver/init)
  (println "Webdriver initialized")
  (db/setup-db)
  (println "Database initialized"))
