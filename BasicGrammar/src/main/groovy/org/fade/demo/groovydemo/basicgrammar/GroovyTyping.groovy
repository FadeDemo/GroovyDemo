package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam

import java.util.function.Predicate

// 使用@TypeChecked开启类型检查
// 声明在类上会为这个类的所有内容进行类型检查
@TypeChecked
class Calculator {

    // 声明在方法上则只会为该方法内的所有内容进行类型检查
//    @TypeChecked
    int sum(int x, int y) { x+y }

}

// 也可以给TypeChecked注解指定value值跳过类型检查
//@TypeChecked
//class GreetingService {
//    String greeting() {
//        doGreet()
//    }
//
//    @TypeChecked(TypeCheckingMode.SKIP)
//    private String doGreet() {
//        // fixme where is SentenceBuilder
////        def b = new SentenceBuilder()
//        b.Hello.my.name.is.John
//        b
//    }
//}
//def s = new GreetingService()
//assert s.greeting() == 'Hello my name is John'

@groovy.transform.TupleConstructor
class Person1 {
    String firstName
    String lastName
    String toString() {
        firstName + " " + lastName
    }
}

// List构造器
Person1 list = ['Ada','Lovelace']
println list
// Map构造器
Person1 map = [firstName:'Ada', lastName:'Lovelace']
println map

// 要在静态Groovy中推断出闭包参数的类型有三种方法：
// 法一：显示指定闭包参数类型
class Person2 {
    String name
    int age
}

void inviteIf(Person2 p, Closure<Boolean> predicate) {
    if (predicate.call(p)) {
        // send invite
        // ...
    }
}

@groovy.transform.TypeChecked
void failCompilation() {
    Person2 p = new Person2(name: 'Gerard', age: 55)
    inviteIf(p) { Person2 it ->
        it.age >= 18 // No such property: age
    }
}

// 法二：使用SAM类型推断
void inviteIf1(Person2 p, Predicate<Person> predicate) {
    if (predicate.call(p)) {
        // send invite
        // ...
    }
}

@groovy.transform.TypeChecked
void failCompilation1() {
    Person2 p = new Person2(name: 'Gerard', age: 55)
    inviteIf1(p) {
        it.age >= 18 // No such property: age
    }
}

// 法三：使用@ClousureParams注解
void inviteIf3(Person2 p, @ClosureParams(FirstParam.class) Closure<Boolean> predicate) {
    if (predicate.call(p)) {
        // send invite
        // ...
    }
}

@groovy.transform.TypeChecked
void failCompilation3() {
    Person2 p = new Person2(name: 'Gerard', age: 55)
    inviteIf3(p) {
        it.age >= 18 // No such property: age
    }
}



