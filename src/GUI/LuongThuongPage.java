package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LuongThuongPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private LuongPage1 luongPage1;
	private LuongThuongPage_LuongThuong LuongThuong;
	private LuongThuongPage_phuCapKhoanTru phuCapKhoanThu;
	private LuongThuongPage_TangLuong tangLuong ; 
	/**
	 * Create the panel.
	 */
	public LuongThuongPage() {
		
		init();
	}

	public void init() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 980, 30);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("luongPage1");
		
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 64, 128));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 100, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lương Thưởng");
		
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(100, 0, 130, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("phuCapKhoanThu");
		
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(0, 64, 128));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(230, 0, 137, 30);
		panel.add(lblNewLabel_2);
		
		
	JLabel label_tangLuong = new JLabel("Tăng Lương");
	

	label_tangLuong.setOpaque(true);
	label_tangLuong.setForeground(new Color(255, 255, 255));
	label_tangLuong.setBackground(new Color(0, 128, 128));
	label_tangLuong.setHorizontalAlignment(SwingConstants.CENTER);
	label_tangLuong.setFont(new Font("Arial", Font.PLAIN, 14));
	label_tangLuong.setBounds(366, 0, 130, 30);
		panel.add(label_tangLuong);
		
		tangLuong = new LuongThuongPage_TangLuong();
		luongPage1 = new LuongPage1(); 
		LuongThuong = new LuongThuongPage_LuongThuong();
		phuCapKhoanThu = new LuongThuongPage_phuCapKhoanTru();
		JPanel panel_1 = new JPanel(); // chua may cai kia
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 50, 980, 600);
		add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		panel_1.add(luongPage1, 0);
		panel_1.add(LuongThuong, 1);
		panel_1.add(phuCapKhoanThu, 2);
		panel_1.add(tangLuong, 3);
		
		
		lblNewLabel.addMouseListener(new MouseAdapter() { // tong quan
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(0);
			}
		});
		lblNewLabel_1.addMouseListener(new MouseAdapter() { // ki / gia han
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(1);
			}
		});
		lblNewLabel_2.addMouseListener(new MouseAdapter() { // thong ke
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(2);
			}
		});
		label_tangLuong.addMouseListener(new MouseAdapter() { // thong ke
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(3);
			}
		});

		showPage(0);
	}

	public void showPage(int index) {
		if (index == 0) {
			luongPage1.setVisible(true);
			LuongThuong.setVisible(false);
			phuCapKhoanThu.setVisible(false);
			tangLuong.setVisible(false);
		}
		if (index == 1) {
			luongPage1.setVisible(false);
			LuongThuong.setVisible(true);
			phuCapKhoanThu.setVisible(false);
			tangLuong.setVisible(false);
		}
		if (index == 2) {
			luongPage1.setVisible(false);
			LuongThuong.setVisible(false);
			phuCapKhoanThu.setVisible(true);
			tangLuong.setVisible(false);
		}
		if (index == 3) {
			luongPage1.setVisible(false);
			LuongThuong.setVisible(false);
			phuCapKhoanThu.setVisible(false);
			tangLuong.setVisible(true);
		}
		
	}


}
