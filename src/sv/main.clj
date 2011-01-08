(ns sv.main
  (:use ring.adapter.jetty)
  (:use sv.rest))
  
(run-jetty sv {:port 8080})

