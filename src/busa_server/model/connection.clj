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

(defn make-connection-place [keyvals]
  (s/validate ConnectionPlace (map->ConnectionPlace keyvals)))

(defn make-connection [keyvals]
  (s/validate Connection (map->Connection (with-id keyvals))))

(defn save [connections]
  (doseq [c connections] (s/validate Connection c))
  (db/throw-if-errors (db/save connections table) (Exception. "Save of connections failed!")))

(defn delete-all []
  (db/delete-all table))
