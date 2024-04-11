package Front_End.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Back_End.Cliente;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JLabel;


public class Tela_Jogo extends JFrame {

	private static Cliente cliente;
	private String[] perguntas;
	private JPanel contentPane;	
	private JPanel contentPaneResultado;
	private int cont = 0;
	
	
	public Tela_Jogo(Cliente cliente, String[] perguntas) {
		this.perguntas = perguntas;		
		
		//Painel para jogar
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Painel para mostrar resultado da partida
		contentPaneResultado = new JPanel();
		contentPaneResultado.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneResultado.setLayout(null);
		
		Label label = new Label();
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setBounds(50, 61, 500, 57);
		contentPaneResultado.add(label);
		contentPaneResultado.setVisible(false);
		
		//Primeira pergunta
		JLabel pergunta = new JLabel(perguntas[0].split(";")[0]);
		pergunta.setBounds(23, 39, 553, 107);
		contentPane.add(pergunta);
		
		
		JRadioButton buttonOptA = new JRadioButton("A) " + perguntas[0].split(";")[1]);
		buttonOptA.setBounds(23, 199, 398, 23);
		contentPane.add(buttonOptA);
		
		JRadioButton buttonOptB = new JRadioButton("B) " + perguntas[0].split(";")[2]);
		buttonOptB.setBounds(23, 225, 398, 23);
		contentPane.add(buttonOptB);
		
		JRadioButton buttonOptC = new JRadioButton("C) " + perguntas[0].split(";")[3]);
		buttonOptC.setBounds(23, 251, 398, 23);
		contentPane.add(buttonOptC);		
		
		JRadioButton buttonOptD = new JRadioButton("D) " + perguntas[0].split(";")[4]);
		buttonOptD.setBounds(23, 277, 398, 23);
		contentPane.add(buttonOptD);
		
		ButtonGroup radioButtons = new ButtonGroup();
		
		radioButtons.add(buttonOptA);
		radioButtons.add(buttonOptB);
		radioButtons.add(buttonOptC);
		radioButtons.add(buttonOptD);			
		
		buttonOptA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.addRespostas("a");
				
				buttonOptA.setSelected(false);
				
				cont++;
				
				if(cont == 10) {
					PanelCarregamento painel = new PanelCarregamento();
					
					contentPane.setVisible(false);
					
					setContentPane(painel);
					painel.setVisible(true);
					setVisible(true);
					
					update(getGraphics());
					
					
					cliente.enviarRespostas();
					
					painel.setVisible(false);
					
					setContentPane(contentPaneResultado);
					label.setText(cliente.getGanhador());
					contentPaneResultado.setVisible(true);
					
				} else {
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
				}
			}
		});
		
		buttonOptB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.addRespostas("b");
				
				buttonOptB.setSelected(false);
				
				cont++;
				
				if(cont == 10) {
					PanelCarregamento painel = new PanelCarregamento();
					
					contentPane.setVisible(false);
					
					setContentPane(painel);
					painel.setVisible(true);
					update(getGraphics());
					
					
					cliente.enviarRespostas();
					
					painel.setVisible(false);
					
					setContentPane(contentPaneResultado);
					label.setText(cliente.getGanhador());
					contentPaneResultado.setVisible(true);
					
				} else {
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
				}
			}
		});
		
		buttonOptC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.addRespostas("c");
				
				buttonOptC.setSelected(false);
				
				cont++;
				
				if(cont == 10) {
					PanelCarregamento painel = new PanelCarregamento();
					
					contentPane.setVisible(false);
					
					setContentPane(painel);
					painel.setVisible(true);
					update(getGraphics());
					
					
					cliente.enviarRespostas();
					
					painel.setVisible(false);
					
					setContentPane(contentPaneResultado);
					label.setText(cliente.getGanhador());
					contentPaneResultado.setVisible(true);
					
				} else {
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
				}
			}
		});
		
		buttonOptD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.addRespostas("d");
				
				buttonOptD.setSelected(false);
				
				cont++;
				
				if(cont == 10) {
					PanelCarregamento painel = new PanelCarregamento();
					
					contentPane.setVisible(false);
					
					setContentPane(painel);
					painel.setVisible(true);
					update(getGraphics());	
					
					cliente.enviarRespostas();
					
					painel.setVisible(false);
					
					setContentPane(contentPaneResultado);
					label.setText(cliente.getGanhador());
					contentPaneResultado.setVisible(true);
					
				} else {
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
				}
			}
		});
	}
}
