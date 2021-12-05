/**
*   groovy的字符串
*
* @author fade
* @since 2021/12/05
*/
class GroovyString {

    static void main(String[] args) {
        def a = 'Hello Groovy'
        def b = "Hello Groovy"
        def c = '''Hello \
Groovy'''
        def d = """Hello \
Groovy"""
        def e = "Hello Groovy\n" * 3
        println a
        println b
        println c
        println d
        println a[0]
        println a[-1]
        println a[0..-1]
        println e
    }

}
