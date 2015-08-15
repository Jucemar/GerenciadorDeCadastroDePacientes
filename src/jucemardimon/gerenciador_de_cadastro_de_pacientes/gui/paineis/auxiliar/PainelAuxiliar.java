package jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.paineis.auxiliar;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

import jucemardimon.gerenciador_de_cadastro_de_pacientes.gui.JanelaPrincipal;
import jucemardimon.gerenciador_de_cadastro_de_pacientes.logica.entidades.RegistroNoDiretorio;

public class PainelAuxiliar extends JPanel{
	JanelaPrincipal igu;
	JLabel titulo;
	JLabel rotulo_total;
	JLabel rotulo_homens;
	JLabel rotulo_mulheres;
	JLabel rotulo_adultos;
	JLabel rotulo_criancas;
	JLabel rotulo_idosos;

	JLabel campo_total;
	JLabel campo_homens;
	JLabel campo_mulheres;
	JLabel campo_adultos;
	JLabel campo_criancas;
	JLabel campo_idosos;

	private static final long serialVersionUID = 1L;
	
	public PainelAuxiliar(JanelaPrincipal janelaPrincipal) {
		igu=janelaPrincipal;

		definaComponenste();
		posicioneComponentes();
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
			
	}

	private void posicioneComponentes() {
		
		JSeparator linha1=new JSeparator();
		setLayout(new MigLayout("","[grow]",""));
		
		add(titulo,"wrap,gap left 4");
		add(linha1,"wrap,growx");
		add(rotulo_homens,"split 2,growx,gap left 4");
		add(campo_homens,"wrap,right");
		add(rotulo_mulheres,"split 2,growx,gap left 4");
		add(campo_mulheres,"wrap,right");
		add(rotulo_criancas,"split 2,growx,gap left 4");
		add(campo_criancas,"wrap,right");
		add(rotulo_adultos,"split 2,growx,gap left 4");
		add(campo_adultos,"wrap,right");
		add(rotulo_idosos,"split 2,growx,gap left 4");
		add(campo_idosos,"wrap,right");
		add(rotulo_total,"split 2,growx,gap left 4");
		add(campo_total,"right");

		
	}

	private void definaComponenste() {
		titulo=new JLabel("Estatisticas");
		rotulo_total=new JLabel("Totais");
		rotulo_homens=new JLabel("Homens");
		rotulo_mulheres=new JLabel("Mulheres");
		rotulo_adultos=new JLabel("Adultos");
		rotulo_criancas=new JLabel("Crianças");
		rotulo_idosos=new JLabel("Idosos");

		campo_total=new JLabel("0");
		campo_homens=new JLabel("0");
		campo_mulheres=new JLabel("0");
		campo_adultos=new JLabel("0");
		campo_criancas=new JLabel("0");
		campo_idosos=new JLabel("0");
		
	}
	
	public void calculaEstatisticas() {
		RegistroNoDiretorio auxSexo=igu.getGerenciador_dados().getSexo();


		if(igu.getGerenciador_dados().getQtde_usuarios()>0) {			
		
			while (auxSexo != null) {
				if (auxSexo.getNome().equalsIgnoreCase("Masculino")) {
					campo_homens.setText(Integer.toString(auxSexo.getQtde_elementos()));
				}
				if (auxSexo.getNome().equalsIgnoreCase("Feminino")) {
					campo_mulheres.setText(Integer.toString(auxSexo.getQtde_elementos()));
				}

				auxSexo = auxSexo.getProximoRegistro();
			}
		}
		if(igu.getGerenciador_dados().getQtde_usuarios()==0) {
			campo_homens.setText("0");
			campo_mulheres.setText("0");
		}
		
		
		RegistroNoDiretorio auxIdade=igu.getGerenciador_dados().getFaixa_etaria();
		campo_criancas.setText(Integer.toString(auxIdade.getQtde_elementos()));
		
		auxIdade=auxIdade.getProximoRegistro();
		campo_adultos.setText(Integer.toString(auxIdade.getQtde_elementos()));
		
		auxIdade=auxIdade.getProximoRegistro();
		
		campo_idosos.setText(Integer.toString(auxIdade.getQtde_elementos()));
		
		campo_total.setText(Integer.toString(igu.getGerenciador_dados().getQtde_usuarios()));
		
	}
	
	

}
