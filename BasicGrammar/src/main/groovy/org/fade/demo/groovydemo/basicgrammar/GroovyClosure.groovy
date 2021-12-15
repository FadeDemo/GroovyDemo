package org.fade.demo.groovydemo.basicgrammar

/**
*   groovy闭包
*
* @author fade
* @date 2021 /12/13
*/
class GroovyClosure {

    static void main(String[] args) {
        example()
        receiveParameter()
        receiveImplicitParameter()
        quoteVariable()
        useInMethod()
        closureWithList()
        closureWithMap()
    }

    static void example() {
        def clos = {println "Hello World"}
        clos.call()
    }

    static void receiveParameter() {
        def clos = {param->println "Hello ${param}"}
        clos.call("World")
    }

    static void receiveImplicitParameter() {
        def clos = {println "Hello ${it}"}
        clos.call("World")
    }

    static void quoteVariable() {
        def str1 = "Hello"
        def clos = {param -> println "${str1} ${param}"}
        clos.call("World")

        // We are now changing the value of the String str1 which is referenced in the closure
        str1 = "Welcome"
        clos.call("World")
    }

    static void display(Closure closure) {
        closure.call("Inner")
    }

    static void useInMethod() {
        def str1 = "Hello"
        def clos = { param -> println "${str1} ${param}" }
        display clos
    }

    static void closureWithList() {
        def lst = [11, 12, 13, 14]
        lst.each {println it}
    }

    static void closureWithMap() {
        def mp = ["TopicName" : "Maps", "TopicDescription" : "Methods in Maps"]
        mp.each {println it}
        mp.each {println "${it.key} maps to: ${it.value}"}
    }

}
