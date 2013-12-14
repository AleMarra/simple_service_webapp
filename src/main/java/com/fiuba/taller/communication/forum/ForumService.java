package com.fiuba.taller.communication.forum;

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

import com.fiuba.taller.communication.forum.requests.ForumCreateRequest;
import com.fiuba.taller.communication.forum.requests.ForumDeleteRequest;
import com.fiuba.taller.communication.forum.requests.ForumEditRequest;
import com.fiuba.taller.communication.forum.requests.MessageSearchByWordRequest;
import com.fiuba.taller.communication.forum.requests.MessageSearchByUserRequest;
import com.fiuba.taller.communication.forum.requests.MessageCreateRequest;
import com.fiuba.taller.communication.forum.requests.MessageDeleteRequest;
import com.fiuba.taller.communication.forum.requests.MessageEditRequest;
import com.fiuba.taller.communication.forum.requests.SubForumCreateRequest;
import com.fiuba.taller.communication.forum.requests.SubForumDeleteRequest;
import com.fiuba.taller.communication.forum.requests.SubForumEditRequest;
import com.fiuba.taller.communication.forum.requests.SubForumMoveRequest;
import com.fiuba.taller.communication.forum.requests.ThreadCreateRequest;
import com.fiuba.taller.communication.forum.requests.ThreadDeleteRequest;
import com.fiuba.taller.communication.forum.requests.ThreadEditRequest;
import com.fiuba.taller.service.SecurityResponse;

import wtp.LoginAPIHelperStub;
import wtp.MessagerStub;
import wtp.ServiceStub;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ForumService {

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

	@POST
	@Path("createsection")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSection(@CookieParam("authToken") String authToken, ForumCreateRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.CrearSeccion communicationRequst = new ServiceStub.CrearSeccion();
		ServiceStub.CrearSeccionResponse wsResponse = new ServiceStub.CrearSeccionResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setNombre_seccion(request.getSection_name());

		// Hacer el request
        try {
		    wsResponse = api.crearSeccion(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("crear seccion");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setIdSection(responseData);
        }

		return Response.ok().entity(response).build();
	}


	@POST
	@Path("editsection")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editSection(@CookieParam("authToken") String authToken, ForumEditRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EditarSeccion communicationRequst = new ServiceStub.EditarSeccion();
		ServiceStub.EditarSeccionResponse wsResponse = new ServiceStub.EditarSeccionResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_seccion(request.getId_seccion());
		communicationRequst.setId_usuario(request.getUsername());	// waaaaat?
		communicationRequst.setNombre_seccion(request.getSection_name());

		// Hacer el request
        try {
		    wsResponse = api.editarSeccion(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("editar seccion");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("deletesection")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSection(@CookieParam("authToken") String authToken, ForumDeleteRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EliminarSeccion communicationRequst = new ServiceStub.EliminarSeccion();
		ServiceStub.EliminarSeccionResponse wsResponse = new ServiceStub.EliminarSeccionResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_seccion(request.getId_seccion());
		communicationRequst.setId_usuario(request.getUsername());

		// Hacer el request
        try {
		    wsResponse = api.eliminarSeccion(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar seccion");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}


	@POST
	@Path("createsubforum")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSubforum(@CookieParam("authToken") String authToken, SubForumCreateRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.CrearSubforo communicationRequst = new ServiceStub.CrearSubforo();
		ServiceStub.CrearSubforoResponse wsResponse = new ServiceStub.CrearSubforoResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_padre(request.getId_contenedor());
		communicationRequst.setNombre_subforo(request.getSubforum_name());

		// Hacer el request
        try {
		    wsResponse = api.crearSubforo(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("create subforo");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setIdSubForum(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("editsubforum")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editSubforum(@CookieParam("authToken") String authToken, SubForumEditRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EditarSubforo communicationRequst = new ServiceStub.EditarSubforo();
		ServiceStub.EditarSubforoResponse wsResponse = new ServiceStub.EditarSubforoResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setNombre_subforo(request.getSubforum_name());
		communicationRequst.setId_subforo(request.getId_subforo());

		// Hacer el request
        try {
		    wsResponse = api.editarSubforo(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("editar subforo");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("movesubforum")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response moveSubforum(@CookieParam("authToken") String authToken, SubForumMoveRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.MoverSubforo communicationRequst = new ServiceStub.MoverSubforo();
		ServiceStub.MoverSubforoResponse wsResponse = new ServiceStub.MoverSubforoResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_subforo(request.getId_subforo());
		communicationRequst.setId_contenedor_nuevo(request.getId_contenedor_nuevo());
		communicationRequst.setId_contenedor_viejo(request.getId_contenedor_viejo());

		// Hacer el request
        try {
		    wsResponse = api.moverSubforo(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("mover subforo");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("deletesubforum")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSubforum(@CookieParam("authToken") String authToken, SubForumDeleteRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EliminarSubforo communicationRequst = new ServiceStub.EliminarSubforo();
		ServiceStub.EliminarSubforoResponse wsResponse = new ServiceStub.EliminarSubforoResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_subforo(request.getId_subforo());
		communicationRequst.setId_contenedor(request.getId_contenedor());

		// Hacer el request
        try {
		    wsResponse = api.eliminarSubforo(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar subforo");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("createthread")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createThread(@CookieParam("authToken") String authToken, ThreadCreateRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.CrearTema communicationRequst = new ServiceStub.CrearTema();
		ServiceStub.CrearTemaResponse wsResponse = new ServiceStub.CrearTemaResponse();

		// Armo la WSRequest
		communicationRequst.setContenido(request.getContent());
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_subforo(request.getId_subforo());
		communicationRequst.setTitulo(request.getTitle());

		// Hacer el request
        try {
		    wsResponse = api.crearTema(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("creat tema");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setIdThread(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("editthread")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editThread(@CookieParam("authToken") String authToken,ThreadEditRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EditarTema communicationRequst = new ServiceStub.EditarTema();
		ServiceStub.EditarTemaResponse wsResponse = new ServiceStub.EditarTemaResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_tema(request.getId_tema());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setTitulo(request.getTitle());

		// Hacer el request
        try {
		    wsResponse = api.editarTema(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("editar tema");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("deletethread")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteThread(@CookieParam("authToken") String authToken, ThreadDeleteRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EliminarTema communicationRequst = new ServiceStub.EliminarTema();
		ServiceStub.EliminarTemaResponse wsResponse = new ServiceStub.EliminarTemaResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_tema(request.getId_tema());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_subforo(request.getId_subforo());

		// Hacer el request
        try {
		    wsResponse = api.eliminarTema(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar tema");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("stickthread")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response stickThread(@CookieParam("authToken") String authToken, ThreadDeleteRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.DestacarTema communicationRequst = new ServiceStub.DestacarTema();
		ServiceStub.DestacarTemaResponse wsResponse = new ServiceStub.DestacarTemaResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_tema(request.getId_tema());
		communicationRequst.setId_usuario(request.getUsername());

		// Hacer el request
        try {
		    wsResponse = api.destacarTema(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("destacar tema");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("unstickthread")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unstickThread(@CookieParam("authToken") String authToken, ThreadDeleteRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.CancelarDestacarTema communicationRequst = new ServiceStub.CancelarDestacarTema();
		ServiceStub.CancelarDestacarTemaResponse wsResponse = new ServiceStub.CancelarDestacarTemaResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_tema(request.getId_tema());
		communicationRequst.setId_usuario(request.getUsername());

		// Hacer el request
        try {
		    wsResponse = api.cancelarDestacarTema(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("cancelar destacar tema");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("createmessage")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMessage(@CookieParam("authToken") String authToken, MessageCreateRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.CrearMensaje communicationRequst = new ServiceStub.CrearMensaje();
		ServiceStub.CrearMensajeResponse wsResponse = new ServiceStub.CrearMensajeResponse();

		// Armo la WSRequest
		communicationRequst.setContenido(request.getContent());
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_tema(request.getId_tema());
		communicationRequst.setId_usuario(request.getUsername());

		// Hacer el request
        try {
		    wsResponse = api.crearMensaje(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("crear mensaje");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setIdMessage(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("editmessage")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editMessage(@CookieParam("authToken") String authToken, MessageEditRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EditarMensaje communicationRequst = new ServiceStub.EditarMensaje();
		ServiceStub.EditarMensajeResponse wsResponse = new ServiceStub.EditarMensajeResponse();

		// Armo la WSRequest
		communicationRequst.setContenido(request.getContent());
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_mensaje(request.getId_mensaje());
		communicationRequst.setId_tema(request.getId_tema());
		communicationRequst.setId_usuario(request.getUsername());

		// Hacer el request
        try {
		    wsResponse = api.editarMensaje(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("editar mensaje");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("deletemessage")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteMessage(@CookieParam("authToken") String authToken, MessageDeleteRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.EliminarMensaje communicationRequst = new ServiceStub.EliminarMensaje();
		ServiceStub.EliminarMensajeResponse wsResponse = new ServiceStub.EliminarMensajeResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_mensaje(request.getId_mensaje());
		communicationRequst.setId_tema(request.getId_tema());
		communicationRequst.setId_usuario(request.getUsername());

		// Hacer el request
        try {
		    wsResponse = api.eliminarMensaje(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar mensaje");
        }

        // Generate response
        int responseData = wsResponse.get_return();
        if (responseData == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        }

		return Response.ok().entity(response).build();
	}

	@GET
	@Path("forumindex")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getForumIndex(@CookieParam("authToken") String authToken, @QueryParam("username") String username, @QueryParam("id_ambito") Integer id_ambito, @QueryParam("id_foro") Integer id_foro) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.MostrarIndice communicationRequst = new ServiceStub.MostrarIndice();
		ServiceStub.MostrarIndiceResponse wsResponse = new ServiceStub.MostrarIndiceResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(id_ambito);
		communicationRequst.setId_foro(id_foro);
		communicationRequst.setId_usuario(username);

		// Hacer el request
        try {
		    wsResponse = api.mostrarIndice(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("mostrar indice");
        }

        // Generate response
        String responseData = wsResponse.get_return();
        if (responseData == null) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setIndex(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@GET
	@Path("subforumindex")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getSubforumIndex(@CookieParam("authToken") String authToken, @QueryParam("username") String username, @QueryParam("id_ambito") Integer id_ambito, @QueryParam("id_foro") Integer id_foro, @QueryParam("id_subforo") Integer id_subforo, @QueryParam("pagina") Integer pagina) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.MostrarSubForo communicationRequst = new ServiceStub.MostrarSubForo();
		ServiceStub.MostrarSubForoResponse wsResponse = new ServiceStub.MostrarSubForoResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(id_ambito);
		communicationRequst.setId_foro(id_foro);
		communicationRequst.setId_subforo(id_subforo);
		communicationRequst.setId_usuario(username);
		communicationRequst.setPagina(pagina);

		// Hacer el request
        try {
		    wsResponse = api.mostrarSubForo(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("mostrar subforo");
        }

        // Generate response
        String responseData = wsResponse.get_return();
        if (responseData == null) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setSubForum(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@GET
	@Path("threadindex")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getThreadIndex(@CookieParam("authToken") String authToken,  @QueryParam("username") String username, @QueryParam("id_ambito") Integer id_ambito, @QueryParam("id_foro") Integer id_foro, @QueryParam("id_tema") Integer id_tema, @QueryParam("pagina") Integer pagina) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.MostrarTema communicationRequst = new ServiceStub.MostrarTema();
		ServiceStub.MostrarTemaResponse wsResponse = new ServiceStub.MostrarTemaResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(id_ambito);
		communicationRequst.setId_foro(id_foro);
		communicationRequst.setId_tema(id_tema);
		communicationRequst.setId_usuario(username);
		communicationRequst.setPagina(pagina);

		// Hacer el request
        try {
		    wsResponse = api.mostrarTema(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("mostrar tema");
        }

        // Generate response
        String responseData = wsResponse.get_return();
        if (responseData == null) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setThread(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@GET
	@Path("message")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMessage(@CookieParam("authToken") String authToken, @QueryParam("username") String username, @QueryParam("id_ambito") Integer id_ambito, @QueryParam("id_foro") Integer id_foro, @QueryParam("id_tema") Integer id_tema, @QueryParam("id_mensaje") Integer id_mensaje) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.MostrarMensaje communicationRequst = new ServiceStub.MostrarMensaje();
		ServiceStub.MostrarMensajeResponse wsResponse = new ServiceStub.MostrarMensajeResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(id_ambito);
		communicationRequst.setId_foro(id_foro);
		communicationRequst.setId_mensaje(id_mensaje);
		communicationRequst.setId_tema(id_tema);
		communicationRequst.setId_usuario(username);

		// Hacer el request
        try {
		    wsResponse = api.mostrarMensaje(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("mostrar mensaje");
        }

        // Generate response
        String responseData = wsResponse.get_return();
        if (responseData == null) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operacion exitosa");
        	response.setMessage(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@GET
	@Path("searchmessagebyword")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchMessageByWords(@CookieParam("authToken") String authToken, MessageSearchByWordRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.BusquedaMensajesPorPalabra communicationRequst = new ServiceStub.BusquedaMensajesPorPalabra();
		ServiceStub.BusquedaMensajesPorPalabraResponse wsResponse = new ServiceStub.BusquedaMensajesPorPalabraResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_subforo(request.getSubforos());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setPalabra(request.getWords());

		// Hacer el request
        try {
		    wsResponse = api.busquedaMensajesPorPalabra(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("busqueda mensajes por palabra");
        }

        // Generate response
        int[] responseData = wsResponse.get_return();
        if (responseData.length == 1 && responseData[0] == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error.");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operación exitosa.");
        	response.setMessages(responseData);
        }

		return Response.ok().entity(response).build();
	}

	@GET
	@Path("searchmessagebyuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchMessageByUser(@CookieParam("authToken") String authToken, MessageSearchByUserRequest request) throws ParserConfigurationException, SAXException, IOException {
		CommForumResponse response = new CommForumResponse();

		ServiceStub api = new ServiceStub();
		ServiceStub.BusquedaMensajesPorAutor communicationRequst = new ServiceStub.BusquedaMensajesPorAutor();
		ServiceStub.BusquedaMensajesPorAutorResponse wsResponse = new ServiceStub.BusquedaMensajesPorAutorResponse();

		// Armo la WSRequest
		communicationRequst.setId_ambito(request.getId_ambito());
		communicationRequst.setId_foro(request.getId_foro());
		communicationRequst.setId_subforo(request.getSubforos());
		communicationRequst.setId_usuario(request.getUsername());
		communicationRequst.setId_usuario_autor(request.getAutor());

		// Hacer el request
        try {
		    wsResponse = api.busquedaMensajesPorAutor(communicationRequst);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("busqueda mensajes por autor");
        }

        // Generate response
        int[] responseData = wsResponse.get_return();
        if (responseData.length == 1 && responseData[0] == -1) {
        	response.setSuccess(false);
        	response.setReason("Ha ocurrido un error.");
        } else {
        	response.setSuccess(true);
        	response.setReason("Operación exitosa.");
        	response.setMessages(responseData);
        }

		return Response.ok().entity(response).build();
	}

}