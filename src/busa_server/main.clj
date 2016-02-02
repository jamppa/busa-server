(ns busa-server.main
  (:require [busa-server.page.driver :as page-driver])
  (:gen-class))

(defn -main
  [& args]
  (println "Starting BUSA Server")
  (page-driver/init)
  (print "Webdrvier initialized"))
