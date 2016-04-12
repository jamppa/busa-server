(ns busa-server.core.connections
  (:require
    [busa-server.model.connection :as connection]
    [busa-server.model.place :as place]
    [busa-server.core.utils :as utils]
    [busa-server.client.matkahuolto :as matkahuolto]))

(defn clear-connections []
  (connection/delete-all))

(defn load-connections-from-to [departure-place arrival-place]
  (let [fetched-connections (matkahuolto/fetch-todays-connections arrival-place departure-place)]
    (connection/save fetched-connections)))

(defn reload-connections []
  (clear-connections)
  (load-connections-from-to place/nummela place/helsinki)
  (load-connections-from-to place/helsinki place/nummela))

(defn find-connection-departuring-next [from-place to-place]
  nil)

(defn find-all-connections-departuring-next []
  nil)
