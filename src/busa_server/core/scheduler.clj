(ns busa-server.core.scheduler
  (:require
    [clj-time.core :as t]
    [clj-time.local :as l]
    [clj-time.periodic :as p]
    [chime :refer [chime-at]])
  (:import [org.joda.time DateTimeZone]))

(defn- after-midnight-seq []
  (rest (->> (p/periodic-seq (.. (l/local-now) (withTime 0 1 0 0)) (t/days 1)))))

(defn run-everyday-after-midnight [func]
  (chime-at (after-midnight-seq)
    (fn [time]
      (func))))
