(defproject sv "0.1.0"
  :description "The functional SV"
  :url "http://sv.com"

  :main sv.main
  :resources-path "conf"
  
  :target-dir "dist"
  :jar-name "sv.jar"
  :uberjar-name "sv-uber.jar"
  
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [fleetdb "0.3.1"]
                 [compojure "0.5.3"]
                 [ring/ring-jetty-adapter "0.3.1"]])


; Création du fichier des configurations confidentielles s'il n'existe pas
(import (java.io File))
(def conf-secret-file (File. "conf/sv/conf_secret.clj"))
(when-not (.exists conf-secret-file)
  (println "Création du fichier des configurations confidentielles" (str conf-secret-file))
  (spit 
    conf-secret-file
    "(ns sv.conf-secret ^{:doc \"Configurations confidentielles de sv, ignorées par Git.\"})

; Configuration du service de mail
(def mail-properties
  {:mail-smtp-host \"\",
   :mail.smtp.port 25,
   :mail.smtp.login \"\",
   :mail.smtp.password \"\"})
"))
