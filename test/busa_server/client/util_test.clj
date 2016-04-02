(ns busa-server.client.util-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.client.util :as util]
    [clojure.data.json :as json]))

(def response {:body (json/write-str {:key "value"})})

(fact "should return body from response as map"
  (util/body response) => {:key "value"})
