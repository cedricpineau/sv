(ns sv.conf
  (:require (sv [conf-secret :as conf-secret])))

; Bascule du mode d'exécution "prod" ou non
(defn prod? [] false)

; Chemin vers le fichier de données : la première entrée concerne la prod, la seconde filtre sur mon login, à vous définir la votre si besoin
(def data-path 
  (cond
    (prod?) "sv-data.log"
    (= "sc" (System/getProperty "user.name")) "/tmp/sv-data.log"
    (= "jerome" (System/getProperty "user.name")) "/tmp/sv-data.log"
    :else "c:/windows/temp/sv-data.log"))

; Configuration du service de mail
(def mail-properties conf-secret/mail-properties)
