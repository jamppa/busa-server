(ns busa-server.client.util
  (:require
    [clojure.data.json :as json]
    [clojure.walk :refer [keywordize-keys]]))

(defn body [{body :body}]
  (-> (json/read-str body) keywordize-keys))
