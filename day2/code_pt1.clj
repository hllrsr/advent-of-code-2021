(ns temp-project.day2-pt1
  (:require [clojure.string :as string]))


(def input (->> "input.txt"
                slurp
                string/split-lines))


(def final-coordinates (loop [input input
                              coordinates {:depth 0 :horizontal 0}]
                         (if (empty? input)
                           coordinates
                           (let [orientation (first input)
                                 direction (-> orientation (string/split #" ") first)
                                 distance (-> orientation (string/split #" ") second (Integer.))]
                             (cond
                               (= "forward" direction) (recur (drop 1 input) (update coordinates :horizontal + distance))
                               (= "up" direction) (recur (drop 1 input) (update coordinates :depth - distance))
                               (= "down" direction) (recur (drop 1 input) (update coordinates :depth + distance)))))))

(* (:depth final-coordinates) (:horizontal final-coordinates))
