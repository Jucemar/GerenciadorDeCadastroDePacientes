package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes;

public class ExcecaoRacaInvalida extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcecaoRacaInvalida() {
		super("O campo Ra�a/Cor est� vazio.");
	}
	

}
