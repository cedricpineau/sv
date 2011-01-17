(ns sv.velocity) 

(require '(ring.util [response :as ruresponse]))

(import '(org.apache.velocity Template VelocityContext)
        '(org.apache.velocity.app Velocity)
        '(org.apache.velocity.exception)
        '(org.apache.velocity.context Context)
        '(java.io StringWriter))

(. Velocity init)


(defn make-context-2 
  "Create Velocity Context for change keyWord to String"
  []
  (let [context (new VelocityContext)]
    (proxy [Context] []
      (containsKey [key] (contains? context (keyword key)))
	    (get [key] (context (keyword key)))
	    (getKeys [] (keys context))
	    (put [key value] (.put context key value))
	    (remove [key] (.remove context key)))))

(defn make-context
  "Create Velocity Context for change keyWord to String"
  []
  (let [context (new VelocityContext)]
    context))



(defn render-template 
  "Process a Velocity template in the response"
  [template context]
  (let [template (Velocity/getTemplate template)
        output (new StringWriter)]       
      (. template merge context output)
      (ruresponse/response (. output toString))))
