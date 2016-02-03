(ns busa-server.page.connections
  (:require
    [busa-server.page.driver :as driver]
    [net.cgrand.enlive-html :as html]))

(def ^:dynamic *connections-url-template*
  "https://liput.matkahuolto.fi/connectionlist?lang=fi&arrivalPlaceId=:arrivalPlaceId&departurePlaceId=:departurePlaceId&departureDate=:departureDate")

(def ^:dynamic *departure-times-selector* [:div.timeColumns.departureTimeColumn])
(def ^:dynamic *durations-selector* [:div.durationColumn :div.ng-binding])


(defn url [page-details]
  (-> *connections-url-template*
    (.replace ":arrivalPlaceId" (:arrival-place-id page-details))
    (.replace ":departurePlaceId" (:departure-place-id page-details))
    (.replace ":departureDate" (:departure-date page-details))))

(defn new-details [a-id d-id d-date]
  {
    :arrival-place-id a-id
    :departure-place-id d-id
    :departure-date d-date
    })

(defn departure-times [page-details]
  (let [page (html/html-snippet (driver/fetch (url page-details)))]
    (map html/text (html/select page *departure-times-selector*))))

(defn durations [page-details]
  (let [page (html/html-snippet (driver/fetch (url page-details)))]
    (map html/text (html/select page *durations-selector*))))
