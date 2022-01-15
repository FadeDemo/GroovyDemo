package org.fade.demo.groovydemo.basicgrammar
/**
* @author fade
* @since 2021/12/05
*/
class GroovyMap {

    static void main(String[] args) {
        def a = [:]
        def b = ['id': 1]
        def c = [id: 1]
        println a
        println b
        println c
        def d = [1: 2]
        println d[1]
        def e = [d: 1]
        assert !e.containsKey(d)
        def f = [(d): 1]
        assert f.containsKey(d)
    }

}
