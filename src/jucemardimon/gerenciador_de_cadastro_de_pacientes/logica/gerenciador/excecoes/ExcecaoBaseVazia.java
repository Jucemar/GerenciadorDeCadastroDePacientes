package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes;

public class ExcecaoBaseVazia extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoBaseVazia() {
		super("N�o existe nenhum usu�rio cadastrado na base de dados.");
	}

}
