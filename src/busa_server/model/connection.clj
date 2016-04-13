(ns busa-server.model.connection
  (:require
    [schema.core :as s]
    [busa-server.model.db :as db]))

(s/defrecord ConnectionPlace
  [time   :- s/Str
   name   :- s/Str])

(s/defrecord Connection
  [id          :- s/Str
   duration    :- s/Str
   from-place  :- ConnectionPlace
   to-place    :- ConnectionPlace])

(def table "connections")

(defn- with-id [connection]
  (merge connection {:id (.toString (java.util.UUID/randomUUID))}))

(defn- make-connection-place [keyvals]
  (s/validate ConnectionPlace (map->ConnectionPlace keyvals)))

(defn- make-connection-kvs [kvs]
  (let [from-place (make-connection-place (:from-place kvs))
        to-place (make-connection-place (:to-place kvs))]
    (merge kvs {:from-place from-place :to-place to-place})))

(defn make-connection [kvs]
  (let [c-kvs (make-connection-kvs kvs)]
    (s/validate Connection (map->Connection (with-id c-kvs)))))

(defn to-connection [kvs]
  (let [c-kvs (make-connection-kvs kvs)]
    (s/validate Connection (map->Connection c-kvs))))

(defn save [connections]
  (doseq [c connections] (s/validate Connection c))
  (db/throw-if-errors (db/save connections table) (Exception. "Save of connections failed!")))

(defn delete-all []
  (db/delete-all table))

(defn find-by-from-to [from-place to-place]
  (let [from-place-name (:name from-place)
        to-place-name (:name to-place)]
    (->> (db/filter-by-predicate table {:from-place {:name from-place-name} :to-place {:name to-place-name}})
         (map #(to-connection %)))))
