(ns sv.rest
  (:use compojure.core)
  (:require [compojure.route :as route]))

(defroutes sv
  (route/files "/" {:root "web"})
  (route/not-found "Page not found"))
