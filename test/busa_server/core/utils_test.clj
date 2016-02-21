(ns busa-server.core.utils-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.core.utils :as utils]
    [clj-time.local :as l]
    [clj-time.format :as f]))

(fact "should return today as iso string"
  (utils/today-as-iso) => (f/unparse (f/formatter "yyyy-MM-dd") (l/local-now)))

(fact "should map datetime string to millis"
  (utils/datetime-str-to-millis "2016-02-21/16:30") => 1456065000000)

(fact "should format datetime string from date and time componenets"
  (utils/datetime-str "2016-02-21" "17:25") => "2016-02-21/17:25")
