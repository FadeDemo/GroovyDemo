package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.Field

println 'Groovy world!'

// 定义变量要注意的点
// 局部变量
int a = 1
// 局部变量使用@Field注解
@Field
int b = 1
// 非局部变量，通过script binding 实现
c = 1
def printlnAB() {
    // 此时是无法访问a的
    try {
        println a
    } catch(Exception e) {
        e.printStackTrace()
    }
    // 可以访问b
    println b
    // 可以访问c
    println c
}
printlnAB()
