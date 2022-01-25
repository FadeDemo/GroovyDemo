package org.fade.demo.groovydemo.basicgrammar

import java.util.function.Predicate

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
        delegateStrategy()
        convertToSAMType()
//        convertToArbitraryType()
        currying()
        memoization()
        composition()
        trampoline()
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

    static void delegateStrategy() {
        // 不需要显示的设置delegate.
        def p = new Person(name:'Igor')
        def cl = { name.toUpperCase() }
        cl.delegate = p
        // warning owner first导致会报错
        // use groovy script instead
        // or change the delegate strategy
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        assert cl() == 'IGOR'
        // todo 补充Closure.TO_SELF策略
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

    /**
     * <p>闭包转换为SAM类型</p>
     * */
    static void convertToSAMType() {
        Predicate filter = { it.contains 'G' } as Predicate
        assert filter.test('Groovy') == true
        // as Type 是可选的
        Predicate<String> predicate = { it.contains 'G' }
        assert predicate.test("Groovy")
        // 允许使用方法指针 方法指针可以看成是闭包
        Predicate<String> methodPointer = GroovyClosure.&doFilter
        methodPointer.test("Groovy")
        // 方法引用
        Predicate<String> methodReference = GroovyClosure::doFilter
        methodReference.test("Groovy")
    }

    static boolean doFilter(String s) { s.contains('G') }

    /**
     * <p>闭包转为其它任意类型</p>
     * */
    static void convertToArbitraryType() {
        // 闭包转为接口
        def impl = { println 'ok'; 123 } as FooBar
        assert impl.foo() == 123
        // warning 会打印两遍ok
        impl.bar()
        // fixme 按照示例所写会报错
        impl = { println 'ok'; 123 } as FooBarClass
        assert impl.foo() == 123
        impl.bar()
    }

    interface FooBar {
        int foo()
        void bar()
    }

    class FooBarClass {
        int foo() { 1 }
        void bar() { println 'bar' }
    }

    /**
     * <p>currying操作</p>
     * */
    static void currying() {
        // left currying
        def nCopies = { int n, String str -> str*n }
        def twice = nCopies.curry(2)
        assert twice('bla') == 'blabla'
        assert twice('bla') == nCopies(2, 'bla')
        // right currying
        def blah = nCopies.rcurry('bla')
        assert blah(2) == 'blabla'
        assert blah(2) == nCopies(2, 'bla')
        // index based currying
        // 此种情况用于应对有2个以上参数的情况
        def volume = { double l, double w, double h -> l*w*h }
        def fixedWidthVolume = volume.ncurry(1, 2d)
        assert volume(3d, 2d, 4d) == fixedWidthVolume(3d, 4d)
        def fixedWidthAndHeight = volume.ncurry(1, 2d, 4d)
        assert volume(3d, 2d, 4d) == fixedWidthAndHeight(3d)
    }

    /**
     * <p>memoization操作<p>
     * */
    static void memoization() {
        def fib
        fib = { long n -> n<2?n:fib(n-1)+fib(n-2) }.memoize()
        assert fib(25) == 75025
    }

    /**
     * <p>composition操作</p>
     * */
    static void composition() {
        def plus2  = { it + 2 }
        def times3 = { it * 3 }
        // 比如下面这个，先执行times3，执行的结果作为参数传给plus2
        def times3plus2 = plus2 << times3
        assert times3plus2(3) == 11
        assert times3plus2(4) == plus2(times3(4))
        def plus2times3 = times3 << plus2
        assert plus2times3(3) == 15
        assert plus2times3(5) == times3(plus2(5))
        // reverse composition
        assert times3plus2(3) == (times3 >> plus2)(3)
    }

    /**
     * <p>trampoline操作</p>
     * */
    static void trampoline() {
        def factorial
        factorial = { int n, def accu = 1G ->
            if (n < 2) return accu
            factorial.trampoline(n - 1, n * accu)
        }
        factorial = factorial.trampoline()
        assert factorial(1) == 1
        assert factorial(3) == 1 * 2 * 3
        assert factorial(1000)
    }

}
