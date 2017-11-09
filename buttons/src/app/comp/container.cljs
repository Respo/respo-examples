
(ns app.comp.container
  (:require-macros [respo.macros :refer [defcomp list-> cursor-> <> div hr span]])
  (:require
    [respo.core :refer [create-comp]]
    [app.comp.button :refer [comp-button]]
    [app.comp.box :refer [comp-box]]))

(defn handle-inc [simple-event dispatch!]
  (dispatch! :inc 1))

(defn handle-dec-with-log [state store]
  (fn [simple-event dispatch!]
    (println "some info:" state store)
    (dispatch! :dec 1)))

(defcomp comp-container [store]
  (let [states (:states store)
        state (:data states)]
    (div {}
      (div {}
        (<> span "Demo of component with mutate" nil)
        (comp-box states 5))
      (hr {})
      (<> span "Demo of list of them" nil)
      (list-> :div {}
        (->> (range 10)
          (map-indexed (fn [index n]
            [index (cursor-> n comp-box states n)]))))
      (hr {})
      (div {}
        (<> span "Demo of dispatch:" nil)
        (comp-button "inc" handle-inc)
        (comp-button "dec" (handle-dec-with-log state store))
        (<> span (str store) nil)))))
