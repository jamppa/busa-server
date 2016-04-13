(ns busa-server.model.fixtures
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as connection]
    [busa-server.model.db :as db])
  (:import [busa_server.model.connection Connection ConnectionPlace]))

(def connection-nla-hki
  (Connection. "qwe-123" "00:50"
    (ConnectionPlace. "2016-04-10T05:55:00+03:00" "Nummela")
    (ConnectionPlace. "2016-04-10T06:40:00+03:00" "Helsinki")))

(def connection-hki-nla
  (Connection. "qwe-456" "00:50"
    (ConnectionPlace. "2016-04-10T05:55:00+03:00" "Helsinki")
    (ConnectionPlace. "2016-04-10T06:40:00+03:00" "Nummela")))

(def connections
  [connection-nla-hki
   connection-hki-nla])

(defn load-fixtures []
  (db/save connections connection/table))
