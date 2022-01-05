package org.fade.demo.groovydemo.basicgrammar

/**
 * <p>groovy 引用标识符</p>
 * <p>实则比较鸡肋，可以认为是只能在groovy的map中使用。
 * groovy的map可以通过map.xxx来引用映射关系，
 * 当key是字符串时，且字符串有一些特殊字符时，
 * 这时候不给引用的key加引号则会编译报错</p>
 * @author fade
 * @since 2022/01/05
 * */
class GroovyQuotedIdentifier {

    static void main(String[] args) {
        def map = [:]
        map."an identifier with a space and double quotes" = "ALLOWED"
        map.'with-dash-signs-and-single-quotes' = "ALLOWED"
        assert map."an identifier with a space and double quotes" == "ALLOWED"
        assert map.'with-dash-signs-and-single-quotes' == "ALLOWED"
        map.'single quote'
        map."double quote"
        map.'''triple single quote'''
        map."""triple double quote"""
        map./slashy string/
        map.$/dollar slashy string/$
    }

}
