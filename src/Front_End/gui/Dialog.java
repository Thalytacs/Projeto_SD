package Front_End.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class Dialog extends JDialog {

	private static final long serialVersionUID = 1L;

	/*
	public static void main(String[] args) {
		try {
			Dialog dialog = new Dialog("1234");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dialog(String codigo) {
		setBounds(500, 300, 150, 150);
		getContentPane().setLayout(null);
		{
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setBounds(10, 40, 114, 20);
			textPane.setText("Código: " + codigo);
			getContentPane().add(textPane);
		}
	}

}
