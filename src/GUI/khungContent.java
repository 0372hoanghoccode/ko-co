package GUI;

import java.awt.Color;

import javax.swing.*;


public class khungContent extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public khungContent() {
		init();
	}
	
	public void init() {
		
//		this.setBounds(50, 50, 100, 100);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JLabel testlb = new JLabel("Test thu thoi nha ae");
		testlb.setBounds(10, 10, 50, 20);
		this.add(testlb);
		
	}
	
	

}
