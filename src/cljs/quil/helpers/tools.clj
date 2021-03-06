(ns quil.helpers.tools)

(defn bind-handler [prc [processing-name handler]]
  `(aset ~prc ~(name processing-name)
    (fn []
      (~'with-sketch ~prc
        (~@(if (list? handler) handler (list handler)))))))


(defmacro bind-handlers [prc & opts]
  `(do ~@(map #(bind-handler prc %) (partition 2 opts))))


(defmacro with-shape [mode & body]
  (if (keyword? mode)
    `(do
       (quil.core/begin-shape ~mode)
       ~@body
       (quil.core/end-shape))

    `(do
       (quil.core/begin-shape)
       ~mode
       ~@body
       (quil.core/end-shape))))
