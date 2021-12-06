package org.fade.demo.groovydemo.basicgrammar
/**
*   groovy正则表达式
*
* @author fade
* @since 2021/12/05
*/
class GroovyRegex {

    static void main(String[] args) {
        def a = ~'Groovy'
        def b = ('Hello Groovy'=~ '[\\s\\S]*Groovy')
        def c = ('Hello Groovy' ==~ '[\\s\\S]*Groovy')
        def d = ('Groovy' ==~ 'oo')
        println b.matches()
        println c
        println d
    }

}
