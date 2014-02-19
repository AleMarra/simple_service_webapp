package com.fiuba.taller.materials.responses;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fiuba.taller.materials.MaterialsResource;
import com.fiuba.taller.utils.XmlHandler;

@XmlRootElement(name = "response")
public class GetResourcesListResponse extends MaterialsResponse {

	private static final XmlHandler xmlHandler = new XmlHandler();
	
	protected List<MaterialsResource> resources = new LinkedList<MaterialsResource>();
	
	
	public GetResourcesListResponse() {
	}
	
	public GetResourcesListResponse(boolean success, String reason) {
		super(success, reason, "");
	}

	public GetResourcesListResponse(boolean success, String reason, String authToken) {
		super(success, reason, authToken);
	}


	@XmlElement(name = "recursos")
    public List<MaterialsResource> getResources() {
		return resources;
	}

	public void setResources(List<MaterialsResource> resources) {
		this.resources = resources;
	}
	
	public void setResourcesFromXML(Element resourcesRoot) {
		
		NodeList elements = resourcesRoot.getElementsByTagName("recurso");
		
		for(int i = 0; i<elements.getLength(); i++){
			Element eResource = (Element) elements.item(i);
			MaterialsResource resource = new MaterialsResource();
			
			resource.setAmbitoId(Integer.valueOf(xmlHandler.getFirstElementValue(eResource, "ambitoId")));
			resource.setRecursoId(Integer.valueOf(xmlHandler.getFirstElementValue(eResource, "recursoId")));
			resource.setTipo(xmlHandler.getFirstElementValue(eResource, "tipo"));
			resource.setDescripcion(xmlHandler.getFirstElementValue(eResource, "descripcion"));
			
			this.resources.add(resource);
		}
		
	}
    
    @Override 
    public String toString(){
    	return String.format("GetResourcesListResponse - Successful: %s ; Reason: %s ; AuthToken: %s",
    							success, reason, authToken);
    }
}
