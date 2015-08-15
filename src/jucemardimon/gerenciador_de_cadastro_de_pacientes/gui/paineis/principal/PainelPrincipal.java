package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.principal;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;

public class PainelPrincipal extends JPanel{
	JanelaPrincipal igu;



	private static final long serialVersionUID = 1L;
	
	public PainelPrincipal(JanelaPrincipal janelaPrincipal) {
		
		igu=janelaPrincipal;

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));	
	}

}
