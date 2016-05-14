(ns busa-server.model.connection-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as c]
    [busa-server.model.place :as p]
    [busa-server.model.db :as db]
    [busa-server.model.fixtures :as fixtures])
  (:import [busa_server.model.connection Connection ConnectionPlace]))

(def connection
  (Connection. "qweasd" "00:50"
    (ConnectionPlace. "2016-04-10T05:55:00+03:00" "Nummela")
    (ConnectionPlace. "2016-04-10T06:40:00+03:00" "Helsinki")))

(def invalid-connection (dissoc connection :id))
(def connection-with-too-long-id (merge connection {:id (apply str (take 128 (repeat "x")))}))

(with-state-changes [(before :facts (do (db/setup-test-db) (fixtures/load-fixtures)))]

  (fact "should make new connection from map"
    (c/make-connection
      {:duration "00:50"
       :from {:time "2016-04-10T05:55:00+03:00" :name "Nummela"}
       :to {:time "2016-04-10T06:40:00+03:00" :name "Helsinki"}})

       => (contains {
         :id anything
         :duration (:duration connection)
         :to (:to connection)
         :from (:from connection)}))

  (fact "should not make new connection violating schema"
    (c/make-connection {:id "123qwe"}) => (throws Exception))

  (fact "should save new connection"
    (c/save [connection]) => (contains {:inserted 1}))

  (fact "should not save invalid connection missing field"
    (c/save [invalid-connection]) => (throws Exception))

  (fact "should not save invalid connection with too long id"
    (c/save [connection-with-too-long-id]) => (throws Exception))

  (fact "should delete all connections"
    (c/delete-all) => (contains {:deleted (count fixtures/connections)}))

  (fact "should find connections by from-to places"
    (c/find-by-from-to p/nummela p/helsinki) => [fixtures/connection-nla-hki])

)
