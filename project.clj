(defproject busa-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.7.0"]
    [prismatic/schema "1.1.0"]
    [jarohen/chime "0.1.9"]
    [clj-time "0.11.0"]
    [com.apa512/rethinkdb "0.11.0"]
    [ring/ring-core "1.4.0"]
    [ring-cors "0.1.7"]
    [ring-middleware-format "0.7.0"]
    [compojure "1.4.0"]
    [http-kit "2.1.19"]
    [javax.servlet/servlet-api "2.5"]]
  :main ^:skip-aot busa-server.main
  :target-path "target/%s"
  :profiles {
    :uberjar {:aot :all}
    :dev {
      :dependencies [[midje "1.8.3"] [ring/ring-mock "0.3.0"]]
      :plugins [[lein-midje "3.2"]]}
  })
