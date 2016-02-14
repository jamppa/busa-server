(ns busa-server.model.db
  (:require [rethinkdb.query :as r]))

(def current-db (atom nil))
(def current-db-connection (atom nil))
(def config-map {:db "busa" :db-test "busa_test"})

(defn config [& keys]
  (get-in config-map keys))

(defn set-current-db! [db]
  (reset! current-db (config db)))

(defn set-test-db! []
  (set-current-db! :db-test))

(defn set-db! []
  (set-current-db! :db))

(defn connect []
  (reset! current-db-connection
    (r/connect :host "busa-db" :port 28015 :db @current-db)))
