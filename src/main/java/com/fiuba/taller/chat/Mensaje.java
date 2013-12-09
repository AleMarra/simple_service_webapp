package com.fiuba.taller.chat;

public class Mensaje{
	
	public int id_member;
	public String name_member;
	public String content; 
	public int date;
	
	public Mensaje(){
		this.id_member = -1;
		this.name_member = "";
		this.content = "";
		this.date = -1;
	}
	
	public Mensaje(int id, String name, String content, int date){
		this.id_member = id;
		this.name_member = name;
		this.content = content;
		this.date = date;
	}
}