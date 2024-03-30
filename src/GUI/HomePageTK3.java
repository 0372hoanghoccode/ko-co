package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.Color;


public class HomePageTK3 extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePageTK3() {
		setBackground(new Color(255, 255, 255));
		init();
	}
	
	public void init() {
		setLayout(null);
		setBounds(5, 300, 940, 300);
		JLabel lblNewLabel = new JLabel("Thống kê chi tiết từng phòng ban ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 216, 29);
		add(lblNewLabel);
	}
}
