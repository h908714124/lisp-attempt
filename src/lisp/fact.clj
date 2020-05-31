(defn true [a b] a)

(defn false [a b] b)

(defn K [x]
  (fn [u] x))

(defn I [x] x)

(defn value [v]
  (fn [h] (h v)))

(defn extract [k]
  (k I))

; not succ
(defn inc [f]
  (fn [g h] (h (g f))))

(defn zero? [n]
  (n (K false) true))

(defn * [m n]
  (fn [f x] (m (n f) x)))

(defn pred [n]
  (fn [f x]
    (extract (n (inc f) (K x)))))

(defn Y [f]
  ((fn [x] (x x))
    (fn [x] (f (x x)))))

(defn fact1 [f n]
  ((zero? n)
    1
    (* n (f (pred n)))))

(def fact (Y fact1))
