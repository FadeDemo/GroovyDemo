package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.TupleConstructor

interface Greeter {
    void greet(String name)
}

class DefaultGreeter {
    void greet(String name) { println "Hello" }
}

greeter = new DefaultGreeter()
coerced = greeter as Greeter
assert coerced instanceof Greeter

@TupleConstructor
class Customer {
    String first
    String last
    int age
    Date since
    Collection favItems
}

customer = new Customer("fade")
customer = new Customer("fade", "pi")

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

// 以def作为方法返回值

def someMethod() { println 'method called' }

someMethod()

// 不指定方法参数类型

def thirdMethod(param) { println "$param passed" }
thirdMethod(1)
thirdMethod("str")

// 以方法内执行的最后一条语句的值作为返回值

String anotherMethod() { 'another method called' }
println anotherMethod()

// 方法支持命名参数

def foo(Map args) { "${args.name}: ${args.age}" }
println foo(name: 'Marie', age: 1)

// 方法支持命名参数和位置参数混用，但此时Map必须作为第一个参数

def foo(Map args, Integer number) { "${args.name}: ${args.age}, and the number is ${number}" }
println foo(name: 'Marie', age: 1, 23)
println foo(23, name: 'Marie', age: 1)

// 当Map不作为方法参数时，必须显示的传递一个Map参数
def foo(Integer number, Map args) { "${args.name}: ${args.age}, and the number is ${number}" }
println foo(23, [name: 'Marie', age: 1])

// 方法支持给参数提供默认值
def foo(String par1, Integer par2 = 1) { [name: par1, age: par2] }
assert foo('Marie').age == 1
assert foo('Marie', 2).age == 2

// 方法支持变长参数，和Java变长参数本质是一样的，都是等价于数组作为参数
def foo(Object... args) { args.length }
assert foo() == 0
assert foo(1) == 1
assert foo(1, 2) == 2

// 变长参数的方法与具体参数的方法在一起时，方法会优先选择具体的
def foo1(Object... args) { 1 }
def foo1(Object x) { 2 }
assert foo1() == 1
assert foo1(1) == 2
assert foo1(1, 2) == 1

// 重载方法的选择策略
// 1.实现多个接口的类更匹配接近implement关键字的
interface I1 {}
interface I2 extends I1 {}
interface I3 {}
class Clazz implements I3, I2 {}

def method(I1 i1) { 'I1' }
def method(I3 i3) { 'I3' }

assert method(new Clazz()) == 'I3'

// 2.Object数组的优先级比Object高
def method(Object[] arg) { 'array' }
def method(Object arg) { 'object' }

assert method([] as Object[]) == 'array'

// 3.非变长参数的优先级比变长参数的优先级高
def method(String s, Object... vargs) { 'vararg' }
def method(String s) { 'non-vararg' }

assert method('foo') == 'non-vararg'

// 4.如果多个变长参数方法，则会匹配对应的变长参数最少的
def method1(String s, Object... vargs) { 'two vargs' }
def method1(String s, Integer i, Object... vargs) { 'one varg' }

// 对于第一个来说，变长参数有 35和new Date()
// 对于第二个来说，变长参数只有new Date()
assert method1('foo', 35, new Date()) == 'one varg'

// 5.接口的优先级比父类的高
interface I {}
class Base {}
class Child extends Base implements I {}

def method(Base b) { 'superclass' }
def method(I i) { 'interface' }

assert method(new Child()) == 'interface'

// 6.对于原生参数类型，会选择稍微更大
def method(Long l) { 'Long' }
def method(Short s) { 'Short' }
def method(BigInteger bi) { 'BigInteger' }

assert method(35) == 'Long'

