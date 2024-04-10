package Front_End.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Back_End.Cliente;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Canvas;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Panel;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;

public class Tela_Principal extends JFrame {
	
	private static Tela_Principal tela;
	private JTextField textField;
	private Cliente cliente;

	public static void main(String[] args) {
		tela = new Tela_Principal();
		tela.setVisible(true);
	}

	
	public Tela_Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 413);
		
		//Painel de opções
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(67, 182, 451, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setToolTipText("Digite código da partida privada");
		textField.setBounds(98, 104, 125, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		Label label = new Label("Jogo das Perguntas");
		label.setFont(new Font("Arial", Font.BOLD, 35));
		label.setBounds(123, 61, 336, 57);
		contentPane.add(label);
		
		
		//Painel para aguardar novo jogador
		PanelCarregamento painel = new PanelCarregamento();
			
		
		//Botão para iniciar partida publica
		Button buttonPublic = new Button("Partida pública");
		buttonPublic.setActionCommand("1");
		buttonPublic.addActionListener(new ActionListener() {
			//Ação do botão
			public void actionPerformed(ActionEvent e) {
				cliente = new Cliente();
				
				contentPane.setVisible(false);
				setContentPane(painel);
				painel.setVisible(true);
				
				setVisible(true);
				update(getGraphics());
				
				cliente.criarPartida(e.getActionCommand());
				
				String[] perguntas = cliente.receberPerguntas();
				
				
				Tela_Jogo telaJogo = new Tela_Jogo(cliente, perguntas);
				
				tela.setVisible(false);
				telaJogo.setVisible(true);
			}
		});
		buttonPublic.setBounds(160, 20, 134, 29);
		panel.add(buttonPublic);
		

		//Botão para iniciar partida privada
		Button buttonPrivate = new Button("Criar partida privada");
		buttonPrivate.setActionCommand("2");
		buttonPrivate.addActionListener(new ActionListener() {
			//Ação do botão
			public void actionPerformed(ActionEvent e) {
				cliente = new Cliente();
				String codigo = null;
				
				cliente.criarPartida(e.getActionCommand());
						
				try {	
					BufferedReader doServidor = new BufferedReader(new InputStreamReader(cliente.socket.getInputStream()));
					System.out.println("Antes de receber");
					codigo = doServidor.readLine();
					
					System.out.println("Codigo na tela: "+ codigo);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				contentPane.setVisible(false);
				setContentPane(painel);
				painel.setVisible(true);
				
				setVisible(true);
				update(getGraphics());
				
				Dialog d = new Dialog(codigo);
				
				d.setVisible(true);
				
				d.update(getGraphics());
				
				d.setVisible(false);
				
				
				String[] perguntas = cliente.receberPerguntas();
				
				Tela_Jogo telaJogo = new Tela_Jogo(cliente, perguntas);
				
				tela.setVisible(false);
				telaJogo.setVisible(true);
			}
		});
		buttonPrivate.setBounds(160, 55, 134, 29);
		panel.add(buttonPrivate);
		
		
		//Botão para entrar em partida privada
		Button buttonConnectPrivate = new Button("Entrar em partida privada");
		buttonConnectPrivate.addActionListener(new ActionListener() {
			//Ação do botão
			public void actionPerformed(ActionEvent e) {
				String codigo = textField.getText();
								
				if(codigo.equals("")) {
					JOptionPane.showMessageDialog(null, "Código inválido", "Erro", JOptionPane.ERROR_MESSAGE);
				} else {
					cliente = new Cliente();
					cliente.criarPartida(codigo);
					
					if(cliente.existePartidaPrivada().equals("1")) {
						
						String[] perguntas = cliente.receberPerguntas();
						
						Tela_Jogo telaJogo = new Tela_Jogo(cliente, perguntas);
					
						tela.setVisible(false);
						telaJogo.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Código inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					}					
				}
			}
		});
		buttonConnectPrivate.setBounds(238, 104, 141, 29);
		panel.add(buttonConnectPrivate);
	}
	
	public JTextPane atualizaCodigo(String codigo) {
		JTextPane textCodigo = new JTextPane();
		textCodigo.setEditable(false);
		textCodigo.setText(codigo);
		textCodigo.setBounds(313, 59, 115, 25);
		
		return textCodigo;
	}
}
