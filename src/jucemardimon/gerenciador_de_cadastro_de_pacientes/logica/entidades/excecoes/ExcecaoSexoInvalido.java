package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes;

public class ExcecaoSexoInvalido extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoSexoInvalido() {
		super("O campo Sexo está vazio.");
	}

}
