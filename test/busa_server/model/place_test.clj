(ns busa-server.model.place-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.place :as place]))

(fact "should create new Place"
  (place/new-place "p1447" "Nummela") => {:name "Nummela" :id "p1447"})
