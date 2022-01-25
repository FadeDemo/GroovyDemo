package org.fade.demo.groovydemo.basicgrammar

import java.time.YearMonth

class MyYearMonth {

    int year

    int month

    String toString() {
        year + "-" + String.format("%02d", month)
    }

}

// 感觉离大谱，闭包是允许不定义类型的，但是不定义类型又无法识别
YearMonth.metaClass.asType = { Class target -> {
    if (target == MyYearMonth) {
        println delegate
        assert delegate instanceof YearMonth
        return new MyYearMonth(year: year, month: getMonthValue())
    }
} }

test = YearMonth.of(2022, 1) as MyYearMonth
println test

// as 后面只能使用类的静态引用，如果后面接的是Class对象会报错
clz = Class.forName('org.fade.demo.groovydemo.basicgrammar.MyYearMonth')
// 如果非要使用Class对象 使用asType方法
test = YearMonth.of(2022, 1).asType(clz)
println test