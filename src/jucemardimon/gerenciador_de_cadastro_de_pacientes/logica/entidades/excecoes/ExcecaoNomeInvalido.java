package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes;

public class ExcecaoNomeInvalido extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoNomeInvalido() {
		super("O nome de usuário informado não é válido.");
	}
	

}
