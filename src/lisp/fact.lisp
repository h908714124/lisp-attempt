(def true ((lambda (a b) a)))
(def false ((lambda (a b) b)))

(def zero? (lambda (n) (n (lambda (x) false) true)))

(def * (lambda (m n) (lambda (f x) (m (n f) x))))

(def pred (lambda (n) (lambda (f x) ((n (lambda (g h) (h (g f))) (lambda (u) x)) (lambda (u1) u1)))))

(def 1 (lambda (f x) (f x)))

