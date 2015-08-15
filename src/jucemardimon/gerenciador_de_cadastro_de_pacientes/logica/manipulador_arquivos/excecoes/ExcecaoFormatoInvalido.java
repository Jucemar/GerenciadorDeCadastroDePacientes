package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.manipulador_arquivos.excecoes;

public class ExcecaoFormatoInvalido extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoFormatoInvalido() {
		super("Arquivo com formato incompatível!");
	}

}
