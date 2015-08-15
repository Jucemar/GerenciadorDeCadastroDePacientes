package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.cadastro_usuarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;



import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.menu.OpcoesDeMenu;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.Usuario;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoNomeInvalido;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoNomeNull;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoRacaInvalida;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.excecoes.ExcecaoSexoInvalido;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.gerenciador.excecoes.ExcecaoUsuarioJaCadastrado;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.utilitarios.ConversorDeDatas;


public class PainelCadastroUsuario extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JanelaPrincipal igu;
	
	StringBuffer erros_validacao;
	
	JButton botao_salvar;
	JButton botao_cancelar;
	JButton botao_limpar;

	JTextField campo_prontuario;
	JTextField campo_rg;
	JTextField campo_nome;
	JTextField campo_nome_mae;
	JTextField campo_nome_pai;
	JTextField campo_dataNascimento;
	JComboBox campo_sexo;
	JComboBox campo_raca;
	JTextField campo_logradouro;
	JTextField campo_bairro;
	JTextField campo_complemento;
	JTextField campo_cep;
	JTextField campo_municipio;
	JComboBox campo_estado;
	JTextArea campo_erros;
	JTextField campo_telefone;
	
	JLabel rotulo_prontuario;
	JLabel rotulo_rg;
	JLabel rotulo_nome;
	JLabel rotulo_nome_mae;
	JLabel rotulo_nome_pai;
	JLabel rotulo_dataNascimento;
	JLabel rotulo_sexo;
	JLabel rotulo_raca;
	JLabel rotulo_logradouro;
	JLabel rotulo_bairro;
	JLabel rotulo_complemento;
	JLabel rotulo_cep;
	JLabel rotulo_municipio;
	JLabel rotulo_estado;
	JLabel rotulo_telefone;
	
	JLabel titulo;
	JLabel rotulo_erros;
	

	
	

	public PainelCadastroUsuario(JanelaPrincipal janelaPrincipal){
		
		igu=janelaPrincipal;
		

		definaComponentes();
		posicionaComponentes();	


		
		
	}

	private void posicionaComponentes() {		
		
		setLayout(new MigLayout("","[grow]",""));	
		
		JSeparator linha1=new JSeparator();		
		
		JSeparator linha2=new JSeparator();
		
		add(titulo,"wrap");
		
		add(linha1,"wrap,growx");
		
		add(rotulo_prontuario,"split 2,width 65,gaptop 10");
		add(campo_prontuario,"wrap,width 45");
		
		add(rotulo_rg,"split 2,width 65,gaptop 10");
		add(campo_rg,"wrap,width 75");
		
		add(rotulo_nome,"split 4,width 65,gaptop 10");
		add(campo_nome,"width 200");
		
		add(rotulo_dataNascimento,"gapleft 15,split 2");
		add(campo_dataNascimento,"width 65,wrap");
		
		add(rotulo_sexo,"width 65,split 4,gaptop 10");
		add(campo_sexo,"width 100");
		
		add(rotulo_raca,"gapleft 30");
		add(campo_raca,"wrap,width 100");
		
		add(rotulo_nome_mae,"split 4,width 65,gaptop 10");
		add(campo_nome_mae,"width 200");
		
		add(rotulo_nome_pai,"width 65,gaptop 10,gapleft 30");
		add(campo_nome_pai,"wrap,width 200");
		
		add(rotulo_logradouro,"split 4,width 65,gaptop 10");
		add(campo_logradouro,"width 300");
		
		add(rotulo_cep,"gapleft 30");
		add(campo_cep,"wrap,width 55");
	
		add(rotulo_complemento,"split 4,width 65,gaptop 10");
		add(campo_complemento,"width 200");
		
		add(rotulo_bairro,"gapleft 30");
		add(campo_bairro,"wrap,width 145");	
			
		add(rotulo_municipio,"split 4,width 65,gaptop 10");
		add(campo_municipio,"width 125");

		add(rotulo_telefone,"gapleft 30");
		add(campo_telefone,"wrap,width 100");
		
		add(rotulo_estado,"split 2,width 65,gaptop 10");
		add(campo_estado,"wrap,width 45");	

		add(botao_limpar,"right,split 2");
		
		add(botao_salvar,"wrap");
		
		add(rotulo_erros,"wrap");
		
		add(linha2,"wrap,growx");
		
		add(campo_erros,"wrap,growx,gaptop 10,height 110");
		
		add(botao_cancelar,"right,gaptop 10");
		
		
		

	}

	private void definaComponentes() {
		
		String[] sexo={" ","Masculino","Feminino"};
		String[] raca={" ","Branca","Negra","Amarela","Parda","Indigena","Outra"};	
		String[] estado = {" ", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES",
				"GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR",
				"RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
		
		campo_prontuario=new JTextField();
		campo_prontuario.setEditable(false);

		
		campo_rg=new JTextField();
		
		campo_nome=new JTextField();
		campo_nome_mae=new JTextField();
		
		campo_nome_pai=new JTextField();
		
		campo_dataNascimento=new JTextField();
		
		campo_sexo=new JComboBox(sexo);
		
		campo_raca=new JComboBox(raca);
		
		campo_logradouro=new JTextField();
		
		campo_bairro=new JTextField();
		
		campo_complemento=new JTextField();
		
		campo_cep=new JTextField();
		
		campo_municipio=new JTextField();
		
		campo_estado=new JComboBox(estado);
		
		campo_telefone=new JTextField();
		
		campo_erros=new JTextArea(" ");
		
		campo_erros.setOpaque(false);
		
		
		campo_erros.setFont(new Font(Font.DIALOG,Font.PLAIN,12));

		
		rotulo_telefone=new JLabel("Telefone");
		rotulo_prontuario=new JLabel("Prontuário");
		rotulo_rg=new JLabel("RG");
		rotulo_nome=new JLabel("Nome *");
		rotulo_nome_mae=new JLabel("Nome da mãe");
		rotulo_nome_pai=new JLabel("Nome do pai");
		rotulo_dataNascimento=new JLabel("DN *");
		rotulo_sexo=new JLabel("Sexo *");
		rotulo_raca=new JLabel("Raça/Cor *");
		rotulo_logradouro=new JLabel("Logradouro");
		rotulo_bairro=new JLabel("Bairro");
		rotulo_complemento=new JLabel("Complemento");
		rotulo_cep=new JLabel("CEP");
		rotulo_municipio=new JLabel("Município");
		rotulo_estado=new JLabel("Estado");
		
		botao_salvar = new JButton("Salvar");
		botao_salvar.setActionCommand(OpcoesDeMenu.SALVAR_USUARIO.name());
		botao_salvar.addActionListener(this);

		botao_cancelar = new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesDeMenu.CANCELAR.name());
		botao_cancelar.addActionListener(this);

		botao_limpar = new JButton("Limpar");
		botao_limpar.setActionCommand(OpcoesDeMenu.LIMPAR_CAMPOS.name());
		botao_limpar.addActionListener(this);
		
		titulo=new JLabel("Cadastro de usuários");
		
		rotulo_erros=new JLabel("Erros encontrados:");

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDeMenu opcao = OpcoesDeMenu.valueOf(e.getActionCommand());

		switch (opcao) {
		
		case SALVAR_USUARIO:
			cadastrarUsuario();
			
			
			
			
			break;	
			
		case LIMPAR_CAMPOS:
			limpaCampos();

			
			
			break;
			
		case CANCELAR:
			fecharPainel();
			
			
		}
			

		
	}

	public void atualizaumProtuario() {
		campo_prontuario.setText(Integer.toString(igu.getGerenciador_dados().getNum_protuarios()));
		
	}

	private void fecharPainel() {
		limpaCampos();
		igu.abrePainel(OpcoesDeMenu.SOBRE.name());
		
	}

	private void cadastrarUsuario() {
		Usuario usuario = null;
		usuario = validaDadosDosCampos();

		
		if(usuario!=null) {
			try {
				igu.getGerenciador_dados().insere(usuario);
				
				limpaCampos();
				atualizaumProtuario();
				atualizaCampoInformativo("Não foram encontrados erros. \nO Usuário "+usuario.getNome()+" foi cadastrado com sucesso!","ok");
				igu.getPainel_menu().getPainel_auxiliar().calculaEstatisticas();
			} catch (ExcecaoUsuarioJaCadastrado e) {
				atualizaCampoInformativo(e.getMessage(),"erro");
			}
			
		}else {
			erros_validacao.append("O cadastro não será efetuado até que todos os erros sejam corrigidos.");
			atualizaCampoInformativo(erros_validacao.toString(),"erro");

		}
		
	}

	private Usuario validaDadosDosCampos() {
		Usuario novoUsuario=new Usuario();
		Usuario usuarioValido=new Usuario();

		boolean validacao = true;
		
		erros_validacao = new StringBuffer();
		


		
	

				try {
					novoUsuario.setProntuario(Integer.parseInt(campo_prontuario.getText()));
					novoUsuario.setNome(campo_nome.getText());
					
				} catch (ExcecaoNomeNull e) {
					erros_validacao.append(e.getMessage()+"\n");
					validacao = false;
					
				} catch (ExcecaoNomeInvalido e) {
					erros_validacao.append(e.getMessage()+"\n");
					validacao = false;
				}
				
				
				if(campo_dataNascimento.getText().equalsIgnoreCase("")) {
					erros_validacao.append("O campo data de nascimento está vazio.\n");
					validacao = false;
					
				}else {
					try {
						novoUsuario.setData_nascimento(ConversorDeDatas.converteStringParaDate(campo_dataNascimento.getText()));
					} catch (ParseException e) {
						erros_validacao.append("Data com formato inválido. \n");
						validacao = false;
					}
				}
				
				try {
					novoUsuario.setRaca(campo_raca.getSelectedItem().toString());
				} catch (ExcecaoRacaInvalida e) {
					erros_validacao.append(e.getMessage()+"\n");
					validacao = false;
				}
				
				try {
					novoUsuario.setSexo(campo_sexo.getSelectedItem().toString());
				} catch (ExcecaoSexoInvalido e) {
					erros_validacao.append(e.getMessage()+"\n");
					validacao = false;
				}

				
				

			
			if (validacao == false) {
				atualizaCampoInformativo(erros_validacao.toString(),"erro");
			}
			

			if (validacao == true) {
				usuarioValido = novoUsuario;
				
			} else {
				usuarioValido = null;

			}
			
			
			
			return usuarioValido;
		
		
		
		
	}

	public void limpaCampos() {
		campo_prontuario.setText("");	
		campo_rg.setText("");
		campo_nome.setText("");
		campo_nome_mae.setText("");
		campo_nome_pai.setText("");
		campo_dataNascimento.setText("");
		campo_sexo.setSelectedIndex(0);
		campo_raca.setSelectedIndex(0);
		campo_logradouro.setText("");
		campo_bairro.setText("");
		campo_complemento.setText("");
		campo_cep.setText("");
		campo_municipio.setText("");
		campo_estado.setSelectedIndex(0);
		limpaCampoInformativo();
		atualizaumProtuario();

		
	}
	
	public void atualizaCampoInformativo(String texto,String status) {
		if(status.equalsIgnoreCase("erro")) {
			campo_erros.setText(texto);
			campo_erros.setForeground(Color.red);
		}else {
			if(status.equalsIgnoreCase("ok")) {
				campo_erros.setText(texto);
				campo_erros.setForeground(Color.blue);				
			}
		}
	}
	
	 public void limpaCampoInformativo() {
		 campo_erros.setText(" ");
		 campo_erros.setForeground(Color.black);		 
		 
	 }


}
