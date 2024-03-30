package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

public class appContent extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<JPanel> khungs;
	private HomePage home;
	private EmployeePage employee;
//	private JPanel khung1;
//	private JPanel khung2;
	private JPanel khung3;
	private JPanel khung4;
	private JPanel khung5;
	private JPanel khung6;



	/**
	 * Create the panel.
	 */
	public appContent() {
		init();
	}
	
	public void init() {
		
		setLayout(new CardLayout(0, 0));
		
		
		home = new HomePage();
		
//		khung1 = new JPanel();
//		khung1.setBounds(5, 5, 500, 560);
//		khung1.setBackground(Color.BLUE);
		employee = new EmployeePage();
		
		
//		khung2 = new JPanel();
//		khung2.setBounds(5, 5, 1000, 560);
//		khung2.setBackground(Color.GREEN);
		
		khung3 = new JPanel();
		khung3.setBounds(5, 5, 1000, 560);
		khung3.setBackground(Color.YELLOW);
		
		khung4 = new JPanel();
		khung4.setBounds(5, 5, 1000, 560);
		khung4.setBackground(Color.PINK);
		
		khung5 = new JPanel();
		khung5.setBounds(5, 5, 1000, 560);
		khung5.setBackground(Color.RED);
		
		khung6 = new JPanel();
		khung6.setBounds(5, 5, 100, 560);
		khung6.setBackground(Color.ORANGE);
		
//		add(khung1, "KHUNG1");
		add(home, "KHUNG1");
		add(employee, "KHUNG2");
//		add(khung2, "KHUNG2");
		add(khung3, "KHUNG3");
		add(khung4, "KHUNG4");
		add(khung5, "KHUNG5");
		add(khung6, "KHUNG6");
		
		addPageToList();
		displayContent(0);
	}

	public void addPageToList() {
		khungs = new ArrayList<JPanel>();
//		khungs.add(khung1);
		khungs.add(home);
		khungs.add(employee);
//		khungs.add(khung2);
		khungs.add(khung3);
		khungs.add(khung4);
		khungs.add(khung5);
		khungs.add(khung6);
	}

	public void displayContent(int index) {
		((CardLayout)this.getLayout()).show(this, "KHUNG" + (index + 1));
	}
}
