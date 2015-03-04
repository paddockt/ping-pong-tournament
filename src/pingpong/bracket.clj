(ns pingpong.bracket)

(defrecord Bracket [left right game])

(def bracket (Bracket. :left :right :game))

;;Helper function to determine if num-players is a power of 2.
;;Found here https://github.com/aphyr/merkle/blob/master/test/merkle/kv/fixed_test.clj
(defn power-of-two?
  "Is x a positive integer power of 2?"
  [x]
  (if-not (pos? x)
    false
    (loop [x x]
      (if (= 1 x)
        true
        (let [x (/ x 2)]
          (if-not (integer? x)
            false
            (recur x)))))))

;;Build pingpong bracket based on number of players and elimination type.
(defn start-tournament [players elim-type]
  (if (= elim-type 0)
     (build-bracket bracket players)))

;;Builds bracket recursively.
(defn build-bracket [bracket players]
  (cond
   (= (count players) 2)
   (assoc bracket 
     :left nil 
     :right nil 
     :game {:p1 (first players) :p2 (first (rest players))})
   (and (> (count players) 2) (power-of-two? (count players)))
   (assoc bracket 
     :left 
     (build-bracket bracket (take (/ (count players) 2) players))
     :right 
     (build-bracket bracket (take-last (/ (count players) 2) players))
     :game {:p1 nil :p2 nil})))
