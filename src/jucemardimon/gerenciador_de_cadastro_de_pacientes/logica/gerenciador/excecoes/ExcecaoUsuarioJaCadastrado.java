package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes;

public class ExcecaoUsuarioJaCadastrado extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoUsuarioJaCadastrado() {
		super("O Usu�rio informado j� possui cadastro na base de dados!");
	}

}
