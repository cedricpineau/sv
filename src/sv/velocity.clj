(ns sv.velocity) 

(require '(ring.util [response :as ruresponse]))

(import '(org.apache.velocity Template VelocityContext)
        '(org.apache.velocity.app Velocity)
        '(org.apache.velocity.exception)
        '(org.apache.velocity.context Context)
        '(java.io StringWriter))

(. Velocity init)


(defn make-context
  "Create Velocity Context"
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
