package org.fade.demo.groovydemo.basicgrammar
/**
*   groovy列表
*
* @author fade
* @since 2021 /12/05
*/
class GroovyList {

    static void main(String[] args) {
        def a = [1, 2, 3]
        def b = [4, 5]
        def c = [3, 4]
        println a.plus(b)
        println a.minus(c)
        println a[0]
        println a[-1]
        println a[0..-1]
        def d = [1]
        d << 2
        println d[0..-1]
    }

}
