;; User-defined functions are always evaluated in normal order,
;; to prevent infinite expansions.
;; However some built-ins may use applicative order.
;; See: https://en.wikipedia.org/wiki/Evaluation_strategy

;; Defining the factorial function.
(defn fact_ [f n]
  (zero? n
    1
    (* n (f (pred n)))))

(def fact (Y fact_))

;; Defining subtraction.
;; If the subtrahend is larger than the minuend,
;; the result will be 0.
(defn -_ [f m s]
  (zero? s
    m
    (f (pred m) (pred s))))

(def - (Y -_))
