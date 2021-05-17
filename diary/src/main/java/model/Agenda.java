/*
 * Auhtor: Bryan Jonathan Melo De Oliveira
 */
package model;

public class Agenda {
	private int id;
	private String texto;
	private String data;
	
	public Agenda() {
		this.id = -1;
		this.texto = "";
		this.data = "";
	}
	
	public Agenda(int id) {
		this.id = id;
		this.texto = "";
		this.data = "";
	}
	
	public Agenda(int id, String texto, String data) {
		this.id = id;
		this.texto = texto;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
     
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
               this.texto = texto;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
	        this.data = data;
	}

	@Override
	public String toString() {
		return "Agenda [ id= " + id + ", texto= " + texto + ", data= " + data + " ]";
	}
	
}
