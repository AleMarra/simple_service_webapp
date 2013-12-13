package com.fiuba.taller.activity;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
//import com.fiuba.taller.service.requests.EnableAccountRequest;
//import com.fiuba.taller.service.requests.LoginRequest;
//import com.fiuba.taller.service.requests.ChangePasswordRequest;
//import com.fiuba.taller.service.requests.RegisterUserRequest;
import javax.ws.rs.*;

import com.fiuba.taller.activity.requests.*;

import com.fiuba.taller.service.SecurityResponse;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import wtp.LoginAPIHelperStub;
import wtp.activity.src.fiuba.taller.actividad.*;


@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityService {

	private Document getDoc(String xml) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();
		
		return doc;
	} 
	
	private Node getNode(Document doc, String nodeName){
		
		return doc.getElementsByTagName(nodeName).item(0);
	}
	
	private String getFirstElementValue(Node node, String eName){
		
		Element elem = (Element) node;
		
		return elem.getElementsByTagName(eName).item(0).getTextContent();
	}

    private Response buildError(String service) {
        ActivityResponse response = new ActivityResponse ();

        response.setSuccess(false);
        response.setReason("El servicio de " + service + " no está disponible.");

        return Response.status(502).entity(response).build();
    }

    private Response buildError(String service, String fullReason) {
        SecurityResponse response = new SecurityResponse();

        response.setSuccess(false);
        response.setReason("El servicio de " + service + " no está disponible.");
        response.setFullReason(fullReason);

        return Response.status(502).entity(response).build();
    }

    // El valor verdadero en String debería estar definido en algún archivo de cosas comunes, y en ese caso
    // no nos hubiera afectado el cambio de "1" como valor verdadero a "true"
    private static String TRUE_STRING = "true";


    String makeXMLFromMap(String root, HashMap<String,String> map) throws ParserConfigurationException, TransformerException{
    	
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
    private String getUsernameFromAuthToken(String authToken) throws IOException, SAXException, ParserConfigurationException {
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

    
    // ------------------------------------------------ API METHODS ------------------------------------------------
	@POST
	@Path("creategroupactivity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGroupActivity(CreateGroupActivityRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		
		// Init
		ActivityResponse response = new ActivityResponse();
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadGrupal crearActividadRequest = new ActividadStub.CrearActividadGrupal();
		ActividadStub.CrearActividadGrupalResponse wsResponse = new ActividadStub.CrearActividadGrupalResponse();
		
		crearActividadRequest.setUsername(getUsernameFromAuthToken(token));
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadGrupal(crearActividadRequest);
        } catch (ActividadXmlErroneoExcepcionException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildError(e.toString());
        }

      
        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setReason(Integer.toString((int)activityID));
		}else{
    		response.setReason(message);
		}

		return Response.ok().entity(response).build();
		  

	}
	
	
	@POST
	@Path("creategroupevaluableactivity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGroupEvaluableActivity(CreateGroupEvaluableActivityRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		
		// Init
		ActivityResponse response = new ActivityResponse();
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadGrupalEvaluable crearActividadRequest = new ActividadStub.CrearActividadGrupalEvaluable();
		ActividadStub.CrearActividadGrupalEvaluableResponse wsResponse = new ActividadStub.CrearActividadGrupalEvaluableResponse();
		
		crearActividadRequest.setUsername(getUsernameFromAuthToken(token));
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadGrupalEvaluable(crearActividadRequest);
        } catch (ActividadXmlErroneoExcepcionException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildError(e.toString());
        }

        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setReason(Integer.toString((int)activityID));
		}else{
    		response.setReason(message);
		}

		return Response.ok().entity(response).build();
		
	}
	
	@POST
	@Path("createindividualactivity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createIndividualActivity(CreateIndividualActivityRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		// Init
		ActivityResponse response = new ActivityResponse();
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadIndividual crearActividadRequest = new ActividadStub.CrearActividadIndividual();
		ActividadStub.CrearActividadIndividualResponse wsResponse = new ActividadStub.CrearActividadIndividualResponse();
		
		crearActividadRequest.setUsername(getUsernameFromAuthToken(token));
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadIndividual(crearActividadRequest);
        } catch (ActividadXmlErroneoExcepcionException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildError(e.toString());
        }

        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setReason(Integer.toString((int)activityID));
		}else{
    		response.setReason(message);
		}

		return Response.ok().entity(response).build();

	}
	
	@POST
	@Path("createindividualevaluableactivity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createIndividualEvaluableActivity(CreateIndividualEvaluableActivityRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
	
		// Init
		ActivityResponse response = new ActivityResponse();
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadIndividualEvaluable crearActividadRequest = new ActividadStub.CrearActividadIndividualEvaluable();
		ActividadStub.CrearActividadIndividualEvaluableResponse wsResponse = new ActividadStub.CrearActividadIndividualEvaluableResponse();
		
		crearActividadRequest.setUsername(getUsernameFromAuthToken(token));
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadIndividualEvaluable(crearActividadRequest);
        } catch (ActividadXmlErroneoExcepcionException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildError(e.toString());
        }

        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setReason(Integer.toString((int)activityID));
		}else{
    		response.setReason(message);
		}

		return Response.ok().entity(response).build();
		
	}
	
	
	@POST
	@Path("getproperties/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getActivityProperties(@PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		
		return Response.ok().build();
	}
	
	@POST
	@Path("setproperties/{id}") //TODO /ID
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setActivityProperties(@PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		return Response.ok().build();
	}
	
}