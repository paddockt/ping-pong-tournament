(ns pingpong.core
  (:require pingpong.web)
  (:gen-class))

(defn -main [& args]
  (apply pingpong.web/-main args))
