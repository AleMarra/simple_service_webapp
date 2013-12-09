package com.fiuba.taller.chat;

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

import com.fiuba.taller.chat.requests.CreateChatRequest;
import com.fiuba.taller.chat.requests.HistoryMessageRequest;
import com.fiuba.taller.chat.requests.SendMessageRequest;
import com.fiuba.taller.communication.CommunicationResponse;
import com.fiuba.taller.service.SecurityResponse;

import wtp.LoginAPIHelperStub;
import wtp.MessagerStub;
import wtp.MessagerStub.Chat;
import wtp.MessagerStub.GetHistoryChatResponse;
import wtp.MessagerStub.MensajeChat;
import wtp.MessagerStub.Miembros;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ChatService {
	
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
	@Path("createchat")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createChat(@CookieParam("authToken") String authToken, CreateChatRequest request) throws ParserConfigurationException, SAXException, IOException {
		//Init 
		CreateChatResponse response = new CreateChatResponse();
		
		MessagerStub api = new MessagerStub();
		MessagerStub.CreateChat chatRequest = new MessagerStub.CreateChat();
		MessagerStub.CreateChatResponse wsResponse = new MessagerStub.CreateChatResponse();
		
		// Armo la WSRequest
		chatRequest.setScopeId(request.getId_ambito());
		chatRequest.setMemberId(request.getId_member());
		
		// Hacer el request
        try {
		    wsResponse = api.createChat(chatRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("crear chat");
        }
        
        // Generate response
		Chat responseData = wsResponse.get_return();
		if (responseData == null || responseData.getData() == null || responseData.getMeessage() == null) {
			response.setSuccess(false);
			response.setReason("Respuesta malformada");
		} else if (responseData.getData().getIdChat() == -1) {
			response.setSuccess(false);
			response.setReason(responseData.getMeessage().getCode() + ": " + responseData.getMeessage().getDescription());
		} else {
			response.setSuccess(true);
			response.setReason(responseData.getMeessage().getCode() + ": " + responseData.getMeessage().getDescription());
			response.setId_chat((int) responseData.getData().getIdChat());
		}
		return Response.ok().entity(response).build();
		
	}
	
	@GET
	@Path("updatechat")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateChat(@CookieParam("authToken") String authToken, @QueryParam("id_chat") Integer id_chat, @QueryParam("id_miembro") Integer id_miembro, @QueryParam("lastCallTimestamp") Integer lastCallTimestamp) throws ParserConfigurationException, SAXException, IOException {
		UpdateChatResponse response = new UpdateChatResponse();
		
		MessagerStub api = new MessagerStub();
		MessagerStub.UpdateChat chatRequest = new MessagerStub.UpdateChat();
		MessagerStub.UpdateChatResponse wsResponse = new MessagerStub.UpdateChatResponse();
		
		// Armo la WSRequest
		chatRequest.setChatId(id_chat);
		chatRequest.setIdMiembro(id_miembro);
		chatRequest.setLastCallTimestamp(lastCallTimestamp);
		
		// Hacer el request
        try {
		    wsResponse = api.updateChat(chatRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("updateChat");
        }
		
        // Generate response
 		Chat responseData = wsResponse.get_return();
 		if (responseData == null || responseData.getData() == null || responseData.getMeessage() == null) {
 			response.setSuccess(false);
 			response.setReason("Respuesta malformada");
 		} else {
 			response.setSuccess(true);
			response.setReason(responseData.getMeessage().getCode() + ": " + responseData.getMeessage().getDescription());
			// Create messages array
			MensajeChat[] messagesResponse = responseData.getData().getMessages();
			Mensaje[] messages = new Mensaje[messagesResponse.length];
			for (int i = 0; i < messagesResponse.length; i++) {
				messages[i] = new Mensaje();
				messages[i].content = messagesResponse[i].getContenidoMensaje();
				messages[i].date = (int) messagesResponse[i].getFechaMensaje();
				messages[i].id_member = (int) messagesResponse[i].getMiembro().getIdMiembro();
				messages[i].name_member = messagesResponse[i].getMiembro().getNombre();
			}
			response.setMessages(messages);
 		}
		return Response.ok().entity(response).build();
	}
	
	@GET
	@Path("gethistorychat")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getHistoryChat(@CookieParam("authToken") String authToken, @QueryParam("id_chat") Integer id_chat, @QueryParam("id_member") Integer id_member) throws ParserConfigurationException, SAXException, IOException {
		GetHistoryResponse response = new GetHistoryResponse();
		
		MessagerStub api = new MessagerStub();
		MessagerStub.GetHistoryChat chatRequest = new MessagerStub.GetHistoryChat();
		MessagerStub.GetHistoryChatResponse wsResponse = new MessagerStub.GetHistoryChatResponse();
		
		// Armo la WSRequest
		chatRequest.setChatId(id_chat);
		chatRequest.setIdMiembro(id_member);
		
		// Hacer el request
        try {
		    wsResponse = api.getHistoryChat(chatRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("getHistoryChat");
        }
        
        // Generate response
  		Chat responseData = wsResponse.get_return();
  		if (responseData == null || responseData.getData() == null || responseData.getMeessage() == null) {
  			response.setSuccess(false);
  			response.setReason("Respuesta malformada");
  		} else {	
  			response.setSuccess(true);
 			response.setReason(responseData.getMeessage().getCode() + ": " + responseData.getMeessage().getDescription());
 			response.setId_chat((int) responseData.getData().getIdChat());
 			response.setLastCallTimestamp((int) responseData.getLastCallTimestamp());
 			// Create messages array
 			MensajeChat[] messagesResponse = responseData.getData().getMessages();
 			Mensaje[] messages = new Mensaje[messagesResponse.length];
 			for (int i = 0; i < messagesResponse.length; i++) {
 				messages[i] = new Mensaje();
 				messages[i].content = messagesResponse[i].getContenidoMensaje();
 				messages[i].date = (int) messagesResponse[i].getFechaMensaje();
 				messages[i].id_member = (int) messagesResponse[i].getMiembro().getIdMiembro();
 				messages[i].name_member = messagesResponse[i].getMiembro().getNombre();
 			}
 			response.setMessages(messages);
  		}
		
		return Response.ok().entity(response).build();
	}
	
	@GET
	@Path("getmembers")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMembers(@CookieParam("authToken") String authToken, @QueryParam("getmembers") Integer getmembers) throws ParserConfigurationException, SAXException, IOException {
		GetMembersResponse response = new GetMembersResponse();
		
		MessagerStub api = new MessagerStub();
		MessagerStub.GetMembers chatRequest = new MessagerStub.GetMembers();
		MessagerStub.GetMembersResponse wsResponse = new MessagerStub.GetMembersResponse();
		
		// Armo la WSRequest
		chatRequest.setChatId(getmembers);
		
		// Hacer el request
        try {
		    wsResponse = api.getMembers(chatRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("getMembers");
        }
        
        // Generate response
  		Miembros membersData = wsResponse.get_return();
  		if (membersData == null || membersData.getMessages() == null) {
  			response.setSuccess(false);
  			response.setReason("Respuesta malformada");
  		} else {
  			response.setSuccess(true);
 			response.setReason("Operacion exitosa");
 			// Create members array
 			Miembro[] members = new Miembro[membersData.getMessages().length];
 			for (int i = 0; i < membersData.getMessages().length; i++) {
 				members[i] = new Miembro();
 				members[i].setId((int) membersData.getMessages()[i].getIdMiembro());
 				members[i].setName(membersData.getMessages()[i].getNombre());
 			}
 			response.setMembers(members);
  		}
		
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("sendmessage")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendMessage(@CookieParam("authToken") String authToken, SendMessageRequest request) throws ParserConfigurationException, SAXException, IOException {
		SendMessageResponse response = new SendMessageResponse();
		
		MessagerStub api = new MessagerStub();
		MessagerStub.SendMessage chatRequest = new MessagerStub.SendMessage();
		MessagerStub.SendMessageResponse wsResponse = new MessagerStub.SendMessageResponse();
		
		// Armo la WSRequest
		chatRequest.setChatId(request.getId_chat());
		chatRequest.setMessage(request.getMessage());
		chatRequest.setMemberId(request.getId_member());
		
		// Hacer el request
        try {
		    wsResponse = api.sendMessage(chatRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("sendMessage");
        }
        
        // Generate response
 		Chat responseData = wsResponse.get_return();
 		if (responseData == null || responseData.getData() == null || responseData.getMeessage() == null) {
 			response.setSuccess(false);
 			response.setReason("Respuesta malformada");
 		} else {
 			response.setSuccess(true);
 			response.setReason(responseData.getMeessage().getCode() + ": " + responseData.getMeessage().getDescription());
 		}
		
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logOut(@CookieParam("authToken") String authToken, HistoryMessageRequest request) throws ParserConfigurationException, SAXException, IOException {
		LogoutResponse response = new LogoutResponse();
		
		MessagerStub api = new MessagerStub();
		MessagerStub.Logout chatRequest = new MessagerStub.Logout();
		MessagerStub.LogoutResponse wsResponse = new MessagerStub.LogoutResponse();
		
		// Armo la WSRequest
		chatRequest.setChatId(request.getId_chat());
		chatRequest.setIdMiembro(request.getId_member());
		
		// Hacer el request
        try {
		    wsResponse = api.logout(chatRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("logOut");
        }
		
        // Generate response
 		boolean responseData = wsResponse.get_return();
 		if (!responseData) {
 			response.setSuccess(false);
 			response.setReason("Error al desloguear");
 		} else {
 			response.setSuccess(true);
 			response.setReason("Deslogueo exitoso");
 		}        
   
		return Response.ok().entity(response).build();
	}
	
}