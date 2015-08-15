package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes;

public class ExcecaoUsuarioJaCadastrado extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoUsuarioJaCadastrado() {
		super("O Usuário informado já possui cadastro na base de dados!");
	}

}
