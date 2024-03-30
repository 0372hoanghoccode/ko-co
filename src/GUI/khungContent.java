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
		
		this.setBackground(Color.yellow); 
		this.setLayout(null);
		
		JLabel testlb = new JLabel("Test thu thoi nha ae");
		testlb.setBounds(10, 10, 100, 100);
		this.add(testlb);
	}

}
