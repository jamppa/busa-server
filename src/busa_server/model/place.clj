(ns busa-server.model.place
  (:require [schema.core :as s]))

(s/defrecord Place
  [id   :- s/Str
   name :- s/Str])

(def nummela (Place. "p1447" "Nummela"))
(def helsinki (Place. "p1001" "Helsinki"))
(def veikkola (Place. "p205" "Veikkola"))
(def places [nummela helsinki veikkola])

(defn- names-match? [expected actual]
  (= (.toLowerCase expected)  (.toLowerCase actual)))

(defn find-by-name [name]
  (->> places
    (filter #(names-match? name (:name %1)))
    first))
