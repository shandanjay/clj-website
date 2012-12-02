(ns leiningen.new.clj-website
  "Generate a Heroku-ready web project."
  (:use [leiningen.new.templates :only [renderer year project-name
                                        sanitize-ns name-to-path ->files]]))

(def render (renderer "clj_website"))

(defn heroku-web
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
             ["resources/public/css/bootstrap-responsive.css" (render "bootstrap-responsive.css" data)]
             ["resources/public/css/bootstrap-responsive.min.css" (render "bootstrap-responsive.min.css" data)]
             ["resources/public/css/bootstrap.css" (render "bootstrap.css" data)]
             ["resources/public/css/bootstrap.min.css" (render "bootstrap.min.css" data)]
             ["resources/public/js/bootstrap.js" (render "bootstrap.js" data)]
             ["resources/public/js/bootstrap.min.js" (render "bootstrap.min.js" data)]
             ["resources/public/img/glyphicons-halflings-white.png" (render "glyphicons-halflings-white.png" data)]
             ["resources/public/img/glyphicons-halflings.png" (render "glyphicons-halflings.png" data)]
             ["src/{{nested-dirs}}/templates/layout.html" (render "layout.html")])))
