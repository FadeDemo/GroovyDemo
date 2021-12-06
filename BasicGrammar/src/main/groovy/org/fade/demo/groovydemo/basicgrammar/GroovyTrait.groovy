package org.fade.demo.groovydemo.basicgrammar

/**
* @author fade
* @since 2021/12/06
*/
class GroovyTrait {

    static void main(String[] args) {
        GroovyTrait instance = new GroovyTrait()
        instance.basicUse()
        instance.abstractMethod()
        instance.privateMethod()
    }

    /*basic use*/

    trait FlyingAbility {

        String fly() { "I'm flying!" }

    }

    class Bird implements FlyingAbility {}

    void basicUse() {

        def b = new Bird()

        assert b.fly() == "I'm flying!"

    }

    /*basic use*/

    /*abstract method*/

    trait Greetable {

        abstract String name()

        String greeting() { "Hello, ${name()}!" }

    }

    class Person implements Greetable {

        String name() { 'Bob' }

    }

    void abstractMethod() {
        def p = new Person()
        assert p.greeting() == 'Hello, Bob!'
    }

    /*abstract method*/

    /*private method*/

    trait Greeter {

        private String greetingMessage() {
            'Hello from a private method!'
        }

        String greet() {
            def m = greetingMessage()
            println m
            m
        }

    }

    class GreetingMachine implements Greeter {}

    void privateMethod() {
        def g = new GreetingMachine()
        assert g.greet() == "Hello from a private method!"
        try {
            assert g.greetingMessage()
        } catch (MissingMethodException e) {
            println "greetingMessage is private in trait"
        }
    }

    /*private method*/

}
