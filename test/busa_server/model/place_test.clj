(ns busa-server.model.place-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.place :as place]))

(fact "should find place by name"
  (place/find-by-name "Nummela") => place/nummela)

(fact "should not find place with non-existing name"
  (place/find-by-name "tsäggärä") => nil)
