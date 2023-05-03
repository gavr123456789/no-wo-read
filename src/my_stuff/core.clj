(ns my-stuff.core
  (:gen-class)
  (:require [clojure.data.json :as json]
            [clojure.string :as string]
            [clojure.java.io :as io]))

(def filename "base.json")

(defn- file-exists? [filename]
  (.exists (io/as-file filename)))


(def data-from-file (if (file-exists? filename)
                      (json/read-str (slurp filename))
                      {}))

(defn save-data [data]
  (spit filename (json/write-str data {:escape-unicode false})))

;; returns 3 thing: [eng-word attempts ru-word]
(defn get-least-attempts [data]
  (first (sort-by second (for [[word info] data]
                           [word (get info "attempts") (get info "translation")]))))

(defn- update-attempts [data word]
  (update-in data [word "attempts"] inc))


(inc (rand-int 10))


(defn- count-to-3 [counter]
  (if (< counter 3)
    (inc counter)
    0)
  )


(defn random-pair [m]
  (let [entries (seq m)
        entry-count (count entries)
        rand-num (rand-int entry-count)
        rand-pair (nth entries rand-num)]
    [(.getKey rand-pair) (.getValue rand-pair)]))

(defn random-triple [data]
  (let [pair (random-pair data)
        word (first pair)
        info (second pair)
        attempts (get info "attempts")
        translation (get info "translation")
        ]
    [word attempts translation]
    )
  )


(defn get-next-triple [data count]
  (if (= count 3)
   (get-least-attempts data)
   (random-triple data)) 
  )


(defn guess-word2 [data counter]
  
  (let [least-attempts (get-next-triple data counter)
        need-translate (first least-attempts)
        translation (last least-attempts)
        attempts (second least-attempts)
        count (count-to-3 counter)]

    (println "Translate the word:" need-translate attempts)
    (println "count:" count)

    (let [word (clojure.string/trim (read-line))]

      (cond
        (= word translation) (do
                               (println "Valid answer!")
                               (recur (update-attempts data need-translate) count))
        (= word "exit") (do
                          (println "Exiting")
                          (save-data data))
        :else (do
                (println "Invalid answer. Please try again.")
                (recur data count))))))

(defn -main []
  (guess-word2 data-from-file -1))