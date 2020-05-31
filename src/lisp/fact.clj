(defn true [a b] a)

(defn false [a b] b)

(defn K [x] (lambda [y] x))

(defn I [x] x)

(defn value [v]
  (fn [h] (h v)))

(defn extract [k]
  (k I))

(defn inc [f]
  (fn [g h] (h (f g))))

(defn zero? [n]
  (n (K false) true))

(defn * [m n]
  (fn [f x] (m (n f) x)))

(defn pred [n]
  (fn [f x]
    ((n (fn [g h] (h (g f)))
        (fn [u] x))
      I)))

(defn Y [f]
  ((fn [x] (x x))
    (fn [x] (f (x x)))))

(defn fact1 [f n]
  ((zero? n)
    1
    (* n (f (pred n)))))

(def fact (Y fact1))
