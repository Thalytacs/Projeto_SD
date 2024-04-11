package Front_End.gui;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelCarregamento extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelCarregamento() {
		setLayout(null);
		setBounds(100, 100, 602, 413);
		
		JLabel lblNewLabel = new JLabel("Aguardando Oponente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(120, 111, 363, 79);
		add(lblNewLabel);
	}

}
