(ns busa-server.model.connection
  (:require
    [schema.core :as s]
    [busa-server.model.db :as db]))

(s/defrecord Connection
  [id                 :- s/Str
   departure-time     :- s/Num
   duration           :- s/Str
   arrival-place-id   :- s/Str
   departure-place-id :- s/Str])

(def table "connections")

(defn- new-uuid []
  (str (java.util.UUID/randomUUID)))

(defn- with-id [obj]
  (assoc obj :id (new-uuid)))

(defn- to-connection [fields]
  (s/validate Connection (map->Connection fields)))

(defn- to-connections [fields-seq]
  (map #(to-connection %1) fields-seq))

(defn new-connection [fields]
  (s/validate Connection (map->Connection (-> fields with-id))))

(defn save [connections]
  (doseq [c connections] (s/validate Connection c))
  (db/save connections table))

(defn delete-all []
  (db/delete-all table))

(defn find-by-places [departure arrival]
  (-> (db/find-by-predicate table {:departure-place-id (:id departure) :arrival-place-id (:id arrival)})
      (to-connections)))
