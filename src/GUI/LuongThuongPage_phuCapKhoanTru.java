package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

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
		
		JLabel lblPhCpV = new JLabel("Phụ Cấp và Khoản Thu");
		lblPhCpV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhCpV.setBounds(43, 38, 221, 20);
		add(lblPhCpV);
		
		JComboBox comboBox_nam = new JComboBox();
		comboBox_nam.setBounds(43, 86, 65, 21);
		add(comboBox_nam);
		
		JComboBox comboBox_thang = new JComboBox();
		comboBox_thang.setBounds(134, 86, 65, 21);
		add(comboBox_thang);
		
		JComboBox comboBox_phongBan = new JComboBox();
		comboBox_phongBan.setBounds(230, 86, 147, 21);
		add(comboBox_phongBan);
		
		JComboBox comboBox_thoiGian = new JComboBox();
		comboBox_thoiGian.setBounds(402, 86, 147, 21);
		add(comboBox_thoiGian);
		
		JComboBox comboBox_tangGiam = new JComboBox();
		comboBox_tangGiam.setBounds(574, 86, 65, 21);
		add(comboBox_tangGiam);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 190, 626, 493);
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
		
		JLabel lblNewLabel = new JLabel("Phụ Cấp Nhân Sự");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(674, 45, 213, 37);
		add(lblNewLabel);
		
		JLabel label_thoiGian = new JLabel("Thời Gian");
		label_thoiGian.setBounds(686, 124, 93, 37);
		add(label_thoiGian);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(686, 188, 105, 21);
		add(comboBox_5);
		
		JComboBox comboBox_5_1 = new JComboBox();
		comboBox_5_1.setBounds(813, 188, 105, 21);
		add(comboBox_5_1);
		
		JLabel label_maNhanVien = new JLabel("mã nhân viên");
		label_maNhanVien.setBounds(686, 235, 122, 37);
		add(label_maNhanVien);
		
		JLabel label_phuCapKhac = new JLabel("Phụ cấp khác");
		label_phuCapKhac.setBounds(686, 322, 122, 21);
		add(label_phuCapKhac);
		
		JButton button_xacNhan = new JButton("Xác nhận");
		button_xacNhan.setBounds(805, 679, 113, 27);
		add(button_xacNhan);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(683, 267, 204, 19);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(683, 350, 204, 19);
		add(textField_2);
		
		JButton button_xacNhan_1 = new JButton("Xác nhận");
		button_xacNhan_1.setBounds(787, 385, 113, 27);
		add(button_xacNhan_1);
		
		JLabel label_thoiGian2 = new JLabel("Thời Gian");
		label_thoiGian2.setBounds(686, 457, 122, 37);
		add(label_thoiGian2);
		
		JComboBox comboBox_5_2 = new JComboBox();
		comboBox_5_2.setBounds(686, 505, 105, 20);
		add(comboBox_5_2);
		
		JLabel label_maNhanVien_1 = new JLabel("mã nhân viên");
		label_maNhanVien_1.setBounds(686, 535, 122, 37);
		add(label_maNhanVien_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(683, 567, 204, 19);
		add(textField);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(683, 650, 204, 19);
		add(textField_3);
		
		JLabel label_phuCapKhac_1 = new JLabel("Phụ cấp khác");
		label_phuCapKhac_1.setBounds(686, 622, 122, 21);
		add(label_phuCapKhac_1);
		
		JLabel label_khoanTru_1 = new JLabel("Khoản Trừ");
		label_khoanTru_1.setBounds(686, 422, 122, 27);
		add(label_khoanTru_1);
		
		JComboBox comboBox_5_1_1_1 = new JComboBox();
		comboBox_5_1_1_1.setBounds(813, 505, 105, 21);
		add(comboBox_5_1_1_1);

	}
}
