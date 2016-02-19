(ns busa-server.core.utils
  (:require
    [clj-time.format :as f]
    [clj-time.local :as l]))

(def date-format (f/formatter "yyyy-MM-dd"))

(defn date-to-iso [local-date]
  (f/unparse date-format local-date))

(defn today-as-iso []
  (-> (l/local-now) date-to-iso))
