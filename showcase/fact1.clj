(fact 1)
((Y fact_) 1)
(((fn [f] ((fn [x] (x x)) (fn [x] (f (x x))))) fact_) 1)
(((fn [x] (x x)) (fn [x] (fact_ (x x)))) 1)
(((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x)))) 1)
((fact_ ((fn [x] (fact_ (x x))) (fn [x] (fact_ (x x))))) 1)
(((fn [f n] ((zero? n) 1 (* n (f (pred n))))) ((fn [x] ((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (x x))) (fn [x] ((fn [f n] ((zero? n) 1 (* n (f (pred n))))) (x x))))) 1)
((fn [n] ((zero? n) 1 (* n (((fn [x] ((fn [f n1] ((zero? n1) 1 (* n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) 1 (* n1 (f (pred n1))))) (x x)))) (pred n))))) 1)
((zero? 1) 1 (* 1 (((fn [x] ((fn [f n1] ((zero? n1) 1 (* n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) 1 (* n1 (f (pred n1))))) (x x)))) (pred 1))))
(((fn [n] (n (K false) true)) 1) 1 ((fn [m n] (fn [f1 x1] (m (n f1) x1))) 1 (((fn [x] ((fn [f n1] ((zero? n1) 1 ((fn [m n] (fn [f1 x1] (m (n f1) x1))) n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) 1 ((fn [m n] (fn [f1 x1] (m (n f1) x1))) n1 (f (pred n1))))) (x x)))) (pred 1))))
((1 (K false) true) 1 ((fn [n] (fn [f1 x1] (1 (n f1) x1))) (((fn [x] ((fn [f n1] ((zero? n1) 1 ((fn [m n] (fn [f1 x1] (m (n f1) x1))) n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) 1 ((fn [m n] (fn [f1 x1] (m (n f1) x1))) n1 (f (pred n1))))) (x x)))) (pred 1))))
(((fn [f x] (f x)) (K false) true) 1 (fn [f1 x1] (1 ((((fn [x] ((fn [f n1] ((zero? n1) 1 ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) 1 ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x)))) (pred 1)) f1) x1)))
(((fn [x] ((K false) x)) true) 1 (fn [f1 x1] ((fn [f3 x3] (f3 x3)) ((((fn [x] ((fn [f n1] ((zero? n1) (fn [f3 x3] (f3 x3)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) (fn [f3 x3] (f3 x3)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x)))) (pred (fn [f3 x3] (f3 x3)))) f1) x1)))
(((K false) true) 1 (fn [f1 x1] ((fn [x3] (((((fn [x] ((fn [f n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x)))) (pred (fn [f3 x4] (f3 x4)))) f1) x3)) x1)))
((((fn [x] (fn [u] x)) false) true) 1 (fn [f1 x1] (((((fn [x] ((fn [f n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x))) (fn [x] ((fn [f n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) (x x)))) (pred (fn [f3 x4] (f3 x4)))) f1) x1)))
(((fn [u] false) true) 1 (fn [f1 x1] (((((fn [f n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (f (pred n1))))) ((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))))) ((fn [n] (fn [f x] (extract (n (inc f) (K x))))) (fn [f3 x4] (f3 x4)))) f1) x1)))
(false 1 (fn [f1 x1] ((((fn [n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred n1))))) (fn [f x] (extract ((fn [f3 x4] (f3 x4)) (inc f) (K x))))) f1) x1)))
((fn [a b] b) 1 (fn [f1 x1] ((((fn [n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred n1))))) (fn [f x] (extract ((fn [f3 x4] (f3 x4)) (inc f) (K x))))) f1) x1)))
((fn [b] b) (fn [f1 x1] ((((fn [n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred n1))))) (fn [f x] (extract ((fn [f3 x4] (f3 x4)) (inc f) (K x))))) f1) x1)))
(fn [f1 x1] ((((fn [n1] ((zero? n1) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) n1 (((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred n1))))) (fn [f x] (extract ((fn [f3 x4] (f3 x4)) (inc f) (K x))))) f1) x1))
(fn [f1 x1] ((((zero? (fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7))))) (fn [f3 x4] (f3 x4)) ((fn [m n] (fn [f2 x2] (m (n f2) x2))) (fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7)))) (((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7)))))))) f1) x1))
(fn [f1 x1] (((((fn [n] (n (K false) true)) (fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7))))) (fn [f3 x4] (f3 x4)) ((fn [n] (fn [f2 x2] ((fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7)))) (n f2) x2))) (((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7)))))))) f1) x1))
(fn [f1 x1] (((((fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7)))) (K false) true) (fn [f3 x4] (f3 x4)) (fn [f2 x2] ((fn [f x7] (extract ((fn [f7 x6] (f7 x6)) (inc f) (K x7)))) ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] (extract ((fn [f8 x8] (f8 x8)) (inc f3) (K x4)))))) f2) x2))) f1) x1))
(fn [f1 x1] (((((fn [x7] (extract ((fn [f7 x6] (f7 x6)) (inc (K false)) (K x7)))) true) (fn [f3 x4] (f3 x4)) (fn [f2 x2] ((fn [x7] (extract ((fn [f7 x6] (f7 x6)) (inc ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] (extract ((fn [f8 x8] (f8 x8)) (inc f3) (K x4)))))) f2)) (K x7)))) x2))) f1) x1))
(fn [f1 x1] ((((extract ((fn [f7 x6] (f7 x6)) (inc (K false)) (K true))) (fn [f3 x4] (f3 x4)) (fn [f2 x2] (extract ((fn [f7 x6] (f7 x6)) (inc ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] (extract ((fn [f8 x8] (f8 x8)) (inc f3) (K x4)))))) f2)) (K x2))))) f1) x1))
(fn [f1 x1] (((((fn [k] (k I)) ((fn [f7 x6] (f7 x6)) (inc (K false)) (K true))) (fn [f3 x4] (f3 x4)) (fn [f2 x2] ((fn [k] (k I)) ((fn [f7 x6] (f7 x6)) (inc ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) (inc f3) (K x4)))))) f2)) (K x2))))) f1) x1))
(fn [f1 x1] ((((((fn [f7 x6] (f7 x6)) (inc (K false)) (K true)) I) (fn [f3 x4] (f3 x4)) (fn [f2 x2] (((fn [f7 x6] (f7 x6)) (inc ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) (inc f3) (K x4)))))) f2)) (K x2)) I))) f1) x1))
(fn [f1 x1] ((((((fn [x6] ((inc (K false)) x6)) (K true)) I) (fn [f3 x4] (f3 x4)) (fn [f2 x2] (((fn [x6] ((inc ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) (inc f3) (K x4)))))) f2)) x6)) (K x2)) I))) f1) x1))
(fn [f1 x1] ((((((inc (K false)) (K true)) I) (fn [f3 x4] (f3 x4)) (fn [f2 x2] (((inc ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) (inc f3) (K x4)))))) f2)) (K x2)) I))) f1) x1))
(fn [f1 x1] (((((((fn [f] (fn [g h] (h (g f)))) (K false)) ((fn [x] (fn [u] x)) true)) I) (fn [f3 x4] (f3 x4)) (fn [f2 x2] ((((fn [f] (fn [g h] (h (g f)))) ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) ((fn [f] (fn [g h] (h (g f)))) f3) (K x4)))))) f2)) ((fn [x] (fn [u] x)) x2)) I))) f1) x1))
(fn [f1 x1] ((((((fn [g h] (h (g (K false)))) (fn [u] true)) I) (fn [f3 x4] (f3 x4)) (fn [f2 x2] (((fn [g h] (h (g ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) ((fn [f] (fn [g1 h1] (h1 (g1 f)))) f3) (K x4)))))) f2)))) (fn [u] x2)) I))) f1) x1))
(fn [f1 x1] (((((fn [h] (h ((fn [u] true) (K false)))) I) (fn [f3 x4] (f3 x4)) (fn [f2 x2] ((fn [h] (h ((fn [u] x2) ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) ((fn [f] (fn [g1 h1] (h1 (g1 f)))) f3) (K x4)))))) f2)))) I))) f1) x1))
(fn [f1 x1] ((((I ((fn [u] true) (K false))) (fn [f3 x4] (f3 x4)) (fn [f2 x2] (I ((fn [u] x2) ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k I)) ((fn [f8 x8] (f8 x8)) ((fn [f] (fn [g1 h1] (h1 (g1 f)))) f3) (K x4)))))) f2))))) f1) x1))
(fn [f1 x1] (((((fn [x] x) ((fn [u] true) (K false))) (fn [f3 x4] (f3 x4)) (fn [f2 x2] ((fn [x6] x6) ((fn [u] x2) ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k (fn [x6] x6))) ((fn [f8 x8] (f8 x8)) ((fn [f] (fn [g1 h1] (h1 (g1 f)))) f3) (K x4)))))) f2))))) f1) x1))
(fn [f1 x1] (((((fn [u] true) (K false)) (fn [f3 x4] (f3 x4)) (fn [f2 x2] ((fn [u] x2) ((((fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x))) (fn [x] ((fn [f4 n2] ((zero? n2) (fn [f5 x3] (f5 x3)) ((fn [m1 n3] (fn [f6 x5] (m1 (n3 f6) x5))) n2 (f4 (pred n2))))) (x x)))) (pred (fn [f3 x4] ((fn [k] (k (fn [x6] x6))) ((fn [f8 x8] (f8 x8)) ((fn [f] (fn [g1 h1] (h1 (g1 f)))) f3) (K x4)))))) f2)))) f1) x1))
(fn [f1 x1] (((true (fn [f3 x4] (f3 x4)) (fn [f2 x2] x2)) f1) x1))
(fn [f1 x1] ((((fn [a b] a) (fn [f3 x4] (f3 x4)) (fn [f2 x2] x2)) f1) x1))
(fn [f1 x1] ((((fn [b] (fn [f3 x4] (f3 x4))) (fn [f2 x2] x2)) f1) x1))
(fn [f1 x1] (((fn [f3 x4] (f3 x4)) f1) x1))
(fn [f1 x1] ((fn [x4] (f1 x4)) x1))
(fn [f1 x1] (f1 x1))
