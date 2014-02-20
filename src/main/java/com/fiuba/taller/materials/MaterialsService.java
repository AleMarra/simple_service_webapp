package com.fiuba.taller.materials;

import javax.ws.rs.ext.ExceptionMapper;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.fiuba.taller.BaseService;
import com.fiuba.taller.materials.responses.GetResourcesListResponse;
import com.fiuba.taller.materials.responses.MaterialsResponse;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

import com.fiuba.taller.activity.requests.*;

//import com.fiuba.taller.materials.requests.AddFileRequest;
import com.fiuba.taller.materials.requests.AddLinkRequest;
import com.fiuba.taller.materials.requests.AddPollRequest;
import com.fiuba.taller.materials.requests.GetResourceRequest;
import com.fiuba.taller.materials.requests.GetResourcesListRequest;
import com.fiuba.taller.service.SecurityResponse;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import wtp.loginapihelper.wtp.LoginAPIHelperStub;
import wtp.activity.fiuba.taller.actividad.*;
import wtp.materials.MaterialsImplServiceStub;


@Path("/materialsservice")
@Produces(MediaType.APPLICATION_JSON)
public class MaterialsService extends BaseService {

    // ------------------------------------------------ API METHODS ------------------------------------------------
	@POST
	@Path("getresourceslist")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getResourcesList(GetResourcesListRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		
		// Init
        GetResourcesListResponse response = new GetResourcesListResponse();
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }

		MaterialsImplServiceStub api = new MaterialsImplServiceStub();

//        GetRecursosParams payload = new GetRecursosParams();
        MaterialsImplServiceStub.GetRecursos getRecursosRequest = new MaterialsImplServiceStub.GetRecursos();
        MaterialsImplServiceStub.GetRecursosE getRecursosERequest = new MaterialsImplServiceStub.GetRecursosE();
        MaterialsImplServiceStub.GetRecursosResponseE wsResponseE = new MaterialsImplServiceStub.GetRecursosResponseE();
        MaterialsImplServiceStub.GetRecursosResponse wsResponse = new MaterialsImplServiceStub.GetRecursosResponse();

//        getRecursosRequest.setParametros(payload.toString());
        getRecursosRequest.setParametros("" +
                "<parametro>" +
                    "<recurso>" +
                        "<ambitoId>3</ambitoId>" +
                    "</recurso>" +
                    "<usuarioId>2</usuarioId>" +
                "</parametro>");
        getRecursosERequest.setGetRecursos(getRecursosRequest);

		boolean success = true;
	    String message = "";
	        
//		// Hacer el request
//        try {
//            // Las clases 'E' wrappean las excepciones controladas, como poner los parámetros mal
//            wsResponseE = api.getRecursos(getRecursosERequest);
//        } catch (AxisFault error) {
//            System.out.println(error.getReason());
//            return buildServiceUnavailable("obtener recursos", error.getReason());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        //  Parsear el response
//        wsResponse =  wsResponseE.getGetRecursosResponse();

        // Parsear el response
//        Document doc = getDoc(wsResponse.getRecursos());
        Document doc = getDoc("" +
                "<response>" +
                    "<success>true</success>" +
                    "<recursos>" +
                        "<recurso>" +
                            "<recursoId>11002</recursoId>" +
                            "<tipo>Link</tipo>" +
                            "<ambitoId>-1</ambitoId>" +
                            "<descripcion>un link a google copado</descripcion>" +
                        "</recurso>" +
                        "<recurso>" +
                            "<recursoId>11003</recursoId>" +
                            "<tipo>Encuesta</tipo>" +
                            "<ambitoId>-1</ambitoId>" +
                            "<descripcion>una encuesta chica</descripcion>" +
                        "</recurso>" +
                        "<recurso>" +
                            "<recursoId>11004</recursoId>" +
                            "<tipo>Encuesta</tipo>" +
                            "<ambitoId>-1</ambitoId>" +
                            "<descripcion>una encuesta grande</descripcion>" +
                        "</recurso>" +
                    "</recursos>" +
                "</response>");
        Node node = getNode(doc, "response");

        String successString = getFirstElementValue( node, "success");

        if (successString == null) {
            return buildWrongXmlError("success");
        }
        success = successString.equals(TRUE_STRING);
        response.setSuccess(success);

        if (success) {
            response.setResourcesFromXML((Element) getNode(doc, "recursos"));
        } else {
            response.setReason(getFirstElementValue(node, "reason"));
        }

		return Response.ok().entity(response).build();

	}
	
	
	@POST
	@Path("addpoll")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPoll(AddPollRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		
		return null;
	
	}
	
	@POST
	@Path("addlink")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLink(AddLinkRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		
		return null;
	
	}
	
	
}
