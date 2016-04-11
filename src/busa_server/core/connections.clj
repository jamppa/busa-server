(ns busa-server.core.connections
  (:require
    [busa-server.model.connection :as connection]
    [busa-server.model.place :as place]
    [busa-server.core.utils :as utils]))

(defn clear-connections []
  (connection/delete-all))

(defn load-connections-from-to [departure-place arrival-place]
  nil)

(defn load-connections []
  (clear-connections)
  (load-connections-from-to place/nummela place/helsinki)
  (load-connections-from-to place/helsinki place/nummela))

(defn find-connection-departuring-next [from-place to-place]
  nil)

(defn find-all-connections-departuring-next []
  nil)
