package org.fade.demo.groovydemo.basicgrammar
/**
*   groovy io 操作
*
* @author fade
* @since 2021 /12/04
*/
class IO {

    private static final File file = new File(IO.class.classLoader.getResource("IOExample.txt").getFile())

    static void main(String[] args) {
        println "**************readFile**************"
        readFile()
        println "**************readFileText**************"
        readFileText()
        println "**************writeFile**************"
        writeFile()
        println "**************getFileSize**************"
        getFileSize()
        println "**************isFileDirectory**************"
        isFileDirectory()
        println "**************createDirectory**************"
        createDirectory()
        println "**************copyFile**************"
        copyFile()
        println "**************javaIO**************"
        javaIO()
        println "**************deleteFile**************"
        deleteFile()
        println "**************getDirectoryContent**************"
        getDirectoryContent()
    }

    static void readFile() {
        file.eachLine {
            content -> println "$content"
        }
    }

    static void readFileText() {
        println file.text
    }

    static void writeFile() {
        file.withWriterAppend("utf-8") {
            writer -> writer.writeLine("Content wrote by Groovy feature")
        }
    }

    static void getFileSize() {
        println "file's size is ${file.length()}"
    }

    static void isFileDirectory() {
        println "file is directory: ${file.isDirectory()}"
    }

    static void createDirectory() {
        File dir = new File(IO.class.classLoader.getResource("").getFile() + "test")
        dir.mkdir()
    }

    static void deleteFile() {
        file.delete()
    }

    static void copyFile() {
        File copy = new File(IO.class.classLoader.getResource("").getFile() + "test/IOExample.txt")
        copy << file.text
    }

    static void getDirectoryContent() {
        File dir = new File("/")
        dir.eachFile {
            f -> println f.getAbsolutePath()
        }
    }

    static void javaIO() {
        FileInputStream fi = new FileInputStream(file)
        FileOutputStream fo = new FileOutputStream(IO.class.classLoader.getResource("test").getFile() + "/JavaIO.txt")
        BufferedInputStream bi = new BufferedInputStream(fi)
        BufferedOutputStream bo = new BufferedOutputStream(fo)
        byte[] buffer = new byte[1024]
        int length
        while ((length = bi.read(buffer)) >= 0) {
            bo.write(buffer, 0, length)
            bo.flush()
        }
        bo.close()
        bi.close()
    }

}
