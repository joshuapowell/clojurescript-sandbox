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

After executing the above command I successfully received the ClojureScript
browser page at `http://localhost:9000`

## Using the REPL
After a successful build, run, and launch in the browser. I executed the code
the "hello world" page told me to run in the REPL (Terminal.app).

```
(js/alert "Hello CLJS!")
```

I received an alert message in the browser.

I was curious if I could run other Javascript keyword commands, and it turned
out that I could do so. First I tried executing the following code in the REPL
and I recieved an error.

```
(js/window/confirm "Did it work?")
```

I then attempted to using dot syntax and it worked successfully.

```
(js/window.confirm "Did it work?")
```

## Writing and Executing Methods
Next up, is writing methods. After completing the simple two-digit average
method example that the ClojureScript website provided, I wanted to try with
implementing a common coordinate conversion equation on my own.

```
(defn dmsToDd [d m s]
  (+ d (/ m 60) (/ s 3600)))
```

I restarted my REPL and executed the following two lines of code to see if my
new coordinate conversion method worked properly.

```
(require '[hello-human.core :as human] :reload)
(human/dmsToDd 30 15 50)
```

Which successfully returned the following response.

```
30.26388888888889
```

## Build for Release
Creating a build of your ClojureScript can be completed in one line.

```
clj -m cljs.main --optimizations advanced -c hello-human.core
```

Once the build is complete, you may test the build by executing the following
command.
```
clj -m cljs.main --serve
```

## Testing
Testing is performed with the PhantomJS headless browser. To install the
PhantomJS utility onto your macOS operating system, execute the following
command.

```
npm install -g phantomjs-prebuilt
```
