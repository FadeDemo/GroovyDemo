package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode

import java.time.YearMonth
import java.util.regex.Matcher
import java.util.regex.Pattern
/**
*   groovy运算符
*
* @author fade
* @date 2022 /01/15
*/
class GroovyOperators {

    static void main(String[] args) {
//        relationalOperators()
        elvisOperator()
        safeNavigationOperator()
        directFieldAccessOperator()
        methodPointerOperator()
        patternOperator()
        findOperator()
        matchOperator()
        spreadOperatorExample()
        spreadOperatorNullSafe()
        spreadOperatorUsedInIterable()
        spreadOperatorMultipleInvocation()
        spreadMethodArguments()
        spreadList()
        spreadMap()
        range()
        spaceShipOperator()
        subscript()
        safeIndexOperator()
        membershipOperator()
        identityOperator()
        coercionOperator()
    }

    @EqualsAndHashCode
    class Creature { String type }

    static void relationalOperators() {
        // fixme Exception in thread "main" groovy.lang.GroovyRuntimeException:
        //  Could not find named-arg compatible constructor
        def cat = new Creature(type: 'cat')
        def copyCat = cat
        def lion = new Creature(type: 'cat')
        assert cat.equals(lion) // Java logical equality
        assert cat == lion      // Groovy shorthand operator
        assert cat.is(copyCat)  // Groovy identity
        assert cat === copyCat  // operator shorthand
        assert cat !== lion     // negated operator shorthand
    }

    static void elvisOperator() {
        // elvis 运算符
        def a = true
        // a ?: false 等价于 a ? a : false
        def b = a ?: false
        println a
        println b
        // elvis 赋值运算符
        def c
        // c ?= false 应该等价于 c = c ? c : false
        c ?= false
        println c
    }

    static void safeNavigationOperator() {
        // 安全引用操作符
        def a = null
        println a?.b
    }

    static class User {
        public final String name
        User(String name) { this.name = name}
        String getName() { "Name: $name" }
    }

    static void directFieldAccessOperator() {
        def user = new User('Bob')
        assert user.@name == 'Bob'
    }

    static def doSomething(String str) { str.toUpperCase() }

    static def doSomething(Integer x) { 2*x }

    static void methodPointerOperator() {
        // 方法指针运算符
        def str = 'example of method reference'
        // 方法指针可以看成是闭包
        def fun = str.&toUpperCase
        def upper = fun()
        assert upper == str.toUpperCase()
        // 当有多个匹配的方法时，方法指针会运行时匹配参数
        def reference = this.&doSomething
        assert reference('foo') == 'FOO'
        assert reference(123)   == 246
    }

    static void patternOperator() {
        def p = ~/foo/
        assert p instanceof Pattern
    }

    static void findOperator() {
        def text = "some text to match"
        def m = text =~ /match/
        assert m instanceof Matcher
        if (!m) {
            throw new RuntimeException("Oops, text not found!")
        }
    }

    static void matchOperator() {
        def text = "some text to match"
        def m = text ==~ /match/
        assert m instanceof Boolean
        if (m) {
            throw new RuntimeException("Should not reach that point!")
        }
    }

    static class Car {
        String make
        String model
    }

    static void spreadOperatorExample() {
        // spread operator example
        def cars = [
                new Car(make: 'Peugeot', model: '508'),
                new Car(make: 'Renault', model: 'Clio')]
        def makes = cars*.make
        assert makes == ['Peugeot', 'Renault']
    }

    static void spreadOperatorNullSafe() {
        // 空安全的
        assert null*.make == null
    }

    static class Component {
        Long id
        String name
    }

    static class CompositeObject implements Iterable<Component> {
        def components = [
                new Component(id: 1, name: 'Foo'),
                new Component(id: 2, name: 'Bar')]

        @Override
        Iterator<Component> iterator() {
            components.iterator()
        }
    }

    static void spreadOperatorUsedInIterable() {
        // 能被用在实现了Iterable接口的类上
        def composite = new CompositeObject()
        assert composite*.id == [1,2]
        assert composite*.name == ['Foo','Bar']
    }

    static class Make {
        String name
        List<Model> models
    }

    @Canonical
    static class Model {
        String name
    }

    static void spreadOperatorMultipleInvocation() {
        // spread operator multiple invocation
        def cars = [
                new Make(name: 'Peugeot',
                        models: [new Model('408'), new Model('508')]),
                new Make(name: 'Renault',
                        models: [new Model('Clio'), new Model('Captur')])
        ]
        def models = cars*.models*.name
        assert models == [['408', '508'], ['Clio', 'Captur']]
        // or use collectNested instead
        cars = [
                [
                        new Car(make: 'Peugeot', model: '408'),
                        new Car(make: 'Peugeot', model: '508')
                ], [
                        new Car(make: 'Renault', model: 'Clio'),
                        new Car(make: 'Renault', model: 'Captur')
                ]
        ]
        models = cars.collectNested{ it.model }
        assert models == [['408', '508'], ['Clio', 'Captur']]
    }

    static int function(int x, int y, int z) {
        x*y+z
    }

    static void spreadMethodArguments() {
        // 展开方法参数
        def args = [4,5,6]
        assert function(*args) == 26
        // 可以混用
        args = [4]
        assert function(*args,5,6) == 26
    }

    static void spreadList() {
        // 展开list
        def items = [4,5]
        def list = [1,2,3,*items,6]
        assert list == [1,2,3,4,5,6]
    }

    static void spreadMap() {
        // 展开map
        def m1 = [c:3, d:4]
        def map = [a:1, b:2, *:m1]
        assert map == [a:1, b:2, c:3, d:4]
        // map中的展开是会受位置影响的
        m1 = [c:3, d:4]
        map = [a:1, b:2, *:m1, d: 8]
        assert map == [a:1, b:2, c:3, d:8]
    }

    static void range() {
        def a = YearMonth.of(2021, 1)
        def b = YearMonth.of(2021, 4)
        def c = a..b
        println c.collect()
    }

    static void spaceShipOperator() {
        // 与compareTo方法等价
        assert (1 <=> 1) == 0
        assert (1 <=> 2) == -1
        assert (2 <=> 1) == 1
        assert ('a' <=> 'z') == -1
        assert (YearMonth.of(2021, 1) <=> YearMonth.of(2021, 2)) == -1
    }

    static class SubscriptUser {
        Long id
        String name
        def getAt(int i) {
            switch (i) {
                case 0: return id
                case 1: return name
            }
            throw new IllegalArgumentException("No such element $i")
        }
        void putAt(int i, def value) {
            switch (i) {
                case 0: id = value; return
                case 1: name = value; return
            }
            throw new IllegalArgumentException("No such element $i")
        }
    }

    static void subscript() {
        def list = [0,1,2,3,4]
        assert list[2] == 2
        list[2] = 4
        assert list[0..2] == [0,1,4]
        list[0..2] = [6,6,6]
        assert list == [6,6,6,3,4]
        // 下标运算符等价与getAt方法或putAt方法
        assert list.getAt(2) == 6
        def user = new SubscriptUser(id: 1, name: 'Alex')
        assert user[0] == 1
        assert user[1] == 'Alex'
        user[1] = 'Bob'
        assert user.name == 'Bob'
    }

    static void safeIndexOperator() {
        def array = null
        println array?[1]
    }

    static void membershipOperator() {
        def list = ['Grace','Rob','Emmy']
        assert ('Emmy' in list)
        // in operator is equal to isCase method
        assert list.isCase("Emmy")
    }

    static void identityOperator() {
        // 判断是否是同一个对象
        def list1 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
        def list2 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
        assert list1 == list2
        assert !list1.is(list2)
    }

    static class Identifiable {
        String name
    }
    static class CoercionUser {
        Long id
        String name
        def asType(Class target) {
            if (target == Identifiable) {
                return new Identifiable(name: name)
            }
            throw new ClassCastException("User cannot be coerced into $target")
        }
    }

    static void coercionOperator() {
        Integer x = 123
        String s
        try {
            // 这种类型的强制转换不会考虑值是否兼容
            // fixme 此示例并不会报错
            s = (String) x
            assert !s.is(x)
        } catch(Exception e) {
            e.printStackTrace()
        }
        x = 123
        // as 运算符则会自动把值的类型修改过来
        s = x as String
        // 除非目标对象和源对象类型相同，不然会创建一个新的对象
        assert !s.is(x)
        // 使用as运算符需要定义规则（即asType方法）
        def u = new CoercionUser(name: 'Xavier')
        def p = u as Identifiable
        assert p instanceof Identifiable
        assert !(p instanceof CoercionUser)
    }

}
