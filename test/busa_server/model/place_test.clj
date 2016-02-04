(ns busa-server.model.place-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.place :as place]))

(fact "should create new Place"
  (place/new-place "p1447" "Nummela") => {:name "Nummela" :id "p1447"})

(fact "should not create new Place missing id"
  (place/new-place nil "Nummela") => (throws Exception))

(fact "should not create new Place missing name"
  (place/new-place "p1447" nil) => (throws Exception))

(fact "should find place by name"
  (place/find-by-name "nummela") => place/nummela)

(fact "should not find place with non-existing name"
  (place/find-by-name "tsäggärä") => nil)
