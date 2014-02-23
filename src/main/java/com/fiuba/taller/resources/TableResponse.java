package com.fiuba.taller.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import wtp.ServicioTablaServiceStub.Fila;

@XmlRootElement(name = "resources-response")
public class TableResponse {
	private boolean success;
	private String reason;
	private String[] typeFields,namesColumns;
	private int id;
	private Fila[] filas;

	@XmlElement(name = "typeFields")
	public String[] getTypeFields() {
		return typeFields;
	}

	public void setTypeFields(String[] typeFields) {
		this.typeFields = typeFields;
	}

	@XmlElement(name = "success")
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@XmlElement(name = "reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "namesColumns")
	public String[] getNamesColumns() {
		return namesColumns;
	}

	public void setNamesColumns(String[] namesColumns) {
		this.namesColumns = namesColumns;
	}

	@XmlElement(name = "rows")
	public Fila[] getFilas() {
		return filas;
	}

	public void setFilas(Fila[] filas) {
		this.filas = filas;
	}

}
