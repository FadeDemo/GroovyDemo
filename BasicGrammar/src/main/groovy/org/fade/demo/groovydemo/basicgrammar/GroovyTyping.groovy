package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

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





