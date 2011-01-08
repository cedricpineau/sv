(ns sv.sample-data
  (:require (sv [core :as core]))
  (:import [sv.core User]) 
  (:require (fleetdb [embedded :as fleet])))

(def data
  (User. 1 "cpi@sv.com" "" "Cédric" "Pineau" :male))
