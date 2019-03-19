package entity;

import java.io.Serializable;

public class Departamento implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	
	public Departamento() {
	};
	
	public Departamento(int id,String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "[id : "+id+", nome : "+nome+"]";
	}
}
