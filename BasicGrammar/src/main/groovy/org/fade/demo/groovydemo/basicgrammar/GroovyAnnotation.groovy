package org.fade.demo.groovydemo.basicgrammar

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
    }

    static void closureAsValue() {
        def tasks = Runner.run(Tasks)
        assert tasks.result == [1, 'JDK 6'] as Set
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

