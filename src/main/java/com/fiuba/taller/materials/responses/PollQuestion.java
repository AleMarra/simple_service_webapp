package com.fiuba.taller.materials.responses;

import javax.xml.bind.annotation.XmlAttribute;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class PollQuestion {

	protected Integer id;
	protected String statement;
	
	@XmlAttribute(name="idPregunta")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name="enunciado")
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public abstract void setFromXML(Element resourcesRoot);
}
