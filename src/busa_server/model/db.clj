(ns busa-server.model.db
  (:require
    [rethinkdb.query :as r]))

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

(defn table-exists? [name]
  (not (empty? (filter #(= name %1)
    (-> (r/db @current-db)
        (r/table-list)
        (r/run @current-db-connection))))))

(defn create-table [name]
  (when-not (table-exists? name)
    (-> (r/db @current-db)
        (r/table-create name)
        (r/run @current-db-connection))))

(defn db-exists? [name]
  (not (empty? (filter #(= name %1)
    (-> (r/db-list)
        (r/run @current-db-connection))))))

(defn create-db [name]
  (when-not (db-exists? name)
    (->
      (r/db-create name)
      (r/run @current-db-connection))))

(defn drop-db [name]
  (when (db-exists? name)
    (->
      (r/db-drop name)
      (r/run @current-db-connection))))

(defn setup-test-db []
  (set-test-db!)
  (connect)
  (drop-db @current-db)
  (create-db @current-db)
  (create-table "connections"))

(defn setup-db []
  (set-db!)
  (connect)
  (create-db @current-db)
  (create-table "connections"))

(defn save [objs table]
  (->
    (r/db @current-db)
    (r/table table)
    (r/insert objs)
    (r/run @current-db-connection)))

(defn delete-all [table]
  (->
    (r/db @current-db)
    (r/table table)
    (r/delete)
    (r/run @current-db-connection)))

(defn find-by-predicate [table predicate]
  (->
    (r/db @current-db)
    (r/table table)
    (r/filter predicate)
    (r/run @current-db-connection)))
