(fact 1)
(Y fact_ 1)
(fact_ (Y fact_) 1)
((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 1)
(zero? 1 1 (* 1 (Y fact_ (pred 1))))
(* 1 (Y fact_ (pred 1)))
(* 1 (fact_ (Y fact_) (pred 1)))
(* 1 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred 1)))
(* 1 (zero? (pred 1) 1 (* (pred 1) (Y fact_ (pred (pred 1))))))
(* 1 1)
1
