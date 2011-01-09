(defproject sv "0.1.0"
  :description "The functional SV"
  :url "http://sv.com"

  :main sv.main
  :resources-path "conf"
  
  :target-dir "dist"
  :jar-name "sv.jar"
  :uberjar-name "sv-uber.jar"
  
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [fleetdb "0.3.1"]
                 [compojure "0.5.3"]
                 [ring/ring-jetty-adapter "0.3.1"]])
