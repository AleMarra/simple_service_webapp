package com.fiuba.taller.materials;

import com.fiuba.taller.BaseService;
import com.fiuba.taller.materials.MaterialsResource.ResourceTypes;
import com.fiuba.taller.materials.requests.*;
import com.fiuba.taller.materials.responses.MaterialsResponse;
import com.fiuba.taller.materials.responses.MaterialsResponseFactory;
import com.fiuba.taller.utils.XmlHandler;

import static com.fiuba.taller.utils.XmlHandler.PARAMS_KEY;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import wtp.materials.MaterialsImplServiceStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


@Path("/materialsservice")
@Produces(MediaType.APPLICATION_JSON)
public class MaterialsService extends BaseService{

	public static final String dummyOkResponse = "<response>"+
													"<success>true</success>"+
												"</response>";
															
	public static final String dummyRecursos = "<response>" +
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
                                            "</response>";

	public static final String dummyLink = "<response>" +
											"<success>true</success>" +
											"<recurso>" +
												"<recursoId>1002</recursoId>" +
												"<tipo>Link</tipo>"+
												"<ambitoId>-1</ambitoId>"+
												"<descripcion>un link a google copado</descripcion>"+
												"<link>www.google.com.ar</link>"+
											"</recurso>"+
										"</response>";
	
	public static final String dummyEncuesta = "<response>"+
												"<success>true</success>"+
												"<encuesta evaluada='true'>"+
												"<recursoId>11004</recursoId>"+
												"<tipo>Encuesta</tipo>"+
												"<ambitoId>-1</ambitoId>"+
												"<descripcion>una encuesta grande</descripcion>"+
												"<preguntas>"+
												"<preguntaConOpciones multiplesCorrectas='false' correctas='4' idPregunta='1' enunciado='de que color es el caballo blanco de san martin?'>"+
												"<respuestas>rojo,verde,azul,blanco</respuestas>"+
												"</preguntaConOpciones>"+
												"<preguntaConOpciones multiplesCorrectas='true' correctas='1,8,11,12,13,7' idPregunta='2' enunciado='Un test unitario debe presentar las siguientes caractersticas'>"+
												"<respuestas>Rapido,Moldeable,Configurable,Acoplable,Lento,Extensible,Repetible,Profesional,Maduro,Amplio,Simple,Independiente,Automatizable</respuestas>"+
												"</preguntaConOpciones>"+
												"<preguntaSinOpciones correcta='4' idPregunta='5' enunciado='cuantas patas tiene un gato?'/>"+
												"</preguntas>"+
												"</encuesta>"+
											   "</response>";
										
	
	
	private static final XmlHandler xmlHandler = new XmlHandler();
	
	// El valor verdadero en String debería estar definido en algún archivo de cosas comunes, y en ese caso
	// no nos hubiera afectado el cambio de "1" como valor verdadero a "true"
	private static String TRUE_STRING = "true";


	public String materialsRequestBuilder(Map<String, Object> params) 
			throws ParserConfigurationException, TransformerException
	{

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(PARAMS_KEY);
		doc.appendChild(rootElement);

		xmlHandler.appendChildsFromMap(doc, rootElement, params);

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
			MaterialsResponse response = MaterialsResponseFactory.getResourceResponse(ResourceTypes.SIMPLE);
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
		Map<String, String> user = new HashMap<String, String>();
		Map<String, String> resource = new HashMap<String, String>();

		resource.put("ambitoId", request.getAmbitoId().toString());
        user.put("username", username);

        params.put("recurso", resource);
        params.put("Usuario", user);


        System.out.println(" \n "); System.out.println(materialsRequestBuilder(params)); System.out.println(" \n ");

		resourceRequest.setParametros(materialsRequestBuilder(params));
		requestEnvelope.setGetRecursos(resourceRequest);
		
		
		// Hacer el request
		try {
			wsResponse = api.getRecursos(requestEnvelope).getGetRecursosResponse();

		} catch (AxisFault error) {
			System.out.println(error.getReason());
			return buildServiceUnavailable("obtener recursos", error.getReason());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

//		// Parsear el response
		Document doc = xmlHandler.getDoc(wsResponse.getRecursos());
//		Document doc = getDoc(dummyRecursos);
		
		Element responseElement = xmlHandler.getFirstElementWithTag(doc, "response");

		String successString = xmlHandler.getFirstElementValue( responseElement, "success");
		if (successString == null) {
            return buildWrongXmlError("success");
        }
        boolean success = successString.equals(TRUE_STRING);
		
		MaterialsResponse response = MaterialsResponseFactory.getResourceResponse(ResourceTypes.LISTA);
		response.setSuccess(success);

		if (success){
			response.setReason("Recursos obtenidos exitosamente");
			response.setResourcesFromXML(xmlHandler.getFirstElementWithTag(responseElement, "recursos"));
			response.setAuthToken(token);
			
		}else{
			response.setReason(xmlHandler.getFirstElementValue(responseElement, "reason"));
		}
		
		
		return Response.ok().entity(response).build();
	}
	
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
			MaterialsResponse response = MaterialsResponseFactory.getResourceResponse(ResourceTypes.SIMPLE);
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
		Map<String, String> user = new HashMap<String, String>();
		Map<String, String> resource = new HashMap<String, String>();

		resource.put("recursoId", request.getRecursoId().toString());
		resource.put("tipo", request.getTipo());
		user.put("username", username);

		params.put("recurso", resource);
		params.put("Usuario", user);

        System.out.println(" \n "); System.out.println(materialsRequestBuilder(params)); System.out.println(" \n ");

        resourceRequest.setParametro(materialsRequestBuilder(params));
		requestEnvelope.setGetRecurso(resourceRequest);
		
//		// Hacer el request
		try {
			wsResponse = api.getRecurso(requestEnvelope).getGetRecursoResponse();

		} catch (AxisFault error) {
			System.out.println(error.getReason());
			return buildServiceUnavailable("obtener recursos", error.getReason());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

//		// Parsear el response
		Document doc = xmlHandler.getDoc(wsResponse.getRecurso());

//        String dummyInput = ((request.getTipo().toLowerCase().contains("link")) ? dummyLink : dummyEncuesta);
//        Document doc = xmlHandler.getDoc(dummyInput);

		Element responseElement = xmlHandler.getFirstElementWithTag(doc, "response");
		Element successElement = xmlHandler.getFirstElementWithTag(responseElement, "success");
		if (successElement == null) {
			return buildWrongXmlError("success");
		}
		boolean success = successElement.getTextContent().equals(TRUE_STRING);

		MaterialsResponse response = null;
		if (success){
			// Uso suibling porque los nombres de los TAGS cambian, - encuesta, recurso, archivo - 
			response = MaterialsResponseFactory.getResourceResponseFromXml((Element)successElement.getNextSibling());
			
			response.setSuccess(success);
			response.setReason("Recursos obtenidos exitosamente");
			response.setAuthToken(token);

			
		}else{
			response = MaterialsResponseFactory.getResourceResponse(ResourceTypes.SIMPLE);
			response.setSuccess(success);
			response.setReason(xmlHandler.getFirstElementValue(responseElement, "reason"));
		}
		
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("deleteresource")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteResource(DeleteResourceRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		System.out.println(request);

		// Init
		String username = getUsernameFromAuthToken(token);
		if (username.equals("")) {
			MaterialsResponse response = MaterialsResponseFactory.getResourceResponse(ResourceTypes.SIMPLE);
			response.setSuccess(false);
			response.setReason("Usuario no logueado");
			return Response.ok()
					.header("Set-Cookie",
							"authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
							.entity(response).build();
		}
		
		MaterialsImplServiceStub api = new MaterialsImplServiceStub();
		MaterialsImplServiceStub.BorrarRecurso resourceRequest = new MaterialsImplServiceStub.BorrarRecurso();
		MaterialsImplServiceStub.BorrarRecursoE requestEnvelope = new MaterialsImplServiceStub.BorrarRecursoE(); 
		MaterialsImplServiceStub.BorrarRecursoResponse wsResponse= new MaterialsImplServiceStub.BorrarRecursoResponse();

		// Armar el request
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> user = new HashMap<String, String>();
		Map<String, String> resource = new HashMap<String, String>();

		resource.put("recursoId", request.getRecursoId().toString());
		resource.put("tipo", request.getTipo());
		user.put("username", username);

		params.put("recurso", resource);
		params.put("Usuario", user);

        System.out.println(" \n "); System.out.println(materialsRequestBuilder(params)); System.out.println(" \n ");

		resourceRequest.setParametro(materialsRequestBuilder(params));
		requestEnvelope.setBorrarRecurso(resourceRequest);
		
//		// Hacer el request
		try {
			wsResponse = api.borrarRecurso(requestEnvelope).getBorrarRecursoResponse();

		} catch (AxisFault error) {
			System.out.println(error.getReason());
			return buildServiceUnavailable("obtener recursos", error.getReason());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

//		// Parsear el response
		Document doc = xmlHandler.getDoc(wsResponse.getBorrado());
//		Document doc = xmlHandler.getDoc(dummyOkResponse);

		Element responseElement = xmlHandler.getFirstElementWithTag(doc, "response");
		Element successElement = xmlHandler.getFirstElementWithTag(responseElement, "success");
		if (successElement == null) {
			return buildWrongXmlError("success");
		}
		boolean success = successElement.getTextContent().equals(TRUE_STRING);

		MaterialsResponse response = MaterialsResponseFactory.getResourceResponse(ResourceTypes.SIMPLE);
		response.setSuccess(success);
		
		if (success){
			response.setReason("Recursos obtenidos exitosamente");
			response.setAuthToken(token);
			
		}else{
			response.setReason(xmlHandler.getFirstElementValue(responseElement, "reason"));
		}
		
		return Response.ok().entity(response).build();
	}
	
}
