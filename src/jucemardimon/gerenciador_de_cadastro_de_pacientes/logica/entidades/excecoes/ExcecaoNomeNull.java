package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes;

public class ExcecaoNomeNull extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoNomeNull() {
		super("O campo Nome de usuário está vazio.");
	}

}
