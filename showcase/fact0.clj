(fact 0)
(Y fact_ 0)
(fact_ (Y fact_) 0)
((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 0)
(zero? 0 1 (* 0 (Y fact_ (pred 0))))
1
