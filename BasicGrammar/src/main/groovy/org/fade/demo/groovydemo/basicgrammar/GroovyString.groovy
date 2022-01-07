package org.fade.demo.groovydemo.basicgrammar

import org.codehaus.groovy.runtime.StringGroovyMethods

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
        def f = '''\
abc'''
        def g = '''
abc'''
        def h = '''  a
    b
  c'''
        def i = '''a
    |b
   |c'''
        println a
        println b
        println c
        println d
        println a[0]
        println a[-1]
        println a[0..-1]
        println e
        assert !f.startsWith("\n")
        assert g.startsWith("\n")
        println f
        println g
        // stripIndent方法作用是去除String中每一行最前面的空格
        // 去除空格的数量是由空格最少的行的最前面的空格数决定
        // 对控制字符\t \n 等不起作用
        println StringGroovyMethods.stripIndent(h)
        // stripMargin方法作用是去除String中每一行最前面的空格和控制符（\n）除外
        // 空格和控制符后面需跟着 |
        println StringGroovyMethods.stripMargin(i)
    }

}
