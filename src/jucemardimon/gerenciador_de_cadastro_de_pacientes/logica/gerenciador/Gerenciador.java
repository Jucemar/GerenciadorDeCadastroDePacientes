package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Box;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.RegistroNoDiretorio;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Usuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes.ExcecaoBaseVazia;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes.ExcecaoUsuarioJaCadastrado;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes.ExcecaoUsuarioNaoLocalizado;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios.CalculadoraDeIdade;

public class Gerenciador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Box ultimo_da_lista;
	private Box lista_de_dados;
	private RegistroNoDiretorio sexo;
	private RegistroNoDiretorio raca;
	private RegistroNoDiretorio faixa_etaria;
	private int qtde_usuarios;
	private int num_protuarios;
	
	public Gerenciador() {
		num_protuarios=1;
		qtde_usuarios=0;
		sexo=new RegistroNoDiretorio();
		raca=new RegistroNoDiretorio();
		lista_de_dados=new Box();
		
		faixa_etaria=new RegistroNoDiretorio();	
		faixa_etaria.setNome("Crianças");
		faixa_etaria.setProximoRegistro(new RegistroNoDiretorio());
		faixa_etaria.getProximoRegistro().setNome("Adultos");
		faixa_etaria.getProximoRegistro().setProximoRegistro(new RegistroNoDiretorio());
		faixa_etaria.getProximoRegistro().getProximoRegistro().setNome("Idosos");
	}
	
	public void insere(Usuario novo_usuario) throws ExcecaoUsuarioJaCadastrado {
		if(qtde_usuarios>0) {
			
			if (contemUsuario(novo_usuario)) {
				
				throw new ExcecaoUsuarioJaCadastrado();
				
			} else {
				insereNaBaseDeDados(novo_usuario);
				insereNoDiretorioFaixaEtaria(novo_usuario);
				insereNoDiretorioRaca(novo_usuario);
				insereNoDiretorioSexo(novo_usuario);
			}
			
		}else {
			insereNaBaseDeDados(novo_usuario);
			insereNoDiretorioFaixaEtaria(novo_usuario);
			insereNoDiretorioRaca(novo_usuario);
			insereNoDiretorioSexo(novo_usuario);


			
		}
	}
	
	private void insereNaBaseDeDados(Usuario novo_usuario) {
		Box novoElemento=new Box();
		novoElemento.setDado(novo_usuario);
		if(this.qtde_usuarios==0){
			this.lista_de_dados=novoElemento;
			this.ultimo_da_lista=this.lista_de_dados;
			qtde_usuarios++;
			num_protuarios++;
			
		}else{
			novoElemento.setAnterior(ultimo_da_lista);
			this.ultimo_da_lista.setProximo(novoElemento);
			this.ultimo_da_lista=novoElemento;
			qtde_usuarios++;
			num_protuarios++;
			
		}
		
	}
	
	private void insereNoDiretorioRaca(Usuario novo_usuario){
		Box novoElemento=new Box();
		novoElemento.setDado(novo_usuario);
		
		if(this.raca.getElementos()==null){
			this.raca.setNome(novoElemento.getDado().getRaca());
			this.raca.setElementos(novoElemento);
			this.raca.incrementaQtde_elementos();
		}else{
			RegistroNoDiretorio auxVertical=this.raca;
			while(!auxVertical.getNome().equalsIgnoreCase(novoElemento.getDado().getRaca()) && auxVertical.getProximoRegistro()!=null){
				auxVertical=auxVertical.getProximoRegistro();
			}
			if(auxVertical.getNome().equalsIgnoreCase(novoElemento.getDado().getRaca())){
				Box auxHorizontal=auxVertical.getElementos();
				auxVertical.incrementaQtde_elementos();
				while(auxHorizontal.getProximo()!=null){
					auxHorizontal=auxHorizontal.getProximo();
				}
				novoElemento.setAnterior(auxHorizontal);
				auxHorizontal.setProximo(novoElemento);
			}else{
				RegistroNoDiretorio novoRegistro=new RegistroNoDiretorio();
				novoRegistro.setNome(novoElemento.getDado().getRaca());
				novoRegistro.setElementos(novoElemento);
				novoRegistro.setRegistroAnterior(auxVertical);
				novoRegistro.incrementaQtde_elementos();
				auxVertical.setProximoRegistro(novoRegistro);
				
			}
		}
	}
	
	private void insereNoDiretorioSexo(Usuario novo_usuario){
		Box novoElemento=new Box();
		novoElemento.setDado(novo_usuario);
			
		if(this.sexo.getElementos()==null){
			this.sexo.setNome(novoElemento.getDado().getSexo());
			this.sexo.setElementos(novoElemento);
			this.sexo.incrementaQtde_elementos();
		}else{
			RegistroNoDiretorio auxVertical=this.sexo;
			while(!auxVertical.getNome().equalsIgnoreCase(novoElemento.getDado().getSexo()) && auxVertical.getProximoRegistro()!=null){
				auxVertical=auxVertical.getProximoRegistro();
			}
			if(auxVertical.getNome().equalsIgnoreCase(novoElemento.getDado().getSexo())){
				Box auxHorizontal=auxVertical.getElementos();
				auxVertical.incrementaQtde_elementos();
				while(auxHorizontal.getProximo()!=null){
					auxHorizontal=auxHorizontal.getProximo();
				}
				novoElemento.setAnterior(auxHorizontal);
				auxHorizontal.setProximo(novoElemento);
			}else{
				RegistroNoDiretorio novoRegistro=new RegistroNoDiretorio();
				novoRegistro.setNome(novoElemento.getDado().getSexo());
				novoRegistro.setElementos(novoElemento);
				novoRegistro.setRegistroAnterior(auxVertical);
				novoRegistro.incrementaQtde_elementos();
				auxVertical.setProximoRegistro(novoRegistro);
				
			}
		}
	}
	
	private void insereNoDiretorioFaixaEtaria(Usuario novo_usuario) {


		Box novoElemento = new Box();
		novoElemento.setDado(novo_usuario);

		RegistroNoDiretorio auxVertical = null;
		int idade = CalculadoraDeIdade.calculeIdade(novo_usuario.getData_nascimento());

		if (idade <= 14) {
			auxVertical = this.faixa_etaria;
		} else {
			if (idade > 14 && idade < 60) {
				auxVertical = this.faixa_etaria.getProximoRegistro();
			} else {
				if (idade >= 60) {
					auxVertical = this.faixa_etaria.getProximoRegistro().getProximoRegistro();
				}
			}
		}
		Box auxHorizontal = auxVertical.getElementos();
		auxVertical.incrementaQtde_elementos();
		if (auxHorizontal == null) {
			auxVertical.setElementos(novoElemento);
		} else {
			while (auxHorizontal.getProximo() != null) {
				auxHorizontal = auxHorizontal.getProximo();
			}
			novoElemento.setAnterior(auxHorizontal);
			auxHorizontal.setProximo(novoElemento);
		}
	}	
	
	public ArrayList<Usuario> buscaPorSexo(String sexo){
		ArrayList<Usuario> usuarios_encotrados=new ArrayList<Usuario>();
		RegistroNoDiretorio auxVertical=this.sexo;
		while(!auxVertical.getNome().equalsIgnoreCase(sexo) && auxVertical.getProximoRegistro()!=null){
			auxVertical=auxVertical.getProximoRegistro();
		}
		Box auxHorizontal=auxVertical.getElementos();
		while(auxHorizontal!=null){
			if(auxHorizontal.getDado().getSexo().equalsIgnoreCase(sexo)){
				usuarios_encotrados.add(auxHorizontal.getDado());				
			}
			auxHorizontal=auxHorizontal.getProximo();		
		}
		return usuarios_encotrados;		
	}
	
	public ArrayList<Usuario> buscaPorRaca(String raca) {
		ArrayList<Usuario> usuarios_encotrados = new ArrayList<Usuario>();
		RegistroNoDiretorio auxVertical = this.raca;
		while (!auxVertical.getNome().equalsIgnoreCase(raca) && auxVertical.getProximoRegistro() != null) {
			auxVertical = auxVertical.getProximoRegistro();
		}
		Box auxHorizontal = auxVertical.getElementos();
		while (auxHorizontal != null) {
			if (auxHorizontal.getDado().getRaca().equalsIgnoreCase(raca)) {
				usuarios_encotrados.add(auxHorizontal.getDado());
			}
			auxHorizontal = auxHorizontal.getProximo();
		}
		return usuarios_encotrados;            
	}
	
	public ArrayList<Usuario> buscaPorFaixaEtaria(String faixa_etaria) {
		ArrayList<Usuario> usuarios_encotrados= new ArrayList<Usuario>();
		RegistroNoDiretorio auxVertical = this.faixa_etaria;
		while (!auxVertical.getNome().equalsIgnoreCase(faixa_etaria) && auxVertical.getProximoRegistro() != null) {
			auxVertical = auxVertical.getProximoRegistro();
		}
		Box auxHorizontal = auxVertical.getElementos();
		while (auxHorizontal != null) {
			usuarios_encotrados.add(auxHorizontal.getDado());			
			auxHorizontal = auxHorizontal.getProximo();
		}
		return usuarios_encotrados;
	}
	
	public void removeElemento(int prontuario) throws ExcecaoUsuarioNaoLocalizado,ExcecaoBaseVazia{
		
		Box aux=this.lista_de_dados;

		
		if(this.qtde_usuarios==0) {
			throw new ExcecaoBaseVazia();
		}else {
			
			while(aux.getDado().getProntuario()!=prontuario && aux.getProximo()!=null){

				aux=aux.getProximo();			
			}
			
			if(aux.getDado().getProntuario()==prontuario){
				
				if(aux.getAnterior()==null && aux.getProximo()==null) {
					this.lista_de_dados=null;
					this.ultimo_da_lista=null;
				}else {
					if(aux.getAnterior()==null){
						this.lista_de_dados=aux.getProximo();
						this.lista_de_dados.setAnterior(null);
					}else {
						if(aux.getProximo()==null) {
							this.ultimo_da_lista=aux.getAnterior();
							this.ultimo_da_lista.setProximo(null);
						}else {
							aux.getAnterior().setProximo(aux.getProximo());
							aux.getProximo().setAnterior(aux.getAnterior());
						}
					}
				}
				this.qtde_usuarios--;
				removeDoDiretorioFaixaEtaria(aux.getDado());
				removeDoDiretorioRaca(aux.getDado());
				removeDoDiretorioSexo(aux.getDado());
					
				
				
			}else {
				throw new ExcecaoUsuarioNaoLocalizado();
			}
		}
	}	
	
	private void removeDoDiretorioFaixaEtaria(Usuario usuarioParaExclusao) {
		RegistroNoDiretorio seletor_faixa_etaria=null;

		int idade = CalculadoraDeIdade.calculeIdade(usuarioParaExclusao.getData_nascimento());
		if (idade <= 14) {
			seletor_faixa_etaria = this.faixa_etaria;
			seletor_faixa_etaria.decrementaQtde_elementos();
		} else {
			if (idade > 14 && idade < 60) {
				seletor_faixa_etaria = this.faixa_etaria.getProximoRegistro();
				seletor_faixa_etaria.decrementaQtde_elementos();
			} else {
				if (idade >= 60) {
					seletor_faixa_etaria = this.faixa_etaria.getProximoRegistro().getProximoRegistro();
					seletor_faixa_etaria.decrementaQtde_elementos();
				}
			}
		}
		Box auxHorizontal = seletor_faixa_etaria.getElementos();
		while(auxHorizontal!=null&&auxHorizontal.getDado().getProntuario()!=usuarioParaExclusao.getProntuario()){
			auxHorizontal=auxHorizontal.getProximo();		
		}
		if(auxHorizontal!=null){
			if(auxHorizontal.getAnterior()==null && auxHorizontal.getProximo()==null){
				seletor_faixa_etaria.setElementos(null);
			}else{
				if(auxHorizontal.getAnterior()==null){
					auxHorizontal.getProximo().setAnterior(null);
					seletor_faixa_etaria.setElementos(auxHorizontal.getProximo());
				}else{
					if(auxHorizontal.getProximo()==null){
						auxHorizontal.getAnterior().setProximo(null);					
					}else{
						auxHorizontal.getAnterior().setProximo(auxHorizontal.getProximo());
						auxHorizontal.getProximo().setAnterior(auxHorizontal.getAnterior());
					}
				}
			}
		}
		
	}
	
	private void removeDoDiretorioRaca(Usuario usuarioParaExclusao) {
		RegistroNoDiretorio auxVertical=this.raca;
		while(!auxVertical.getNome().equalsIgnoreCase(usuarioParaExclusao.getRaca())) {
			auxVertical=auxVertical.getProximoRegistro();
		}
		Box auxHorizontal=auxVertical.getElementos();
		auxVertical.decrementaQtde_elementos();
		while(auxHorizontal.getDado().getProntuario()!=usuarioParaExclusao.getProntuario()) {
			auxHorizontal=auxHorizontal.getProximo();			
		}
		if(auxHorizontal.getProximo()==null && auxHorizontal.getAnterior()==null) {
			if(auxVertical.getRegistroAnterior()==null && auxVertical.getProximoRegistro()==null) {
				this.raca.setElementos(null);
				this.raca.setNome(null);
				this.raca.setProximoRegistro(null);
				this.raca.setRegistroAnterior(null);
			}else {
				if(auxVertical.getRegistroAnterior()==null) {					
					this.raca=auxVertical.getProximoRegistro();
					this.raca.setRegistroAnterior(null);
					auxVertical.setProximoRegistro(null);
				}else {
					if(auxVertical.getProximoRegistro()==null) {
						auxVertical.getRegistroAnterior().setProximoRegistro(null);
						auxVertical.setRegistroAnterior(null);
					}else {
						auxVertical.getRegistroAnterior().setProximoRegistro(auxVertical.getProximoRegistro());
						auxVertical.getProximoRegistro().setRegistroAnterior(auxVertical.getRegistroAnterior());
						auxVertical.setProximoRegistro(null);
						auxVertical.setRegistroAnterior(null);
					}
				}
				
			}
		}else{
			if(auxHorizontal.getProximo()==null) {
				auxHorizontal.getAnterior().setProximo(null);
				auxHorizontal.setAnterior(null);
			}else {
				if(auxHorizontal.getAnterior()==null) {
					auxVertical.setElementos(auxHorizontal.getProximo());
					auxHorizontal.getProximo().setAnterior(null);
					auxHorizontal.setProximo(null);
				}else {
					if(auxHorizontal.getProximo()!=null && auxHorizontal.getAnterior()!=null) {
						auxHorizontal.getAnterior().setProximo(auxHorizontal.getProximo());
						auxHorizontal.getProximo().setAnterior(auxHorizontal.getAnterior());
						auxHorizontal.setAnterior(null);
						auxHorizontal.setProximo(null);
					}
				}
			}
		}
	}
	
	private void removeDoDiretorioSexo(Usuario usuarioParaExclusao) {
		RegistroNoDiretorio auxVertical=this.sexo;
		while(!auxVertical.getNome().equalsIgnoreCase(usuarioParaExclusao.getSexo())) {
			auxVertical=auxVertical.getProximoRegistro();
		}
		Box auxHorizontal=auxVertical.getElementos();
		auxVertical.decrementaQtde_elementos();
		while(auxHorizontal.getDado().getProntuario()!=usuarioParaExclusao.getProntuario()) {
			auxHorizontal=auxHorizontal.getProximo();			
		}
		if(auxHorizontal.getProximo()==null && auxHorizontal.getAnterior()==null) {
			if(auxVertical.getRegistroAnterior()==null && auxVertical.getProximoRegistro()==null) {
				this.sexo.setElementos(null);
				this.sexo.setNome(null);
				this.sexo.setProximoRegistro(null);
				this.sexo.setRegistroAnterior(null);
			}else {
				if(auxVertical.getRegistroAnterior()==null) {					
					this.sexo=auxVertical.getProximoRegistro();
					this.sexo.setRegistroAnterior(null);
					auxVertical.setProximoRegistro(null);
				}else {
					if(auxVertical.getProximoRegistro()==null) {
						auxVertical.getRegistroAnterior().setProximoRegistro(null);
						auxVertical.setRegistroAnterior(null);
					}else {
						auxVertical.getRegistroAnterior().setProximoRegistro(auxVertical.getProximoRegistro());
						auxVertical.getProximoRegistro().setRegistroAnterior(auxVertical.getRegistroAnterior());
						auxVertical.setProximoRegistro(null);
						auxVertical.setRegistroAnterior(null);
					}
				}
				
			}
		}else{
			if(auxHorizontal.getProximo()==null) {
				auxHorizontal.getAnterior().setProximo(null);
				auxHorizontal.setAnterior(null);
			}else {
				if(auxHorizontal.getAnterior()==null) {
					auxVertical.setElementos(auxHorizontal.getProximo());
					auxHorizontal.getProximo().setAnterior(null);
					auxHorizontal.setProximo(null);
				}else {
					if(auxHorizontal.getProximo()!=null && auxHorizontal.getAnterior()!=null) {
						auxHorizontal.getAnterior().setProximo(auxHorizontal.getProximo());
						auxHorizontal.getProximo().setAnterior(auxHorizontal.getAnterior());
						auxHorizontal.setAnterior(null);
						auxHorizontal.setProximo(null);
					}
				}
			}
		}
	}
	
	public boolean contemUsuario(Usuario usuarioProcurado) {
		boolean resultado=false;
		Box aux=this.lista_de_dados;
		while(aux!=null && !resultado) {
			if(
			aux.getDado().getNome().equalsIgnoreCase(usuarioProcurado.getNome())

			&& aux.getDado().getData_nascimento().compareTo(usuarioProcurado.getData_nascimento())==0
			&& aux.getDado().getSexo().equalsIgnoreCase(usuarioProcurado.getSexo())) {
				resultado=true;
			}
			aux=aux.getProximo();					
		}		
		return resultado;
	}
	
	public List<Usuario> pesquisaUsuarioPeloNome(String nome) throws ExcecaoBaseVazia{
		List<Usuario> resultadoPesquisa=new ArrayList<Usuario>(); 
		if(qtde_usuarios==0) {
			throw new ExcecaoBaseVazia();
		}else {			
		
		String nomeUsuario;
		Box aux=this.lista_de_dados;
		while(aux!=null) {
			nomeUsuario=aux.getDado().getNome().toLowerCase();
			if(nomeUsuario.indexOf(nome.toLowerCase())!=-1) {
				resultadoPesquisa.add(aux.getDado());
			}
			aux=aux.getProximo();			
		}
		}
		return resultadoPesquisa;
	}
	
	public List<Usuario> pesquisaUsuarioPeloProntuario(int prontuario){		
		List<Usuario> resultadoPesquisa=new ArrayList<Usuario>(); 
		
		Box aux=this.lista_de_dados;
		while(aux!=null) {
			
			if(aux.getDado().getProntuario()==prontuario) {
				resultadoPesquisa.add(aux.getDado());
			}
			aux=aux.getProximo();			
		}
		return resultadoPesquisa;
	}
	
	public List<Usuario> pesquisaUsuarioPeloascimento(Date datanascimeto){		
		List<Usuario> resultadoPesquisa=new ArrayList<Usuario>(); 
		
		Box aux=this.lista_de_dados;
		while(aux!=null) {
			
			if(aux.getDado().getData_nascimento().compareTo(datanascimeto)==0) {
				resultadoPesquisa.add(aux.getDado());
			}
			aux=aux.getProximo();			
		}
		return resultadoPesquisa;
	}
	
	public ArrayList<String> retornaLinhasDosDiretorios(String opcao) {
		RegistroNoDiretorio aux=null;
		if(opcao.equalsIgnoreCase("Faixa etária")) {
			aux=this.faixa_etaria;
		}else {
			if(opcao.equalsIgnoreCase("sexo")) {
				aux=this.sexo;
			}else {
				if(opcao.equalsIgnoreCase("Raça/cor")) {
					aux=this.raca;
				}
			}
		}
		
		ArrayList<String> resultado=new ArrayList<String>(); 

		while(aux!=null) {
			if(aux.getElementos()!=null) {
						
				resultado.add(aux.getNome());		
			}
			aux=aux.getProximoRegistro();
		}
		return resultado;
		
	}

	public int getQtde_usuarios() {
		return qtde_usuarios;
	}

	public Box getLista_de_dados() {
		return lista_de_dados;
	}

	public int getNum_protuarios() {
		return num_protuarios;
	}
	
	private RegistroNoDiretorio retornaDiretorio(String registro) {
		RegistroNoDiretorio resultado=null;
		
		if(registro.equalsIgnoreCase("Sexo")) {
			resultado=this.sexo;
		}else {
			if(registro.equalsIgnoreCase("Raça/cor")) {
				resultado=this.raca;
			}else {
				if(registro.equalsIgnoreCase("Faixa etária")) {
					resultado=this.faixa_etaria;
				}
			}
		}
		
		return resultado;
		
	}

	private Box retornaElementosDoDiretorio(RegistroNoDiretorio linha_registro, String elementos) {
		RegistroNoDiretorio aux=linha_registro;
		Box resultado=null;
		boolean localizado=false;
		
		while(aux!=null && !localizado) {
			if(aux.getNome().equalsIgnoreCase(elementos)) {
				resultado=aux.getElementos();
				localizado=true;
			}
			aux=aux.getProximoRegistro();
		}		
		
		return resultado;
		
	}
	
	public ArrayList<Usuario> buscacomposta(String coluna1,String valor1,String coluna2,String valor2){
		ArrayList<Usuario> resultado=new ArrayList<Usuario>();
		Box parametro1=null;
		Box parametro2=null;
		
		parametro1=retornaElementosDoDiretorio(retornaDiretorio(coluna1), valor1);
		parametro2=retornaElementosDoDiretorio(retornaDiretorio(coluna2), valor2);
		
		Box aux_parametro1=parametro1;
		Box aux_parametro2=parametro2;
		
		while(aux_parametro1!=null) {	
			while(aux_parametro2!=null) {
				if(aux_parametro1.getDado().equals(aux_parametro2.getDado())) {
					resultado.add(aux_parametro1.getDado());
				}
				aux_parametro2=aux_parametro2.getProximo();				
			}
			aux_parametro1=aux_parametro1.getProximo();
			aux_parametro2=parametro2;
		}
		return resultado;
	}
	
	public RegistroNoDiretorio getSexo() {
		return sexo;
	}

	public RegistroNoDiretorio getRaca() {
		return raca;
	}

	public RegistroNoDiretorio getFaixa_etaria() {
		return faixa_etaria;
	}
	

	
}
