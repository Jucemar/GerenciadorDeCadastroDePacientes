package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.busca_simples;

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

public class PainelBuscaSimples extends JPanel implements ActionListener{
	
	JanelaPrincipal igu;
	DefaultTableModel modelo_tabela;
	DefaultComboBoxModel modelo_comobox_1;
	JTable tabela_resultados;
	JComboBox combobox_coluna;
	JComboBox combobox_valor;
	JButton botao_pesquisar;
	JButton botao_visualizar_historico;
	JButton botao_limpar;
	JButton botao_cancelar;
	JLabel titulo;
	JLabel rotulo_pesquisa;
	JLabel rotulo_coluna;
	JLabel rotulo_valor;
	JLabel rotulo_resultados;
	JLabel qtde_resultados;
	JLabel erros_selecao_campos;
	String[] nomes_colunas;
	ArrayList<Usuario> resultado_pesquisa;

	private static final long serialVersionUID = 1L;
	
	public PainelBuscaSimples(JanelaPrincipal janelaPrincipal) {
		igu=janelaPrincipal;
		
		nomes_colunas=new String[] {"Prontuário","Nome","Nascimento","Sexo","Raça/cor"};
		
		modelo_tabela=new DefaultTableModel();
		modelo_tabela.setColumnIdentifiers(nomes_colunas);
		modelo_comobox_1=new DefaultComboBoxModel();
		
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
		add(rotulo_pesquisa,"span,center,gaptop 30");
		add(linha3,"center,width 300,wrap");
		add(rotulo_coluna,"split 2,center,gaptop 15");
		add(rotulo_valor,"wrap,gapleft 120,,gaptop 15");
		add(combobox_coluna,"split 2,center,width 150");
		add(combobox_valor,",wrap,center,width 150");
		add(botao_limpar,"split 2,center,width 100,,gaptop 15");
		add(botao_pesquisar,"wrap,center,width 100,,gaptop 15");
		add(erros_selecao_campos,"span,center,wrap,gaptop 15");
		add(rotulo_resultados,"wrap,gaptop 30");
		add(linha2,"growx,wrap");
		add(scroll_tabela,"growx,wrap,gaptop 15");
		add(qtde_resultados,"width 550,split 3,gaptop 15");
		add(botao_visualizar_historico);
		add(botao_cancelar);
		
		
	}

	private void definaComponentes() {

		tabela_resultados=new JTable(modelo_tabela);
		
		String[] opcoes_combobox=new String[] {"","Faixa etária","Sexo","Raça/cor"};
		
		combobox_coluna=new JComboBox(opcoes_combobox);
		combobox_coluna.addActionListener(this);
		combobox_coluna.setActionCommand(OpcoesDeBusca.COMBOBOX_COLUNA_1.name());
		
		
		combobox_valor=new JComboBox(modelo_comobox_1);
		
		
		botao_pesquisar=new JButton("Pesquisar");
		botao_pesquisar.setActionCommand(OpcoesDeBusca.PESQUISAR.name());
		botao_pesquisar.addActionListener(this);
		
		botao_limpar=new JButton("Limpar");
		botao_limpar.setActionCommand(OpcoesDeBusca.LIMPAR_CAMPOS.name());
		botao_limpar.addActionListener(this);
		
		botao_cancelar=new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesDeBusca.CANCELAR.name());
		botao_cancelar.addActionListener(this);
		
		botao_visualizar_historico=new JButton("Histórico");
		botao_visualizar_historico.setActionCommand(OpcoesDeBusca.VISUALIZAR_HISTORICO.name());
		botao_visualizar_historico.addActionListener(this);
		
		titulo=new JLabel("Busca simplificada");
		
		rotulo_pesquisa=new JLabel("Selecione as opções de pesquisa");
		
		rotulo_resultados=new JLabel("Resultados de busca");
		
		qtde_resultados=new JLabel();
		
		rotulo_coluna=new JLabel("Coluna");
		
		rotulo_valor=new JLabel("Valor");
		
		erros_selecao_campos=new JLabel(" ");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDeBusca opcao = OpcoesDeBusca.valueOf(e.getActionCommand());

		switch (opcao) {

		case CANCELAR:
			fecharPainel();
			

			break;

		case PESQUISAR:
			pesquisar();
			
			

			break;
			
		case COMBOBOX_COLUNA_1:
			atualizaComboboxValor();
			
			
			break;
			
		case VISUALIZAR_HISTORICO:
			visualizarHistoricos(igu);
			
			
			

			break;

		case LIMPAR_CAMPOS:
			limpaCampos();

		}
	}
	
	private void visualizarHistoricos(JanelaPrincipal igu) {
		int selecao=tabela_resultados.getSelectedRow();
		if(selecao!=-1) {
			new JanelaVisualizarHistoricos(igu,resultado_pesquisa.get(selecao));
		}			
		
	}

	private void pesquisar() {
		if(combobox_coluna.getSelectedIndex()==0) {
			if(igu.getGerenciador_dados().getQtde_usuarios()==0) {
				erros_selecao_campos.setText("Não existem usuários cadastrados na base de dados.");
			}else {
				erros_selecao_campos.setText("É necessário cofigurar a busca antes de pesquisar.");
			}			
		}else {
			if(igu.getGerenciador_dados().getQtde_usuarios()==0) {
				erros_selecao_campos.setText("Não existem usuários cadastrados na base de dados.");
			}else {
				if(combobox_valor.getSelectedIndex()==0) {
					erros_selecao_campos.setText("É necessário cofigurar a busca antes de pesquisar.");
				}else {
					iniciaBusca();
				}
			}
			
		}
		
	}

	private void iniciaBusca() {
		String parametro=combobox_valor.getSelectedItem().toString();
		String tipo_busca=combobox_coluna.getSelectedItem().toString();
		ArrayList<Usuario> resultado=new ArrayList<Usuario>();
		if(tipo_busca.equalsIgnoreCase("Faixa etária")) {
			resultado=igu.getGerenciador_dados().buscaPorFaixaEtaria(parametro);
		}else {
			if(tipo_busca.equalsIgnoreCase("Sexo")) {
				resultado=igu.getGerenciador_dados().buscaPorSexo(parametro);
			}else {
				if(tipo_busca.equalsIgnoreCase("Raça/cor")) {
					resultado=igu.getGerenciador_dados().buscaPorRaca(parametro);
				}
			}			
		}
		
		if(resultado.size()==0) {
			qtde_resultados.setText("Nenhum resultado foi encontrado.");
		}else {
			preencheTabela(resultado);
			resultado_pesquisa=resultado;
			erros_selecao_campos.setText("Busca efetuada com sucesso.");
		}
		
	}
	
	

	private void atualizaComboboxValor() {
		modelo_comobox_1.removeAllElements();
		
		ArrayList<String> opcoes_comobox=new ArrayList<String>();
		
		int opcao=combobox_coluna.getSelectedIndex();
		
		opcoes_comobox=igu.getGerenciador_dados().retornaLinhasDosDiretorios(combobox_coluna.getItemAt(opcao).toString());


		modelo_comobox_1.addElement("");
		
		for (String registro : opcoes_comobox) {
			modelo_comobox_1.addElement(registro);
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
		qtde_resultados.setText("Foram encontrado(s) "+resultado.size()+" usuário(s.");

	}
	
	private void fecharPainel() {
		limpaCampos();
		igu.abrePainel(OpcoesDeMenu.SOBRE.name());

	}

	public void limpaCampos() {
		combobox_coluna.setSelectedIndex(0);		
		modelo_comobox_1.removeAllElements();
		modelo_tabela.setRowCount(0);
		erros_selecao_campos.setText(" ");
		qtde_resultados.setText(" ");
	}
			

		
}
