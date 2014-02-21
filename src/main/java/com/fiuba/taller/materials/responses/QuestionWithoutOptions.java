package com.fiuba.taller.materials.responses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

@XmlRootElement(name="preguntaSinOpciones")
public class QuestionWithoutOptions extends PollQuestion {

	protected String correct;
	
	public QuestionWithoutOptions(){}
	
	public QuestionWithoutOptions(Element resourcesRoot){
		setFromXML(resourcesRoot);
	}
	
	@XmlAttribute(name="correcta")
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}
	
	
	@Override
	public void setFromXML(Element resourcesRoot) {

		this.id = Integer.valueOf(resourcesRoot.getAttribute("idPregunta"));
		this.statement = resourcesRoot.getAttribute("enunciado");
		this.correct = resourcesRoot.getAttribute("correcta");
	}
	
}
