package org.fade.demo.groovydemo.basicgrammar

// 集合和数组
assert [1, 2, 3]
assert ![]

// Matcher
assert ('a' =~ /a/)
assert !('a' =~ /b/)

// Iterators and Enumerations
assert [0].iterator()
assert ![].iterator()
Vector v = [0] as Vector
Enumeration enumeration = v.elements()
assert enumeration
enumeration.nextElement()
assert !enumeration

// Map
assert ['one' : 1]
assert ![:]

// String
assert 'a'
assert !''
def nonEmpty = 'a'
assert "$nonEmpty"
def empty = ''
assert !"$empty"
blank = " "
assert blank

// Number
assert 1
assert 3.5
assert !0
assert !BigDecimal.ZERO
assert !Float.valueOf(0.0)

// 对象引用
assert new Object()
assert !null

// 自定义判断规则
class Color {

    String name

    boolean asBoolean(){
        name == 'green' ? true : false
    }

}

assert new Color(name: 'green')
assert !new Color(name: 'red')

