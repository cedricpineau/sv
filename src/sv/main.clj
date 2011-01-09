(ns sv.main
  (:gen-class)
  (:use ring.adapter.jetty)
  (:use sv.rest))

(defn -main [& args]
  (run-jetty sv {:port 8080}))
