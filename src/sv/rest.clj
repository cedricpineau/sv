(ns sv.rest
  (:use compojure.core)
  (:require [compojure.route :as route]))

(defroutes example
  (GET "/" [] "<h1>SV !</h1>")
  (route/not-found "Page not found"))
