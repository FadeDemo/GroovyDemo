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
        instance.finalMethod()
        instance.thisKeyword()
        instance.interfaces()
        instance.properties()
        instance.privateFields()
        instance.publicFields()
        instance.compositionOfBehaviors()
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

    /*final method*/

    trait Fish {

        final void swim() {
            println 'I am swimming'
        }

    }

    class Goldfish implements Fish {
        /*see the decompile class file in the output folder,
        you will know what exactly 'final' keyword is in Groovy trait*/
    }

    void finalMethod() {
        def fish = new Goldfish()
        fish.swim()
    }

    /*final method*/

    /*this*/

    trait Introspector {
        def whoAmI() { this }
    }
    class Foo implements Introspector {}

    void thisKeyword() {
        def foo = new Foo()
        assert foo.whoAmI().is(foo)
        assert foo.whoAmI() == foo
    }

    /*this*/

    /*interfaces*/
    interface Named {
        String name()
    }
    trait GreetableInterfaces implements Named {
        String greeting() { "Hello, ${name()}!" }
    }
    class PersonInterfaces implements GreetableInterfaces {
        String name() { 'Bob' }
    }

    void interfaces() {
        def p = new PersonInterfaces()
        assert p.greeting() == 'Hello, Bob!'
        assert p instanceof Named
        assert p instanceof GreetableInterfaces
    }

    /*interfaces*/

    /*properties*/

    trait NamedProperties {
        String name
    }

    class PersonProperties implements NamedProperties {}

    void properties() {
        def p = new PersonProperties(name: 'Bob')
        assert p.name == 'Bob'
        assert p.getName() == 'Bob'
    }

    /*properties*/

    /*private fields*/

    trait Counter {
        private int count = 0
        int count() { count += 1; count }
    }
    class FooPrivateFields implements Counter {}

    void privateFields() {
        def f = new FooPrivateFields()
        assert f.count() == 1
        assert f.count() == 2
    }

    /*private fields*/

    /*public fields*/

    trait NamedPublicFields {
        public String name
    }

    class PersonPublicFields implements NamedPublicFields {}

    void publicFields() {
        def p = new PersonPublicFields()
        p.org_fade_demo_groovydemo_basicgrammar_GroovyTrait$NamedPublicFields__name = 'Bob'
    }

    /*public fields*/

    /*Composition of behaviors*/

    trait FlyingAbilityComposition {
        String fly() { "I'm flying!" }
    }
    trait SpeakingAbilityComposition {
        String speak() { "I'm speaking!" }
    }

    class Duck implements FlyingAbilityComposition, SpeakingAbilityComposition {}

    void compositionOfBehaviors() {
        def d = new Duck()
        assert d.fly() == "I'm flying!"
        assert d.speak() == "I'm speaking!"
    }

    /*Composition of behaviors*/

    /*Overriding default methods*/

    

    /*Overriding default methods*/

}
