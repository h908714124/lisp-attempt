(fact 4)
(Y fact_ 4)
(fact_ (Y fact_) 4)
((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) 4)
(zero? 4 1 (* 4 (Y fact_ (pred 4))))
(* 4 (Y fact_ (pred 4)))
(* 4 (fact_ (Y fact_) (pred 4)))
(* 4 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred 4)))
(* 4 (zero? (pred 4) 1 (* (pred 4) (Y fact_ (pred (pred 4))))))
(* 4 (* (pred 4) (Y fact_ (pred (pred 4)))))
(* 4 (* 3 (Y fact_ (pred (pred 4)))))
(* 4 (* 3 (fact_ (Y fact_) (pred (pred 4)))))
(* 4 (* 3 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred (pred 4)))))
(* 4 (* 3 (zero? (pred (pred 4)) 1 (* (pred (pred 4)) (Y fact_ (pred (pred (pred 4))))))))
(* 4 (* 3 (* (pred (pred 4)) (Y fact_ (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 (Y fact_ (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 (fact_ (Y fact_) (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 (zero? (pred (pred (pred 4))) 1 (* (pred (pred (pred 4))) (Y fact_ (pred (pred (pred (pred 4))))))))))
(* 4 (* 3 (* 2 (* (pred (pred (pred 4))) (Y fact_ (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 (Y fact_ (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 (fact_ (Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 ((fn [f n] (zero? n 1 (* n (f (pred n))))) (Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 (zero? (pred (pred (pred (pred 4)))) 1 (* (pred (pred (pred (pred 4)))) (Y fact_ (pred (pred (pred (pred (pred 4))))))))))))
(* 4 (* 3 (* 2 (* 1 1))))
(* 4 (* 3 (* 2 1)))
(* 4 (* 3 2))
(* 4 6)
24
