package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.cabecalho;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;

public class PainelCabecalho extends JPanel{
	JLabel titulo;


	public PainelCabecalho(JanelaPrincipal janelaPrincipal) {
		
		

		
		titulo=new JLabel();
		titulo.setText("Gerenciador de Cadastro de Pacientes");
		titulo.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		

		

		add(titulo);		


		
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));	
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
