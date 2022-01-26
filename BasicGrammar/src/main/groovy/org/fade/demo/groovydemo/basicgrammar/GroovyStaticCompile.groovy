package org.fade.demo.groovydemo.basicgrammar

import groovy.transform.CompileStatic

class Computer {
    int compute(String str) {
        str.length()
    }
    String compute(int x) {
        String.valueOf(x)
    }
}

@CompileStatic
void test() {
    def computer = new Computer()
    computer.with {
        assert compute(compute('foobar')) =='6'
    }
}
// 编译期间已完成链接，即使通过元数据编程修改了内容，仍能正常运行
Computer.metaClass.compute = { String str -> new Date() }
test()
