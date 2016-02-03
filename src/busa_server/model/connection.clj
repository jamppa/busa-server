(ns busa-server.model.connection
  (:require [schema.core :as s]))

(s/defrecord Connection
  [id                 :- s/Str
   departure-time     :- s/Str
   duration           :- s/Str
   arrival-place-id   :- s/Str
   departure-place-id :- s/Str])

(defn- new-uuid []
  (str (java.util.UUID/randomUUID)))

(defn- with-id [obj]
  (assoc obj :id (new-uuid)))

(defn new-connection [fields]
  (s/validate Connection (map->Connection (-> fields with-id))))
