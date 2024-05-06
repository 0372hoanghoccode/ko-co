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
		tk1.setBounds(5, 5, 500, 295);
		add(tk1);
		
		tk2 = new HomePageTK2();
		tk2.setBounds(510, 5, 480, 295);
		tk2.setData(DAO.PhongBanDAO.getInstance().getDanhSachTenVaSoLuongNhanVienPhongBan());
		tk2.ani();
		add(tk2);
		
		tk3 = new HomePageTK3();
		tk3.setBounds(5, 304, 985, 346);
		add(tk3);
		
	}
	

}
