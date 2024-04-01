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
	private PhongbanPage phongBan;
	private HopdongPage hopDong;
	private LuongThuongPage LuongThuongPage; 
	

	
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
		employee = new EmployeePage();
		phongBan = new PhongbanPage();
		hopDong = new HopdongPage();
		LuongThuongPage = new LuongThuongPage();
		

		
		
//		
//		luongPage = new JPanel();
//		luongPage.setBounds(5, 5, 1000, 560);
//		luongPage.setBackground(Color.RED);
		
		khung6 = new JPanel();
		khung6.setBounds(5, 5, 100, 560);
		khung6.setBackground(Color.ORANGE);
		

		add(home, "KHUNG1");
		add(employee, "KHUNG2");
		add(phongBan, "KHUNG3");
		add(hopDong, "KHUNG4");
		add(LuongThuongPage, "KHUNG5");
		add(khung6, "KHUNG6");
		
		addPageToList();
		displayContent(0);
	}

	public void addPageToList() {
		khungs = new ArrayList<JPanel>();
		khungs.add(home);
		khungs.add(employee);
		khungs.add(phongBan);
		khungs.add(hopDong);
		khungs.add(LuongThuongPage);
		khungs.add(khung6);
	}

	public void displayContent(int index) {
		((CardLayout)this.getLayout()).show(this, "KHUNG" + (index + 1));
	}
}
