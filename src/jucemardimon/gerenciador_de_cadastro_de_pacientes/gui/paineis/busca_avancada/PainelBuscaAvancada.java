package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.busca_avancada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.janelas.visualizar_historicos.JanelaVisualizarHistoricos;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu.OpcoesDeMenu;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Usuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios.ConversorDeDatas;

public class PainelBuscaAvancada extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JanelaPrincipal igu;
	DefaultTableModel modelo_tabela;
	DefaultComboBoxModel modelo_combobox_valor_1;
	DefaultComboBoxModel modelo_combobox_coluna_1;
	DefaultComboBoxModel modelo_combobox_coluna_2;
	DefaultComboBoxModel modelo_combobox_valor_2;
	JTable tabela_resultados;
	JComboBox combobox_coluna_1;
	JComboBox combobox_coluna_2;
	JComboBox combobox_valor_1;
	JComboBox combobox_valor_2;
	JButton botao_pesquisar;
	JButton botao_limpar;
	JButton botao_cancelar;
	JButton botao_visualizar_historico;
	JLabel titulo;
	JLabel rotulo_pesquisa;
	JLabel rotulo_coluna_2;
	JLabel rotulo_valor_2;
	JLabel rotulo_coluna_1;
	JLabel rotulo_valor_1;
	JLabel rotulo_resultados;
	JLabel qtde_resultados;
	JLabel erros_selecao_campos;
	String[] nomes_colunas;
	ArrayList<Usuario> resultado_pesquisa;


	
	public PainelBuscaAvancada(JanelaPrincipal janelaPrincipal) {
		igu=janelaPrincipal;
		
		nomes_colunas=new String[] {"Prontuário","Nome","Nascimento","Sexo","Raça/cor"};
		
		modelo_tabela=new DefaultTableModel();
		modelo_tabela.setColumnIdentifiers(nomes_colunas);
		
		modelo_combobox_valor_1=new DefaultComboBoxModel();
		modelo_combobox_coluna_2=new DefaultComboBoxModel(new String[] {"","Faixa etária","Sexo","Raça/cor"});
		modelo_combobox_valor_2=new DefaultComboBoxModel();
		modelo_combobox_coluna_1=new DefaultComboBoxModel(new String[] {"","Faixa etária","Sexo","Raça/cor"});
		
		
		
		definaComponentes();
		posicionaComponenstes();
		
	}

	private void posicionaComponenstes() {
		setLayout(new MigLayout("","[grow]",""));
		
		JSeparator linha1=new JSeparator();
		JSeparator linha2=new JSeparator();
		JSeparator linha3=new JSeparator();
		JScrollPane scroll_tabela=new JScrollPane(tabela_resultados);
		
		add(titulo,"wrap");
		add(linha1,"growx,wrap");
		add(rotulo_pesquisa,"wrap,center,gaptop 30");
		add(linha2,"width 410,wrap,center");
		add(combobox_coluna_1,"width 100,split 4,center");
		add(combobox_valor_1,"width 100");
		add(combobox_coluna_2,"width 100");
		add(combobox_valor_2,"width 100,wrap");
		add(rotulo_coluna_1,"split 4,width 50,gapleft 135,gaptop 15");
		add(rotulo_valor_1,"width 50,gapleft 55");
		add(rotulo_coluna_2,"width 50,gapleft 55");
		add(rotulo_valor_2,"width 50,wrap,gapleft 55");		
		add(botao_limpar,"split 2,center,width 100,gaptop 15");
		add(botao_pesquisar,"wrap,width 100,center,gaptop 15");
		add(erros_selecao_campos,"wrap,gaptop 15,center");
		add(rotulo_resultados,"wrap,gaptop 30");
		add(linha3,"growx,wrap");
		add(scroll_tabela,"growx,wrap,gaptop 15");
		add(qtde_resultados,",growx,split 3,gaptop 15");
		add(botao_visualizar_historico);
		add(botao_cancelar);
		
		
	}

	private void definaComponentes() {

		tabela_resultados=new JTable(modelo_tabela);
		
		
		
		combobox_coluna_1=new JComboBox(modelo_combobox_coluna_1);
		combobox_coluna_1.addActionListener(this);
		combobox_coluna_1.setActionCommand(OpcoesDeBuscaAvancada.COMBOBOX_COLUNA_1.name());
		
		
		combobox_valor_1=new JComboBox(modelo_combobox_valor_1);
		combobox_valor_1.addActionListener(this);
		combobox_valor_1.setActionCommand(OpcoesDeBuscaAvancada.COMBOBOX_VALOR_1.name());	

		combobox_coluna_2=new JComboBox(modelo_combobox_coluna_2);
		combobox_coluna_2.addActionListener(this);
		combobox_coluna_2.setActionCommand(OpcoesDeBuscaAvancada.COMBOBOX_COLUNA_2.name());
		
		combobox_valor_2=new JComboBox(modelo_combobox_valor_2);
		combobox_valor_2.addActionListener(this);
		combobox_valor_2.setActionCommand(OpcoesDeBuscaAvancada.COMBOBOX_VALOR_2.name());	
		
		botao_pesquisar=new JButton("Pesquisar");
		botao_pesquisar.setActionCommand(OpcoesDeBuscaAvancada.PESQUISAR.name());
		botao_pesquisar.addActionListener(this);
		
		botao_limpar=new JButton("Limpar");
		botao_limpar.setActionCommand(OpcoesDeBuscaAvancada.LIMPAR_CAMPOS.name());
		botao_limpar.addActionListener(this);
		
		botao_cancelar=new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesDeBuscaAvancada.CANCELAR.name());
		botao_cancelar.addActionListener(this);
		
		botao_visualizar_historico=new JButton("Histórico");
		botao_visualizar_historico.setActionCommand(OpcoesDeBuscaAvancada.VISUALIZAR_HISTORICO.name());
		botao_visualizar_historico.addActionListener(this);
		
		titulo=new JLabel("Busca refinada");
		
		rotulo_pesquisa=new JLabel("Selecione as opções de pesquisa");
		
		rotulo_resultados=new JLabel("Resultados de busca");
		
		qtde_resultados=new JLabel("Testando testando testando");
		
		rotulo_coluna_1=new JLabel("Coluna 01");
		
		rotulo_valor_1=new JLabel("Valor 01");
		
		rotulo_coluna_2=new JLabel("Coluna 02");  
		rotulo_valor_2=new JLabel("Valor 02");
		
		erros_selecao_campos=new JLabel("Testando testando testando");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDeBuscaAvancada opcao = OpcoesDeBuscaAvancada.valueOf(e.getActionCommand());

		switch (opcao) {

		case CANCELAR:
			fecharPainel();
			
			

			break;
			
		case PESQUISAR:
			pesquisar();
			
			

			break;
			
		case COMBOBOX_COLUNA_1:
			atualizaComboboxValor1(); 
			
			

			break;
			
		case COMBOBOX_COLUNA_2:
			atualizaComboboxValor2();
			
			

			break;

		case LIMPAR_CAMPOS:
			limpaCampos();
			
			
			break;
			
		case VISUALIZAR_HISTORICO:
			visualizarHistoricos(igu); 			

			
		}
	}
	
	private void pesquisar() {
		if(igu.getGerenciador_dados().getQtde_usuarios()>0) {
			
			if(combobox_coluna_1.getSelectedIndex()>0 &&
			   combobox_valor_1.getSelectedIndex()>0 &&
			   combobox_coluna_2.getSelectedIndex()>0 &&
			   combobox_valor_2.getSelectedIndex()>0) {
				
				if(combobox_coluna_1.getSelectedIndex()!=combobox_valor_2.getSelectedIndex()) {
					iniciaBusca();
					
				}else {
					erros_selecao_campos.setText("Para realizar a busca composta é obrigatório selecionar colunas diferentes.");
				}
				
			}else {
				erros_selecao_campos.setText("É obrigatório cofigurar todos os campos antes de iniciar a pesquisa.");
			}
					
		}else {
			erros_selecao_campos.setText("Não há nenhum registro na base de dados.");
		}		
		
	}

	private void iniciaBusca() {
		String coluna1=combobox_coluna_1.getSelectedItem().toString();
		String valor1=combobox_valor_1.getSelectedItem().toString();
		String coluna2=combobox_coluna_2.getSelectedItem().toString();
		String valor2=combobox_valor_2.getSelectedItem().toString();
		
		ArrayList<Usuario> resultado=igu.getGerenciador_dados().buscacomposta(coluna1, valor1, coluna2, valor2);		
		preencheTabela(resultado);
		erros_selecao_campos.setText("Busca efetuada com sucesso.");

		
	}
	
	private void atualizaComboboxValor1() {
		modelo_combobox_valor_1.removeAllElements();	
		String coluna_1=combobox_coluna_1.getSelectedItem().toString();
		ArrayList<String> valores_1=igu.getGerenciador_dados().retornaLinhasDosDiretorios(coluna_1);
		
		modelo_combobox_valor_1.addElement("");
		for (String string : valores_1) {
			modelo_combobox_valor_1.addElement(string);			
		}		
	}
	
	private void atualizaComboboxValor2() {
		modelo_combobox_valor_2.removeAllElements();	
		String coluna_2=combobox_coluna_2.getSelectedItem().toString();
		ArrayList<String> valores_2=igu.getGerenciador_dados().retornaLinhasDosDiretorios(coluna_2);
		
		modelo_combobox_valor_2.addElement("");
		for (String string : valores_2) {
			modelo_combobox_valor_2.addElement(string);			
		}		
	}

	private void preencheTabela(ArrayList<Usuario> resultado) {
		tabela_resultados.getColumnModel().getColumn(0).setPreferredWidth(8);
		tabela_resultados.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela_resultados.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabela_resultados.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabela_resultados.getColumnModel().getColumn(4).setPreferredWidth(50);

		modelo_tabela.setNumRows(0);
		for (Usuario usuario : resultado) {
			modelo_tabela.addRow(new Object[] {
					usuario.getProntuario(),
					usuario.getNome(),
					ConversorDeDatas.converteDateParaString(usuario
							.getData_nascimento()), usuario.getSexo(),
					usuario.getRaca() });
		}
		tabela_resultados.setModel(modelo_tabela);
		qtde_resultados.setText("Foram encontrado(s) "+resultado.size()+" usuário(s).");

	}
	
	private void fecharPainel() {
		limpaCampos();
		igu.abrePainel(OpcoesDeMenu.SOBRE.name());


	}
	
	private void visualizarHistoricos(JanelaPrincipal igu) {
		int selecao=tabela_resultados.getSelectedRow();
		if(selecao!=-1) {
			new JanelaVisualizarHistoricos(igu,resultado_pesquisa.get(selecao));
		}
			
		
	}

	public void limpaCampos() {		
		modelo_tabela.setRowCount(0);
		combobox_coluna_1.setSelectedIndex(0);
		combobox_coluna_2.setSelectedIndex(0);
		modelo_combobox_valor_1.removeAllElements();		
		modelo_combobox_valor_2.removeAllElements();		
		qtde_resultados.setText(" ");
		erros_selecao_campos.setText(" ");		
	}



}
