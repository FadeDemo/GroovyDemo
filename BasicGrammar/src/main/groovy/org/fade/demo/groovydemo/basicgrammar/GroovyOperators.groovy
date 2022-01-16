package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.EqualsAndHashCode

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
*   groovy运算符
*
* @author fade
* @date 2022 /01/15
*/
class GroovyOperators {

    static void main(String[] args) {
//        relationalOperators()
        elvisOperator()
        safeNavigationOperator()
        directFieldAccessOperator()
        methodPointerOperator()
        patternOperator()
        findOperator()
        matchOperator()
    }

    @EqualsAndHashCode
    class Creature { String type }

    static void relationalOperators() {
        // fixme Exception in thread "main" groovy.lang.GroovyRuntimeException:
        //  Could not find named-arg compatible constructor
        def cat = new Creature(type: 'cat')
        def copyCat = cat
        def lion = new Creature(type: 'cat')
        assert cat.equals(lion) // Java logical equality
        assert cat == lion      // Groovy shorthand operator
        assert cat.is(copyCat)  // Groovy identity
        assert cat === copyCat  // operator shorthand
        assert cat !== lion     // negated operator shorthand
    }

    static void elvisOperator() {
        // elvis 运算符
        def a = true
        // a ?: false 等价于 a ? a : false
        def b = a ?: false
        println a
        println b
        // elvis 赋值运算符
        def c
        // c ?= false 应该等价于 c = c ? c : false
        c ?= false
        println c
    }

    static void safeNavigationOperator() {
        // 安全引用操作符
        def a = null
        println a?.b
    }

    static class User {
        public final String name
        User(String name) { this.name = name}
        String getName() { "Name: $name" }
    }

    static void directFieldAccessOperator() {
        def user = new User('Bob')
        assert user.@name == 'Bob'
    }

    static def doSomething(String str) { str.toUpperCase() }

    static def doSomething(Integer x) { 2*x }

    static void methodPointerOperator() {
        // 方法指针运算符
        def str = 'example of method reference'
        // 方法指针可以看成是闭包
        def fun = str.&toUpperCase
        def upper = fun()
        assert upper == str.toUpperCase()
        // 当有多个匹配的方法时，方法指针会运行时匹配参数
        def reference = this.&doSomething
        assert reference('foo') == 'FOO'
        assert reference(123)   == 246
    }

    static void patternOperator() {
        def p = ~/foo/
        assert p instanceof Pattern
    }

    static void findOperator() {
        def text = "some text to match"
        def m = text =~ /match/
        assert m instanceof Matcher
        if (!m) {
            throw new RuntimeException("Oops, text not found!")
        }
    }

    static void matchOperator() {
        def text = "some text to match"
        def m = text ==~ /match/
        assert m instanceof Boolean
        if (m) {
            throw new RuntimeException("Should not reach that point!")
        }
    }

}
