package org.fade.demo.groovydemo.basicgrammar

import groovy.xml.DOMBuilder
import groovy.xml.XmlParser
import groovy.xml.XmlSlurper
import groovy.xml.dom.DOMCategory
import groovy.xml.slurpersupport.GPathResult

/**
*   groovy xml
*
* @author fade
* @date 2021 /12/20
*/
class GroovyXML {

    static void main(String[] args) {
        exampleOfXmlParser()
        exampleOfXmlSlurper()
        exampleOfDOMCategory()
    }

    static void exampleOfXmlParser() {
        def text = '''
                                <list>
                                    <technology>
                                        <name>Groovy</name>
                                    </technology>
                                </list>
                            '''
        def list = new XmlParser().parseText(text)
        assert list instanceof Node
        assert list.technology.name.text() == 'Groovy'
    }

    static void exampleOfXmlSlurper() {
        def text = '''
                                <list>
                                    <technology>
                                        <name>Groovy</name>
                                    </technology>
                                </list>
                            '''
        def list = new XmlSlurper().parseText(text)
        assert list instanceof GPathResult
        assert list.technology.name == 'Groovy'
    }

    static void exampleOfDOMCategory() {
        def CAR_RECORDS = '''
                                        <records>
                                          <car name='HSV Maloo' make='Holden' year='2006'>
                                            <country>Australia</country>
                                            <record type='speed'>Production Pickup Truck with speed of 271kph</record>
                                          </car>
                                          <car name='P50' make='Peel' year='1962'>
                                            <country>Isle of Man</country>
                                            <record type='size'>Smallest Street-Legal Car at 99cm wide and 59 kg in weight</record>
                                          </car>
                                          <car name='Royale' make='Bugatti' year='1931'>
                                            <country>France</country>
                                            <record type='price'>Most Valuable Car at $15 million</record>
                                          </car>
                                        </records>
                                    '''
        def reader = new StringReader(CAR_RECORDS)
        def doc = DOMBuilder.parse(reader)
        def records = doc.documentElement
        use(DOMCategory) {
            assert records.car.size() == 3
        }
    }

}
