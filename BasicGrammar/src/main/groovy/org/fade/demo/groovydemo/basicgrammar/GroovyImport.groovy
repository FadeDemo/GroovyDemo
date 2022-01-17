package org.fade.demo.groovydemo.basicgrammar

import static java.util.Calendar.getInstance as now
import java.util.Calendar as MyCalendar

/**
*   groovy导入
*
* @author fade
* @date 2022 /01/17
*/
class GroovyImport {

    static void main(String[] args) {
        // 静态导入的别名
        assert now().class == Calendar.getInstance().class
        // 正常导入的别名
        assert MyCalendar.class == Calendar.class
    }

}
