(ns sv.dev)

(require '(ring.adapter [jetty :as rajetty]))
(require '(ring.util [response :as ruresponse]))
(require '(ring.middleware [session :as rmsession]))
(require '(compojure [core :as cjcore] [route :as cjroute]))
 
(defn go-home []
  (ruresponse/redirect "/"))

(defn login [session params]
  (print params)
  (if
    (= "secret" (params "passwd"))
    (-> (ruresponse/file-response "./web/private/dashboard.html")
      (assoc-in [:session :name] (params :name)))
    (go-home)))

(defn logout [session]
  (alter session assoc :name nil)
  (go-home))

(defn access-controller [session]
  (print session)
  (if (session :name)
    :next
    (go-home)))

(cjcore/defroutes sv-routes
  (cjroute/files "/" {:root "web"})
  (cjcore/POST "/login" {session :session params :params} (login session params))
  (cjcore/ANY "/*" {session :session} (access-controller session)) 
  (cjcore/ANY "/logout" {session :session} (logout session))
  (cjroute/not-found (go-home)))

(def sv
  (-> sv-routes rmsession/wrap-session))

(defonce server (rajetty/run-jetty sv {:port 8080 :join? false}))

;(.stop server)
;(.start server)


;(def app
;   (-> my-routes
;        with-session
;        (wrap-file “public”)
;        wrap-file-info))
;
;(run-jetty (var app) {:ssl? true :port 8080 :ssl-port 8443
;                      :keystore "my.keystore"
;                      :key-password "foobar"})

