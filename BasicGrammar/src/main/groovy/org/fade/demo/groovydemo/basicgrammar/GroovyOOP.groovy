package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.TupleConstructor

interface Greeter {
    void greet(String name)
}

class DefaultGreeter {
    void greet(String name) { println "Hello" }
}

greeter = new DefaultGreeter()
coerced = greeter as Greeter
assert coerced instanceof Greeter

@TupleConstructor
class Customer {
    String first
    String last
    int age
    Date since
    Collection favItems
}

customer = new Customer("fade")
customer = new Customer("fade", "pi")
