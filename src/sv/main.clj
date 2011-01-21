(ns sv.main
  (:gen-class))

(require '(sv [rest :as svrest]))
(require '(ring.adapter [jetty :as rajetty]))


(defn -main [& args]
  (rajetty/run-jetty svrest/sv {:port 8080}))

(-main)