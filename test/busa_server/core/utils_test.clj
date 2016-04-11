(ns busa-server.core.utils-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.core.utils :as utils]
    [clj-time.local :as l]
    [clj-time.format :as f]))

(fact "should return today as iso string"
  (utils/today-as-iso) => (f/unparse (f/formatter "yyyy-MM-dd") (l/local-now)))
