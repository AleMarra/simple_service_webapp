package com.fiuba.taller.materials;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.*;

import com.fiuba.taller.activity.requests.*;

import com.fiuba.taller.materials.requests.GetResourceRequest;
import com.fiuba.taller.materials.requests.GetResourcesListRequest;
import com.fiuba.taller.materials.responses.GetResourceResponse;
import com.fiuba.taller.materials.responses.GetResourcesListResponse;
import com.fiuba.taller.materials.responses.MaterialsResponse;
import com.fiuba.taller.service.SecurityResponse;
import com.fiuba.taller.service.requests.RegisterUserRequest;
import com.fiuba.taller.utils.XmlHandler;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import wtp.LoginAPIHelperStub;
import wtp.activity.fiuba.taller.actividad.*;
import wtp.materials.MaterialsImplServiceStub;
import wtp.materials.MaterialsImplServiceStub.GetRecursos;
import wtp.materials.MaterialsImplServiceStub.GetRecursosE;


@Path("/activityservice")
@Produces(MediaType.APPLICATION_JSON)
public class MaterialsService {

	private static final XmlHandler xmlHandler = new XmlHandler();
	private static final String ATTRIBUTES_KEY = "attributes";
	private static final String PARAMS_KEY = "parametro";

	
	private Response buildError(String service) {
		MaterialsResponse response = new MaterialsResponse();

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


	// Devuelve string vacía si la sesión es inválida
	private String getUsernameFromAuthToken(String authToken) throws IOException, SAXException, ParserConfigurationException {
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
		Document doc = xmlHandler.getDoc(wsResponse.get_return());
		Element node = xmlHandler.getFirstElementWithTag(doc, "response");

		String successString = xmlHandler.getFirstElementValue( node, "success");
		boolean success = successString.equals(TRUE_STRING);

		if (success) {
			username = xmlHandler.getFirstElementValue( node, "username");
		}

		return username;
	}


	
	@SuppressWarnings("unchecked")
	Element appendChildsFromMap(Document doc, Element parent, Map<String,Object> map) 
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

	
	public String materialsRequestBuilder(Map<String, Object> params) 
			throws ParserConfigurationException, TransformerException
	{

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(PARAMS_KEY);
		doc.appendChild(rootElement);

		appendChildsFromMap(doc, rootElement, params);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

		return output;
	}

	// =============================================================================================================
	// ------------------------------------------------ API METHODS ------------------------------------------------
	// =============================================================================================================

	//	GET RECURSOS
	//	<parametro>
	//	<recurso>
	//	<ambitoId>INT idDelAmbiente</ambitoId>
	//	</recurso>
	//	<usuarioId>INT idDelUsuario</usuarioId>
	//	</parametro>
	//
	@POST
	@Path("getresourceslist")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getResourcesList(GetResourcesListRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		System.out.println(request);

		// Init
		
		String username = getUsernameFromAuthToken(token);
		if (username.equals("")) {
			MaterialsResponse response = new MaterialsResponse();
			response.setSuccess(false);
			response.setReason("Usuario no logueado");
			return Response.ok()
					.header("Set-Cookie",
							"authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
							.entity(response).build();
		}

		
		MaterialsImplServiceStub api = new MaterialsImplServiceStub();
		MaterialsImplServiceStub.GetRecursos resourceRequest = new MaterialsImplServiceStub.GetRecursos();
		MaterialsImplServiceStub.GetRecursosE requestEnvelope = new MaterialsImplServiceStub.GetRecursosE(); 
		MaterialsImplServiceStub.GetRecursosResponse wsResponse= new MaterialsImplServiceStub.GetRecursosResponse();

		// Armar el request
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> resource = new HashMap<String, String>();
		
		resource.put("ambitoId", request.getAmbitoId().toString()); 
		
		params.put("recurso", resource);
		params.put("usuarioId", Integer.toString(00001)); //FIXME no hardcodear userId

		resourceRequest.setParametros(materialsRequestBuilder(params));
		requestEnvelope.setGetRecursos(resourceRequest);
		
		
		// Hacer el request
		try {
			wsResponse = api.getRecursos(requestEnvelope).getGetRecursosResponse();
			
		} catch (AxisFault error) {
			System.out.println(error.getReason());
			return buildError("get recursos", error.getReason());
		}

		// Parsear el response
		Document doc = xmlHandler.getDoc(wsResponse.getRecursos());
		Element responseElement = xmlHandler.getFirstElementWithTag(doc, "response");

		String successString = xmlHandler.getFirstElementValue( responseElement, "success");
		boolean success = successString.equals(TRUE_STRING);
		
		GetResourcesListResponse response = new GetResourcesListResponse();
		response.setSuccess(success);

		if (success){
			response.setReason("Recursos obtenidos exitosamente");
			response.setResourcesFromXML(xmlHandler.getFirstElementWithTag(responseElement, "recursos"));
			
		}else{
			response.setReason(xmlHandler.getFirstElementValue(responseElement, "reason"));
		}
		
		
		return Response.ok().entity(response).build();
	}
	
	//GET RECURSO
	//	<parametro>
	//	<recurso>
	//	<recursoId>INT idDelRecurso</recursoId>
	//	<tipo>STRING tipo (ARCHIVO,LINK,ENCUESTA)</tipo>
	//	</recurso>
	//	</parametro>
	//	
	@POST
	@Path("getresource")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getResource(GetResourceRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		System.out.println(request);

		// Init
		String username = getUsernameFromAuthToken(token);
		if (username.equals("")) {
			MaterialsResponse response = new MaterialsResponse();
			response.setSuccess(false);
			response.setReason("Usuario no logueado");
			return Response.ok()
					.header("Set-Cookie",
							"authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
							.entity(response).build();
		}
		
		MaterialsImplServiceStub api = new MaterialsImplServiceStub();
		MaterialsImplServiceStub.GetRecurso resourceRequest = new MaterialsImplServiceStub.GetRecurso();
		MaterialsImplServiceStub.GetRecursoE requestEnvelope = new MaterialsImplServiceStub.GetRecursoE(); 
		MaterialsImplServiceStub.GetRecursoResponse wsResponse= new MaterialsImplServiceStub.GetRecursoResponse();

		// Armar el request
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> resource = new HashMap<String, String>();
		
		resource.put("recursoId", request.getRecursoId().toString());
		resource.put("tipo", request.getTipo());
		
		params.put("recurso", resource);
		params.put("usuarioId", Integer.toString(00001)); //FIXME no hardcodear userId

		resourceRequest.setParametro(materialsRequestBuilder(params));
		requestEnvelope.setGetRecurso(resourceRequest);
		
		
		// Hacer el request
		try {
			wsResponse = api.getRecurso(requestEnvelope).getGetRecursoResponse();
			
		} catch (AxisFault error) {
			System.out.println(error.getReason());
			return buildError("get recurso", error.getReason());
		}

		// Parsear el response
		Document doc = xmlHandler.getDoc(wsResponse.getRecurso());
		Element responseElement = xmlHandler.getFirstElementWithTag(doc, "response");

		String successString = xmlHandler.getFirstElementValue( responseElement, "success");
		boolean success = successString.equals(TRUE_STRING);
		
		//TODO select response depending on the type retrieved.
		response.setSuccess(success);

		if (success){
			response.setReason("Recursos obtenidos exitosamente");
			response.setResourcesFromXML(xmlHandler.getFirstElementWithTag(responseElement, "recursos"));
			
		}else{
			response.setReason(xmlHandler.getFirstElementValue(responseElement, "reason"));
		}
		
		
		return Response.ok().entity(response).build();
	}
	
	//BORRAR RECURSO
	//	<parametro>
	//	<recurso>
	//	<recursoId>INT idDelRecurso</recursoId>
	//	</recurso>
	//	<usuarioId>INT idDelUsuario</usuarioId>
	//	</parametro>
	//
	//GET ENCUESTA RESPONDIDA
	//	<parametro>
	//	<recurso>
	//	<recursoId>INT idDelRecurso</recursoId>
	//	</recurso>
	//	<usuarioId>INT idDelUsuario</usuarioId>
	//	</parametro>
	//	
	//AGREGAR ENCUESTA RESPONDIDA
	//	<parametro>
	//	<encuestaRespondida>
	//	<usuarioId>5</usuarioId>
	//	<recursoId>10</recursoId>
	//	<preguntasRespondidas>
	//	<preguntaRespondidaConOpciones respuestas="2,4"/>
	//	<preguntaRespondidaSinOpciones respuesta="blanco"/>
	//	<preguntaRespondidaConOpciones respuestas="3"/>
	//	</preguntasRespondidas>
	//	</encuestaRespondida>
	//	</parametro>
	//
	//AGREGAR ARCHIVO
	//	<parametro>
	//	<archivo>
	//	<tipo>Archivo</tipo>
	//	<recursoId>14</recursoId>
	//	<descripcion>una clase java</descripcion>
	//	<tipo>class</tipo>
	//	<nombre>Client</nombre>
	//	</archivo>
	//	<usuarioId>5</usuarioId>
	//	</parametro>
	//	
	//AGREGAR RECURSO
	//	<parametro>
	//	<link>
	//	<recursoId>1017</recursoId>
	//	<ambitoId>-1</ambitoId>
	//	<descripcion>alto link</descripcion>
	//	<nombre>www.hola.com</nombre>
	//	</link>
	//	<usuarioId>5</usuarioId>
	//	</parametro>



	//	@POST
	//	@Path("getresourceslist")
	//	@Consumes(MediaType.APPLICATION_JSON)
	//	public Response getResourcesList(GetResourcesListRequest request, @CookieParam("authToken") String token)
	//			throws ParserConfigurationException, SAXException, IOException, TransformerException
	//	{
	//		
	//		// Init
	//		MaterialsResponse response = new MaterialsResponse();
	//        String username = getUsernameFromAuthToken(token);
	//        if (username.equals("")) {
	//            response.setSuccess(false);
	//            response.setReason("Usuario no logueado");
	//            return Response.ok()
	//                    .header("Set-Cookie",
	//                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
	//                    .entity(response).build();
	//        }
	//		
	//		MaterialsImplServiceStub api = new MaterialsImplServiceStub();
	//		
	//		MaterialsImplServiceStub.ObtenerRecursos payload = new MaterialsImplServiceStub.ObtenerRecursos()
	//		
	//		MaterialsImplServiceStub.ObtenerRecursosE obtenerRecursosRequest = new MaterialsImplServiceStub.ObtenerRecursosE();
	//		MaterialsImplServiceStub.ObtenerRecursosResponseE wsResponse = new MaterialsImplServiceStub.ObtenerRecursosResponseE();
	//		
	//		payload.setArg0(request.getAmbientId());
	//		payload.setArg1(request.getUserId());
	//		
	//		obtenerRecursosRequest.setObtenerRecursos(payload);
	//		
	//		boolean success = true;
	//	    String message = "";
	//	        
	//		// Hacer el request
	//        try {
	//		    wsResponse = api.obtenerRecursos(obtenerRecursosRequest); //      	no se que va acá ni que es la implementacion "ObtenerRecursosE"
	//        } catch (ActividadRemoteExceptionException e) {
	//        	success = false;
	//        	message = e.toString();
	//        	
	//        } catch (Exception e) {
	//            System.out.println(e.toString());
	//            return buildError(e.toString());
	//        }
	//
	//      
	//        //  Parsear el response
	//        MaterialsImplServiceStub.ObtenerRecursosResponse resourcesResponse =  wsResponse.getObtenerRecursosResponse();				
	//		response.setSuccess(success);
	//		
	//		if (success){
	//			response.setReason( /* TODO */ );
	//		}else{
	//    		response.setReason(message);
	//		}
	//
	//		return Response.ok().entity(response).build();
	//
	//	}
	//	

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