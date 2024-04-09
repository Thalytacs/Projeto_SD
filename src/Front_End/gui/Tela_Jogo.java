package Front_End.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Back_End.Cliente;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tela_Jogo extends JFrame {

	private JPanel contentPane;
	private static Cliente cliente;
	private String[] perguntas;
	private int cont = 1;
	
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public Tela_Jogo(Cliente cliente, String codigo) {
		
		this.cliente = cliente;
		JOptionPane aviso = new JOptionPane();
		
		if(codigo != null) {
			aviso.showInputDialog(codigo);
			
			setVisible(true);
			aviso.setVisible(true);
		}
		
		perguntas = cliente.receberPerguntas();
		
		aviso.setVisible(false);
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel pergunta = new JLabel(perguntas[0].split(";")[0]);
		pergunta.setBounds(82, 39, 388, 107);
		contentPane.add(pergunta);
		
		JRadioButton buttonOptA = new JRadioButton("A) " + perguntas[0].split(";")[1]);
		buttonOptA.setBounds(61, 199, 398, 23);
		contentPane.add(buttonOptA);
		
		JRadioButton buttonOptB = new JRadioButton("B) " + perguntas[0].split(";")[2]);
		buttonOptB.setBounds(61, 225, 398, 23);
		contentPane.add(buttonOptB);
		
		JRadioButton buttonOptC = new JRadioButton("C) " + perguntas[0].split(";")[3]);
		buttonOptC.setBounds(61, 251, 398, 23);
		contentPane.add(buttonOptC);
		
		JRadioButton buttonOptD = new JRadioButton("D) " + perguntas[0].split(";")[4]);
		buttonOptD.setBounds(61, 277, 398, 23);
		contentPane.add(buttonOptD);
		
		JButton btnNewButton = new JButton("Escolher");
		btnNewButton.setBackground(new Color(128, 255, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (buttonOptA.isSelected()) {
					cliente.addRespostas("a");
					
					buttonOptA.setSelected(false);
					
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
					cont++;
				}
				else if (buttonOptB.isSelected()) {
					cliente.addRespostas("b");
					
					buttonOptB.setSelected(false);
					
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
					cont++;
				}
				else if (buttonOptC.isSelected()) {
					cliente.addRespostas("c");
					
					buttonOptC.setSelected(false);
					
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
					cont++;
				}
				else if (buttonOptD.isSelected()) {
					cliente.addRespostas("d");
					
					buttonOptD.setSelected(false);
					
					buttonOptA.setText("A) " + perguntas[cont].split(";")[1]);
					buttonOptB.setText("B) " + perguntas[cont].split(";")[2]);
					buttonOptC.setText("C) " + perguntas[cont].split(";")[3]);
					buttonOptD.setText("D) " + perguntas[cont].split(";")[4]);
					pergunta.setText(perguntas[cont].split(";")[0]);
					cont++;
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione uma opção", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				if(cont == 5) {
					cliente.enviarRespostas();
					contentPane.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(431, 340, 89, 23);
		contentPane.add(btnNewButton);
	}
}
