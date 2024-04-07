package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class LuongThuongPage_phuCapKhoanTru  extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public LuongThuongPage_phuCapKhoanTru() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 95, 655, 515);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Nhân Viên", "Thời Gian",
				"Lương cơ bản", "% lương thưởng", "Lương Thưởng"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 655, 90);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblPhCpV = new JLabel("Phụ Cấp và Khoản Thu");
		lblPhCpV.setBounds(10, 10, 221, 20);
		panel.add(lblPhCpV);
		lblPhCpV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JComboBox comboBox_nam = new JComboBox();
		comboBox_nam.setBounds(10, 56, 65, 21);
		panel.add(comboBox_nam);
		int currentYear = LocalDate.now().getYear();
        for (int year = currentYear; year >= currentYear - 10; year--) {
            comboBox_nam.addItem(year);
        }
        // Thiết lập giá trị mặc định cho comboBox_nam
        comboBox_nam.setSelectedItem(currentYear); // Chọn năm hiện tại
        
		JComboBox comboBox_thang = new JComboBox();
		comboBox_thang.setBounds(85, 56, 65, 21);
		panel.add(comboBox_thang);
		
		   for (int month = 1; month <= 12; month++) {
	            comboBox_thang.addItem(month);
	        }
	        // thiet lap mac dinh
	        comboBox_thang.setSelectedItem(LocalDate.now().getMonthValue()); // chọn tháng hiện tại
		
		JComboBox comboBox_phongBan = new JComboBox();
		comboBox_phongBan.setBounds(160, 56, 147, 21);
		panel.add(comboBox_phongBan);
		
        comboBox_phongBan.addItem("Phòng A");
        comboBox_phongBan.addItem("Phòng B");
        comboBox_phongBan.addItem("Phòng C");
        
		JComboBox comboBox_thoiGian = new JComboBox();
		comboBox_thoiGian.setBounds(317, 56, 147, 21);
		panel.add(comboBox_thoiGian);
		
	    comboBox_thoiGian.addItem("Thời gian");
        comboBox_thoiGian.addItem("Lương thưởng");
        comboBox_thoiGian.addItem("Thực lãnh");
		
		JComboBox comboBox_tangGiam = new JComboBox();
		comboBox_tangGiam.setBounds(474, 56, 65, 21);
		panel.add(comboBox_tangGiam);
		
		 comboBox_tangGiam.addItem("Tăng dần");
	        comboBox_tangGiam.addItem("Giảm dần");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(660, 0, 325, 610);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phụ Cấp Nhân Sự");
		lblNewLabel.setBounds(10, 10, 140, 37);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel label_thoiGian = new JLabel("Thời Gian");
		label_thoiGian.setBounds(10, 57, 93, 24);
		panel_1.add(label_thoiGian);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(10, 91, 105, 21);
		panel_1.add(comboBox_5);
		
		JComboBox comboBox_5_1 = new JComboBox();
		comboBox_5_1.setBounds(125, 91, 105, 21);
		panel_1.add(comboBox_5_1);
		
		JLabel label_maNhanVien = new JLabel("mã nhân viên");
		label_maNhanVien.setBounds(10, 122, 122, 24);
		panel_1.add(label_maNhanVien);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 156, 204, 19);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_phuCapKhac = new JLabel("Phụ cấp khác");
		label_phuCapKhac.setBounds(10, 196, 122, 21);
		panel_1.add(label_phuCapKhac);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 225, 204, 19);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		  ImageIcon confirm1 = new ImageIcon(getClass().getResource("/assets/appIcon/icons8-confirm-30.png"));
			JButton button_xacNhan_1 = new JButton(confirm1);
			button_xacNhan_1.setBounds(189, 254, 60, 27);
			panel_1.add(button_xacNhan_1);
		
		JLabel label_khoanTru_1 = new JLabel("Khoản Trừ");
		label_khoanTru_1.setBounds(10, 295, 122, 27);
		panel_1.add(label_khoanTru_1);
		
	
		JComboBox comboBox_5_2 = new JComboBox();
		comboBox_5_2.setBounds(10, 378, 105, 21);
		panel_1.add(comboBox_5);
		
		 int currentYear2 = LocalDate.now().getYear();
	        for (int year = currentYear2; year >= currentYear2 - 10; year--) {
	            comboBox_5.addItem(year);
	        }
	        comboBox_nam.setSelectedItem(currentYear2);
		
		JComboBox comboBox_5_1_1_1 = new JComboBox();
		comboBox_5_1_1_1.setBounds(125, 378, 105, 21);
		panel_1.add(comboBox_5_1_1_1);
		
		   // them dư lieu 12 tháng vào
        for (int month = 1; month <= 12; month++) {
            comboBox_5_1.addItem(month);
        }
        // thiet lap mac dinh
        comboBox_5_1.setSelectedItem(LocalDate.now().getMonthValue()); // chọn tháng hiện tại
		
		JLabel label_phuCapKhac_1 = new JLabel("Phụ cấp khác");
		label_phuCapKhac_1.setBounds(10, 471, 122, 21);
		panel_1.add(label_phuCapKhac_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 442, 204, 19);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(10, 502, 204, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
	    ImageIcon confirm = new ImageIcon(getClass().getResource("/assets/appIcon/icons8-confirm-30.png"));
		JButton button_xacNhan = new JButton(confirm);
		button_xacNhan.setBounds(189, 531, 60, 35);
		panel_1.add(button_xacNhan);
		
		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setBounds(10, 408, 105, 24);
		panel_1.add(lblNewLabel_1);

	}
}
