(ns busa-server.page.connections
  (:require
    [busa-server.page.driver :as driver]
    [busa-server.core.utils :as utils]
    [net.cgrand.enlive-html :as html]))

(def ^:dynamic *connections-url-template*
  "https://liput.matkahuolto.fi/connectionlist?lang=fi&arrivalPlaceId=:arrivalPlaceId&departurePlaceId=:departurePlaceId&departureDate=:departureDate")

(def ^:dynamic *departure-times-selector* [:div.timeColumns.departureTimeColumn])
(def ^:dynamic *durations-selector* [:div.durationColumn :div.ng-binding])

(defn url [page-details]
  (-> (str *connections-url-template*)
    (.replace ":arrivalPlaceId" (:arrival-place-id page-details))
    (.replace ":departurePlaceId" (:departure-place-id page-details))
    (.replace ":departureDate" (:departure-date page-details))))

(defn new-details [departure-place arrival-place date]
  {:arrival-place-id (:id arrival-place)
    :departure-place-id (:id departure-place)
    :departure-date date})

(defn page-html [page-details]
  (driver/fetch (url page-details) ".timeColumns"))

(defn departure-times [page-html]
    (map html/text
      (html/select (-> page-html html/html-snippet) *departure-times-selector*)))

(defn durations [page-html]
    (map html/text
      (html/select (-> page-html html/html-snippet) *durations-selector*)))

(defn departure-times-to-millis [date departure-times]
  (map #(-> (utils/datetime-str date %1) (utils/datetime-str-to-millis)) departure-times))

(defn connections [page-details]
  (let [page-html (page-html page-details)
        departure-times (departure-times page-html)
        departure-times-as-millis (departure-times-to-millis (:departure-date page-details) departure-times)
        durations (durations page-html)]

        (map #(merge (select-keys page-details [:departure-place-id :arrival-place-id])
          {:departure-time %1 :duration %2}) departure-times-as-millis durations)))
