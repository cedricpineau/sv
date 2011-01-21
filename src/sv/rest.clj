(ns sv.rest)

(require '(sv [data :as data] [template :as web]))
(require '(ring.adapter [jetty :as rajetty]))
(require '(ring.util [response :as ruresponse]))
(require '(ring.middleware [session :as rmsession]))
(require '(compojure [core :as cjcore] [route :as cjroute]))


(defn go-page 
  [template & args]
  {:status  200
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body    (apply template args)}) 

(defn go-home []
  (ruresponse/redirect "/"))

(defn login [session params]
  (let [user (data/find-user-by-email (params "email"))]
  (if (not (nil? user))
    (go-page web/dashboard user)    
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
