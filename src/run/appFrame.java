package run;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DTO.NHANVIEN;
import DTO.TAIKHOAN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import GUI.appContent;
import GUI.appHeader;
import GUI.menuNavigation;

public class appFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainContentPanel; //Cái này bao trùm toàn bộ nội dung của cái frame app này
	private appHeader head; //panel head
	private menuNavigation nav;
	private appContent content;
	private TAIKHOAN account;
	private boolean eventFnc[]; // mảng chức năng
	
	public void setAccountCurrenLogin(TAIKHOAN tk) {
		this.account = tk;
	}

	public void setEventFnc(boolean[] eventFnc) {
		this.eventFnc = eventFnc;
	}

	public appFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setLocationRelativeTo(null);

//		setUndecorated(true);
		
		mainContentPanel = new JPanel(); 
		mainContentPanel.setBorder(new LineBorder(Color.GRAY,2));
		mainContentPanel.setLayout(new BorderLayout());
		setContentPane(mainContentPanel);
		
		//header
		head = new appHeader();
		head.setPreferredSize(new Dimension(1200, 40));
		mainContentPanel.add(head, BorderLayout.NORTH);
		
		// menubar + chức năng chính
		JPanel fncPanel = new JPanel();
		fncPanel.setLayout(new BorderLayout());
		fncPanel.setPreferredSize(new Dimension(1200,660));
		mainContentPanel.add(fncPanel, BorderLayout.CENTER);

		nav = new menuNavigation(this);
		nav.setPreferredSize(new Dimension(200,660));
		fncPanel.add(nav, BorderLayout.WEST);
		
		content = new appContent();
		content.setPreferredSize(new Dimension(1000, 660));
		fncPanel.add(content, BorderLayout.CENTER);
//		StartingApp();
		
	}

	public void StartingApp() {

		// NHANVIEN nv = DAO.NhanVienDAO.getInstance().getNhanVien(account.getUsername());
		ArrayList<String> title = new ArrayList<>();
		ArrayList<String> icon = new ArrayList<>();
		title.add(nav.fnc[0]);
		icon.add(nav.iconMenu[0]);
		
		// nhân viên 
		if (eventFnc[0] || eventFnc[1]) {
			title.add(nav.fnc[1]);
			icon.add(nav.iconMenu[1]);

			// if (!eventFnc[1]) {
			// 	// xem thông tin cá nhân 
			// } else {
			// 	if (!eventFnc[2]) {
			// 		// vô hiệu hóa import với thêm (setEnable và setVisible)
			// 	}
			// 	if (!eventFnc[4]) {
			// 		// vô hiệu hóa export 
			// 	}
			// 	if (!eventFnc[3]) {
			// 		// hiển thị chi tiết 
			// 		// ẩn xóa sửa tt(xóa , sửa, chi tiết)
			// 		content.getEmployeePage().getButton_ChiTiet().setBounds(915, 83, 50, 30);
			// 	}

			// 	if (!eventFnc[2] && !eventFnc[4]) {
			// 		// hiện nút tìm kiếm (filed và button)
			// 	} else if (!eventFnc[2] && eventFnc[4]) {
			// 		// không có thêm, có xuất (export, find filed, find button)
			// 	} else if (eventFnc[2] && !eventFnc[4]) {
					
			// 	}

			// }
		}


		// Phòng ban
		if (eventFnc[17] || eventFnc[21]) {
			title.add(nav.fnc[2]);
			icon.add(nav.iconMenu[2]);

			if (!eventFnc[18]) {
				// k thể tạo phòng ban
			}
			if (!eventFnc[19]) {
				// k thể sửa xóa phòng ban
			}
			if (!eventFnc[22]) {
				// k thể xuất file excel 
			}
			if (!eventFnc[20]) {
				// Ko thể chỉnh sửa thông tin nhân viên được chọn 
			}

			if (!eventFnc[17] && eventFnc[21]) {
				// ẩn trình quản lí phòng ban 	
			} else if (eventFnc[17] && !eventFnc[21]) {
				// ẩn thống kê, hiện quản lí 
			} else {

			}
		}

		
		

		// Hợp đồng
		if (eventFnc[12]) {
			title.add(nav.fnc[3]);
			icon.add(nav.iconMenu[3]);

			if (!eventFnc[16]) {
				// ẩn btn export và vô hiệu hóa nó 
			} 


			if (!eventFnc[13] && !eventFnc[15]) {
				// ẩn kí hợp đồng và thống kê
			} else if (eventFnc[13] && !eventFnc[15]) {
				// ẩn thống kê
			} else if (!eventFnc[13] && eventFnc[15]) {
				// ẩn kí hợp đồng
			} else {

			}
		}


		// chấm công
		if (eventFnc[23] || eventFnc[24]) {
			title.add(nav.fnc[4]);
			icon.add(nav.iconMenu[4]);	

			if (!eventFnc[27]) {
				// ẩn nút xuất của bảng chấm công form 1 
			}
			if (eventFnc[23] && !eventFnc[24]) {
				// ẩn nút thêm và đặt data của user hiện tại
			} else if (eventFnc[24]) {
				if (!eventFnc[25]) {
					// ẩn nút thêm 
				}
			}
		}


		// Lương
		if (eventFnc[28] || eventFnc[29]) {
			title.add(nav.fnc[5]);
			icon.add(nav.iconMenu[5]);
			if (!eventFnc[33]) {
				// ko xuất excel dc 
			}
			if (eventFnc[28] && !eventFnc[29]) {
				// ẩn lương thưởng, phụ cấp khoản trừ và tăng lương (ẩn btn)
				// lấy data lươnhg cua nhan vien
			} else {
				if (eventFnc[30] && eventFnc[31] && !eventFnc[32]) {
					// ẩn tăng lương
				} else if (eventFnc[30] && !eventFnc[31] && !eventFnc[32]) {
					// ẩn phụ cấp khoản trừ và tăng lương
				} else if (!eventFnc[30] && !eventFnc[31] && !eventFnc[32]) {
					// ẩn lương thưởng, phụ cấp khoản trừ và tăng lương (ẩn btn)
				} else if (eventFnc[30] && !eventFnc[31] && eventFnc[32]) {
					// ẩn phụ cấp khoản trừ, setbound tăng lương
				} else if (!eventFnc[30] && eventFnc[31] && eventFnc[32]) {
					// ẩn lương thưởng setbound phụ cấp khoản trừ và tăng lương lại
				} else if (!eventFnc[30] && eventFnc[31] && !eventFnc[32]) {
					// ẩn lương thưởng tăng lương, phụ cap khoan tru setbound lại
				} else if (!eventFnc[30] && !eventFnc[31] && eventFnc[32]) {
					// ẩn phụ cấp khoản trừ tăng lương, setboudn lại tăng lương
				}
			}
		}

		// Đánh giá
		if (eventFnc[34] || eventFnc[35]) {
			title.add(nav.fnc[6]);
			icon.add(nav.iconMenu[6]);
			if (eventFnc[34] && !eventFnc[35]) {
				// ẩn nút thêm và xóa form
			} else {
				if (!eventFnc[36]) {
					// ẩn thêm xóa chi tiết của đánh giá
				}
			}
		}

		// tài khoản
		if (eventFnc[37]) {
			title.add(nav.fnc[7]);
			icon.add(nav.iconMenu[7]);
		}

		// tuyển dụng 
		if (eventFnc[5] || eventFnc[8]) {
			
			title.add(nav.fnc[8]); // tuyển dụng
			icon.add(nav.iconMenu[8]);
			// if (!eventFnc[5]) {
			// 	// không có trang tuyển dụng, có trang ứng viên
			// 	content.getUngvien().getLb1().setText("Ứng viên");
			// 	content.getUngvien().getLb1().setBounds(15, 0, 100, 30);
			// 	content.getUngvien().getLb2().setVisible(false);
			// 	content.getUngvien().getTuyenDungView().setVisible(false);
			// 	content.getUngvien().getUngVienView().setVisible(true);

			// 	if (!eventFnc[9]) {
			// 		content.getUngvien().getUngVienView().getButton_UngVien_Them().setVisible(false);
			// 	}
			// 	if (!eventFnc[10]) {
			// 		content.getUngvien().getUngVienView().getButton_UngVien_Xoa().setVisible(false);
			// 	}
			// 	if (!eventFnc[11]) {
			// 		content.getUngvien().getUngVienView().getButtonTuyenUngVien().setVisible(false);
			// 	}
			// }
			// if (!eventFnc[8]) {
			// 	// không có trang ứng viên, có trang tuyển dụng
			// 	content.getUngvien().getLb1().setVisible(false);
			// 	content.getUngvien().getLb2().setBounds(115, 0, 130, 30);
			// 	content.getUngvien().getTuyenDungView().setVisible(true);
			// 	content.getUngvien().getUngVienView().setVisible(false);

			// 	if (!eventFnc[6]) {
			// 		content.getUngvien().getTuyenDungView().getButton_TuyenDung_Them().setVisible(false);
			// 	}
			// 	if (!eventFnc[7]) {
			// 		content.getUngvien().getTuyenDungView().getButton_TuyenDung_Xoa().setVisible(false);
			// 	}
			// }
		}

		

		


		// render menu ở đây 
		// mảng title và icon xem dc ở đây
		int number_of_menu = title.size();
		String title_menu[] = new String[number_of_menu];
		String icon_menu[] = new String[number_of_menu];
		for (int i = 0; i < number_of_menu; i++) {
			title_menu[i] = title.get(i);
			icon_menu[i] = icon.get(i);
		}

		nav.set_fnc_use(title_menu);
		nav.set_iconMenu_use(icon_menu);
		nav.Render_nav();
	}

    public appContent getAppContent() {
        return content;
    }

	public boolean[] getEventFnc() {
		return eventFnc;
	}
	public TAIKHOAN getAccountCurrenLogin() {
		return account;
	}

}
