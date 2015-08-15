package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.exclusao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.janelas.visualizar_historicos.JanelaVisualizarHistoricos;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu.OpcoesDeMenu;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Usuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes.ExcecaoBaseVazia;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes.ExcecaoUsuarioNaoLocalizado;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios.ConversorDeDatas;

public class PainelExclusaoUsuario extends JPanel implements ActionListener{
	JanelaPrincipal igu;
	JLabel titulo;
	JTable resultados_busca;
	DefaultTableModel modelo_tabela;
	JTextField campo_excluir;
	JLabel titulo_pesquisa;
	JTextField campo_pesquisa;
	JLabel rotulo_excluir;
	JButton botao_excluir;
	JButton botao_pesquisar;
	JButton botao_cancelar;
	JButton botao_visualizar_historico;
	JComboBox tipo_pesquisa;
	JLabel campo_iformativo_exclusao;
	JLabel campo_iformativo_pesquisa;
	JLabel rotulo_pesquisa;
	JLabel rotulo_tipo_pesquisa;
	String [] nomes_colunas;
	ArrayList<Usuario> resultado_pesquisa;
	

	private static final long serialVersionUID = 1L;
	
	public PainelExclusaoUsuario(JanelaPrincipal janelaPrincipal) {
		igu=janelaPrincipal;
		
		
		
		definaComponentes();
		posicionaComponentes();
		

		

	}

	private void posicionaComponentes() {
		
		
		
		JSeparator linha1=new JSeparator();

		
		JSeparator linha2=new JSeparator();

		
		JScrollPane scroll_tabela=new JScrollPane(resultados_busca);

		
		setLayout(new MigLayout("","[grow]",""));
		
		add(titulo,"wrap");
		add(linha1,"growx, wrap");
		add(rotulo_excluir,"split 4,gaptop 15");
		add(campo_excluir,"width 100");
		add(botao_excluir);
		add(campo_iformativo_exclusao,"gapleft 50,wrap");
		
		add(titulo_pesquisa,"wrap,gaptop 50");
		add(linha2,"growx, wrap");
		add(rotulo_tipo_pesquisa,"split 5,gaptop 15");
		add(tipo_pesquisa);
		add(rotulo_pesquisa,"gapleft 15");
		add(campo_pesquisa,"growx");
		add(botao_pesquisar,"gapleft 15,wrap");
		add(scroll_tabela,"growx,gaptop 15,wrap");
		add(campo_iformativo_pesquisa,"gaptop 15,split 3,growx");
		add(botao_visualizar_historico,"right");
		add(botao_cancelar);
		
		
		
	}

	private void definaComponentes() {
		titulo=new JLabel("Exclusão de usuários");	
		
		botao_excluir=new JButton("Excluir");
		botao_excluir.setActionCommand(OpcoesDeExclusao.EXCLUIR_USUARIO.name());
		botao_excluir.addActionListener(this);
		
		botao_pesquisar=new JButton("Pesquisar");
		botao_pesquisar.setActionCommand(OpcoesDeExclusao.PESQUISAR_USUARIO.name());
		botao_pesquisar.addActionListener(this);
		
		botao_cancelar=new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesDeExclusao.CANCELAR.name());
		botao_cancelar.addActionListener(this);
		
		botao_visualizar_historico=new JButton("Histórico");
		botao_visualizar_historico.setActionCommand(OpcoesDeExclusao.VISUALIZAR_HISTORICO.name());
		botao_visualizar_historico.addActionListener(this);
		
		String[] opcoesDeBusca=new String[] {"Nome","Prontuário","Data de nascimento"};
		
		nomes_colunas=new String[] {"Prontuário","Nome","Nascimento","Sexo","Raça/cor"};
		
		tipo_pesquisa=new JComboBox(opcoesDeBusca);
		
		campo_iformativo_exclusao=new JLabel("Usuário excluido com sucesso.");
		
		campo_iformativo_pesquisa=new JLabel("Nehum usuários encontrado");
		
		campo_excluir=new JTextField();
		
		rotulo_excluir=new JLabel("Nº prontuário");
		
		campo_pesquisa=new JTextField();
		
		titulo_pesquisa=new JLabel("Busca de usuários");
		
		rotulo_pesquisa=new JLabel("Digite a informação");
		
		rotulo_tipo_pesquisa=new JLabel("Tipo de pesquisa");
		
		resultados_busca=new JTable();
		
		modelo_tabela=new DefaultTableModel();
		
		modelo_tabela.setColumnIdentifiers(nomes_colunas);
		
		resultados_busca.setModel(modelo_tabela);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDeExclusao opcao = OpcoesDeExclusao.valueOf(e.getActionCommand());

		switch (opcao) {
		
		case PESQUISAR_USUARIO:
			modelo_tabela.setRowCount(0);
			pesquisarUsuario();
			
			
			break;	

		case CANCELAR:
			fecharPainel();
			
			
			break;	
			
		case VISUALIZAR_HISTORICO:
			visualizarHistoricos(igu);
			
			
			
			break;	
			
		case EXCLUIR_USUARIO:	
			excluirUsuario();
			
			
		}
	}
	
	private void visualizarHistoricos(JanelaPrincipal igu) {
		int selecao=resultados_busca.getSelectedRow();
		if(selecao!=-1) {
			new JanelaVisualizarHistoricos(igu,resultado_pesquisa.get(selecao));
		}
			
		
	}
	

	private void limpaCamposAposExclusao() {
		modelo_tabela.setRowCount(0);
		campo_excluir.setText(" ");
		campo_pesquisa.setText(" ");
		campo_iformativo_pesquisa.setText(" ");
		tipo_pesquisa.setSelectedIndex(0);
		
	}

	private void excluirUsuario() {
		
		if(campo_excluir.getText().trim().length()==0) {
			campo_iformativo_exclusao.setText("O campo de exclusão está vazio.");
		}else{
			
			
			try {
				Integer.parseInt(campo_excluir.getText().trim());
				
				try {
					igu.getGerenciador_dados().removeElemento(Integer.parseInt(campo_excluir.getText().trim()));
					campo_iformativo_exclusao.setText("Usuário excluido com sucesso.");
					igu.getPainel_menu().getPainel_auxiliar().calculaEstatisticas();
					limpaCamposAposExclusao();
					
				} catch (ExcecaoBaseVazia e) {
					campo_iformativo_exclusao.setText(e.getMessage());
				
				} catch (ExcecaoUsuarioNaoLocalizado e) {
					campo_iformativo_exclusao.setText(e.getMessage());
				}				
				
			}catch (NumberFormatException e) {
				campo_iformativo_exclusao.setText("O campo de pesquisa não comtém um número válido.");
			}
		}	
		
	}

	private void pesquisarUsuario() {
		
		ArrayList<Usuario> resultado=new ArrayList<Usuario>();
		String tipo_busca=tipo_pesquisa.getSelectedItem().toString();
		
		boolean erro=false;
		
		if (igu.getGerenciador_dados().getQtde_usuarios() == 0) {
			campo_iformativo_pesquisa
					.setText("Ainda não existe nenhum usuário cadastrado na base de dados.");
		} else {

			if (tipo_busca.equalsIgnoreCase("nome")) {
				if (campo_pesquisa.getText().trim().equalsIgnoreCase("")) {
					campo_iformativo_pesquisa
							.setText("O campo de pesquisa está vazio.");
					erro = true;

				} else {

					try {
						resultado = (ArrayList<Usuario>) igu
								.getGerenciador_dados()
								.pesquisaUsuarioPeloNome(
										campo_pesquisa.getText());
					} catch (ExcecaoBaseVazia e) {
						campo_iformativo_pesquisa.setText(e.getMessage());
						erro = true;

					}
				}
			} else {
				if (tipo_busca.equalsIgnoreCase("Prontuário")) {
					if (campo_pesquisa.getText().trim().equalsIgnoreCase("")) {
						campo_iformativo_pesquisa
								.setText("O campo de pesquisa está vazio.");
						erro = true;

					} else {

						try {
							int pesquisar = Integer.parseInt(campo_pesquisa
									.getText().trim());
							resultado = (ArrayList<Usuario>) igu
									.getGerenciador_dados()
									.pesquisaUsuarioPeloProntuario(pesquisar);
						} catch (NumberFormatException e) {
							campo_iformativo_pesquisa
									.setText("O campo de pesquisa não comtém um número válido.");
							erro = true;
						}
					}
				} else {
					if (tipo_busca.equalsIgnoreCase("Data de nascimento")) {
						if (campo_pesquisa.getText().trim()
								.equalsIgnoreCase("")) {
							campo_iformativo_pesquisa
									.setText("O campo de pesquisa está vazio.");
							erro = true;

						} else {
							try {
								String data_digitada = campo_pesquisa.getText()
										.trim();
								Date data = ConversorDeDatas
										.converteStringParaDate(data_digitada);
								resultado = (ArrayList<Usuario>) igu
										.getGerenciador_dados()
										.pesquisaUsuarioPeloascimento(data);

							} catch (ParseException e) {
								campo_iformativo_pesquisa
										.setText("O campo de pesquisa não comtém uma data válida. Ex:(dd/mm/aaaa)");
								erro = true;
							}
						}
					}
				}

			}

			if (resultado.size() > 0) {
				preencheTabela(resultado);
				resultado_pesquisa=resultado;
				
				
				
			} else {
				if (erro == false) {
					campo_iformativo_pesquisa
							.setText("Nenhum resultado de busca foi encontrado");
				}
			}
		}

	}

	private void preencheTabela(ArrayList<Usuario> resultado) {
		resultados_busca.getColumnModel().getColumn(0).setPreferredWidth(8);
		resultados_busca.getColumnModel().getColumn(1).setPreferredWidth(150);
		resultados_busca.getColumnModel().getColumn(2).setPreferredWidth(80);
		resultados_busca.getColumnModel().getColumn(3).setPreferredWidth(50);
		resultados_busca.getColumnModel().getColumn(4).setPreferredWidth(50);

		modelo_tabela.setNumRows(0);
		for (Usuario usuario : resultado) {
			modelo_tabela.addRow(new Object[] {
					usuario.getProntuario(),
					usuario.getNome(),
					ConversorDeDatas.converteDateParaString(usuario
							.getData_nascimento()), usuario.getSexo(),
					usuario.getRaca() });
		}
		resultados_busca.setModel(modelo_tabela);
		campo_iformativo_pesquisa.setText("Foram econtrado(s) "+resultado.size()+" usuário(s)");

	}
	
	public void limpaCampos() {
		modelo_tabela.setRowCount(0);
		campo_excluir.setText(" ");
		campo_pesquisa.setText(" ");
		tipo_pesquisa.setSelectedIndex(0);
		campo_iformativo_exclusao.setText(" ");
		campo_iformativo_pesquisa.setText(" ");

		
	}
	
	private void fecharPainel() {
		limpaCampos();
		igu.abrePainel(OpcoesDeMenu.SOBRE.name());

	}

}
