package jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios;

import java.util.Calendar;
import java.util.Date;

public class CalculadoraDeIdade {

	public static int calculeIdade(Date dataNasc) {
		Date hoje = new Date();
		Calendar cal = Calendar.getInstance();

		cal.setTime(hoje);
		int day1 = cal.get(Calendar.DAY_OF_YEAR);
		int ano1 = cal.get(Calendar.YEAR);

		cal.setTime(dataNasc);
		int day2 = cal.get(Calendar.DAY_OF_YEAR);
		int ano2 = cal.get(Calendar.YEAR);

		int nAno = ano1 - ano2;

		if (day1 < day2)
			nAno--; 

		return nAno;
	}

}
