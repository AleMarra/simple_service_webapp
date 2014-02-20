package com.fiuba.taller.materials.responses;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fiuba.taller.materials.MaterialsResource;
import com.fiuba.taller.materials.responses.GetResourceLinkResponse.MaterialsLinkResource;
import com.fiuba.taller.utils.XmlHandler;

public class GetResourcePollResponse extends MaterialsResponse {

	private static final XmlHandler xmlHandler = new XmlHandler();

	public static class MaterialsPollResource extends MaterialsResource{
		
		public MaterialsPollResource(){}

		private List<PollQuestion> questions = new LinkedList<PollQuestion>();
		
		private Boolean evaluable;
		
		@XmlElement(name="preguntas")
		public List<PollQuestion> getQuestions() {
			return questions;
		}

		public void setQuestions(List<PollQuestion> questions) {
			this.questions = questions;
		}
		
		@XmlAttribute(name="evaluada")
		public Boolean getEvaluable() {
			return evaluable;
		}

		public void setEvaluable(Boolean evaluable) {
			this.evaluable = evaluable;
		}

		@Override 
		public String toString(){
			return String.format("MaterialsPollResource: recurso: %s; ambito: %s; tipo: %s; desc: %s; questionsCount: %d", 
					recursoId, ambitoId, tipo, descripcion, questions.size());
		}
	}
	
	private MaterialsPollResource resource = new MaterialsPollResource();
	
	@XmlElement(name = "encuesta")
    public MaterialsPollResource getResource() {
		return resource;
	}

	public void setResources(MaterialsPollResource resource) {
		this.resource = resource;
	}
	
	public GetResourcePollResponse() {}
	
	@Override
	public void setResourcesFromXML(Element resourceRoot) {

		MaterialsPollResource pollResource = new MaterialsPollResource();
		List<PollQuestion> pollQuestions = new LinkedList<PollQuestion>();
		
		NodeList questionsNodes = xmlHandler.getFirstElementWithTag(resourceRoot, "preguntas").getChildNodes();
		Element question = null;
		for(int i=0; i<questionsNodes.getLength(); i++ ){
			if(questionsNodes.item(i).getNodeType() == Node.ELEMENT_NODE){
				question = (Element) questionsNodes.item(i);
				
				PollQuestion current = null;
				if(question.getTagName().equals("preguntaConOpciones"))
					current = new QuestionWithOptions();
				else
					current = new QuestionWithoutOptions();
				
				current.setFromXML(question);
				
				pollQuestions.add(current);
			}
		}
		
		pollResource.setQuestions(pollQuestions);
		
		pollResource.setAmbitoId(Integer.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "ambitoId")));
		pollResource.setRecursoId(Integer.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "recursoId")));
		pollResource.setTipo(xmlHandler.getFirstElementValue(resourceRoot, "tipo"));
		pollResource.setDescripcion(xmlHandler.getFirstElementValue(resourceRoot, "descripcion"));
		pollResource.setEvaluable(Boolean.valueOf(resourceRoot.getAttribute("evaluada")));

		this.resource = pollResource;
	}

}
