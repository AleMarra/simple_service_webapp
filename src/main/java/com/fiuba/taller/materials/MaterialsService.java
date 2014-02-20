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
import com.fiuba.taller.materials.MaterialsResource.ResourceTypes;
import com.fiuba.taller.materials.responses.GetResourcesListResponse;
import com.fiuba.taller.materials.responses.MaterialsResponse;
import com.fiuba.taller.materials.responses.MaterialsResponseFactory;

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
import com.fiuba.taller.service.SecurityResponse;
import com.fiuba.taller.service.requests.RegisterUserRequest;
import com.fiuba.taller.utils.XmlHandler;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.fiuba.taller.materials.requests.AddLinkRequest;
import com.fiuba.taller.materials.requests.AddPollRequest;
import org.apache.axis2.AxisFault;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import wtp.activity.fiuba.taller.actividad.*;
import wtp.materials.MaterialsImplServiceStub;
import wtp.materials.MaterialsImplServiceStub.GetRecursos;
import wtp.materials.MaterialsImplServiceStub.GetRecursosE;
import wtp.loginapihelper.wtp.LoginAPIHelperStub;


@Path("/materialsservice")
@Produces(MediaType.APPLICATION_JSON)
public class MaterialsService extends BaseService{

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
		Element rootElement = doc.createElement(xmlHandler.PARAMS_KEY);
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
		Map<String, String> resource = new HashMap<String, String>();
		
		resource.put("ambitoId", request.getAmbitoId().toString()); 
		
		params.put("recurso", resource);
		params.put("usuarioId", Integer.toString(00001)); //FIXME no hardcodear userId

		System.out.println(materialsRequestBuilder(params));
		resourceRequest.setParametros(materialsRequestBuilder(params));
		requestEnvelope.setGetRecursos(resourceRequest);
		
		
		// Hacer el request
//		try {
//			wsResponse = api.getRecursos(requestEnvelope).getGetRecursosResponse();
//			
//		} catch (AxisFault error) {
//			System.out.println(error.getReason());
//			return buildServiceUnavailable("obtener recursos", error.getReason());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		// Parsear el response
//		Document doc = xmlHandler.getDoc(wsResponse.getRecursos());
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
			
		}else{
			response.setReason(xmlHandler.getFirstElementValue(responseElement, "reason"));
		}
		
		
		return Response.ok().entity(response).build();
		
		/*
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
	*/
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
		Map<String, String> resource = new HashMap<String, String>();
		
		resource.put("recursoId", request.getRecursoId().toString());
		resource.put("tipo", request.getTipo());
		
		params.put("recurso", resource);
		params.put("usuarioId", Integer.toString(00001)); //FIXME no hardcodear userId

		System.out.println(materialsRequestBuilder(params));
		resourceRequest.setParametro(materialsRequestBuilder(params));
		requestEnvelope.setGetRecurso(resourceRequest);
		
//		// Hacer el request
//		try {
//			wsResponse = api.getRecurso(requestEnvelope).getGetRecursoResponse();
//			
//		} catch (AxisFault error) {
//			System.out.println(error.getReason());
//			return buildServiceUnavailable("obtener recursos", error.getReason());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		// Parsear el response
//		Document doc = xmlHandler.getDoc(wsResponse.getRecurso());
		
//		Document doc = xmlHandler.getDoc(
//										"<response>" +
//											"<success>true</success>" +
//											"<recurso>" +
//												"<recursoId>1002</recursoId>" +
//												"<tipo>Link</tipo>"+
//												"<ambitoId>-1</ambitoId>"+
//												"<descripcion>un link a google copado</descripcion>"+
//												"<link>www.google.com.ar</link>"+
//											"</recurso>"+
//										"</response>");
		Document doc = xmlHandler.getDoc(
				"<response>"+
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
				"</response>"
				);
				
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
			
		}else{
			response = MaterialsResponseFactory.getResourceResponse(ResourceTypes.SIMPLE);
			response.setSuccess(success);
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
