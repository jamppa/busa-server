(defproject busa-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jvm-opts ["-Dphantomjs.binary.path=./phantomjs-2.1.1-linux-x86_64/bin/phantomjs"]
  :dependencies [
    [org.clojure/clojure "1.7.0"]
    [clj-webdriver "0.7.2"]
    [com.codeborne/phantomjsdriver "1.2.1" :exclusion [org.seleniumhq.selenium/selenium-java
                                                          org.seleniumhq.selenium/selenium-server
                                                          org.seleniumhq.selenium/selenium-remote-driver]]
    [enlive "1.1.6"]
    [jarohen/chime "0.1.9"]
    [clj-time "0.11.0"]]
  :main ^:skip-aot busa-server.main
  :target-path "target/%s"
  :profiles {
    :uberjar {:aot :all}
    :dev {
      :dependencies [[midje "1.8.3"]]
      :plugins [[lein-midje "3.2"]]}
  })
