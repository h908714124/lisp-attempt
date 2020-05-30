(def true (lambda (a b) a))
(def false (lambda (a b) b))

(def zero? (lambda (n) (n (lambda (x) false) true)))

(def * (lambda (m n) (lambda (f x) (m (n f) x))))

(def pred (lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u) u)))))

(def Y (lambda (f) ((lambda (x) (x x)) (lambda (x) (f (x x))))))

(def fact1 (lambda (f n) ((zero? n) 1 (* n (f (pred n))))))
(def fact (Y fact1))
