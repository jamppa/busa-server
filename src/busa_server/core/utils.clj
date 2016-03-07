(ns busa-server.core.utils
  (:require
    [clj-time.format :as f]
    [clj-time.local :as l]))

(def date-format (f/formatter "yyyy-MM-dd"))
(def datetime-format (f/formatter "yyyy-MM-dd/HH:mm"))

(defn date-to-iso [local-date]
  (f/unparse date-format local-date))

(defn today-as-iso []
  (-> (l/local-now) date-to-iso))

(defn datetime-str-to-millis [datetime-str]
  (.getMillis (l/to-local-date-time (f/parse datetime-format datetime-str))))

(defn datetime-str [date time]
  (str date "/" time))

(defn now-millis []
  (.getMillis (l/local-now)))
