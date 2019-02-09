(ns hello_human.core)

(println "Hello there, human")

;;
;; Average
;;
;; Usage:
;;
;; (require '[hello-human.core :as human] :reload)
;; (human/average 20 13)
;;
;; : return (float) 16.5
;;
(defn average [a b]
  (/ (+ a b) 2.0))

;;
;; Coordinate Conversion, Degree-Minute-Second to Decimal Degrees
;;
;; Equation:
;;
;; dd = d + (m/60) + (s/3600)
;;
;; If heading is S(outh) or W(est) the number should be negated.
;;
;; Usage:
;;
;; (require '[hello-human.core :as human] :reload)
;; (human/dmsToDd 30 15 50)
;;
(defn dmsToDd [d m s]
  (+ d (/ m 60) (/ s 3600)))
