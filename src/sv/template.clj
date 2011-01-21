(ns sv.template
  (:require [net.cgrand.enlive-html :as html]))

(defn web-resource 
  [page]
  (java.io.File. (str "./web/" page)))

(html/deftemplate dashboard (web-resource  "private/dashboard.html")
  [user]
   [:p#welcome] (html/content (str "You are in M " (:fname user) " " (:lname user))))
