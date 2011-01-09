(ns sv.rest
  (:require (sv [data :as data]))
  (:use compojure.core)
  (:use ring.util.response)
  (:require [compojure.route :as route]))

(defroutes sv
  (route/files "/" {:root "web"})
  (POST "/login" {params :params} 
    ; (str "POST email=" (params "email") " params=" params))
    (if (data/find-user-by-email (params "email"))
    (file-response "./web/private/dashboard.html")
    (file-response "./web/index.html")))
  (route/not-found "Page not found :-/"))
