(ns sv.rest)

(require '(sv [data :as data] [velocity :as velocity]))
(require '(ring.adapter [jetty :as rajetty]))
(require '(ring.util [response :as ruresponse]))
(require '(ring.middleware [session :as rmsession]))
(require '(compojure [core :as cjcore] [route :as cjroute]))


(defn go-home []
  (ruresponse/redirect "/"))

(defn login [session params]
  (let [user (data/find-user-by-email (params "email"))]
  (if (not (nil? user))
    (velocity/render-template 
      "./web/private/dashboard.vlt" 
      (doto (velocity/make-context) (.put "user" (data/to-fleet user))))
    (go-home))))

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
