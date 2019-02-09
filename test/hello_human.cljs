(ns hello_human.core-test
  (:require [hello_human.core :refer [dmsToDd]]
            [clojure.test :refer [deftest testing is are run-tests]]))

(deftest dmsToDd-test[d m s]
  (testing "Does hello_human.core/dmsToDd equal static value?")
  (is (= (hello_human.core/dmsToDd 30 15 50) 30.26388888888889))

(dmsToDd-test)
(run-tests)
