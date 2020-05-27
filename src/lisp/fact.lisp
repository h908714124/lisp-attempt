(defn foobar)

(((lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) 
  (lambda (f n) 
    (zero? n 1 (* n (f (+ -1 n)))))) 
 (lambda (f x) (f (f x))))
