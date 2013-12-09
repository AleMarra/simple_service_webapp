package com.fiuba.taller.communication.wall;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fiuba.taller.communication.CommunicationResponse;
import com.fiuba.taller.communication.wall.requests.EventCreate;
import com.fiuba.taller.communication.wall.requests.EventDelete;
import com.fiuba.taller.communication.wall.requests.EventEdit;
import com.fiuba.taller.communication.wall.requests.EventsSearchByWords;
import com.fiuba.taller.service.SecurityResponse;

import wtp.LoginAPIHelperStub;
import wtp.ServiceStub;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class WallService {
	
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
		
		return elem.getElementsByTagName("success").item(0).getTextContent();
	}
	
	private Response buildError(String service) {
        SecurityResponse response = new SecurityResponse();

        response.setSuccess(false);
        response.setReason("El servicio de " + service + " no está disponible.");

        return Response.status(509).entity(response).build();
	}
	
	
	@GET
	@Path("searcheventsbywords")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchEventsByWords(@CookieParam("authToken") String authToken, EventsSearchByWords request) throws ParserConfigurationException, SAXException, IOException {
		CommWallResponse response = new CommWallResponse();
		
		ServiceStub api = new ServiceStub();
		ServiceStub.BusquedaEventosPorPalabras communicationRequst = new ServiceStub.BusquedaEventosPorPalabras();
		ServiceStub.BusquedaEventosPorPalabrasResponse wsResponse = new ServiceStub.BusquedaEventosPorPalabrasResponse();
		
		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_muro(request.getId_muro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setPalabras(request.getWords());
		
		// Hacer el request
        try {
		    wsResponse = api.busquedaEventosPorPalabras(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("Busqueda eventos por palabras");
        }
		
        // Generate response
		int[] responseData = wsResponse.get_return();
		if (responseData.length == 1 && responseData[0] == -1) {
			response.setSuccess(false);
			response.setReason("Ha ocurrido un error.");
		} else {
			response.setSuccess(true);
			response.setReason("Operación exitosa.");
			response.setEvents(responseData);
		}
		return Response.ok().entity(response).build();
	}	
	
	@GET
	@Path("showwall")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getWall(@CookieParam("authToken") String authToken, @QueryParam("username") String username, @QueryParam("id_ambito") Integer id_ambito,  @QueryParam("id_muro") Integer id_muro,  @QueryParam("pagina") Integer pagina) throws ParserConfigurationException, SAXException, IOException {
		CommWallResponse response = new CommWallResponse();
		
		ServiceStub api = new ServiceStub();
		ServiceStub.MostrarMuro communicationRequst = new ServiceStub.MostrarMuro();
		ServiceStub.MostrarMuroResponse wsResponse = new ServiceStub.MostrarMuroResponse();
		
		// Armo la WSRequest
		communicationRequst.setId_ambito(id_ambito);
		communicationRequst.setId_muro(id_muro);
		communicationRequst.setId_usuario(username);
		communicationRequst.setPagina(pagina);
		
		// Hacer el request
        try {
		    wsResponse = api.mostrarMuro(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("Mostrar muro");
        }
		
        // Generate response
		String responseData = wsResponse.get_return();
		if (responseData == null) {
			response.setSuccess(false);
			response.setReason("Ha ocurrido un error.");
		} else {
			response.setSuccess(true);
			response.setReason("Operación exitosa.");
			response.setWall(responseData);
		}
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("createevent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvent(@CookieParam("authToken") String authToken, EventCreate request) throws ParserConfigurationException, SAXException, IOException {
		CommWallResponse response = new CommWallResponse();
		
		ServiceStub api = new ServiceStub();
		ServiceStub.CrearEvento communicationRequst = new ServiceStub.CrearEvento();
		ServiceStub.CrearEventoResponse wsResponse = new ServiceStub.CrearEventoResponse();
		
		// Armo la WSRequest
		communicationRequst.setContenido(request.getContent());
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_muro(request.getId_muro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setTitulo(request.getTitle());
		
		// Hacer el request
        try {
		    wsResponse = api.crearEvento(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("crear evento");
        }
		
        // Generate response
		int responseData = wsResponse.get_return();
		if (responseData == -1) {
			response.setSuccess(false);
			response.setReason("Ha ocurrido un error.");
		} else {
			response.setSuccess(true);
			response.setReason("Operación exitosa.");
			response.setId_event(responseData);
		}
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("editevent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editEvent(@CookieParam("authToken") String authToken, EventEdit request) throws ParserConfigurationException, SAXException, IOException {
		CommWallResponse response = new CommWallResponse();
		
		// NO EXISTE ESTE SERVICIO
		
		response.setSuccess(true);
		response.setReason("Implementación de prueba, esto se debe implementar");
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("deleteevent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEvent(@CookieParam("authToken") String authToken, EventDelete request) throws ParserConfigurationException, SAXException, IOException {
		CommWallResponse response = new CommWallResponse();
		
		ServiceStub api = new ServiceStub();
		ServiceStub.EliminarEvento communicationRequst = new ServiceStub.EliminarEvento();
		ServiceStub.EliminarEventoResponse wsResponse = new ServiceStub.EliminarEventoResponse();
		
		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_muro(request.getId_muro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_evento(request.getId_evento());

		// Hacer el request
        try {
		    wsResponse = api.eliminarEvento(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar evento");
        }
		
        // Generate response
		int responseData = wsResponse.get_return();
		if (responseData == -1) {
			response.setSuccess(false);
			response.setReason("Ha ocurrido un error.");
		} else {
			response.setSuccess(true);
			response.setReason("Operación exitosa.");
		}
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("hideevent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response hideEvent(@CookieParam("authToken") String authToken, EventDelete request) throws ParserConfigurationException, SAXException, IOException {
		CommWallResponse response = new CommWallResponse();
		
		ServiceStub api = new ServiceStub();
		ServiceStub.OcultarEvento communicationRequst = new ServiceStub.OcultarEvento();
		ServiceStub.OcultarEventoResponse wsResponse = new ServiceStub.OcultarEventoResponse();
		
		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_muro(request.getId_muro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_evento(request.getId_evento());

		// Hacer el request
        try {
		    wsResponse = api.ocultarEvento(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("ocultar evento");
        }
		
        // Generate response
		int responseData = wsResponse.get_return();
		if (responseData == -1) {
			response.setSuccess(false);
			response.setReason("Ha ocurrido un error.");
		} else {
			response.setSuccess(true);
			response.setReason("Operación exitosa.");
		}
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("showevent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response showEvent(@CookieParam("authToken") String authToken, EventDelete request) throws ParserConfigurationException, SAXException, IOException {
		CommWallResponse response = new CommWallResponse();
		
		ServiceStub api = new ServiceStub();
		ServiceStub.MostrarEvento communicationRequst = new ServiceStub.MostrarEvento();
		ServiceStub.MostrarEventoResponse wsResponse = new ServiceStub.MostrarEventoResponse();
		
		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_muro(request.getId_muro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_evento(request.getId_evento());

		// Hacer el request
        try {
		    wsResponse = api.mostrarEvento(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("mostrar evento");
        }
		
        // Generate response
		String responseData = wsResponse.get_return();
		if (responseData == null) {
			response.setSuccess(false);
			response.setReason("Ha ocurrido un error.");
		} else {
			response.setSuccess(true);
			response.setReason("Operación exitosa.");
			response.setEvent(responseData);
		}
		return Response.ok().entity(response).build();
	}
	
	
}