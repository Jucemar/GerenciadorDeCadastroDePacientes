package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;

public class PainelMenuArquivo extends JPanel implements ActionListener{
	JLabel titulo;
	JButton botao_abrir;
	JButton botao_salvar;
	JButton botao_salvar_como;
	JButton botao_sair;
	JanelaPrincipal igu;


	private static final long serialVersionUID = 1L;
	
	public PainelMenuArquivo(JanelaPrincipal janelaPrincipal) {
		igu=janelaPrincipal;
		definaComponentes();
		posicioneComponentes();	
	}


	private void posicioneComponentes() {


		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);

		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		layout.linkSize(SwingConstants.HORIZONTAL, botao_abrir,titulo,
				botao_salvar, botao_salvar_como, botao_sair);

		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(titulo)
				.addComponent(botao_abrir)
				.addComponent(botao_salvar)
				.addComponent(botao_salvar_como)
				.addComponent(botao_sair)
				);		
		
		
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(titulo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_abrir));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_salvar));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_salvar_como));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_sair));
		
		
		layout.setVerticalGroup(vertical);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));	
		
	}


	private void definaComponentes() {
		titulo=new JLabel();
		titulo.setText("Menu de arquivo");
		
		botao_abrir=new JButton("Abrir arquivo");
		botao_abrir.setActionCommand(OpcoesDeMenu.ABRIR.name());
		botao_abrir.addActionListener(this);
		
		botao_salvar=new JButton("Salvar arquivo");
		botao_salvar.setActionCommand(OpcoesDeMenu.SALVAR.name());
		botao_salvar.addActionListener(this);
		
		botao_salvar_como=new JButton("Salvar como");
		botao_salvar_como.setActionCommand(OpcoesDeMenu.SALVAR_COMO.name());
		botao_salvar_como.addActionListener(this);
		
		botao_sair=new JButton("Fechar programa");
		botao_sair.setActionCommand(OpcoesDeMenu.SAIR.name());
		botao_sair.addActionListener(this);		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDeMenu opcao = OpcoesDeMenu.valueOf(e.getActionCommand());

		switch (opcao) {
		
		case ABRIR:
			igu.getPainel_barra_status().limpaCampoInformativo();
			igu.leiaBaseDeArquivo();

			
			
			
			break;	
			
		case SALVAR:
			igu.getPainel_barra_status().limpaCampoInformativo();
			igu.salveBaseEmArquivo();
			
			
			
			break;
			
		case SALVAR_COMO:
			igu.getPainel_barra_status().limpaCampoInformativo();
			igu.salveBaseEmArquivoAlternativo();
			
			
			break;
			
		case SAIR:
			igu.fecharPrograma();
			
		}
		
	}

}
