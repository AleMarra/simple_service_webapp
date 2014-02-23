package com.fiuba.taller.resources;

import java.io.IOException;
import java.io.StringReader;

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

import wtp.ServicioTablaServiceStub;
import wtp.ServicioTablaServiceStub.Fila;

import com.fiuba.taller.resources.requests.AgregarCampoRequest;
import com.fiuba.taller.resources.requests.CrearFilaRequest;
import com.fiuba.taller.resources.requests.CrearTablaRequest;
import com.fiuba.taller.resources.requests.EliminarCampoRequest;
import com.fiuba.taller.resources.requests.EliminarFilaRequest;
import com.fiuba.taller.resources.requests.EliminarTablaRequest;
import com.fiuba.taller.resources.requests.ModificarCampoRequest;
import com.fiuba.taller.resources.requests.ModificarFilaRequest;
import com.fiuba.taller.service.SecurityResponse;

@Path("/resources")
@Produces(MediaType.APPLICATION_JSON)
public class ResourcesService {
	
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
	
	private Response buildParamError(String paramName) {
        SecurityResponse response = new SecurityResponse();

        response.setSuccess(false);
        response.setReason("No se recibió el parametro: " + paramName);

        return Response.status(509).entity(response).build();
	}
	
	@GET
	@Path("obtenerTiposCampos")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obtenerTiposCampos(@CookieParam("authToken") String authToken) throws ParserConfigurationException, SAXException, IOException {
		//ObtenerTiposDeCamposResponse response = new ObtenerTiposDeCamposResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.ObtenerTiposDeCampos resourcesRequest = new ServicioTablaServiceStub.ObtenerTiposDeCampos();
		ServicioTablaServiceStub.ObtenerTiposDeCamposResponse wsResponse = new ServicioTablaServiceStub.ObtenerTiposDeCamposResponse();
		
		// No hay parametros
		
		// Hacer el request
        try {
		    wsResponse = api.obtenerTiposDeCampos(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("obtener tipos campos");
        }
        
        TableResponse response = new TableResponse();
        String[] types = wsResponse.getObtenerTiposDeCamposReturn();
		if (types == null) {
			response.setSuccess(false);
			response.setReason("Respuesta malformada");
		} else {
			response.setSuccess(true);
			response.setTypeFields(types);
		}
		
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("crearTabla")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearTabla(@CookieParam("authToken") String authToken, CrearTablaRequest request) throws ParserConfigurationException, SAXException, IOException {
		//CrearTablaResponse response = new CrearTablaResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.CrearTabla resourcesRequest = new ServicioTablaServiceStub.CrearTabla();
		ServicioTablaServiceStub.CrearTablaResponse wsResponse = new ServicioTablaServiceStub.CrearTablaResponse();
		
		// Armo la WSRequest
		resourcesRequest.setNombres(request.getNombres());
		resourcesRequest.setPermisos(request.getPermisos());
		resourcesRequest.setTipos(request.getTipos());
		resourcesRequest.setUsuario(request.getUsuario());
		
		// Hacer el request
        try {
		    wsResponse = api.crearTabla(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("crear tabla");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getCrearTablaReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
			response.setId(value);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@GET
	@Path("obtenerNombresColumnas")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obtenerNombresColumnas(@CookieParam("authToken") String authToken, @QueryParam("usuario") String usuario, @QueryParam("IdTabla") Integer IdTabla) throws ParserConfigurationException, SAXException, IOException {
		//ObtenerNombresColumnasResponse response = new ObtenerNombresColumnasResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.ObtenerNombresColumnas resourcesRequest = new ServicioTablaServiceStub.ObtenerNombresColumnas();
		ServicioTablaServiceStub.ObtenerNombresColumnasResponse wsResponse = new ServicioTablaServiceStub.ObtenerNombresColumnasResponse();
		
		if (IdTabla == null) {
            return buildParamError("IdTabla");
		}
		
		// Armo la WSRequest
		resourcesRequest.setTabla(IdTabla.intValue());
		resourcesRequest.setUsuario(usuario);
		
		// Hacer el request
        try {
		    wsResponse = api.obtenerNombresColumnas(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("obtener nombres columnas");
        }
        
        TableResponse response = new TableResponse();
        String[] names = wsResponse.getObtenerNombresColumnasReturn();
		if (names == null) {
			response.setSuccess(false);
			response.setReason("Error: La tabla no existe");
		} else {
			response.setSuccess(true);
			response.setNamesColumns(names);
		}
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("crearFila")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearFila(@CookieParam("authToken") String authToken, CrearFilaRequest request) throws ParserConfigurationException, SAXException, IOException {
		//CrearFilaResponse response = new CrearFilaResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.CrearFila resourcesRequest = new ServicioTablaServiceStub.CrearFila();
		ServicioTablaServiceStub.CrearFilaResponse wsResponse = new ServicioTablaServiceStub.CrearFilaResponse();
		
		// Armo la WSRequest
		resourcesRequest.setUsuario(request.getUsuario());
		resourcesRequest.setTabla(request.getIdTabla());
		resourcesRequest.setValores(request.getValores());
		
		// Hacer el request
        try {
		    wsResponse = api.crearFila(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("crear fila");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getCrearFilaReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("modificarFila")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificarFila(@CookieParam("authToken") String authToken, ModificarFilaRequest request) throws ParserConfigurationException, SAXException, IOException {
		//ModificarFilaResponse response = new ModificarFilaResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.ModificarFila resourcesRequest = new ServicioTablaServiceStub.ModificarFila();
		ServicioTablaServiceStub.ModificarFilaResponse wsResponse = new ServicioTablaServiceStub.ModificarFilaResponse();
		
		// Armo la WSRequest
		resourcesRequest.setFila(request.getNumFila());
		resourcesRequest.setTabla(request.getIdTabla());
		resourcesRequest.setUsuario(request.getUsuario());
		resourcesRequest.setValores_nuevos(request.getValoresNuevos());
		
		// Hacer el request
        try {
		    wsResponse = api.modificarFila(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("modificar fila");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getModificarFilaReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("eliminarFila")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response eliminarFila(@CookieParam("authToken") String authToken, EliminarFilaRequest request) throws ParserConfigurationException, SAXException, IOException {
		//EliminarFilaResponse response = new EliminarFilaResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.EliminarFila resourcesRequest = new ServicioTablaServiceStub.EliminarFila();
		ServicioTablaServiceStub.EliminarFilaResponse wsResponse = new ServicioTablaServiceStub.EliminarFilaResponse();
		
		// Armo la WSRequest
		resourcesRequest.setFila(request.getNumFila());
		resourcesRequest.setTabla(request.getIdTabla());
		resourcesRequest.setUsuario(request.getUsuario());
		
		// Hacer el request
        try {
		    wsResponse = api.eliminarFila(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar fila");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getEliminarFilaReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@GET
	@Path("obtenerFilas")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obtenerFilas(@CookieParam("authToken") String authToken, @QueryParam("usuario") String usuario, @QueryParam("IdTabla") Integer IdTabla) throws ParserConfigurationException, SAXException, IOException {
		//ObtenerFilasResponse response = new ObtenerFilasResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.ObtenerFilas resourcesRequest = new ServicioTablaServiceStub.ObtenerFilas();
		ServicioTablaServiceStub.ObtenerFilasResponse wsResponse = new ServicioTablaServiceStub.ObtenerFilasResponse();
		
		if (IdTabla == null) {
            return buildParamError("IdTabla");
		}
		
		// Armo la WSRequest
		resourcesRequest.setTabla(IdTabla.intValue());
		resourcesRequest.setUsuario(usuario);
		
		// Hacer el request
        try {
		    wsResponse = api.obtenerFilas(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("obtener filas");
        }
        
        TableResponse response = new TableResponse();
        Fila[] filas = wsResponse.getObtenerFilasReturn();
		if (filas == null) {
			response.setSuccess(false);
			response.setReason("Error");
		} else {
			response.setSuccess(true);
			response.setFilas(filas);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("modificarCampo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificarCampo(@CookieParam("authToken") String authToken, ModificarCampoRequest request) throws ParserConfigurationException, SAXException, IOException {
		//ModificarCampoResponse response = new ModificarCampoResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.ModificarCampo resourcesRequest = new ServicioTablaServiceStub.ModificarCampo();
		ServicioTablaServiceStub.ModificarCampoResponse wsResponse = new ServicioTablaServiceStub.ModificarCampoResponse();
		
		// Armo la WSRequest
		resourcesRequest.setCampo(request.getCampo());
		resourcesRequest.setNombre(request.getNuevoNombre());
		resourcesRequest.setNuevoTipo(request.getNuevoTipo());
		resourcesRequest.setPermiteVacio(request.isPermiteNulo());
		resourcesRequest.setTabla(request.getTabla());
		resourcesRequest.setUsuario(request.getUsuario());
		
		// Hacer el request
        try {
		    wsResponse = api.modificarCampo(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("modificar campo");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getModificarCampoReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("agregarCampo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response agregarCampo(@CookieParam("authToken") String authToken, AgregarCampoRequest request) throws ParserConfigurationException, SAXException, IOException {
		//AgregarCampoResponse response = new AgregarCampoResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.AgregarCampo resourcesRequest = new ServicioTablaServiceStub.AgregarCampo();
		ServicioTablaServiceStub.AgregarCampoResponse wsResponse = new ServicioTablaServiceStub.AgregarCampoResponse();
		
		// Armo la WSRequest
		resourcesRequest.setTabla(request.getTabla());
		resourcesRequest.setUsuario(request.getUsuario());
		resourcesRequest.setTipo(request.getTipo());
		resourcesRequest.setNombre(request.getNombre());
		
		// Hacer el request
        try {
		    wsResponse = api.agregarCampo(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("agregar campo");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getAgregarCampoReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("eliminarCampo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response eliminarCampo(@CookieParam("authToken") String authToken, EliminarCampoRequest request) throws ParserConfigurationException, SAXException, IOException {
		//EliminarCampoResponse response = new EliminarCampoResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.EliminarCampo resourcesRequest = new ServicioTablaServiceStub.EliminarCampo();
		ServicioTablaServiceStub.EliminarCampoResponse wsResponse = new ServicioTablaServiceStub.EliminarCampoResponse();
		
		// Armo la WSRequest
		resourcesRequest.setCampo(request.getCampo());
		resourcesRequest.setTabla(request.getTabla());
		resourcesRequest.setUsuario(request.getUsuario());
		
		// Hacer el request
        try {
		    wsResponse = api.eliminarCampo(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar campo");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getEliminarCampoReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
		}
        
		return Response.ok().entity(response).build();
	}
	
	@POST
	@Path("eliminarTabla")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response eliminarTabla(@CookieParam("authToken") String authToken, EliminarTablaRequest request) throws ParserConfigurationException, SAXException, IOException {
		//EliminarTablaResponse response = new EliminarTablaResponse();
		
		ServicioTablaServiceStub api = new ServicioTablaServiceStub();
		ServicioTablaServiceStub.EliminarTabla resourcesRequest = new ServicioTablaServiceStub.EliminarTabla();
		ServicioTablaServiceStub.EliminarTablaResponse wsResponse = new ServicioTablaServiceStub.EliminarTablaResponse();
		
		// Armo la WSRequest
		resourcesRequest.setTabla(request.getTabla());
		resourcesRequest.setUsuario(request.getUsuario());
		
		// Hacer el request
        try {
		    wsResponse = api.eliminarTabla(resourcesRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildError("eliminar tabla");
        }
        
        TableResponse response = new TableResponse();
        int value = wsResponse.getEliminarTablaReturn();
		if (value < 0) {
			response.setSuccess(false);
			response.setReason("Error: " + value);
		} else {
			response.setSuccess(true);
		}
        
		return Response.ok().entity(response).build();
	}
}
