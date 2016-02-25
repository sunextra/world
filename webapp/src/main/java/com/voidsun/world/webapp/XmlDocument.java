package com.voidsun.world.webapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by voidsun on 16/2/25.
 */
public class XmlDocument {
    private Map<String, Element> elementMap = new HashMap<>();
    private final String rootQN;

    public XmlDocument(String rootQN){
        this.rootQN = rootQN;
        put(rootQN);
    }

    public Element get(String qName){
        return elementMap.get(qName);
    }

    public Element put(String name){
        Element element = new Element();
        elementMap.put(name, element);
        return element;
    }

    public Element addChild(String childQN){
        return addChild(rootQN, childQN);
    }

    public Element addChild(String parentQN, String childQN){
        Element child = new Element();
        elementMap.get(parentQN).addChild(childQN, child);
        elementMap.put(childQN, child);
        return child;
    }

    public void addAttr(String qn, String attrName){
        elementMap.get(qn).addAttr(attrName);
    }

    public String val(String qName, String attrName){
        return get(qName).val(attrName);
    }

    static class Element{
        Map<String, Element> children = new HashMap<>();
        Map<String, String> attribute = new HashMap<>();

        public Element addChild(String qn, Element child){
            children.put(qn, child);
            return this;
        }

        public void addAttr(String attrName){
            attribute.put(attrName, attrName);
        }

        public Set<String> attrs(){
            return attribute.keySet();
        }

        public void setAttrVal(String attrName, String attrVal){
            attribute.put(attrName, attrVal);
        }

        public String val(String attrName){
            return attribute.get(attrName);
        }
    }
}
