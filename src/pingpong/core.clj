(ns pingpong.core
  (:require pingping.web)
  (:gen-class))

(defn -main [& args]
  (apply pingpong.web/-main args))
