(fact 3)
(Y fact_ 3)
(fact_ (Y fact_) 3)
((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 3)
(zero? 3 1 (* 3 (Y fact_ (pred 3))))
(* 3 (Y fact_ (pred 3)))
(* 3 (fact_ (Y fact_) (pred 3)))
(* 3 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred 3)))
(* 3 (zero? (pred 3) 1 (* (pred 3) (Y fact_ (pred (pred 3))))))
(* 3 (* (pred 3) (Y fact_ (pred (pred 3)))))
(* 3 (* 2 (Y fact_ (pred (pred 3)))))
(* 3 (* 2 (fact_ (Y fact_) (pred (pred 3)))))
(* 3 (* 2 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred (pred 3)))))
(* 3 (* 2 (zero? (pred (pred 3)) 1 (* (pred (pred 3)) (Y fact_ (pred (pred (pred 3))))))))
(* 3 (* 2 (* (pred (pred 3)) (Y fact_ (pred (pred (pred 3)))))))
(* 3 (* 2 (* 1 (Y fact_ (pred (pred (pred 3)))))))
(* 3 (* 2 (* 1 (fact_ (Y fact_) (pred (pred (pred 3)))))))
(* 3 (* 2 (* 1 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred (pred (pred 3)))))))
(* 3 (* 2 (* 1 (zero? (pred (pred (pred 3))) 1 (* (pred (pred (pred 3))) (Y fact_ (pred (pred (pred (pred 3))))))))))
(* 3 (* 2 (* 1 1)))
(* 3 (* 2 1))
(* 3 2)
6
