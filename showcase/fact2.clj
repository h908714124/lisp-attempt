(fact 2)
((Y fact_) 2)
(fact_ (Y fact_) 2)
((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) 2)
((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) 2)
((zero? 2) 1 (* 2 ((Y fact_) (pred 2))))
(false 1 (* 2 ((Y fact_) (pred 2))))
(* 2 ((Y fact_) (pred 2)))
((fn [m n] (fn [f x] (m (n f) x))) 2 ((Y fact_) (pred 2)))
((fn [n] (fn [f x] (2 (n f) x))) ((Y fact_) (pred 2)))
(fn [f x] (2 (((Y fact_) (pred 2)) f) x))
(fn [f x] ((((Y fact_) (pred 2)) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (((fact_ (Y fact_) (pred 2)) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred 2)) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred 2)) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((zero? (pred 2)) 1 (* (pred 2) ((Y fact_) (pred (pred 2))))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((zero? 1) 1 (* (pred 2) ((Y fact_) (pred (pred 2))))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (((false 1 (* (pred 2) ((Y fact_) (pred (pred 2))))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (((* (pred 2) ((Y fact_) (pred (pred 2)))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((fn [m n] (fn [f x] (m (n f) x))) (pred 2) ((Y fact_) (pred (pred 2)))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((fn [n] (fn [f x] ((pred 2) (n f) x))) ((Y fact_) (pred (pred 2)))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (((fn [f x] ((pred 2) (((Y fact_) (pred (pred 2))) f) x)) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((fn [x] ((pred 2) (((Y fact_) (pred (pred 2))) f) x)) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((pred 2) (((Y fact_) (pred (pred 2))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (1 (((Y fact_) (pred (pred 2))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((Y fact_) (pred (pred 2))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (((fact_ (Y fact_) (pred (pred 2))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred (pred 2))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 2))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((zero? (pred (pred 2))) 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((zero? (pred 1)) 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((((zero? 0) 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (((true 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] ((1 f) ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (f ((((Y fact_) (pred 2)) f) x)))
(fn [f x] (f (((fact_ (Y fact_) (pred 2)) f) x)))
(fn [f x] (f ((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred 2)) f) x)))
(fn [f x] (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred 2)) f) x)))
(fn [f x] (f ((((zero? (pred 2)) 1 (* (pred 2) ((Y fact_) (pred (pred 2))))) f) x)))
(fn [f x] (f ((((zero? 1) 1 (* (pred 2) ((Y fact_) (pred (pred 2))))) f) x)))
(fn [f x] (f (((false 1 (* (pred 2) ((Y fact_) (pred (pred 2))))) f) x)))
(fn [f x] (f (((* (pred 2) ((Y fact_) (pred (pred 2)))) f) x)))
(fn [f x] (f ((((fn [m n] (fn [f x] (m (n f) x))) (pred 2) ((Y fact_) (pred (pred 2)))) f) x)))
(fn [f x] (f ((((fn [n] (fn [f x] ((pred 2) (n f) x))) ((Y fact_) (pred (pred 2)))) f) x)))
(fn [f x] (f (((fn [f x] ((pred 2) (((Y fact_) (pred (pred 2))) f) x)) f) x)))
(fn [f x] (f ((fn [x] ((pred 2) (((Y fact_) (pred (pred 2))) f) x)) x)))
(fn [f x] (f ((pred 2) (((Y fact_) (pred (pred 2))) f) x)))
(fn [f x] (f (1 (((Y fact_) (pred (pred 2))) f) x)))
(fn [f x] (f ((((Y fact_) (pred (pred 2))) f) x)))
(fn [f x] (f (((fact_ (Y fact_) (pred (pred 2))) f) x)))
(fn [f x] (f ((((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (Y fact_) (pred (pred 2))) f) x)))
(fn [f x] (f ((((fn [n] ((zero? n) 1 (* n ((Y fact_) (pred n))))) (pred (pred 2))) f) x)))
(fn [f x] (f ((((zero? (pred (pred 2))) 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) x)))
(fn [f x] (f ((((zero? (pred 1)) 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) x)))
(fn [f x] (f ((((zero? 0) 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) x)))
(fn [f x] (f (((true 1 (* (pred (pred 2)) ((Y fact_) (pred (pred (pred 2)))))) f) x)))
(fn [f x] (f ((1 f) x)))
(fn [f x] (f (f x)))
