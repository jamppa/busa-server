(ns busa-server.page.driver
  (:require
    [clj-webdriver.taxi :as wd-taxi]
    [clj-webdriver.driver :as wd])
  (:import
    [org.openqa.selenium.phantomjs PhantomJSDriver]
    [org.openqa.selenium.remote DesiredCapabilities]))

(defn init
  "init web driver with phantomjs"
  []
  (wd-taxi/set-driver! (wd/init-driver {:webdriver (PhantomJSDriver. (DesiredCapabilities. ))})))

(defn fetch [url]
  (wd-taxi/to url)
  (wd-taxi/html "body"))
