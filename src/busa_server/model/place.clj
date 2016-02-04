(ns busa-server.model.place
  (:require [schema.core :as s]))

(s/defrecord Place
  [id   :- s/Str
   name :- s/Str])

(defn new-place [id name]
  (s/validate Place (map->Place {:id id :name name})))

(def nummela (new-place "p1447" "nummela"))
(def helsinki (new-place "p1001" "helsinki"))
(def places [nummela helsinki])

(defn- name-is? [expected actual]
  (= expected actual))

(defn find-by-name [name]
  (->> places
    (filter #(name-is? name (:name %1)))
    first))
