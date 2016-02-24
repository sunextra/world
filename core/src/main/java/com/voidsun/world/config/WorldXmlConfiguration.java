package com.voidsun.world.config;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by voidsun on 16/2/24.
 */
public class WorldXmlConfiguration extends XmlConfiguration {

    private String path;

    public void load() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(this.getClass().getClassLoader().getResourceAsStream("world.xml"), new WorldXmlConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println(getXPath());

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

}
