package org.fade.demo.groovydemo.basicgrammar

/**
* @author fade
* @date 2022 /01/15
*/
class GroovyNumber {

    static void main(String[] args) {
        integerLiterals()
        decimalLiterals()
        underscoreInLiterals()
        numberTypeSuffixes()
        mathOperations()
    }

    static void integerLiterals() {
        def a = 1
        println a
        a = 0b1
        println a
        a = 012
        println a
        a = 0x10
        println a
    }

    static void decimalLiterals() {
        def a = 1.0
        println a
        a = 1e3
        assert a == 1000.0
        println a
    }

    static void underscoreInLiterals() {
        def a = 1_1
        println a
    }

    static void numberTypeSuffixes() {
        def a = 1L
        println a
        def b = 1.0D
        println b
    }

    static void mathOperations() {
        def a = 1.0
        def b = 2.0
        def c = a + b
        println c
        def d = 1 / 2
        assert d instanceof BigDecimal
        def e = 1f / 2
        assert e instanceof Double
    }

}
