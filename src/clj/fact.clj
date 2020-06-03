;; Most of these definitions are from here:
;; https://en.wikipedia.org/wiki/Church_encoding
(defn true [a b] a)

(defn false [a b] b)

(defn K [x]
  (fn [u] x))

(defn I [x] x)

(defn extract [k]
  (k I))

(defn inc [f]
  (fn [g h] (h (g f))))

(defn zero? [n]
  (n (K false) true))

(defn * [m n]
  (fn [f x] (m (n f) x)))

;; Predecessor function, returns (n - 1), or 0 if n == 0,
(defn pred [n]
  (fn [f x]
    (extract (n (inc f) (K x)))))

;; A slightly shorter way to write the fixed-point combinator Y.
;; Reduces to the "normal" Y in one step.
(defn Y [f]
  ((fn [x] (x x))
    (fn [x] (f (x x)))))

(defn fact_ [f n]
  ((zero? n)
    1
    (* n (f (pred n)))))

;; Recursively defined factorial function.
;; Takes a single numeral argument.
(def fact (Y fact_))
