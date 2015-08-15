package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.auxiliar.PainelAuxiliar;

public class PainelMenu extends JPanel{
	PainelMenuCadastro painel_menu_cadastro;	
	PainelMenuArquivo painel_menu_arquivo;
	PainelAuxiliar painel_auxiliar;
	JanelaPrincipal igu;


	private static final long serialVersionUID = 1L;
	
	public PainelMenu(JanelaPrincipal janelaPrincipal) {
		setBackground(Color.white);
		igu=janelaPrincipal;
		definaComponentes();
		posicionaComponentes();
	}

	private void posicionaComponentes() {
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);



		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		layout.linkSize(SwingConstants.HORIZONTAL, painel_menu_cadastro,painel_auxiliar,
				painel_menu_arquivo);

		horizontal.addGroup(layout.createParallelGroup()				
				.addComponent(painel_menu_cadastro)
				.addComponent(painel_menu_arquivo)
				.addComponent(painel_auxiliar)
				);		
		
		
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(painel_menu_cadastro));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(painel_menu_arquivo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(painel_auxiliar));
		
		layout.setVerticalGroup(vertical);
		
	}


	private void definaComponentes() {
		painel_menu_arquivo=new PainelMenuArquivo(igu);
		painel_menu_cadastro=new PainelMenuCadastro(igu);
		painel_auxiliar=new PainelAuxiliar(igu);
		
	}

	public PainelAuxiliar getPainel_auxiliar() {
		return painel_auxiliar;
	}
		
	

}
