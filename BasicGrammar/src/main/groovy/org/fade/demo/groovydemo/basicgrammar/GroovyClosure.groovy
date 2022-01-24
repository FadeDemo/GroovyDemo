package org.fade.demo.groovydemo.basicgrammar

/**
*   groovy闭包
*
* @author fade
* @date 2021 /12/13
*/
class GroovyClosure {

    static void main(String[] args) {
        defineClosure()
        closureAsAnObject()
        example()
        receiveParameter()
        receiveImplicitParameter()
        quoteVariable()
        useInMethod()
        closureWithList()
        closureWithMap()
        Enclosing enclosing = new Enclosing()
        enclosing.run()
        NestedClosures nestedClosures = new NestedClosures()
        nestedClosures.run()
        changeDelegateOfClosure()
    }

    static void defineClosure() {
        // 无参
        def clos = { println "Hello Closure" }
        clos()
        // 有参，未指定参数
        clos = { it -> println it }
        clos("Hello Closure")
        // 有参，指定参数
        clos = { String it -> println it }
        clos("Hello Closure")
    }

    static void closureAsAnObject() {
        def listener = { e -> println "Clicked on $e.source" }
        assert listener instanceof Closure
        Closure callback = { println 'Done!' }
        callback()
        Closure<Boolean> isTextFile = {
            File it -> it.name.endsWith('.txt')
        }
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

    static class Enclosing {
        void run() {
            def whatIsThisObject = { getThisObject() }
            assert whatIsThisObject() == this
            def whatIsThis = { this }
            assert whatIsThis() == this
        }
    }

    static class NestedClosures {
        void run() {
            def nestedClosures = {
                def cl = { owner }
                cl()
            }
            assert nestedClosures() == nestedClosures
        }
    }

    static class Person {
        String name
    }

    static class Thing {
        String name
    }

    static void changeDelegateOfClosure() {
        def p = new Person(name: 'Norman')
        def t = new Thing(name: 'Teapot')
        def upperCasedName = { delegate.name.toUpperCase() }
        upperCasedName.delegate = p
        assert upperCasedName() == 'NORMAN'
        upperCasedName.delegate = t
        assert upperCasedName() == 'TEAPOT'
    }

}
