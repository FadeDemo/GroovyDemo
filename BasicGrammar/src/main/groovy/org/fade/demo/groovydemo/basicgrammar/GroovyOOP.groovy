package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.MapConstructor

// 没有构造参数，使用命名参数
class PersonNoConstructor {

    String name

    Integer age

}

def person1 = new PersonNoConstructor()
def person2 = new PersonNoConstructor(name: 'Marie')
def person3 = new PersonNoConstructor(age: 1)
def person4 = new PersonNoConstructor(name: 'Marie', age: 2)

// 定义了无参构造函数，使用命名参数
class PersonWithNoArgConstructor {

    String name

    Integer age

    PersonWithNoArgConstructor() {

    }

}

def person5 = new PersonWithNoArgConstructor()
def person6 = new PersonWithNoArgConstructor(name: 'Marie')
def person7 = new PersonWithNoArgConstructor(age: 1)
def person8 = new PersonWithNoArgConstructor(name: 'Marie', age: 2)

// 定义了Map作为第一个参数的构造器，使用命名参数
class PersonWithMapAsFirstArgConstructor {

    String name

    Integer age

    PersonWithMapAsFirstArgConstructor(Map<String, Object> parameterMap) {
        // 貌似有点问题，并未调用此构造器
    }

}

def person9 = new PersonWithNoArgConstructor()
def person10 = new PersonWithNoArgConstructor(name: 'Marie')
def person11 = new PersonWithNoArgConstructor(age: 1)
def person12 = new PersonWithNoArgConstructor(name: 'Marie', age: 2)
println person12.age

// 上面的也可以用MapConstructor代替
@MapConstructor
class PersonWithMapConstructorAnnotation {

    String name

    Integer age

}

def person13 = new PersonWithMapConstructorAnnotation()
def person14 = new PersonWithMapConstructorAnnotation(name: 'Marie')
def person15 = new PersonWithMapConstructorAnnotation(age: 1)
def person16 = new PersonWithMapConstructorAnnotation(name: 'Marie', age: 2)
println person16.age