package com.fiuba.taller.activity;

import com.fiuba.taller.BaseResponse;
import com.fiuba.taller.BaseService;
import com.fiuba.taller.activity.requests.*;
import com.fiuba.taller.activity.responses.GetPropertiesResponse;
import com.fiuba.taller.utils.XmlHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import wtp.activity.fiuba.taller.actividad.ActividadStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;


@Path("/activityservice")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ActivityService extends BaseService {
    public static final String dummyActivityProperties =
        "<WS>" +
            "<list>" +
                "<Actividad>" +
                    "<id>4928</id>" +
                    "<actividadId>4928</actividadId>" +
                    "<nombre>Trabajo Practico</nombre>" +
                    "<descripcion>Se lala.</descripcion>" +
                    "<tipo>Grupal Evaluable</tipo>" +
                    "<fechaInicio>1382918400</fechaInicio>" +
                    "<fechaFin>1383004800</fechaFin>" +
                    "<gruposExclusivos>false</gruposExclusivos>" +
                    "<tipoEscala>Decimal</tipoEscala>" +
                "</Actividad>" +
            "</list>" +
        "</WS>";

    private static final XmlHandler xmlHandler = new XmlHandler();

    // ------------------------------------------------ API METHODS ------------------------------------------------
	@POST
	@Path("creategroupactivity")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGroupActivity(CreateGroupActivityRequest request, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		
		// Init
		ActivityResponse response = new ActivityResponse();
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadGrupal crearActividadRequest = new ActividadStub.CrearActividadGrupal();
		ActividadStub.CrearActividadGrupalResponse wsResponse = new ActividadStub.CrearActividadGrupalResponse();
		
		crearActividadRequest.setUsername(username);
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadGrupal(crearActividadRequest);
        } catch (RemoteException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

      
        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setId(activityID);
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
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadGrupalEvaluable crearActividadRequest = new ActividadStub.CrearActividadGrupalEvaluable();
		ActividadStub.CrearActividadGrupalEvaluableResponse wsResponse = new ActividadStub.CrearActividadGrupalEvaluableResponse();
		
		crearActividadRequest.setUsername(username);
        crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));

		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadGrupalEvaluable(crearActividadRequest);
        } catch (RemoteException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setId(activityID);
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
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadIndividual crearActividadRequest = new ActividadStub.CrearActividadIndividual();
		ActividadStub.CrearActividadIndividualResponse wsResponse = new ActividadStub.CrearActividadIndividualResponse();
		
		crearActividadRequest.setUsername(username);
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad", (HashMap<String, String>) request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadIndividual(crearActividadRequest);
        } catch (RemoteException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setId(activityID);
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
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }
		
		ActividadStub api = new ActividadStub();
		ActividadStub.CrearActividadIndividualEvaluable crearActividadRequest = new ActividadStub.CrearActividadIndividualEvaluable();
		ActividadStub.CrearActividadIndividualEvaluableResponse wsResponse = new ActividadStub.CrearActividadIndividualEvaluableResponse();
		
		crearActividadRequest.setUsername(username);
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadIndividualEvaluable(crearActividadRequest);
        } catch (RemoteException e) {
        	success = false;
        	message = e.toString();
        	
        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        //  Parsear el response
		long activityID =  wsResponse.get_return();
		
		response.setSuccess(success);
		
		if (success){
			response.setId(activityID);
		}else{
    		response.setReason(message);
		}

		return Response.ok().entity(response).build();
		
	}
	
	
	@GET
	@Path("getproperties/{id}")
	public Response getActivityProperties(@PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
        // Init
        GetPropertiesResponse response = new GetPropertiesResponse();
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                    "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }

        ActividadStub api = new ActividadStub();
        ActividadStub.GetPropiedades getPropiedadesRequest = new ActividadStub.GetPropiedades();
        ActividadStub.GetPropiedadesResponse wsResponse = new ActividadStub.GetPropiedadesResponse();

        getPropiedadesRequest.setUsername(username);
        getPropiedadesRequest.setIdActividad(id);

        boolean success = true;
        String message = "";

        // Hacer el request
        try {
            wsResponse = api.getPropiedades(getPropiedadesRequest);
        } catch (RemoteException e) {
            success = false;
            message = e.toString();

        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        if (success) {
            String activityReturn = wsResponse.get_return();
//        	String activityReturn = dummyActivityProperties;

            Document doc = xmlHandler.getDoc(activityReturn);

            Element responseElement = xmlHandler.getFirstElementWithTag(doc, "WS");
            Element listElement = xmlHandler.getFirstElementWithTag(responseElement, "list");

            response.setSuccess(success);

            response.setActivityPropertiesFromXML(xmlHandler.getFirstElementWithTag(listElement, "Actividad"));
        } else {
            response.setReason(message);
        }

        return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("setproperties/{id}") //TODO /ID
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setActivityProperties(EditActivityRequest request, @PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
        // Init
        ActivityResponse response = new ActivityResponse();
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }

        ActividadStub api = new ActividadStub();
        ActividadStub.SetPropiedades editarActividadRequest = new ActividadStub.SetPropiedades();

        editarActividadRequest.setUsername(username);
        editarActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));

        boolean success = true;
        String message = "";

        // Hacer el request
        try {
        	// No hay response. Consideramos exitoso el caso en que no tira excepcion y ya
            api.setPropiedades(editarActividadRequest);
        } catch (RemoteException e) {
            success = false;
            message = e.toString();

        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        response.setSuccess(success);

        // if success then no reason is set
        if (!success){
            response.setReason(message);
        }

        return Response.ok().entity(response).build();
	}
	


	@POST
	@Path("deleteactivity/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteActivity(@PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		// Init
		BaseResponse response = new BaseResponse();
		String username = getUsernameFromAuthToken(token);
		if (username.equals("")) {
			response.setSuccess(false);
			response.setReason("Usuario no logueado");
			return Response.ok()
					.header("Set-Cookie",
							"authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
							.entity(response).build();
		}

		ActividadStub api = new ActividadStub();
		ActividadStub.DestruirActividad destruirActividadRequest = new ActividadStub.DestruirActividad();

		destruirActividadRequest.setUsername(username);
		destruirActividadRequest.setIdActividad(id);

		boolean success = true;
		String message = "";

		// Hacer el request
		try {
			api.destruirActividad(destruirActividadRequest);
		} catch (RemoteException e) {
			success = false;
			message = e.toString();

		} catch (Exception e) {
			System.out.println(e.toString());
			return buildServiceUnavailable(e.toString());
		}

		response.setSuccess(success);

        // if success then no reason is set
        if (!success){
            response.setReason(message);
        }
		return Response.ok().entity(response).build();
		
	}

	
	@POST
	@Path("addparticipant/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActivityParticipant(EditParticipantRequest request, @PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
        // Init
        BaseResponse response = new BaseResponse();
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }

        ActividadStub api = new ActividadStub();
        ActividadStub.AgregarParticipante agregarParticipanteRequest = new ActividadStub.AgregarParticipante();

        agregarParticipanteRequest.setUsername(username);
        agregarParticipanteRequest.setIdActividad(id);
        agregarParticipanteRequest.setUsernameNuevoParticipante(request.getUsernameParticipante());

        boolean success = true;
        String message = "";

        // Hacer el request
        try {
            api.agregarParticipante(agregarParticipanteRequest);
        } catch (RemoteException e) {
            success = false;
            message = e.toString();

        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        response.setSuccess(success);

        // if success then no reason is set
        if (!success){
            response.setReason(message);
        }
        return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("deleteparticipant/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteActivityParticipant(EditParticipantRequest request, @PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
        // Init
        BaseResponse response = new BaseResponse();
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }

        ActividadStub api = new ActividadStub();
        ActividadStub.EliminarParticipante eliminarParticipanteRequest = new ActividadStub.EliminarParticipante();

        eliminarParticipanteRequest.setUsername(username);
        eliminarParticipanteRequest.setIdActividad(id);
        eliminarParticipanteRequest.setUsernameParticipanteAEliminar(request.getUsernameParticipante());

        boolean success = true;
        String message = "";

        // Hacer el request
        try {
            api.eliminarParticipante(eliminarParticipanteRequest);
        } catch (RemoteException e) {
            success = false;
            message = e.toString();

        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        response.setSuccess(success);

        // if success then no reason is set
        if (!success){
            response.setReason(message);
        }
        return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("getparticipants/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getActivityParticipants(@PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{

        // Init
        BaseResponse response = new BaseResponse();
        String username = getUsernameFromAuthToken(token);
        if (username.equals("")) {
            response.setSuccess(false);
            response.setReason("Usuario no logueado");
            return Response.ok()
                    .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
                    .entity(response).build();
        }

        ActividadStub api = new ActividadStub();
        ActividadStub.GetParticipantes participantesRequest = new ActividadStub.GetParticipantes();

        participantesRequest.setUsername(username);
        participantesRequest.setIdActividad(id);

        boolean success = true;
        String message = "";

        // Hacer el request
        try {
            api.getParticipantes(participantesRequest);
        } catch (RemoteException e) {
            success = false;
            message = e.toString();

        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        response.setSuccess(success);

        // if success then no reason is set
        if (!success){
            response.setReason(message);
        }
        return Response.ok().entity(response).build();
	}
	

	
}
