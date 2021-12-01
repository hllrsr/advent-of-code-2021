(ns temp-project.day1_pt1
  (:require [clojure.edn :as edn]
            [clojure.string :as string]))

(def input (->> "input.txt"
                slurp
                string/split-lines
                (map edn/read-string)))

(def report (loop [input input
                   previous nil
                   report []]
              (if (nil? previous)
                (recur (drop 1 input) (first input) (conj report "N/A"))
                (let [current (first input)]
                  (if (not-empty input)
                    (if (> current previous)
                      (recur (drop 1 input) current (conj report "increased"))
                      (recur (drop 1 input) current (conj report "decreased")))
                    report)))))

(->> report
     (filter #(= "increased" %))
     count)
