package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.CompileStatic
import groovy.transform.SelfType

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
        instance.overridingDefaultMethods()
        instance.simpleInheritance()
        instance.dynamicCode()
        instance.dynamicMethods()
        instance.defaultConflictResolution()
        instance.userConflictResolution()
        instance.implementingATraitAtRuntime()
        instance.implementingMultipleTraitAtOnce()
        instance.chainingBehavior()
        instance.semanticsOfSuperInsideATrait()
        instance.samTypeCoercion()
//        instance.differenceWithMixins()
        instance.staticMethodsAndEtc()
        instance.inheritanceOfStateGotchas()
        instance.selfTypes()
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

    class DuckOverride implements FlyingAbilityComposition, SpeakingAbilityComposition {
        String quack() { "Quack!" }
        String speak() { quack() }
    }

    void overridingDefaultMethods() {
        def d = new DuckOverride()
        assert d.fly() == "I'm flying!"
        assert d.quack() == "Quack!"
        assert d.speak() == "Quack!"
    }

    /*Overriding default methods*/

    /*Simple inheritance*/

    trait NamedSimpleInheritance {
        String name
    }
    trait Polite extends NamedSimpleInheritance {
        String introduce() { "Hello, I am $name" }
    }

    class PersonSimpleInheritance implements Polite {}

    void simpleInheritance() {
        def p = new PersonSimpleInheritance(name: 'Alice')
        assert p.introduce() == 'Hello, I am Alice'
    }

    /*Simple inheritance*/

    /*Multiple inheritance*/

    trait WithId {
        Long id
    }
    trait WithName {
        String name
    }

    trait IdentifiedImplements implements WithId, WithName {}

    /*Multiple inheritance*/

    /*Dynamic code*/

    trait SpeakingDuck {
        String speak() { quack() }
    }

    class DuckDynamicCode implements SpeakingDuck {
        String methodMissing(String name, args) {
            "${name.capitalize()}!"
        }
    }

    void dynamicCode() {
        def d = new DuckDynamicCode()
        assert d.speak() == 'Quack!'
    }

    /*Dynamic code*/

    /*Dynamic methods in a trait*/

    trait DynamicObject {
        private Map props = [:]
        def methodMissing(String name, args) {
            name.toUpperCase()
        }
        def propertyMissing(String prop) {
            props[prop]
        }
        void setProperty(String prop, Object value) {
            props[prop] = value
        }
    }

    class Dynamic implements DynamicObject {
        String existingProperty = 'ok'
        String existingMethod() { 'ok' }
    }

    void dynamicMethods() {
        def d = new Dynamic()
        assert d.existingProperty == 'ok'
        assert d.foo == null
        d.foo = 'bar'
        assert d.foo == 'bar'
        assert d.existingMethod() == 'ok'
        assert d.someMethod() == 'SOMEMETHOD'
    }

    /*Dynamic methods in a trait*/

    /*Default conflict resolution*/

    trait A {
        String exec() { 'A' }
    }
    trait B {
        String exec() { 'B' }
    }
    class C implements A,B {}

    void defaultConflictResolution() {
        def c = new C()
        assert c.exec() == 'B'
    }

    /*Default conflict resolution*/

    /*User conflict resolution*/

    class D implements A,B {
        String exec() { A.super.exec() }
    }

    void userConflictResolution() {
        def d = new D()
        assert d.exec() == 'A'
    }

    /*User conflict resolution*/

    /*Implementing a trait at runtime*/

    trait Extra {
        String extra() { "I'm an extra method" }
    }
    class Something {
        String doSomething() { 'Something' }
    }

    void implementingATraitAtRuntime() {
//        def s = new Something() as Extra
        def s = (new Something()).withTraits Extra
        println s.extra()
        println s.doSomething()
    }

    /*Implementing a trait at runtime*/

    /*Implementing multiple traits at once*/

    trait ARuntime { void methodFromA() {} }

    trait BRuntime { void methodFromB() {} }

    class CRuntime {}

    void implementingMultipleTraitAtOnce() {
        def c = new CRuntime()
        def d = c.withTraits ARuntime, BRuntime
        d.methodFromA()
        d.methodFromB()
    }

    /*Implementing multiple traits at once*/

    /*Chaining behavior*/

    interface MessageHandler {
        void on(String message, Map payload)
    }

    trait DefaultHandler implements MessageHandler {
        void on(String message, Map payload) {
            println "Received $message with payload $payload"
        }
    }

    trait LoggingHandler implements MessageHandler {
        void on(String message, Map payload) {
            println "Seeing $message with payload $payload"
            super.on(message, payload)
        }
    }

    class HandlerWithLogger implements DefaultHandler, LoggingHandler {}

    void chainingBehavior() {
        def loggingHandler = new HandlerWithLogger()
        loggingHandler.on('test logging', [:])
    }

    /*Chaining behavior*/

    /*Semantics of super inside a trait*/

    trait Filtering {
        StringBuilder append(String str) {
            def subst = str.replace('o','')
            super.append(subst)
        }
        String toString() { super.toString() }
    }

    void semanticsOfSuperInsideATrait() {
        def sb = new StringBuilder().withTraits Filtering
        sb.append('Groovy')
        assert sb.toString() == 'Grvy'
    }

    /*Semantics of super inside a trait*/

    /*SAM type coercion*/

    trait GreeterSAM {
        String greet() { "Hello $name" }
        abstract String getName()
    }

    void greet(GreeterSAM g) { println g.greet() }

    void samTypeCoercion() {
        GreeterSAM greeter = { 'Alice' }
        greet { 'Alice' }
    }

    /*SAM type coercion*/

    /*Differences with mixins*/

    class AMixins { String methodFromA() { 'A' } }

    class BMixins { String methodFromB() { 'B' } }

    void differenceWithMixins() {
        AMixins.metaClass.mixin BMixins
        def o = new AMixins()
        assert o.methodFromA() == 'A'
        assert o.methodFromB() == 'B'
        assert o instanceof AMixins
        assert !(o instanceof BMixins)
    }

    /*Differences with mixins*/

    /*Static methods, properties and fields*/

    trait TestHelper {
        public static boolean CALLED = false
        static void init() {
            CALLED = true
        }
    }
    class FooStatic implements TestHelper {}

    void staticMethodsAndEtc() {
        FooStatic.init()
        assert FooStatic.org_fade_demo_groovydemo_basicgrammar_GroovyTrait$TestHelper__CALLED
    }

    /*Static methods, properties and fields*/

    /*Inheritance of state gotchas*/

    trait IntCouple {
        int x = 1
        int y = 2
        int sum() { x+y }
        int sumWithGetter() { getX()+getY() }
    }

    class BaseElem implements IntCouple {
        int f() { sum() }
    }

    class Elem implements IntCouple {
        int x = 3
        int y = 4
        int f() { sum() }
        int g() {
            sumWithGetter()
        }
    }

    void inheritanceOfStateGotchas() {
        def base = new BaseElem()
        assert base.f() == 3
        def elem = new Elem()
        assert elem.f() == 3
        assert elem.g() == 7
    }

    /*Inheritance of state gotchas*/

    /*Self types*/

    class CommunicationService {
        static void sendMessage(String from, String to, String message) {
            println "$from sent [$message] to $to"
        }
    }

    class Device { String id }

    class SecurityService {
        static void check(Device d) { if (d.id==null) throw new SecurityException() }
    }

    @SelfType(Device)
//    @CompileStatic
    trait Communicating {
        void sendMessage(Device to, String message) {
            SecurityService.check(this)
            CommunicationService.sendMessage(id, to.id, message)
        }
    }

    class MyDevice extends Device implements Communicating {}

    void selfTypes() {
        def bob = new MyDevice(id:'Bob')
        def alice = new MyDevice(id:'Alice')
        bob.sendMessage(alice,'secret')
    }

    /*Self types*/

}
