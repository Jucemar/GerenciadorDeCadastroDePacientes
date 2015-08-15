package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades;

import java.io.Serializable;
import java.util.Date;


import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoNomeInvalido;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoNomeNull;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoRacaInvalida;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoSexoInvalido;



public class Pessoa implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	private Date data_nascimento;
	private String sexo;
	private String raca;
	private String rg;
	private String telefone;
	private String nome_mae;
	private String nome_pai;
	private String endereco;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;	
	
	
	public Pessoa() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ExcecaoNomeNull, ExcecaoNomeInvalido {
		nome.trim();
		if (nome.length() == 0) {
			throw new ExcecaoNomeNull();
		} else {
			if (nome.indexOf(" ") == -1) {
				throw new ExcecaoNomeInvalido();
			} else {
				this.nome = nome;
			}
		}
	}

	public String getNome_Mae() {
		return nome_mae;
		
	}

	public void setNome_Mae(String nome_Mae) {
		this.nome_mae = nome_Mae;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getSexo() {
		return sexo;
		
	}

	public void setSexo(String sexo) throws ExcecaoSexoInvalido {
		if (sexo.equalsIgnoreCase(" ")) {
			throw new ExcecaoSexoInvalido();
		} else {
			this.sexo = sexo;
		}
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) throws ExcecaoRacaInvalida {
		if (raca.equalsIgnoreCase(" ")) {
			throw new ExcecaoRacaInvalida();
		} else {
			this.raca = raca;
		}
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome_mae() {
		return nome_mae;
	}

	public void setNome_mae(String nome_mae) {
		this.nome_mae = nome_mae;
	}

	public String getNome_pai() {
		return nome_pai;
	}

	public void setNome_pai(String nome_pai) {
		this.nome_pai = nome_pai;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	
	

}
