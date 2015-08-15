package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.informativo;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;

public class PainelInformativo extends JPanel{
	
	JLabel titulo;
	JLabel titulo_sobre;
	JTextArea texto_sobre;
	JLabel nome;
	JLabel matricula;
	
	private static final long serialVersionUID = 1L;
	
	public PainelInformativo(JanelaPrincipal janelaPrincipal) {
		
		
		
		JSeparator linha1=new JSeparator();
		JSeparator linha2=new JSeparator();
		
		titulo=new JLabel("Informativo geral sobre o programa");
		titulo_sobre=new JLabel("Sobre o programa e seu criador");
		texto_sobre=new JTextArea();
		nome=new JLabel("Jucemar Dimon");
		matricula=new JLabel("10101193 - 2011-01");
		
		String sobre="      Este aplicativo é um programa desenvolvido por Jucemar Dimon, acadêmico do " +
				"curso Sistemas de Informação ministrado no Centro Tecnológico - CTC - da " +
				"Universidade Federal de Santa Catarina - UFSC, com o objetivo de colocar em " +
				"prática as técnicas e teorias para criação de estruturas de dados para " +
				"manipulação de dados em memória adquiridas na disciplina INE5609 - " +
				"Estruturas de Dados, ministrada pelo Doutor Jose Eduardo de Lucca na turma " +
				"03238 no semestre 2011-01. \n\n      O programa implementado usa uma lista duplamente " +
				"encadeada para manipulação básica dos dados. Foi usada a estratégia de lista invertida para otimizar os " +
				"processos de pesquisa a qual indexa 03 chaves secundárias dos atributos dos " +
				"objetos usados em 03 diretórios específicos. \n\n      A ideia pricipal do aplicativo desenvolvido seria o gerenciamento do " +
				"cadastro de pacientes ou usuários de um estabelecimento de saude privado ou " +
				"público. O programa seria um módulo de um sistema maior que, provavelmente, " +
				"seria operado por profissionais da área administrativa do estabelecimento que o usaria.";	
		
		texto_sobre.setText(sobre);
		texto_sobre.setLineWrap(true);
		texto_sobre.setWrapStyleWord(true);  
		texto_sobre.setOpaque(false);
		texto_sobre.setAlignmentY(500);
		 

		texto_sobre.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
		
		setLayout(new MigLayout("","[grow]",""));
		
		add(titulo,"wrap");
		add(linha1,"wrap,growx");
		add(titulo_sobre,"wrap,center,gaptop 50");
		add(linha2,"wrap,center,width 500");
		add(texto_sobre,"wrap,width 500,gaptop 20,center");
		add(nome,"wrap,center,gaptop 20");
		add(matricula,"center");

	}

}
