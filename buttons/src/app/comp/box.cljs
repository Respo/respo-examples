
(ns app.comp.box
  (:require
    [respo.macros :refer [defcomp <> div span]]
    [app.comp.button :refer [comp-button]]))

(def initial-state 0)

(defn handle-click [*cursor* next-state]
  (fn [e dispatch!]
    (dispatch! :states [*cursor* next-state])))

(defcomp comp-box [states n]
  (let [state (or (:data states) initial-state)]
    (div {}
      (<> span (str n ". ") nil)
      (comp-button "inc" (handle-click *cursor* (+ state n)))
      (<> span state nil))))
