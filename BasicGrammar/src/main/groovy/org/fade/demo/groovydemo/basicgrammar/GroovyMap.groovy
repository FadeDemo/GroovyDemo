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
        mapCoercion()
    }

    static void mapCoercion() {
        def map
        // 此时Map中key是方法名，value是方法的实现
        map = [
                i: 10,
                hasNext: { map.i > 0 },
                next: { map.i-- },
        ]
        def iter = map as Iterator
        while (iter.hasNext()) {
            println iter.next()
        }
    }

}
