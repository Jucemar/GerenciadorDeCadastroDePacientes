package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.barra_status;

import java.awt.Color;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;

public class PainelBarraStatus extends JPanel{	

	private static final long serialVersionUID = 1L;
	
	JTextField endereco_arquivo;
	JLabel rotulo_arquivo;
	JLabel campo_iformativo;

	public PainelBarraStatus(JanelaPrincipal janelaPrincipal) {
		
		definaComponentes();
		posicionaComponentes();
		
	}

	private void posicionaComponentes() {
		
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
		setLayout(new MigLayout("","[grow]",""));
		
		add(rotulo_arquivo,"split 2");
		add(endereco_arquivo,"width 300");
		add(campo_iformativo,"right");	
		
	}

	private void definaComponentes() {
		
		rotulo_arquivo=new JLabel("Arquivo em uso: ");
		
		endereco_arquivo=new JTextField();
		endereco_arquivo.setEditable(false);
		
		campo_iformativo=new JLabel(" ");


		
	}
	
	public void atualizaCampoEndereco(File arquivo) {
		endereco_arquivo.setText(arquivo.getAbsolutePath());
		
		
		
	}

	public void atualizaCampoInformativo(String texto,String status) {
		if(status.equalsIgnoreCase("erro")) {
			campo_iformativo.setText(texto);
			campo_iformativo.setForeground(Color.red);
		}else {
			if(status.equalsIgnoreCase("ok")) {
				campo_iformativo.setText(texto);
				campo_iformativo.setForeground(Color.blue);				
			}
		}
	}
	 public void limpaCampoInformativo() {
			campo_iformativo.setText(" ");
			campo_iformativo.setForeground(Color.black);		 
		 
	 }

}
