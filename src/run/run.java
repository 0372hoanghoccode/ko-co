package run;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class run {
	
	private loginFrame login;
//	private appFrame app;
	
	public run() {
		init();
		
	}
	// THẤY KO ?
	// Thấy không 2 >>
	public void init() {
		
		
		login = new loginFrame();
		login.setVisible(true);
		
//		app = new appFrame();
//		app.setVisible(false);
		
		
		eventHandler();
		
		
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

				// System.out.println(DAO.ChucVuDAO.getInstance().getList().get(0).getTenChucVu());
					System.out.println(DAO.HopDongLaoDongDAO.getInstance().getList().get(1).getTenNhanVien());
					new run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void eventHandler() {
		if (login.getIsLogin() == true) {
			login.dispose();
//			app.setVisible(true);
		}
		
		
	}

}