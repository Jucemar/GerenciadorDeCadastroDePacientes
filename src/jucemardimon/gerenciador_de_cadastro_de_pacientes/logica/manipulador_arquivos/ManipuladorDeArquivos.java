package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.manipulador_arquivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.Gerenciador;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.manipulador_arquivos.excecoes.ExcecaoFormatoInvalido;



public class ManipuladorDeArquivos {
	private Gerenciador gerenciador;

	public ManipuladorDeArquivos() {

	}

	public void salveBaseDeDadosEmArquivo(File arquivo) throws IOException {

		ObjectOutputStream outputStream = null;

		try {
			outputStream = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(arquivo)));

			outputStream.writeObject(this.gerenciador);

		} finally {
			if (outputStream != null)
				outputStream.close();
		}

	}

	public void leiaBaseDeDadosParaMemoria(File arquivo) throws IOException,ExcecaoFormatoInvalido {
		ObjectInputStream inputStream = null;

		try {
			inputStream = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(arquivo)));

			this.gerenciador = (Gerenciador) inputStream.readObject();


		} catch (EOFException e) {

		} catch (ClassNotFoundException e) {
			throw new ExcecaoFormatoInvalido();
		}

		finally {
			if (inputStream != null)
				inputStream.close();
		}
	}

	public Gerenciador getGerenciador() {
		return gerenciador;
	}

	public void setGerenciador(Gerenciador gerenciador) {
		this.gerenciador = gerenciador;
	}
	
	


	

}
