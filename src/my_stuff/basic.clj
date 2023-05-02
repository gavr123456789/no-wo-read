(ns my-stuff.basic)

(defn get-name [user]
  (if (:name user)
    (user :name)
    "guest"))

;; (get-name {})
(get-name {:name "egor"})

(def guest {})
(def loginned-user {:name "egor"})
(println (get-name guest) (get-name loginned-user))


;; person birthday

(defn birthday [person]
  (update person :age inc))

(birthday {:age 1})

