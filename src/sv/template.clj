(ns sv.template
  (:require [net.cgrand.enlive-html :as html]))


(html/deftemplate dashboard "../web/private/dashboard.html"
  [user]
   [:p#welcome] (html/content (str "You are in M " (:fname user) " " (:lname user))))

  
