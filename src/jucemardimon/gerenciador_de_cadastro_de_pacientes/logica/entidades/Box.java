package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades;

import java.io.Serializable;


public class Box implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	
	private Usuario dado;
	private Box anterior;
	private Box proximo;
	
	public Box() {
		// TODO Auto-generated constructor stub
	}

	public Usuario getDado() {
		return dado;
	}

	public void setDado(Usuario dado) {
		this.dado = dado;
	}

	public Box getProximo() {
		return proximo;
	}

	public void setProximo(Box proximo) {
		this.proximo = proximo;
	}

	public void setAnterior(Box anterior) {
		this.anterior = anterior;
	}

	public Box getAnterior() {
		return anterior;
	}
	
}