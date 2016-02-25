package com.voidsun.world.webapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;

/**
 * Created by voidsun on 16/2/24.
 */
public abstract class XmlConfiguration extends DefaultHandler{
    public abstract void load();
    LinkedList<String> domPath = new LinkedList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        domPath.add(qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        domPath.pollLast();
    }

    protected String getXPath(){
        StringBuilder builder = new StringBuilder();
        String split = "";
        for(String path : domPath){
            builder.append(split).append(path);
            split = ".";
        }
        return builder.toString();
    }
}
