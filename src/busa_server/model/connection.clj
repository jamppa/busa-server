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

(defn save [connections]
  (doseq [c connections] (s/validate Connection c))
  (db/save connections table))

(defn delete-all []
  (db/delete-all table))
