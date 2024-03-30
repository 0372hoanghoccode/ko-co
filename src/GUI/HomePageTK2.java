package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class HomePageTK2 extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePageTK2() {
		setBackground(new Color(255, 255, 255));
		init();
	}
	
	public void init() {
		setLayout(null);
		
		setBounds(470, 5, 460, 275);
		
		JLabel lblNewLabel = new JLabel("Tỉ lệ nhân viên từng phòng ban");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 205, 30);
		add(lblNewLabel);
		
		JPanel panel_4 = new JPanel(); // bao chú thích
		panel_4.setBounds(10, 43, 123, 222);
		add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 10, 10);
		panel_4.add(panel_1);
		panel_1.setBackground(new Color(128, 0, 255));
		
		JLabel lblNewLabel_1 = new JLabel("Phòng 1");
		lblNewLabel_1.setBounds(30, 10, 45, 13);
		panel_4.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 30, 10, 10);
		panel_4.add(panel_2);
		panel_2.setBackground(new Color(255, 0, 128));
		
		JLabel lblNewLabel_2 = new JLabel("Phòng 2");
		lblNewLabel_2.setBounds(30, 30, 45, 13);
		panel_4.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 50, 10, 10);
		panel_4.add(panel_3);
		panel_3.setBackground(new Color(64, 0, 64));
		
		JLabel lblNewLabel_3 = new JLabel("Phòng 3");
		lblNewLabel_3.setBounds(30, 47, 45, 13);
		panel_4.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 73, 10, 10);
		panel_4.add(panel);
		panel.setBackground(new Color(0, 128, 128));
		
		JLabel lblNewLabel_4 = new JLabel("Phòng 4");
		lblNewLabel_4.setBounds(30, 70, 45, 13);
		panel_4.add(lblNewLabel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 0, 0));
		panel_5.setBounds(10, 96, 10, 10);
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_5 = new JLabel("Phòng 5");
		lblNewLabel_5.setBounds(30, 93, 45, 13);
		panel_4.add(lblNewLabel_5);
		
	}
}
