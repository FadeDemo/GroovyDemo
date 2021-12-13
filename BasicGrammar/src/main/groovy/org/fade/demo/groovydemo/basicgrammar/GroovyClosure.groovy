package org.fade.demo.groovydemo.basicgrammar

/**
*   groovy闭包
*
* @author fade
* @date 2021 /12/13
*/
class GroovyClosure {

    static void main(String[] args) {
        example()
        receiveParameter()
    }

    static void example() {
        def clos = {println "Hello World"}
        clos.call()
    }

    static void receiveParameter() {
        def clos = {param->println "Hello ${param}"}
        clos.call("World")
    }

}
