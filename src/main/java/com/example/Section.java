package com.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "section-details")
public class Section {
	
	private int idUser,idAmbito,idForo,idSeccion;
	private String nombreSeccion;
	
	public Section(int idUser, int idAmbito, int idForo, int idSeccion, String nombreSeccion) {
		this.idUser = idUser;
		this.idAmbito = idAmbito;
		this.idForo = idForo;
		this.idSeccion = idSeccion;
		this.nombreSeccion = nombreSeccion;
	}
	
	@XmlElement(name = "idUser")
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@XmlElement(name = "idAmbito")
	public int getIdAmbito() {
		return idAmbito;
	}
	public void setIdAmbito(int idAmbito) {
		this.idAmbito = idAmbito;
	}
	
	@XmlElement(name = "idForo")
	public int getIdForo() {
		return idForo;
	}
	public void setIdForo(int idForo) {
		this.idForo = idForo;
	}
	
	@XmlElement(name = "idSeccion")
	public int getIdSeccion() {
		return idSeccion;
	}
	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}
	
	@XmlElement(name = "nombreSeccion")
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}
	

}
