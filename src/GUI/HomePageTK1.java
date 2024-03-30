package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class HomePageTK1 extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePageTK1() {
		setBackground(new Color(255, 255, 255));
		
		init();
	}
	
	public void init() {
		setLayout(null);
		
		setBounds(5, 5, 460, 275);
		
		JLabel lblNewLabel = new JLabel("Tổng quan ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 76, 31);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Số nhân viên ");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(352, 28, 68, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lương trung bình");
		lblNewLabel_2.setBounds(352, 51, 92, 13);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(128, 0, 64));
		lblNewLabel_3.setBounds(329, 28, 13, 13);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(0, 255, 0));
		lblNewLabel_4.setBounds(329, 51, 13, 13);
		add(lblNewLabel_4);
	}
}
