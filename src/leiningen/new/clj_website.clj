(ns leiningen.new.clj-website
  "Generates a web project with Noir, Enlive and Twitter Bootstrap."
  (:use [leiningen.new.templates :only [renderer year project-name
                                        sanitize-ns name-to-path ->files]]))

(def render (renderer "clj_website"))

(defn clj-website
  "A Heroku-ready web project generator using Noir, Enlive and Twitter Bootstrap."
  [name]
  (let [data {:name (project-name name)
              :nested-dirs (name-to-path name)
              :namespace (sanitize-ns name)
              :year (year)}]
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             [".gitignore" (render "gitignore" data)]
             [".env" (render "env" data)]
             ["Procfile" (render "Procfile" data)]
             ["src/{{nested-dirs}}/core.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}/core_test.clj" (render "test.clj" data)]
             ["resources/public/css/custom.css" (render "custom.css")]
             ["src/{{nested-dirs}}/templates/layout.html" (render "layout.html")])))
