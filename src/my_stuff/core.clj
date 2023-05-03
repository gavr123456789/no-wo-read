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


(defn guess-word2 [data]


  (let [least-attempts (get-least-attempts data)
        need-translate (first least-attempts)
        translation (last least-attempts)
        attempts (second least-attempts)]

    (println "Translate the word:" need-translate attempts)

    (let [word (clojure.string/trim (read-line))]

      (cond
        (= word translation) (do
                               (println "Valid answer!")
                               (recur (update-attempts data need-translate)))
        (= word "exit") (do
                          (println "Exiting")
                          (save-data data))
        :else (do
                (println "Invalid answer. Please try again.")
                (recur data))))))

(defn -main []
  (guess-word2 data-from-file))