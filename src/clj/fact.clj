;; User-defined functions are always evaluated in normal order,
;; to prevent infinite expansions.
;;
;; List of built-ins using applicative order:
;; + (both arguments)
;; - (both arguments)
;; * (both arguments)
;; pred
;; (zero? n a b) (first argument)

;; == Factorial ==
(defn fact_ [f n]
  (zero? n
    1
    (* n (f (pred n)))))

(def fact (Y fact_))

;; == Ackermann ==
(defn ack_ [f m n]
  (zero? m (+ 1 n)
    (zero? n (f (pred m) 1)
      (f (pred m) (f m (pred n))))))

(def ack (Y ack_))
