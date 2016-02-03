(ns busa-server.model.place
  (:require [schema.core :as s]))

(s/defrecord Place
  [id   :- s/Str
   name :- s/Str])

(defn new-place [id name]
  (s/validate Place (map->Place {:id id :name name})))

(def nummela (new-place "p1447" "Nummela"))
(def helsinki (new-place "p1001" "Helsinki"))
(def places [nummela helsinki])
