package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes;

public class ExcecaoBaseVazia extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoBaseVazia() {
		super("Não existe nenhum usuário cadastrado na base de dados.");
	}

}
