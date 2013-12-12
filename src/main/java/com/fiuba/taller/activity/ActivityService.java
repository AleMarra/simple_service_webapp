package com.fiuba.taller.activity;

import java.io.IOException;
import java.io.StringReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

//import com.fiuba.taller.service.requests.EnableAccountRequest;
//import com.fiuba.taller.service.requests.LoginRequest;
//import com.fiuba.taller.service.requests.ChangePasswordRequest;
//import com.fiuba.taller.service.requests.RegisterUserRequest;
import javax.ws.rs.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import wtp.LoginAPIHelperStub;


@Path("/activityservice")
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
    
    // El valor verdadero en String debería estar definido en algún archivo de cosas comunes, y en ese caso
    // no nos hubiera afectado el cambio de "1" como valor verdadero a "true"
    private static String TRUE_STRING = "true";


	@POST
	@Path("algo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response algo()
			throws ParserConfigurationException, SAXException, IOException
	{
		
		// Init
		ActivityResponse response = new ActivityResponse();
		
		// Check if user is loggedIn (this method also returns as the username, that we will need to use)
		// 

		/*LoginAPIHelperStub api = new LoginAPIHelperStub();
		LoginAPIHelperStub.RegisterUser securityRequest = new LoginAPIHelperStub.RegisterUser();
		LoginAPIHelperStub.RegisterUserResponse wsResponse = new LoginAPIHelperStub.RegisterUserResponse();*/

		// Armar el request
		/*securityRequest.setUsername(request.getUsername());*/
		
		// Hacer el request
        /*try {
		    wsResponse = api.registerUser(securityRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("algo");
        }*/

        // Parsear el response
//		Document doc = getDoc(wsResponse.get_return());
//		Node node = getNode(doc, "response");

//        String successString = getFirstElementValue( node, "success");
//        boolean success = successString.equals(TRUE_STRING);
//        response.setSuccess(success);
//
//		if (success){
//			response.setReason("algo exitosamente");
//		}else{
//    		response.setReason(getFirstElementValue(node, "reason"));
//		}

		return Response.ok().entity(response).build();
	}

}
