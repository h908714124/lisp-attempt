(fact 2)
(Y fact_ 2)
(fact_ (Y fact_) 2)
((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 2)
(* 2 (Y fact_ 1))
(* 2 (fact_ (Y fact_) 1))
(* 2 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 1))
(* 2 (* 1 (Y fact_ 0)))
(* 2 (* 1 (fact_ (Y fact_) 0)))
(* 2 (* 1 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 0)))
(* 2 (* 1 1))
2
