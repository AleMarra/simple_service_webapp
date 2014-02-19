package com.fiuba.taller.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlHandler {

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

	
}
