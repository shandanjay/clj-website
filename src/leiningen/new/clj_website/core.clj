(ns {{namespace}}.core
  (:use noir.core)
  (:require [noir.server :as server]
            [net.cgrand.enlive-html :as html]))

(html/deftemplate layout "{{nested-dirs}}/templates/layout.html" [])

(defpage "/" []
  (layout)
  [:.message "Your clj-website is ready. Enjoy!"])

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (server/start port)))
