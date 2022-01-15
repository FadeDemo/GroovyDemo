package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.EqualsAndHashCode

/**
*   groovy运算符
*
* @author fade
* @date 2022 /01/15
*/
class GroovyOperators {

    static void main(String[] args) {
        relationalOperators()
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

}
