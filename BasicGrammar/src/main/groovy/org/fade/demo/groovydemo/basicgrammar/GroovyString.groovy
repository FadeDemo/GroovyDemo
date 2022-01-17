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
        def j = "str"
        def k = "${j}"
        def l = "The sum of 1 and 2 is equal to ${def x = 1; def y = 2; x + y}"
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
        println j
        println k
        println l
        assert '$5' == "\$5"
        assert '${name}' == "\${name}"
        def sParameterLessClosure = "1 + 2 == ${-> 3}"
        assert sParameterLessClosure == '1 + 2 == 3'
        def sOneParamClosure = "1 + 2 == ${ w -> w << 3}"
        assert sOneParamClosure == '1 + 2 == 3'
        // 闭包${}的懒执行
        def number = 1
        def eagerGString = "value == ${number}"
        def lazyGString = "value == ${ -> number }"
        assert eagerGString == "value == 1"
        assert lazyGString ==  "value == 1"
        number = 2
        assert eagerGString == "value == 1"
        assert lazyGString ==  "value == 2"
        // GString作Map的key
        // 应避免这种情况
        def key = "a"
        def m = ["${key}": "letter ${key}"]
        assert m["a"] == null
        def n = """test
test'"""
        println n
        def name = 'Groovy'
        def template = """
    Dear Mr ${name},

    You're the winner of the lottery!

    Yours sincerly,

    Dave
"""
        assert template.toString().contains('Groovy')

        // 斜杠字符串
        def slashString = /abc/
        println slashString
        slashString = /\abc/
        println slashString
        slashString = /a
bc/
        println slashString
        slashString = /ab${key}/
        println slashString
        slashString = /ab${'/'}/
        println slashString
        def dollarSlashString = $/abc/$
        println dollarSlashString
        dollarSlashString = $/ab$$a/$
        println dollarSlashString
        dollarSlashString = $/ab$slashString/$
        println dollarSlashString
        dollarSlashString = $/ab${slashString}/$
        println dollarSlashString
        dollarSlashString = $/ab
c/$
        println dollarSlashString
    }

}
