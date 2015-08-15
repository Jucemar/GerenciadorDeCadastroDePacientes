package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades;

import java.io.Serializable;

public class RegistroNoDiretorio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Box elementos;
	private int qtde_elementos;
	private RegistroNoDiretorio proximoRegistro;
	private RegistroNoDiretorio registroAnterior;
	
	public RegistroNoDiretorio() {
		elementos=null;
		proximoRegistro=null;
		qtde_elementos=0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Box getElementos() {
		return elementos;
	}

	public void setElementos(Box elementos) {
		this.elementos = elementos;
	}

	public RegistroNoDiretorio getProximoRegistro() {
		return proximoRegistro;
	}

	public void setProximoRegistro(RegistroNoDiretorio proximoRegistro) {
		this.proximoRegistro = proximoRegistro;
	}

	public void setRegistroAnterior(RegistroNoDiretorio registroAnterior) {
		this.registroAnterior = registroAnterior;
	}

	public RegistroNoDiretorio getRegistroAnterior() {
		return registroAnterior;
	}

	public int getQtde_elementos() {
		return qtde_elementos;
	}

	public void incrementaQtde_elementos() {
		this.qtde_elementos++;
	}
	
	public void decrementaQtde_elementos() {
		this.qtde_elementos--;
	}

}
