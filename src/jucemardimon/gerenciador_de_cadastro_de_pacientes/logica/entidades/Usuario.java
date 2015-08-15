package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades;

import java.io.Serializable;

public class Usuario extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int prontuario;
	private String historico;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
		super();
	}

	public int getProntuario() {
		return prontuario;
	}

	public void setProntuario(int prontuario) {
		this.prontuario = prontuario;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}	
	

}
