package com.fiuba.taller.materials.responses;

import org.w3c.dom.Element;

import com.fiuba.taller.materials.MaterialsResource;
import com.fiuba.taller.utils.XmlHandler;

import static com.fiuba.taller.materials.MaterialsResource.ResourceTypes;


public abstract class MaterialsResponseFactory {

	private static XmlHandler xmlHandler = new XmlHandler();
	
	public static MaterialsResponse getResourceResponseFromXml(Element resourceRoot){
		
		String type = xmlHandler.getFirstElementValue(resourceRoot, XmlHandler.TYPE_KEY);
		
		MaterialsResponse response = getResourceResponse(type);
		
		if(response != null){
			response.setResourcesFromXML(resourceRoot);
			return response;
		}

		return null;
		
	}
	
	public static MaterialsResponse getResourceResponse(MaterialsResource.ResourceTypes type) {
		
		switch(type){
			
			case ARCHIVO: return new GetResourceFileResponse();
			
			case ENCUESTA: return new GetResourcePollResponse();
			
			case LINK: return new GetResourceLinkResponse();
			
			case LISTA: return new GetResourcesListResponse();
			
			case SIMPLE: return new MaterialsResponseImpl();
			
			default: return null;
		}
	}
	
	public static MaterialsResponse getResourceResponse(String type) {
		return getResourceResponse(MaterialsResource.castToType(type));
	}
	
}
