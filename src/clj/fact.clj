;; This method's name MUST end with an underscore,
;; to get evaluated in normal order.
;; Otherwise, it would be evaluated in applicative order,
;; resulting in an infinite expansion.
;; See: https://en.wikipedia.org/wiki/Evaluation_strategy
(defn fact_ [f n]
  ((zero? n)
    1
    (* n (f (pred n)))))

;; Recursively defined factorial function.
;; Takes a single numeral argument.
(def fact (Y fact_))
