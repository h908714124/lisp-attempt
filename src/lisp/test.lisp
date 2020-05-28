((
  (lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) 
  (lambda (f2 n) 
    ((n (lambda (x2) (lambda (a b) b)) (lambda (a b) a)) 
     (lambda (f1 x2) (f1 x2)) 
     (lambda (f1 x2) 
       (n ((f2 
	    (lambda (f11 x1) 
	      ((n (lambda (g h) (h (g f11))) (lambda (u) x1)) 
	       (lambda (u1) u1)))) f1) x2))))) 2)

((
  (lambda (f) ((lambda (x) (f x x)) (lambda (x) (f x x)))) 
  (lambda (f2 n) 
    ((n (lambda (x2) b) a) (lambda (f1 x2) (f1 x2)) 
     (lambda (n1) 
       (lambda (f1 x2) 
	 ((f2 (lambda (f11 x1) 
		((n (lambda (g h) (h (g f11))) 
		    (lambda (u) x1)) 
		 (lambda (u1) u1)))) (n1 f1) x2)))))) 2)
