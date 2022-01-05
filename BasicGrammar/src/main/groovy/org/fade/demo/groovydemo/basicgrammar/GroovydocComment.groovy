package org.fade.demo.groovydemo.basicgrammar

/**
*   groovydoc (runtime)
*
* @author fade
* @date 2022/01/05
*/
class GroovydocComment {

    static void main(String[] args) {
        assert Foo.class.groovydoc.content.contains('Some class groovydoc for Foo')
        assert Foo.class.getMethod('bar', new Class[0]).groovydoc.content.contains('Some method groovydoc for bar')
    }

    @Groovydoc("Some class groovydoc for Foo")
    static class Foo {

        @Groovydoc("Some method groovydoc for bar")
        void bar() {}

    }

}
