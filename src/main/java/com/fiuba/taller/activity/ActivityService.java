package com.fiuba.taller.activity;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.fiuba.taller.BaseService;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.*;

import com.fiuba.taller.activity.requests.*;

import org.xml.sax.SAXException;

import wtp.activity.fiuba.taller.actividad.*;


@Path("/activityservice")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ActivityService extends BaseService {

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
        } catch (ActividadRemoteExceptionException e) {
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
        } catch (ActividadRemoteExceptionException e) {
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
		crearActividadRequest.setXmlPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
		
		boolean success = true;
	    String message = "";
	        
		// Hacer el request
        try {
		    wsResponse = api.crearActividadIndividual(crearActividadRequest);
        } catch (ActividadRemoteExceptionException e) {
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
        } catch (ActividadRemoteExceptionException e) {
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
			response.setReason(Integer.toString((int)activityID));
		}else{
    		response.setReason(message);
		}

		return Response.ok().entity(response).build();
		
	}
	
	
	@GET
	@Path("getproperties/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getActivityProperties(@PathParam("id") long id, @CookieParam("authToken") String token)
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
        ActividadStub.GetPropiedades getPropiedadesRequest = new ActividadStub.GetPropiedades();
        ActividadStub.GetPropiedadesResponse wsResponse = new ActividadStub.GetPropiedadesResponse();

        getPropiedadesRequest.setUsername(username);
        getPropiedadesRequest.setIdActividad(id);

        boolean success = true;
        String message = "";

        // Hacer el request
        try {
            wsResponse = api.getPropiedades(getPropiedadesRequest);
        } catch (ActividadRemoteExceptionException e) {
            success = false;
            message = e.toString();

        } catch (Exception e) {
            System.out.println(e.toString());
            return buildServiceUnavailable(e.toString());
        }

        response.setSuccess(success);

        if (success) {
            response.setReason("lalalala");
        }

		return Response.ok().build();
	}
	
	@POST
	@Path("setproperties/{id}") //TODO /ID
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setActivityProperties(EditActivityRequest request, @PathParam("id") long id, @CookieParam("authToken") String token)
			throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
//        // Init
//        ActivityResponse response = new ActivityResponse();
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
//        ActividadStub api = new ActividadStub();
//        ActividadStub.SetPropiedades editarActividadRequest = new ActividadStub.SetPropiedades();
//        // cuál es la response de esto?!
//        ActividadStub.SetPropiedadesResponse wsResponse = new ActividadStub.SetPropiedadesResponse();
//
//        editarActividadRequest.setUsername(username);
//        editarActividadRequest.setPropiedades(makeXMLFromMap("Actividad",(HashMap<String,String>)request.toMap()));
//
//        boolean success = true;
//        String message = "";
//
//        // Hacer el request
//        try {
//            wsResponse = api.crearActividadIndividualEvaluable(editarActividadRequest);
//        } catch (ActividadRemoteExceptionException e) {
//            success = false;
//            message = e.toString();
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            return buildServiceUnavailable(e.toString());
//        }

        return Response.ok().build();
	}
	
}