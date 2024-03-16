package Front_End.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.Panel;
import javax.swing.JDesktopPane;

public class Tela_Principal extends JFrame {
	private JTextField textField;
	private Button button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Tela_Principal frame = new Tela_Principal();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Tela_Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 413);
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
		
		button = new Button("Partida pública");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(160, 20, 134, 29);
		panel.add(button);
		
		Button button_1_1 = new Button("Criar partida privada");
		button_1_1.setBounds(160, 55, 134, 29);
		panel.add(button_1_1);
		
		Button button_1 = new Button("Entrar em partida privada");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(textField.getText());
			}
		});
		button_1.setBounds(238, 104, 141, 29);
		panel.add(button_1);
		
		Label label = new Label("Jogo das Perguntas");
		label.setFont(new Font("Arial", Font.BOLD, 35));
		label.setBounds(123, 61, 336, 57);
		contentPane.add(label);
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
