package com.fiuba.taller.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlHandler {

	public static final String TYPE_KEY = "tipo";
	public static final String ATTRIBUTES_KEY = "attributes";
	public static final String PARAMS_KEY = "parametro";

	
	public Document getDoc(String xml) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();

		return doc;
	} 
	
	public Element getFirstElementWithTag(Document doc, String tag){

		return (Element)doc.getElementsByTagName(tag).item(0);
	}

	public Element getFirstElementWithTag(Element elem, String tag){

		return (Element)elem.getElementsByTagName(tag).item(0);
	}

	public String getFirstElementValue(Element elem, String eName){

		return getFirstElementWithTag(elem, eName).getTextContent();
	}
	
	@SuppressWarnings("unchecked")
	public Element appendChildsFromMap(Document doc, Element parent, Map<String,Object> map) 
			throws ParserConfigurationException, TransformerException
	{
		for(String key: map.keySet()){

			if(key.equals(ATTRIBUTES_KEY)){
				setAttributes(parent, (Map<String, String>)map.get(key));
			
			}else{
				Element elem = doc.createElement(key);
	
				if(map.get(key) instanceof Map){
					appendChildsFromMap(doc, elem, (Map<String, Object>)map.get(key));
	
				}else{
					elem.setTextContent((String)map.get(key));
				}
	
				parent.appendChild(elem);
			}
		}
		return parent;
	}
	
	Element setAttributes(Element parent, Map<String, String> attributes){

		for(String attrName: attributes.keySet()){
			parent.setAttribute(attrName, attributes.get(attrName));
		}
		
		return parent;
	}

	
}
