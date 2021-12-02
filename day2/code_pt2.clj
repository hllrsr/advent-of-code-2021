(ns temp-project.day2-pt2
  (:require [clojure.string :as string]))


(def input (->> "input.txt"
                slurp
                string/split-lines))


(def final-coordinates (loop [input input
                              coordinates {:depth 0 :horizontal 0 :aim 0}]
                         (if (empty? input)
                           coordinates
                           (let [orientation (first input)
                                 direction (-> orientation (string/split #" ") first)
                                 distance (-> orientation (string/split #" ") second (Integer.))
                                 depth (:depth coordinates)
                                 horizontal (:horizontal coordinates)
                                 aim (:aim coordinates)]
                             (cond
                               (and (= 0 aim) (= "forward" direction)) (recur (drop 1 input) (update coordinates :horizontal + distance))
                               (= "forward" direction) (recur (drop 1 input) (assoc {} :depth (+ depth (* distance aim)) :horizontal (+ horizontal distance) :aim aim))
                               (= "up" direction) (recur (drop 1 input) (assoc {} :depth depth :horizontal horizontal :aim (- aim distance)))
                               (= "down" direction) (recur (drop 1 input) (assoc {} :depth depth :horizontal horizontal :aim (+ aim distance))))))))

(* (:depth final-coordinates) (:horizontal final-coordinates))

