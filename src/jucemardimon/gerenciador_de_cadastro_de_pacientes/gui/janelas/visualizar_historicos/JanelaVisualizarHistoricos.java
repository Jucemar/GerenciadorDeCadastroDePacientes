package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.janelas.visualizar_historicos;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Usuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios.CalculadoraDeIdade;
import net.miginfocom.swing.MigLayout;

public class JanelaVisualizarHistoricos extends JDialog{

	private static final long serialVersionUID = 1L;
	
	JLabel rotulo_nome;
	JLabel rotulo_idade;
	JLabel rotulo_titulo;

	JLabel rotulo_historico;
	JLabel campo_nome;
	JLabel campo_idade;
	JTextArea campo_historico;
	
	public JanelaVisualizarHistoricos(JanelaPrincipal igu, Usuario usuario) {
		super(igu,"Visualizar histórico de atendimentos",true);
		setSize(new Dimension(400,400));
		setResizable(false);
		setLocationRelativeTo(igu);
		definaComponentes();
		posicionaComponentes();
		preencheDados(usuario);
		setVisible(true);
		
		
	}

	private void preencheDados(Usuario usuario) {
		campo_nome.setText(usuario.getNome());
		campo_idade.setText(Integer.toString(CalculadoraDeIdade.calculeIdade(usuario.getData_nascimento())));
		campo_historico.setText(usuario.getHistorico());
		
	}

	private void posicionaComponentes() {
		JSeparator linha1=new JSeparator();
		JSeparator linha2=new JSeparator();
		
		JScrollPane scroll_campo_historico=new JScrollPane(campo_historico,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane. HORIZONTAL_SCROLLBAR_NEVER);
		scroll_campo_historico.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		
		setLayout(new MigLayout("","[grow]",""));
		

		add(rotulo_titulo,"wrap");
		add(linha1,"wrap,growx");
		add(rotulo_nome,"split 4,gaptop 15");
		add(campo_nome,"gaptop 15,width 250");
		add(rotulo_idade,"gaptop 15,gapleft 15");
		add(campo_idade,"wrap,gaptop 15");
		add(rotulo_historico,"wrap,gaptop 15");
		add(linha2,"wrap,growx");
		add(scroll_campo_historico,"growx,gaptop 15,height 235");
		
	}

	private void definaComponentes() {
		rotulo_nome=new JLabel("Nome:");
		rotulo_idade=new JLabel("Idade:");
		rotulo_titulo=new JLabel("Paciente:");
		
		rotulo_historico=new JLabel("Consultas e/ou exames realizados:");
		campo_nome=new JLabel(" ");
		campo_idade=new JLabel(" ");
		
		campo_historico=new JTextArea();
		campo_historico.setEditable(false);
		campo_historico.setLineWrap(true);
		
		
		
	}
	
	

}
