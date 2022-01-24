package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.AnnotationCollector
import groovy.transform.AnnotationCollectorMode

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
*   groovy注解
*
* @author fade
* @date 2021 /12/15
*/
class GroovyAnnotation {

    static void main(String[] args) {
        // closure作为值传递
        // fixme Modifier - No such property
//        closureAsValue()
        declareMetaAnnotation()
        overrideMetaAnnotationParameter()
        testAnnotationCollectorModeAttribute()
    }

    static void closureAsValue() {
        def tasks = Runner.run(Tasks)
        assert tasks.result == [1, 'JDK 6'] as Set
    }

    static void declareMetaAnnotation() {
        def annotations = MyTransactionalService.annotations*.annotationType()
        assert (Service in annotations)
        assert (Transactional in annotations)
    }

    static void overrideMetaAnnotationParameter() {
        println Joe.getAnnotation(Foo).value()
        println Joe.getAnnotation(Bar).value()
        println Joe.getAnnotation(Foo).test()
    }

    static void testAnnotationCollectorModeAttribute() {
        println Jack.getAnnotation(Bar).value()
        try {
            println Lucy.getAnnotation(Bar).value()
        } catch(Exception e) {
            e.printStackTrace()
        }
    }

}

@interface Anywhere {

}

@Retention(RetentionPolicy.RUNTIME)
@interface OnlyIf {
    Class value()
}

class Runner {
    static <T> T run(Class<T> taskClass) {
        def tasks = taskClass.newInstance()
        def params = [jdk:6, windows: false]
        tasks.class.declaredMethods.each { m ->
            if (Modifier.isPublic(m.modifiers) && m.parameterTypes.length == 0) {
                def onlyIf = m.getAnnotation(OnlyIf)
                if (onlyIf) {
                    Closure cl = onlyIf.value().newInstance(tasks,tasks)
                    cl.delegate = params
                    if (cl()) {
                        m.invoke(tasks)
                    }
                } else {
                    m.invoke(tasks)
                }
            }
        }
        tasks
    }

}

class Tasks {
    Set result = []
    void alwaysExecuted() {
        result << 1
    }
    @OnlyIf({ jdk>=6 })
    void supportedOnlyInJDK6() {
        result << 'JDK 6'
    }
    @OnlyIf({ jdk>=7 && windows })
    void requiresJDK7AndWindows() {
        result << 'JDK 7 Windows'
    }

}

@Retention(RetentionPolicy.RUNTIME)
@interface Service {

}

@Retention(RetentionPolicy.RUNTIME)
@interface Transactional {

}

@Service
@Transactional
@AnnotationCollector
@interface TransactionalService {

}

@TransactionalService
class MyTransactionalService {

}

@Retention(RetentionPolicy.RUNTIME)
@interface Foo {

    String value()

    int test()

}
@Retention(RetentionPolicy.RUNTIME)
@interface Bar {

    String value()

}

@Foo(value = "a", test = 5)
@Bar(value = "b")
@AnnotationCollector
@interface FooBar {}

@FooBar(value = "a", test = 10)
class Joe {}

/**
 * <p>出现重复注解，默认会报错</p>
 * */
@FooBar(value = "a", test = 10)
@Bar(value = "b")
class Lucy {}

/**
 * <p>使用{@link AnnotationCollector#mode}
 * 解决出现重复注解的问题</p>
 * */
@Foo(value = "a", test = 5)
@Bar(value = "b")
@AnnotationCollector(mode = AnnotationCollectorMode.PREFER_COLLECTOR)
@interface FooBarV2 {}

@FooBarV2
@Bar(value = "c")
class Jack {}