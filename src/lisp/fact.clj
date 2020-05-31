(defn true [a b] a)

(defn false [a b] b)

(defn K [x y] x)

(defn I [x] x)

(defn zero? [n]
  (n (K false) true))

(defn * [m n]
  (fn [f x] (m (n f) x)))

(defn pred [n f x]
  ((n (fn [g h] (h (g f)))
      (fn [u] x))
    I))

(defn Y [f]
  ((fn [x] (x x))
    (fn [x] (f (x x)))))

(defn fact1 [f n]
  ((zero? n)
    1
    (* n (f (pred n)))))

(def fact (Y fact1))
