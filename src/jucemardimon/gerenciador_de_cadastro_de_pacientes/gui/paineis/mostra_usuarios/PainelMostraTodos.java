package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.mostra_usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.janelas.visualizar_historicos.JanelaVisualizarHistoricos;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.busca_simples.OpcoesDeBusca;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu.OpcoesDeMenu;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Box;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Usuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios.ConversorDeDatas;

public class PainelMostraTodos extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo_tabela;
	JTable tabela_usuarios;
	JanelaPrincipal igu;
	String[] nomes_colunas;
	JLabel campo_Qtde_usuatios;
	JButton botao_visualizar_historico;
	JButton botao_cancelar;
	JLabel titulo;

	
	
	public PainelMostraTodos(JanelaPrincipal janelaPrincipal) {
		igu=janelaPrincipal;
		definaComponentes();
		posicionaComponentes();
	}

	private void posicionaComponentes() {

		JScrollPane scrollpane = new JScrollPane(tabela_usuarios);
		
		JSeparator linha=new JSeparator();
		
		setLayout(new MigLayout("","[grow]",""));

		add(titulo,"wrap");
		add(linha,"growx,wrap");
		add(scrollpane,"growx,gaptop 15, wrap,height 451");
		add(campo_Qtde_usuatios,"split 3,gaptop 15,growx");
		add(botao_visualizar_historico,"right");
		add(botao_cancelar);
		
		
	}

	public void preencheTabela() {
		Box aux=igu.getGerenciador_dados().getLista_de_dados();
		if(igu.getGerenciador_dados().getQtde_usuarios()==0) {
			limpaCampos();
			atualizaQtde();
		}else {

		
			tabela_usuarios.getColumnModel().getColumn(0).setPreferredWidth(8);
			tabela_usuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
			tabela_usuarios.getColumnModel().getColumn(2).setPreferredWidth(80);
			tabela_usuarios.getColumnModel().getColumn(3).setPreferredWidth(50);
			tabela_usuarios.getColumnModel().getColumn(4).setPreferredWidth(50);
			
			modelo_tabela.setNumRows(0);
			while(aux!=null) {
				modelo_tabela.addRow(new Object[] {
						aux.getDado().getProntuario(),
						aux.getDado().getNome(),
						ConversorDeDatas.converteDateParaString(aux.getDado().getData_nascimento()),
						aux.getDado().getSexo(),
						aux.getDado().getRaca()
				});
				aux=aux.getProximo();
			}
			
			tabela_usuarios.setModel(modelo_tabela);
			atualizaQtde();
		}

		
	}

	private void limpaCampos() {
		modelo_tabela.setRowCount(0);		
		campo_Qtde_usuatios.setText(" ");
		
		
	}

	private void atualizaQtde() {
		campo_Qtde_usuatios.setText("Total de "+igu.getGerenciador_dados().getQtde_usuarios()+" usuário(s) cadastrado(s).");
		
		
	}

	private void definaComponentes() {
		tabela_usuarios=new JTable();		
		nomes_colunas=new String[] {"Prontuário","Nome","Nascimento","Sexo","Raça/cor"};
		

		
		
		modelo_tabela=new DefaultTableModel();
		
		titulo=new JLabel("Apresentação da massa de dados");
		
		modelo_tabela.setColumnIdentifiers(nomes_colunas);
		tabela_usuarios.setModel(modelo_tabela);



		campo_Qtde_usuatios=new JLabel();


		botao_cancelar=new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesDeBusca.CANCELAR.name());
		botao_cancelar.addActionListener(this);
		
		botao_visualizar_historico=new JButton("Histórico");
		botao_visualizar_historico.setActionCommand(OpcoesDeBusca.VISUALIZAR_HISTORICO.name());
		botao_visualizar_historico.addActionListener(this);		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDeBusca opcao = OpcoesDeBusca.valueOf(e.getActionCommand());

		switch (opcao) {

		case CANCELAR:
			fecharPainel();
			

			break;

			
		case VISUALIZAR_HISTORICO:
			visualizarHistoricos(igu);
			
		}
	}
	
	private void visualizarHistoricos(JanelaPrincipal igu) {
		int selecao=tabela_usuarios.getSelectedRow();
		Box aux=igu.getGerenciador_dados().getLista_de_dados();
		for(int i=1;i<=selecao;i++) {
			aux=aux.getProximo();
		}
		Usuario usuario=aux.getDado();
		if(selecao!=-1) {
			new JanelaVisualizarHistoricos(igu,usuario);
		}			
		
	}
	
	private void fecharPainel() {
		limpaCampos();
		igu.abrePainel(OpcoesDeMenu.SOBRE.name());

	}




	
	


	


}
