(ack 1 2)
(Y ack_ 1 2)
(ack_ (Y ack_) 1 2)
((fn [f m n] (zero? m (+ 1 n) (zero? n (f (pred m) 1) (f (pred m) (f m (pred n)))))) (Y ack_) 1 2)
(Y ack_ 0 (Y ack_ 1 1))
(ack_ (Y ack_) 0 (Y ack_ 1 1))
((fn [f m n] (zero? m (+ 1 n) (zero? n (f (pred m) 1) (f (pred m) (f m (pred n)))))) (Y ack_) 0 (Y ack_ 1 1))
(+ 1 (Y ack_ 1 1))
(+ 1 (ack_ (Y ack_) 1 1))
(+ 1 ((fn [f m n] (zero? m (+ 1 n) (zero? n (f (pred m) 1) (f (pred m) (f m (pred n)))))) (Y ack_) 1 1))
(+ 1 (Y ack_ 0 (Y ack_ 1 0)))
(+ 1 (ack_ (Y ack_) 0 (Y ack_ 1 0)))
(+ 1 ((fn [f m n] (zero? m (+ 1 n) (zero? n (f (pred m) 1) (f (pred m) (f m (pred n)))))) (Y ack_) 0 (Y ack_ 1 0)))
(+ 1 (+ 1 (Y ack_ 1 0)))
(+ 1 (+ 1 (ack_ (Y ack_) 1 0)))
(+ 1 (+ 1 ((fn [f m n] (zero? m (+ 1 n) (zero? n (f (pred m) 1) (f (pred m) (f m (pred n)))))) (Y ack_) 1 0)))
(+ 1 (+ 1 (Y ack_ 0 1)))
(+ 1 (+ 1 (ack_ (Y ack_) 0 1)))
(+ 1 (+ 1 ((fn [f m n] (zero? m (+ 1 n) (zero? n (f (pred m) 1) (f (pred m) (f m (pred n)))))) (Y ack_) 0 1)))
(+ 1 (+ 1 2))
4