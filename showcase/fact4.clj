(fact 4)
((Y fact_) 4)
(fact_ (Y fact_) 4)
((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) 4)
((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) 4)
((zero? 4) 1 (* 4 ((Y fact_) (pred 4))))
(false 1 (* 4 ((Y fact_) (pred 4))))
(* 4 ((Y fact_) (pred 4)))
(* 4 (fact_ (Y fact_) (pred 4)))
(* 4 ((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred 4)))
(* 4 ((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred 4)))
(* 4 ((zero? (pred 4)) 1 (* (pred 4) ((Y fact_) (pred (pred 4))))))
(* 4 ((zero? 3) 1 (* (pred 4) ((Y fact_) (pred (pred 4))))))
(* 4 (false 1 (* (pred 4) ((Y fact_) (pred (pred 4))))))
(* 4 (* (pred 4) ((Y fact_) (pred (pred 4)))))
(* 4 (* 3 ((Y fact_) (pred (pred 4)))))
(* 4 (* 3 (fact_ (Y fact_) (pred (pred 4)))))
(* 4 (* 3 ((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred (pred 4)))))
(* 4 (* 3 ((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 4)))))
(* 4 (* 3 ((zero? (pred (pred 4))) 1 (* (pred (pred 4)) ((Y fact_) (pred (pred (pred 4))))))))
(* 4 (* 3 ((zero? (pred 3)) 1 (* (pred (pred 4)) ((Y fact_) (pred (pred (pred 4))))))))
(* 4 (* 3 ((zero? 2) 1 (* (pred (pred 4)) ((Y fact_) (pred (pred (pred 4))))))))
(* 4 (* 3 (false 1 (* (pred (pred 4)) ((Y fact_) (pred (pred (pred 4))))))))
(* 4 (* 3 (* (pred (pred 4)) ((Y fact_) (pred (pred (pred 4)))))))
(* 4 (* 3 (* (pred 3) ((Y fact_) (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 ((Y fact_) (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 (fact_ (Y fact_) (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 ((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 ((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred 4)))))))
(* 4 (* 3 (* 2 ((zero? (pred (pred (pred 4)))) 1 (* (pred (pred (pred 4))) ((Y fact_) (pred (pred (pred (pred 4))))))))))
(* 4 (* 3 (* 2 ((zero? (pred (pred 3))) 1 (* (pred (pred (pred 4))) ((Y fact_) (pred (pred (pred (pred 4))))))))))
(* 4 (* 3 (* 2 ((zero? (pred 2)) 1 (* (pred (pred (pred 4))) ((Y fact_) (pred (pred (pred (pred 4))))))))))
(* 4 (* 3 (* 2 ((zero? 1) 1 (* (pred (pred (pred 4))) ((Y fact_) (pred (pred (pred (pred 4))))))))))
(* 4 (* 3 (* 2 (false 1 (* (pred (pred (pred 4))) ((Y fact_) (pred (pred (pred (pred 4))))))))))
(* 4 (* 3 (* 2 (* (pred (pred (pred 4))) ((Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* (pred 2) ((Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 ((Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 (fact_ (Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 ((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 ((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred (pred 4)))))))))
(* 4 (* 3 (* 2 (* 1 ((zero? (pred (pred (pred (pred 4))))) 1 (* (pred (pred (pred (pred 4)))) ((Y fact_) (pred (pred (pred (pred (pred 4))))))))))))
(* 4 (* 3 (* 2 (* 1 ((zero? (pred (pred (pred 3)))) 1 (* (pred (pred (pred (pred 4)))) ((Y fact_) (pred (pred (pred (pred (pred 4))))))))))))
(* 4 (* 3 (* 2 (* 1 ((zero? (pred (pred 2))) 1 (* (pred (pred (pred (pred 4)))) ((Y fact_) (pred (pred (pred (pred (pred 4))))))))))))
(* 4 (* 3 (* 2 (* 1 ((zero? (pred 1)) 1 (* (pred (pred (pred (pred 4)))) ((Y fact_) (pred (pred (pred (pred (pred 4))))))))))))
(* 4 (* 3 (* 2 (* 1 ((zero? 0) 1 (* (pred (pred (pred (pred 4)))) ((Y fact_) (pred (pred (pred (pred (pred 4))))))))))))
(* 4 (* 3 (* 2 (* 1 (true 1 (* (pred (pred (pred (pred 4)))) ((Y fact_) (pred (pred (pred (pred (pred 4))))))))))))
(* 4 (* 3 (* 2 (* 1 1))))
(* 4 (* 3 (* 2 1)))
(* 4 (* 3 2))
(* 4 6)
24
