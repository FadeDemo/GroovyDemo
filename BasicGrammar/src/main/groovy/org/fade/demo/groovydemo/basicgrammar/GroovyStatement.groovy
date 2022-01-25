package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.Immutable

// def 定义变量
def a = 1
println a

// var 定义变量
var b = 1
println b

// 确切的类型定义变量
Integer c = 1
println c

// 多重赋值
def (d, e, f) = [1, 2, 'Hello Groovy']
println d
println e
println f

// 支持在多重赋值中指定变量类型
def (Integer g, Integer h, String i) = [1, 2, "Hello Groovy"]
println g
println h
println i

// 也支持使用数组进行多重赋值
def (j, k) = 'Hello Groovy!'.split(" ")
println j
println k

// 多重赋值时定义了多余的变量
def (l, m) = [1]
println l
println m

// 对象解构与多重赋值
// 这里要借助运算符重载实现
@Immutable
class Coordinates {
    double latitude
    double longitude

    // 必须要重载getAt方法
    double getAt(int idx) {
        if (idx == 0) latitude
        else if (idx == 1) longitude
        else throw new Exception("Wrong coordinate index, use 0 or 1")
    }
}

def coordinates = new Coordinates(latitude: 43.23, longitude: 3.67)
def (la, lo) = coordinates
assert la == 43.23
assert lo == 3.67

// switch
// 注意这里switch的condition是BigDecimal
// 注意这里的case有各种类型的值
def x = 1.23
def result = ""
switch ( x ) {
    case "foo":
        result = "found foo"
    case "bar":
        result += "bar"
    case [4, 5, 6, 'inList']:
        result = "list"
        break
    case 12..30:
        result = "range"
        break
    case Integer:
        result = "integer"
        break
    case Number:
        result = "number"
        break
    case ~/fo*/:
        result = "foo regex"
        break
    case { it < 0 }:
        result = "negative"
        break
    default:
        result = "default"
}
assert result == "number"

// for-in循环
// 在Range中迭代
result = 0
for (index in 1..3) {
    result += index
}
println result
result = 0
// 在List中迭代
for (index in [1, 2, 3]) {
    result += index
}
println result
// 在数组中迭代
result = 0
for (index in [1, 2, 3].toArray()) {
    result += index
}
println result
// 在Map中迭代
result = 0
for (entry in [1:1, 2:2, 3:3]) {
    result += entry.value
}
println result
// 遍历字符串中的字符
for (character in "Hello Groovy!") {
    println character
}

// groovy assert
//assert result == false
// 自定义assert失败的错误消息
//assert result == false : "custom error message"

// 给语句定义标签
start:
    begin = 1
operate:
    begin++
end:
    println begin