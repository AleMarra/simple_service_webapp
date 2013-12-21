package com.fiuba.taller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.ws.rs.core.Response;

import com.fiuba.taller.service.SecurityResponse;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import wtp.LoginAPIHelperStub;


public class BaseService {

    protected Document getDoc(String xml) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();

        return doc;
    }

    protected Node getNode(Document doc, String nodeName){
        NodeList nodeList = doc.getElementsByTagName(nodeName);
        if (nodeList != null) {
            return nodeList.item(0);
        } else {
            return null;
        }
    }

    protected String getFirstElementValue(Node node, String eName){
        Element elem = (Element) node;
        if (node != null) {
            NodeList nodeList = elem.getElementsByTagName(eName);
            if (nodeList != null) {
                Node firstNode = nodeList.item(0);
                if (firstNode != null) {
                    return firstNode.getTextContent();
                }
            }
        }
        return null;
    }

    protected Response buildServiceUnavailable(String service) {
        BaseResponse response = new BaseResponse();

        response.setSuccess(false);
        response.setReason("El servicio de " + service + " no está disponible.");

        return Response.status(502).entity(response).build();
    }

    protected Response buildServiceUnavailable(String service, String fullReason) {
        BaseResponse response = new BaseResponse();

        response.setSuccess(false);
        response.setReason("El servicio de " + service + " no está disponible.");
        response.setFullReason(fullReason);

        return Response.status(502).entity(response).build();
    }

    protected Response buildWrongXmlError(String expectedNode) {
        BaseResponse response = new BaseResponse();

        response.setSuccess(false);
        response.setReason("No se halló nodo " + expectedNode + " en la respuesta.");

        return Response.status(502).entity(response).build();
    }

    // El valor verdadero en String debería estar definido en algún archivo de cosas comunes, y en ese caso
    // no nos hubiera afectado el cambio de "1" como valor verdadero a "true"
    protected static String TRUE_STRING = "true";


    protected String makeXMLFromMap(String root, HashMap<String,String> map) throws ParserConfigurationException, TransformerException{

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(root);
        doc.appendChild(rootElement);

        Iterator<String> it = map.keySet().iterator();

        while(it.hasNext()){

            String key = it.next();
            String val = map.get(key);

            Element elem = doc.createElement(key);
            elem.appendChild(doc.createTextNode(val));

            rootElement.appendChild(elem);
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

        return output;
    }
    // Devuelve string vacía si la sesión es inválida
    protected String getUsernameFromAuthToken(String authToken) throws IOException, SAXException, ParserConfigurationException {
        if (true) {
            return "juan hardcodeado";
        }
        String username = "";
        LoginAPIHelperStub api = new LoginAPIHelperStub();
        LoginAPIHelperStub.IsTokenValid securityRequest = new LoginAPIHelperStub.IsTokenValid();
        LoginAPIHelperStub.IsTokenValidResponse wsResponse;
        securityRequest.setAuthToken(authToken);

        // Hacer el request
        try {
            wsResponse = api.isTokenValid(securityRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return username;
        }

        // Parsear el response
        Document doc = getDoc(wsResponse.get_return());
        Node node = getNode(doc, "response");

        String successString = getFirstElementValue( node, "success");
        boolean success = successString.equals(TRUE_STRING);

        if (success) {
            username = getFirstElementValue( node, "username");
        }

        return username;
    }
}
