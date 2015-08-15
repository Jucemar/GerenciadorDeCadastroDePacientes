package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.manipulador_arquivos.excecoes;

public class ExcecaoArquivoJaExiste extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoArquivoJaExiste(String nomeDoArquivo) {
		super(nomeDoArquivo);
	}

}
