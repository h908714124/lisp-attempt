## Lisp attempt

Un calculador de lambdaos.
Puede imprimar los pasos de los evaluaciones.

* built-ins: {`true`, `false`, `K`, `I`, `0`, `1`, `2` etc}
* [Definitions of basic functions and the factorial function](https://github.com/h908714124/lisp-attempt/blob/master/src/clj/fact.clj)
* [Evaluation of (fact 0)](https://github.com/h908714124/lisp-attempt/blob/master/showcase/fact0.clj)
* [Evaluation of (fact 1)](https://github.com/h908714124/lisp-attempt/blob/master/showcase/fact1.clj)
* [Evaluation of (fact 2)](https://github.com/h908714124/lisp-attempt/blob/master/showcase/fact2.clj)
* [Evaluation (fact 3)](https://github.com/h908714124/lisp-attempt/blob/master/showcase/fact3.clj)

````
fgrep -n '(fn [f x] (1 (((Y fact_) (pred 1)) f) x))' showcase/fact1.clj
````
