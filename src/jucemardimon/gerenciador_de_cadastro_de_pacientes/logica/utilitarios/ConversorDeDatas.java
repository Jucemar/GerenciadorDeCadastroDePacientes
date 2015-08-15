package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDeDatas {
	
	public static Date converteStringParaDate(String dataDeNascimento) throws ParseException {
		dataDeNascimento.trim();
		Date dataFormatada = new Date();
		SimpleDateFormat formatandoData = new SimpleDateFormat("dd/MM/yyyy");
		dataFormatada = formatandoData.parse(dataDeNascimento);
		return dataFormatada;
	}

	public static String converteDateParaString(Date dataDeNascimento) {
		DateFormat formatandoData = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatandoData.format(dataDeNascimento);
		return dataFormatada;
	}	
	

}
