## Lisp attempt

Not proper Lisp, but rather a clojure-esque representation of lambda calculus. This is a hobby project to teach myself some functional programming basics.

The first goal was to define the factorial function, see [LambdaTest](https://github.com/h908714124/lisp-attempt/blob/master/src/test/java/com/mypack/eval/LambdaTest.java) and [fact.clj](https://github.com/h908714124/lisp-attempt/blob/master/src/clj/fact.clj).

````sh
fgrep -n '(* (pred 1) ((Y fact_) (pred (pred 1))))' showcase/fact1.clj
````

