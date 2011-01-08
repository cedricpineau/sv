(ns sv.main
  (:use ring.adapter.jetty)
  (:use sv.rest))
  
(run-jetty example {:port 8080})

