(fact 1)
(Y fact_ 1)
(fact_ (Y fact_) 1)
((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 1)
(* 1 (Y fact_ 0))
(* 1 (fact_ (Y fact_) 0))
(* 1 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 0))
(* 1 1)
1
