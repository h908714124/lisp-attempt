;; User-defined functions are always evaluated in normal order,
;; to prevent infinite expansions.
;; However some built-ins may use applicative order.
;; See: https://en.wikipedia.org/wiki/Evaluation_strategy
(defn fact_ [f n]
  ((zero? n)
    1
    (* n (f (pred n)))))

;; Recursively defined factorial function.
;; Takes a single numeral argument.
(def fact (Y fact_))
