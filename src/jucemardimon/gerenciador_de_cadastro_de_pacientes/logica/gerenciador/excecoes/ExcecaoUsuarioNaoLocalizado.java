package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes;

public class ExcecaoUsuarioNaoLocalizado extends Exception {

	private static final long serialVersionUID = 1L;
	 
	public ExcecaoUsuarioNaoLocalizado() {
		super("O Usu�rio procurado n�o foi localizado");	
	}
	

}
