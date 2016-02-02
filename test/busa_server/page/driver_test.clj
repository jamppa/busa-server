(ns busa-server.page.driver-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.page.driver :as driver]
    [clj-webdriver.taxi :as wd]))

(def expected-body "<foo></foo>")

(fact "should fetch html body from url"
  (driver/fetch "http://foobar.com") => expected-body
  (provided
    (wd/to "http://foobar.com") => :anything
    (wd/html "body") => expected-body))
