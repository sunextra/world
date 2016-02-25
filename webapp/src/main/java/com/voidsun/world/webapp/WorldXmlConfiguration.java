package com.voidsun.world.webapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import static com.voidsun.world.webapp.Const.*;

/**
 * Created by voidsun on 16/2/24.
 */
public class WorldXmlConfiguration extends XmlConfiguration {

    private XmlDocument config;
    private static WorldXmlConfiguration instance;
    private String path;

    private WorldXmlConfiguration(){
        config = new XmlDocument(QN_WORLD);
        XmlDocument.Element deployElement = config.addChild(QN_DEPLOY);
        deployElement.addAttr(ATTR_DEPLOY_PATH);
    }

    public static WorldXmlConfiguration instance(){
        if(instance != null)return instance;
        synchronized (WorldXmlConfiguration.class){
            if(instance == null){
                instance = new WorldXmlConfiguration();
            }
            return instance;
        }
    }

    public void load() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(this.getClass().getClassLoader().getResourceAsStream("world.xml"), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        path = config.val(QN_DEPLOY, ATTR_DEPLOY_PATH);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        String xPath = getXPath();
        XmlDocument.Element element = config.get(xPath);
        if(element == null) return;
        for(String key : element.attrs()){
            String attrVal = attributes.getValue(key);
            if(attrVal != null){
                element.setAttrVal(key, attrVal);
            }
        }

    }
}
