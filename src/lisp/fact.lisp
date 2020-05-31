(def true (fn [a b] a))
(def false (fn [a b] b))

(def zero? (fn [n] (n (fn [x] false) true)))

(def * (fn [m n] (fn [f x] (m (n f) x))))

(def pred (fn [n] (fn [f x] ((n (fn [g h] (h (g f))) (fn [u] x)) (fn [u] u)))))

(def Y (fn [f] ((fn [x] (x x)) (fn [x] (f (x x))))))

(def fact1 (fn [f n] ((zero? n) 1 (* n (f (pred n))))))
(def fact (Y fact1))
