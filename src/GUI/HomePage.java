package GUI;

import javax.swing.JPanel;


public class HomePage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	
	HomePageTK1 tk1;
	HomePageTK2 tk2;
	HomePageTK3 tk3;
	
	public HomePage() {
		init();
	}
	
	public void init() {
		setLayout(null);
		tk1 = new HomePageTK1();
		tk1.setBounds(5, 5, 496, 275);
		add(tk1);
		
		tk2 = new HomePageTK2();
		tk2.setBounds(511, 5, 479, 275);
		add(tk2);
		
		tk3 = new HomePageTK3();
		tk3.setBounds(5, 290, 985, 340);
		add(tk3);
		
	}
	

}
