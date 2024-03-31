package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class HopdongPage_Thongke extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HopdongPage_Thongke() {
		
		init();
	}

	public void init() {
		setBackground(new Color(128, 255, 255));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Làm biếng quá ko kéo thả nữa TvT");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel.setBounds(133, 108, 448, 60);
		add(lblNewLabel);
	}

}
