(fact 0)
((Y fact_) 0)
(((fn [f] ((fn [x] (x x)) (fn [x] (f (x x))))) fact_) 0)
(((fn [x] (x x)) (fn [x] (fact_ (x x)))) 0)
(((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) 0)
((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) 0)
(((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) 0)
((fn [n] ((zero? n) 1 (* n (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred n))))) 0)
((zero? 0) 1 (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
(((fn [n] (n (K false) true)) 0) 1 (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
((0 (K false) true) 1 (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
(((fn [f x] x) (K false) true) 1 (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
(((fn [x] x) true) 1 (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
(true 1 (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
((fn [a b] a) 1 (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
((fn [b] 1) (* 0 (((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 0))))
1
