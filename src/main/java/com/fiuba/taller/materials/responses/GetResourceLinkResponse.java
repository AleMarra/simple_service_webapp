package com.fiuba.taller.materials.responses;

import com.fiuba.taller.materials.MaterialsResource;
import com.fiuba.taller.utils.XmlHandler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

@XmlRootElement(name = "response")
public class GetResourceLinkResponse extends MaterialsResponse {

	private static final XmlHandler xmlHandler = new XmlHandler();

	public static class MaterialsLinkResource extends MaterialsResource{
		
		protected String link;

		public MaterialsLinkResource(){}
		
		@XmlElement(name="link")
		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}
		
		@Override 
		public String toString(){
			return String.format("MaterialsLinkResource: recurso: %s; ambito: %s; tipo: %s; desc: %s; link: %s", 
					recursoId, ambitoId, tipo, descripcion, link);
		}
	}
	
	private MaterialsLinkResource resource = new MaterialsLinkResource();
	
	public GetResourceLinkResponse() {}
	
	public GetResourceLinkResponse(boolean success, String reason) {
		super(success, reason, "");
	}

	public GetResourceLinkResponse(boolean success, String reason, String authToken) {
		super(success, reason, authToken);
	}
	
	@XmlElement(name = "recurso")
    public MaterialsLinkResource getResource() {
		return resource;
	}

	public void setResources(MaterialsLinkResource resource) {
		this.resource = resource;
	}

	public void setResourcesFromXML(Element resourceRoot) {

		MaterialsLinkResource resource = new MaterialsLinkResource();

		resource.setAmbitoId(Integer.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "ambitoId")));
		resource.setRecursoId(Integer.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "recursoId")));
		resource.setTipo(xmlHandler.getFirstElementValue(resourceRoot, "tipo"));
		resource.setDescripcion(xmlHandler.getFirstElementValue(resourceRoot, "descripcion"));
		resource.setLink(xmlHandler.getFirstElementValue(resourceRoot, "link"));

		this.resource = resource;

	}
    
    @Override 
    public String toString(){
    	return String.format("GetResourceLinkResponse - Successful: %s ; Reason: %s ; AuthToken: %s; Resource: %s",
    							success, reason, authToken, resource.toString());
    }
}
