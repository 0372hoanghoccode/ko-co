package run;

import java.awt.EventQueue;

import javax.swing.UIManager;



public class run {
	
	private loginFrame login;

	public run() {
		init();
		
	}

	public void init() {
		
		login = new loginFrame();
		login.setVisible(true);
		

		eventHandler();
		
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
<<<<<<< Updated upstream

<<<<<<< HEAD
					// System.out.println(DAO.ChucVuDAO.getInstance().getList().get(0).getTenChucVu());
					//System.out.println(DAO.HopDongLaoDongDAO.getInstance().getList().get(1).getTenNhanVien());
=======
				// System.out.println(DAO.ChucVuDAO.getInstance().getList().get(0).getTenChucVu());
					System.out.println(DAO.HopDongLaoDongDAO.getInstance().getList().get(1).getTenNhanVien());
>>>>>>> 9858a890336df2c5092be8eafe4428cd53325208
=======
					
>>>>>>> Stashed changes
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
		}
		
		
	}

}