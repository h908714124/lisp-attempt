(fact 3)
((Y fact_) 3)
(((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) 3)
(((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) 3)
((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) 3)
(((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) 3)
(((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) 3)
((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) 3)
((zero? 3) 1 (* 3 ((Y fact_) (pred 3))))
(false 1 (* 3 ((Y fact_) (pred 3))))
(* 3 ((Y fact_) (pred 3)))
((fn [m n] (fn [f x] (m (n f) x))) 3 ((Y fact_) (pred 3)))
((fn [n] (fn [f x] (3 (n f) x))) ((Y fact_) (pred 3)))
(fn [f x] (3 (((Y fact_) (pred 3)) f) x))
(fn [f x] ((fn [f x] (f (f (f x)))) (((Y fact_) (pred 3)) f) x))
(fn [f x] ((fn [x] ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))) x))
(fn [f x] ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred 3)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((((zero? (pred 3)) 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((((zero? 2) 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (((false 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (((* (pred 3) ((Y fact_) (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((((fn [m n] (fn [f x] (m (n f) x))) (pred 3) ((Y fact_) (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((((fn [n] (fn [f x] ((pred 3) (n f) x))) ((Y fact_) (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (((fn [f x] ((pred 3) (((Y fact_) (pred (pred 3))) f) x)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((fn [x] ((pred 3) (((Y fact_) (pred (pred 3))) f) x)) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((pred 3) (((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] (2 (((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((fn [f x] (f (f x))) (((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((fn [x] ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x))) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x))))
(fn [f x] ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((zero? (pred (pred 3))) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((zero? (pred 2)) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((zero? 1) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((false 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((fn [m n] (fn [f x] (m (n f) x))) (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((fn [n] (fn [f x] ((pred (pred 3)) (n f) x))) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((fn [f x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((fn [x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((pred 2) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (1 (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((fn [f x] (f x)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((fn [x] ((((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((zero? (pred (pred (pred 3)))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((zero? (pred (pred 2))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((zero? (pred 1)) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((((zero? 0) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((true 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((1 f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (((fn [f x] (f x)) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] ((fn [x] (f x)) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((zero? (pred (pred 3))) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((zero? (pred 2)) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((zero? 1) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((false 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((fn [n] (fn [f x] ((pred (pred 3)) (n f) x))) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((fn [f x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((fn [x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((pred 2) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (1 (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((fn [f x] (f x)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((fn [x] ((((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((zero? (pred (pred (pred 3)))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((zero? (pred (pred 2))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((zero? (pred 1)) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((((zero? 0) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((true 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((1 f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (((fn [f x] (f x)) f) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f ((fn [x] (f x)) ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((Y fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred 3)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((zero? (pred 3)) 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((zero? 2) 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (((false 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (((* (pred 3) ((Y fact_) (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred 3) ((Y fact_) (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((fn [n] (fn [f x] ((pred 3) (n f) x))) ((Y fact_) (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (((fn [f x] ((pred 3) (((Y fact_) (pred (pred 3))) f) x)) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((fn [x] ((pred 3) (((Y fact_) (pred (pred 3))) f) x)) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((pred 3) (((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f (2 (((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((fn [f x] (f (f x))) (((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((fn [x] ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x))) ((((Y fact_) (pred 3)) f) x)))))
(fn [f x] (f (f ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((zero? (pred (pred 3))) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((zero? (pred 2)) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((zero? 1) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((false 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((fn [n] (fn [f x] ((pred (pred 3)) (n f) x))) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((fn [f x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((fn [x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((pred 2) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (1 (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((fn [f x] (f x)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((fn [x] ((((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((zero? (pred (pred (pred 3)))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((zero? (pred (pred 2))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((zero? (pred 1)) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((((zero? 0) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((true 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((1 f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (((fn [f x] (f x)) f) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f ((fn [x] (f x)) ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 3))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((zero? (pred (pred 3))) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((zero? (pred 2)) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((zero? 1) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((false 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((fn [n] (fn [f x] ((pred (pred 3)) (n f) x))) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((fn [f x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((fn [x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((pred 2) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (1 (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((fn [f x] (f x)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((fn [x] ((((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((zero? (pred (pred (pred 3)))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((zero? (pred (pred 2))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((zero? (pred 1)) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((((zero? 0) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((true 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((1 f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (((fn [f x] (f x)) f) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f ((fn [x] (f x)) ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f ((((Y fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred 3)) f) x))))))
(fn [f x] (f (f (f (f ((((zero? (pred 3)) 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) x))))))
(fn [f x] (f (f (f (f ((((zero? 2) 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) x))))))
(fn [f x] (f (f (f (f (((false 1 (* (pred 3) ((Y fact_) (pred (pred 3))))) f) x))))))
(fn [f x] (f (f (f (f (((* (pred 3) ((Y fact_) (pred (pred 3)))) f) x))))))
(fn [f x] (f (f (f (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred 3) ((Y fact_) (pred (pred 3)))) f) x))))))
(fn [f x] (f (f (f (f ((((fn [n] (fn [f x] ((pred 3) (n f) x))) ((Y fact_) (pred (pred 3)))) f) x))))))
(fn [f x] (f (f (f (f (((fn [f x] ((pred 3) (((Y fact_) (pred (pred 3))) f) x)) f) x))))))
(fn [f x] (f (f (f (f ((fn [x] ((pred 3) (((Y fact_) (pred (pred 3))) f) x)) x))))))
(fn [f x] (f (f (f (f ((pred 3) (((Y fact_) (pred (pred 3))) f) x))))))
(fn [f x] (f (f (f (f (2 (((Y fact_) (pred (pred 3))) f) x))))))
(fn [f x] (f (f (f (f ((fn [f x] (f (f x))) (((Y fact_) (pred (pred 3))) f) x))))))
(fn [f x] (f (f (f (f ((fn [x] ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x))) x))))))
(fn [f x] (f (f (f (f ((((Y fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 3))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((zero? (pred (pred 3))) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((zero? (pred 2)) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((zero? 1) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((false 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((fn [n] (fn [f x] ((pred (pred 3)) (n f) x))) ((Y fact_) (pred (pred (pred 3))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((fn [f x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((fn [x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((pred 2) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (1 (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((fn [f x] (f x)) (((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((fn [x] ((((Y fact_) (pred (pred (pred 3)))) f) x)) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((Y fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred 3)))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((zero? (pred (pred (pred 3)))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((zero? (pred (pred 2))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((zero? (pred 1)) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((((zero? 0) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((true 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((1 f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (((fn [f x] (f x)) f) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f ((fn [x] (f x)) ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((Y fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 3))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((zero? (pred (pred 3))) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((zero? (pred 2)) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((zero? 1) 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) x)))))))
(fn [f x] (f (f (f (f (f (((false 1 (* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3)))))) f) x)))))))
(fn [f x] (f (f (f (f (f (((* (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred (pred 3)) ((Y fact_) (pred (pred (pred 3))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((fn [n] (fn [f x] ((pred (pred 3)) (n f) x))) ((Y fact_) (pred (pred (pred 3))))) f) x)))))))
(fn [f x] (f (f (f (f (f (((fn [f x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) f) x)))))))
(fn [f x] (f (f (f (f (f ((fn [x] ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)) x)))))))
(fn [f x] (f (f (f (f (f ((pred (pred 3)) (((Y fact_) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f ((pred 2) (((Y fact_) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f (1 (((Y fact_) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f ((fn [f x] (f x)) (((Y fact_) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f ((fn [x] ((((Y fact_) (pred (pred (pred 3)))) f) x)) x)))))))
(fn [f x] (f (f (f (f (f ((((Y fact_) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [f] ((fn [x] (f (x x))) (fn [x] (f (x x))))) fact_) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f (((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_)) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred (pred 3)))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((zero? (pred (pred (pred 3)))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((zero? (pred (pred 2))) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((zero? (pred 1)) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((((zero? 0) 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) x)))))))
(fn [f x] (f (f (f (f (f (((true 1 (* (pred (pred (pred 3))) ((Y fact_) (pred (pred (pred (pred 3))))))) f) x)))))))
(fn [f x] (f (f (f (f (f ((1 f) x)))))))
(fn [f x] (f (f (f (f (f (((fn [f x] (f x)) f) x)))))))
(fn [f x] (f (f (f (f (f ((fn [x] (f x)) x)))))))
(fn [f x] (f (f (f (f (f (f x)))))))
