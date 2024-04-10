package Front_End.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;

public class Dialog extends JDialog {

	public Dialog(String codigo) {
		setBounds(100, 100, 602, 413);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aguardando Oponente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(120, 101, 363, 79);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel2 = new JLabel();
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel2.setText("Código: " + codigo);
		lblNewLabel2.setBounds(221, 216, 172, 49);
		getContentPane().add(lblNewLabel2);
	}
}
