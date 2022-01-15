package org.fade.demo.groovydemo.basicgrammar

/**
*   groovy字符
*
* @author fade
* @date 2022 /01/15
*/
class GroovyCharacter {

    static void main(String[] args) {
        def a = 'a'
        assert !(a instanceof Character)
        char b = 'b'
        assert b instanceof Character
        def c = 'c' as Character
        assert c instanceof Character
        def d = (char) 'd'
        assert d instanceof Character
    }

}
