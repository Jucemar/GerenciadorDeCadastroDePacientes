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



public class PainelMenuCadastro extends JPanel implements ActionListener{
	JLabel titulo;
	JButton botao_cadastrar_usuario;
	JButton botao_excluir_usuario;
	JButton botao_busca_simples;
	JButton botao_busca_avancada;
	JButton botao_mostra_base_dados;
	JanelaPrincipal igu;

	private static final long serialVersionUID = 1L;
	
	
	public PainelMenuCadastro(JanelaPrincipal janelaPrincipal) {
		this.igu=janelaPrincipal;
		definaComponentes();
		posicioneComponentes();	
	}


	private void posicioneComponentes() {


		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);

		layout.setAutoCreateGaps(true);

		layout.setAutoCreateContainerGaps(true);
		
		layout.linkSize(SwingConstants.HORIZONTAL, botao_cadastrar_usuario,titulo,
				botao_excluir_usuario, botao_busca_simples, botao_busca_avancada,botao_mostra_base_dados);

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();		

		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(titulo)
				.addComponent(botao_cadastrar_usuario)
				.addComponent(botao_excluir_usuario)
				.addComponent(botao_busca_simples)
				.addComponent(botao_busca_avancada)
				.addComponent(botao_mostra_base_dados)
				);		
		
		
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(titulo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_cadastrar_usuario));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_excluir_usuario));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_busca_simples));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_busca_avancada));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(botao_mostra_base_dados));
		
		
		layout.setVerticalGroup(vertical);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));	
		
	}


	private void definaComponentes() {
		titulo=new JLabel();
		titulo.setText("Menu de cadastro");
		
		botao_cadastrar_usuario=new JButton("Cadastrar usuário");
		botao_cadastrar_usuario.setActionCommand(OpcoesDeMenu.CADASTRAR_USUARIO.name());
		botao_cadastrar_usuario.addActionListener(this);
		
		botao_excluir_usuario=new JButton("Excluir usuário");
		botao_excluir_usuario.setActionCommand(OpcoesDeMenu.EXCLUIR_USUARIO.name());
		botao_excluir_usuario.addActionListener(this);
		
		botao_busca_simples=new JButton("Busca simples");
		botao_busca_simples.setActionCommand(OpcoesDeMenu.BUSCA_SIMPLES.name());
		botao_busca_simples.addActionListener(this);
		
		botao_busca_avancada=new JButton("Busca avançada");
		botao_busca_avancada.setActionCommand(OpcoesDeMenu.BUSCA_AVANCADA.name());
		botao_busca_avancada.addActionListener(this);
		
		botao_mostra_base_dados=new JButton("Mostrar dados");
		botao_mostra_base_dados.setActionCommand(OpcoesDeMenu.MOSTRA_USUARIOS.name());
		botao_mostra_base_dados.addActionListener(this);
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDeMenu opcao = OpcoesDeMenu.valueOf(e.getActionCommand());

		switch (opcao) {
		
		case CADASTRAR_USUARIO:
			igu.abrePainel(OpcoesDeMenu.CADASTRAR_USUARIO.name());
			igu.getPainel_cadastro().limpaCampos();
			igu.getPainel_cadastro().atualizaumProtuario();
			
			
			break;	
			
		case EXCLUIR_USUARIO:	
			igu.abrePainel(OpcoesDeMenu.EXCLUIR_USUARIO.name());
			igu.getPainel_excluir().limpaCampos();
			
			
			break;
			
		case BUSCA_SIMPLES:	
			igu.abrePainel(OpcoesDeMenu.BUSCA_SIMPLES.name());
			igu.getPainel_busca_simples().limpaCampos();
			
			
			break;
			
		case BUSCA_AVANCADA:
			igu.abrePainel(OpcoesDeMenu.BUSCA_AVANCADA.name());
			igu.getPainel_busca_avancada().limpaCampos();
			

			break;
			
		case MOSTRA_USUARIOS:	
			igu.abrePainel(OpcoesDeMenu.MOSTRA_USUARIOS.name());
			igu.getPainel_mostra_usuarios().preencheTabela();
			
			
		}
			

		
	}



		
	

}
