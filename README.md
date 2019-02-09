# Getting Setup

## About ClojureScript

ClojureScript is a robust, practical, and fast programming language with a set
of useful features that together form a simple, coherent, and powerful tool.

ClojureScript is a compiler for Clojure that targets JavaScript. It emits 
JavaScript code which is compatible with the advanced compilation mode of the 
Google Closure optimizing compiler.

[For more information visit the ClojureScript website](https://clojurescript.org)

## Getting Started

To begin using this code base you will need to install Clojure on your
development system. I am using macOS as my development operating system and
used the Homebrew to install `clojure`

```
brew install clojure
```

A note on EDN files.

EDN or Extensible Data Notation is subset of the syntax used by Clojure. EDN,
similar to JSON, is restricted to data and will not execute code. [According
to one website] the primary benefit of EDN over JSON or YAML is that the 
notation is Extensible or it can be extended through the use of Tagged
Elements.

```clojure
;;;;;;;;;;;;;;;;;;;;;;;
;;; Tagged Elements ;;;
;;;;;;;;;;;;;;;;;;;;;;;

; EDN can be extended by tagging elements with # symbols.

#MyYelpClone/MenuItem {:name "eggs-benedict" :rating 10}

; Let me explain this with a Clojure example. Suppose I want to transform that
; piece of EDN into a MenuItem record.

(defrecord MenuItem [name rating])

; defrecord defined, among other things, map->MenuItem which will take a map
; of field names (as keywords) to values and generate a user.MenuItem record

; To transform EDN to Clojure values, I will need to use the built-in EDN
; reader, clojure.edn/read-string

(clojure.edn/read-string "{:eggs 2 :butter 1 :flour 5}")
; -> {:eggs 2 :butter 1 :flour 5}

; To transform tagged elements, pass to clojure.edn/read-string an option map
; with a :readers map that maps tag symbols to data-reader functions, like so

(clojure.edn/read-string
    {:readers {'MyYelpClone/MenuItem map->MenuItem}}
    "#MyYelpClone/MenuItem {:name \"eggs-benedict\" :rating 10}")
; -> #user.MenuItem{:name "eggs-benedict", :rating 10}
```

## Build and Run the Application

```
clj --main cljs.main --compile hello-human.core --repl
```
