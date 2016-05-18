(ns busa-server.core.utils
  (:require
    [clj-time.format :as f]
    [clj-time.local :as l]
    [clj-time.core :as t]
    [clojure.data.json :as json]
    [clojure.walk :refer [keywordize-keys]]))

(def date-format (f/formatter "yyyy-MM-dd"))
(def datetime-format (f/formatter "yyyy-MM-dd/HH:mm"))

(defn date-to-iso [local-date]
  (f/unparse (f/with-zone date-format (t/time-zone-for-id "Europe/Helsinki")) local-date))

(defn iso-to-date [iso]
  (f/parse iso))

(defn date-to-millis [date]
  (.getMillis date))

(defn date-after-now? [d]
  (t/after? d (l/local-now)))

(defn today-as-iso []
  (-> (l/local-now) date-to-iso))

(defn now-millis []
  (.getMillis (l/local-now)))

(defn response-body [{body :body}]
    (-> (json/read-str body) keywordize-keys))
