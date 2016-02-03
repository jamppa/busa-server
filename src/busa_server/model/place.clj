(ns busa-server.model.place)

(defrecord Place [id name])

(defn new-place [id name]
  (map->Place {:id id :name name}))
