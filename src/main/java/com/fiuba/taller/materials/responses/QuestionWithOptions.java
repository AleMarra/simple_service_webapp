package com.fiuba.taller.materials.responses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fiuba.taller.materials.MaterialsResource;

@XmlRootElement(name = "preguntaConOpciones")
public class QuestionWithOptions extends PollQuestion {

	protected Boolean multiplesCorrect;
	protected String correct;
	protected String answers;
	
	@XmlAttribute(name="multiplesCorrectas")
	public Boolean getMultiplesCorrect() {
		return multiplesCorrect;
	}
	public void setMultiplesCorrect(Boolean multiplesCorrect) {
		this.multiplesCorrect = multiplesCorrect;
	}
	
	@XmlAttribute(name="correctas")
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	} 
	
	@XmlAttribute(name="respuestas")
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	@Override
	public void setFromXML(Element resourcesRoot) {

		this.id = Integer.valueOf(resourcesRoot.getAttribute("idPregunta"));
		this.statement = resourcesRoot.getAttribute("enunciado");
		this.correct = resourcesRoot.getAttribute("correctas");
		this.multiplesCorrect = Boolean.valueOf(resourcesRoot.getAttribute("multiplesCorrectas"));
		this.answers = resourcesRoot.getAttribute("respuestas");
	}

}
