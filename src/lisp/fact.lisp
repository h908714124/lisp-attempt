(def true ((lambda (a b) a)))
(def false ((lambda (a b) b)))

(def zero? (lambda (n) (n (lambda (x) false) true)))

(zero? (lambda (f x) x))

(zero? (lambda (f x) (f x)))
