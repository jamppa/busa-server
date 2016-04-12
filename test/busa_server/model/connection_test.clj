(ns busa-server.model.connection-test
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as c]
    [busa-server.model.place :as p]
    [busa-server.model.db :as db]
    [busa-server.model.fixtures :as fixtures])
  (:import [busa_server.model.connection Connection ConnectionPlace]))

(def connection
  (Connection. "qwe123" "00:50"
    (ConnectionPlace. "2016-04-10T05:55:00+03:00" "Nummela")
    (ConnectionPlace. "2016-04-10T06:40:00+03:00" "Helsinki")))

(def invalid-connection (dissoc connection :id))
(def connection-with-too-long-id (merge connection {:id (apply str (take 128 (repeat "x")))}))

(with-state-changes [(before :facts (do (db/setup-test-db) (fixtures/load-fixtures)))]

  (fact "should make new connection"
    (c/make-connection
      {:id "qwe123"
       :duration "00:50"
       :from-place (c/make-connection-place {:time "2016-04-10T05:55:00+03:00" :name "Nummela"})
       :to-place (c/make-connection-place {:time "2016-04-10T06:40:00+03:00" :name "Helsinki"})}) => connection)

  (fact "should save new connection"
    (c/save [connection]) => (contains {:inserted 1}))

  (fact "should not save invalid connection"
    (c/save [invalid-connection]) => (throws Exception))

  (fact "should throw exception when save to db fails"
    (c/save [connection-with-too-long-id]) => (throws Exception))
)
