package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;





import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.informativo.PainelInformativo;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu.OpcoesDeMenu;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.barra_status.PainelBarraStatus;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.busca_avancada.PainelBuscaAvancada;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.busca_simples.PainelBuscaSimples;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.cabecalho.PainelCabecalho;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.cadastro_usuarios.PainelCadastroUsuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.exclusao.PainelExclusaoUsuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu.PainelMenu;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.mostra_usuarios.PainelMostraTodos;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.principal.PainelPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.Gerenciador;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.manipulador_arquivos.ManipuladorDeArquivos;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.manipulador_arquivos.excecoes.ExcecaoFormatoInvalido;



public class JanelaPrincipal extends JFrame{

	private PainelMenu painel_menu;	
	private PainelBarraStatus painel_barra_status;
	private PainelCabecalho painel_cabecalho;
	private PainelPrincipal painel_principal;
	private PainelExclusaoUsuario painel_excluir;
	private PainelBuscaSimples painel_busca_simples;
	private PainelBuscaAvancada painel_busca_avancada;
	private PainelInformativo painel_informativo;	
	private PainelCadastroUsuario painel_cadastro;
	private PainelMostraTodos painel_mostra_usuarios;	
	File arquivo_padrao;	
	CardLayout iframe;	
	Gerenciador gerenciador_dados;
	
	
	private static final long serialVersionUID = 1L;	
	
	public JanelaPrincipal(){
		super("Gerenciador de Cadastro de pacientes");		
		
		iframe=new CardLayout();
		

		
		gerenciador_dados=new Gerenciador();		
		definaComponentes();
		posicionaComponentes();
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            

		} catch (Exception e) {
			
		}
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800,670));		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		


	}
		
	private void definaComponentes() {
		painel_menu=new PainelMenu(this);
		painel_barra_status=new PainelBarraStatus(this);
		painel_cabecalho=new PainelCabecalho(this); 
		painel_principal=new PainelPrincipal(this);
		painel_cadastro=new PainelCadastroUsuario(this);
		painel_excluir=new PainelExclusaoUsuario(this);
		painel_busca_simples=new PainelBuscaSimples(this);
		painel_busca_avancada=new PainelBuscaAvancada(this);
		painel_informativo=new PainelInformativo(this);
		painel_mostra_usuarios=new PainelMostraTodos(this);

		
		

		
	}

	private void posicionaComponentes() {
		Container janela=getContentPane();
		GroupLayout layout = new GroupLayout(janela);
		janela.setLayout(layout);

		layout.setAutoCreateGaps(true);		

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();	
		
		horizontal.addGroup(layout.createParallelGroup(Alignment.TRAILING)				
				.addComponent(painel_cabecalho)				
				.addGroup(layout.createSequentialGroup()
						.addComponent(painel_menu)
						.addComponent(painel_principal))
				.addComponent(painel_barra_status));
		
		layout.setHorizontalGroup(horizontal);
		
		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();		

		vertical.addComponent(painel_cabecalho).addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addComponent(painel_menu)
				.addComponent(painel_principal))
				.addComponent(painel_barra_status);			
		
		layout.setVerticalGroup(vertical);
		
		janela.setBackground(Color.white);
		
		layout.linkSize(SwingConstants.VERTICAL, painel_cabecalho,painel_barra_status);	
		
		painel_principal.setLayout(iframe);

		painel_principal.add(painel_informativo,OpcoesDeMenu.SOBRE.name());
		painel_principal.add(painel_cadastro,OpcoesDeMenu.CADASTRAR_USUARIO.name());
		painel_principal.add(painel_excluir,OpcoesDeMenu.EXCLUIR_USUARIO.name());
		painel_principal.add(painel_busca_simples,OpcoesDeMenu.BUSCA_SIMPLES.name());
		painel_principal.add(painel_busca_avancada,OpcoesDeMenu.BUSCA_AVANCADA.name());
		painel_principal.add(painel_mostra_usuarios,OpcoesDeMenu.MOSTRA_USUARIOS.name());
	}
	
	public void abrePainel(String id_painel) {
		iframe.show(painel_principal, id_painel);

	}

	public Gerenciador getGerenciador_dados() {
		return gerenciador_dados;
	}

	public PainelMostraTodos getPainel_mostra_usuarios() {
		return painel_mostra_usuarios;
	}

	public PainelMenu getPainel_menu() {
		return painel_menu;
	}

	public PainelBarraStatus getPainel_barra_status() {
		return painel_barra_status;
	}

	public PainelCabecalho getPainel_cabecalho() {
		return painel_cabecalho;
	}

	public PainelPrincipal getPainel_principal() {
		return painel_principal;
	}

	public PainelExclusaoUsuario getPainel_excluir() {
		return painel_excluir;
	}

	public PainelBuscaSimples getPainel_busca_simples() {
		return painel_busca_simples;
	}

	public PainelBuscaAvancada getPainel_busca_avancada() {
		return painel_busca_avancada;
	}

	public PainelInformativo getPainel_informativo() {
		return painel_informativo;
	}

	public PainelCadastroUsuario getPainel_cadastro() {
		return painel_cadastro;
	}

	public void salveBaseEmArquivoAlternativo() {
		
	


			JFileChooser jfc = new JFileChooser();
			jfc.setCurrentDirectory(arquivo_padrao);
			FileFilter filtro = new FileNameExtensionFilter("Arquivo juc","juc");
			jfc.addChoosableFileFilter(filtro);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setDialogTitle("Salvar");
			jfc.setMultiSelectionEnabled(false);
			File teste=selecioneArquivoASalvar(jfc);
			if(teste!=null) {
				arquivo_padrao=teste;
				
			
		}
		
		if (arquivo_padrao != null) {
			ManipuladorDeArquivos gerenciador = new ManipuladorDeArquivos();
			gerenciador.setGerenciador(gerenciador_dados);

			try {
				gerenciador.salveBaseDeDadosEmArquivo(arquivo_padrao);
				painel_barra_status.atualizaCampoInformativo("Informações salvas com sucesso.","ok");
				painel_barra_status.atualizaCampoEndereco(arquivo_padrao);
			} catch (IOException e) {
				painel_barra_status.atualizaCampoInformativo(e.getMessage(),"erro");
			}

		}
		
		

	}
	
	private File selecioneArquivoASalvar(JFileChooser jfc) {
		
	@SuppressWarnings("unused")
	File arquivoSemExtensao=null;
	File arquivoComExtencao=null;
	File teste_arquivo=null;
	File resultado=null;
	String teste=null;
	String edereco_arquivo=null;
	
	
		
		if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			
			
			if(!jfc.getSelectedFile().getAbsolutePath().endsWith(".juc")) {			
			
			teste=jfc.getSelectedFile().getAbsolutePath()+".juc";
			teste_arquivo=new File(teste);
			
			}else {
				teste_arquivo=jfc.getSelectedFile();
			}

			if(teste_arquivo.exists()==true) {
				int selecionaOpcao = JOptionPane.showConfirmDialog(null," O arquivo já existe, deseja sobrescreve-lo? ", null,JOptionPane.OK_CANCEL_OPTION);   
                if (selecionaOpcao == JOptionPane.OK_OPTION) { 
                	resultado=teste_arquivo;
                	
                }else {
                	if (selecionaOpcao == JOptionPane.CANCEL_OPTION) {
                	
                	selecioneArquivoASalvar(jfc);
                	}
                }
			}else {
				arquivoSemExtensao=jfc.getSelectedFile();
				edereco_arquivo = jfc.getSelectedFile().getAbsolutePath() + ".juc";
				arquivoComExtencao = new File(edereco_arquivo);
				resultado=arquivoComExtencao;
			}
		}			
		return resultado;
		
	}
	
	public void salveBaseEmArquivo() {
		
		if (arquivo_padrao == null) {
			JFileChooser jfc = new JFileChooser();
			FileFilter filtro = new FileNameExtensionFilter("Arquivo juc","juc");
			jfc.addChoosableFileFilter(filtro);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setDialogTitle("Salvar");
			jfc.setMultiSelectionEnabled(false);
			File teste=selecioneArquivoASalvar(jfc);
			if(teste!=null) {
				arquivo_padrao=teste;
				
			}
		}
		
		if (arquivo_padrao != null) {
			ManipuladorDeArquivos gerenciador = new ManipuladorDeArquivos();
			gerenciador.setGerenciador(gerenciador_dados);

			try {
				gerenciador.salveBaseDeDadosEmArquivo(arquivo_padrao);
				painel_barra_status.atualizaCampoInformativo("Informações salvas com sucesso.","ok");
				painel_barra_status.atualizaCampoEndereco(arquivo_padrao);
			} catch (IOException e) {
				painel_barra_status.atualizaCampoInformativo(e.getMessage(),"erro");
			}

		}
		
	}

	public void leiaBaseDeArquivo() {
		
		JFileChooser jfc = new JFileChooser();
		FileFilter filtro = new FileNameExtensionFilter("Arquivo juc","juc");
		jfc.addChoosableFileFilter(filtro);
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setDialogTitle("Abrir");
		jfc.setMultiSelectionEnabled(false);
		jfc.setCurrentDirectory(arquivo_padrao);


		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			arquivo_padrao = jfc.getSelectedFile();

			ManipuladorDeArquivos gerenciador = new ManipuladorDeArquivos();
			try {
				gerenciador.leiaBaseDeDadosParaMemoria(arquivo_padrao);
				this.gerenciador_dados=gerenciador.getGerenciador();
				painel_barra_status.atualizaCampoInformativo("Informações importadas com sucesso.","ok");
				painel_barra_status.atualizaCampoEndereco(arquivo_padrao);
				painel_menu.getPainel_auxiliar().calculaEstatisticas();

			} catch (IOException e) {
				
				painel_barra_status.atualizaCampoInformativo(e.getMessage(),"erro");
			} catch (ExcecaoFormatoInvalido e) {
				
				painel_barra_status.atualizaCampoInformativo(e.getMessage(),"erro");
			}

		}



	}
	
	
	public void fecharPrograma() {
		if(this.gerenciador_dados.getQtde_usuarios()>0) {
			int selecionaOpcao = JOptionPane.showConfirmDialog(null,"Há iformações que ainda não foram salvas/n Deseja salva-las ates de fechar?", null,JOptionPane.YES_NO_CANCEL_OPTION);   
            if (selecionaOpcao == JOptionPane.OK_OPTION) { 
            	salveBaseEmArquivo();
            	System.exit(0);
            }else {
            	if (selecionaOpcao == JOptionPane.NO_OPTION) {
            		System.exit(0);
            	}
            }
		}else {
			System.exit(0);
		}
		
		
	}

	
	

}
