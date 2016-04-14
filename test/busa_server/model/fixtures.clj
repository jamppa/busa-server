(ns busa-server.model.fixtures
  (:require
    [midje.sweet :refer :all]
    [busa-server.model.connection :as connection]
    [busa-server.model.db :as db]
    [clj-time.format :as f]
    [clj-time.local :as l]
    [clj-time.core :as t])
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

(defn connection-departuring-and-arriving-at [connection d-at a-at]
    (-> connection
      (assoc-in [:from-place :time] (.toString d-at))
      (assoc-in [:to-place :time] (.toString a-at))))

(def nla-hki-departuring-in-one-hour-from-now
  (connection-departuring-and-arriving-at
    connection-nla-hki (t/plus (l/local-now) (t/hours 1)) (t/plus (l/local-now) (t/hours 2))))

(def nla-hki-departuring-in-two-hours-from-now
  (connection-departuring-and-arriving-at
    connection-nla-hki (t/plus (l/local-now) (t/hours 2)) (t/plus (l/local-now) (t/hours 3))))

(def nla-hki-departured-one-hour-ago
  (connection-departuring-and-arriving-at
    connection-nla-hki (t/minus (l/local-now) (t/hours 1)) (t/minus (l/local-now) (t/hours 2))))
