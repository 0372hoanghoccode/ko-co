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

public class LuongThuongPage_LuongThuong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public LuongThuongPage_LuongThuong() {
		setLayout(null);
		
		JLabel label_tieuDe = new JLabel("Tên Thưởng Theo Tháng");
		label_tieuDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_tieuDe.setBounds(43, 38, 221, 20);
		add(label_tieuDe);
		
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
		
		JLabel lblNewLabel = new JLabel("Tạo mức Tiền Thưởng");
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
		
		JComboBox comboBox_5_2 = new JComboBox();
		comboBox_5_2.setBounds(686, 266, 201, 21);
		add(comboBox_5_2);
		
		JLabel label_donViThuHuong = new JLabel("Đơn vị thụ hưởng");
		label_donViThuHuong.setBounds(686, 235, 122, 37);
		add(label_donViThuHuong);
		
		JLabel label_donViChiTiet = new JLabel("Đơn vị chi tiết");
		label_donViChiTiet.setBounds(686, 322, 122, 21);
		add(label_donViChiTiet);
		
		JComboBox comboBox_5_2_1 = new JComboBox();
		comboBox_5_2_1.setBounds(686, 358, 201, 21);
		add(comboBox_5_2_1);
		
		JLabel label_donViThuHuong_1_1 = new JLabel("Đơn vị thụ hưởng");
		label_donViThuHuong_1_1.setBounds(686, 410, 122, 37);
		add(label_donViThuHuong_1_1);
		
		textField = new JTextField();
		textField.setBounds(683, 446, 204, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton button_xacNhan = new JButton("Xác nhận");
		button_xacNhan.setBounds(787, 535, 113, 27);
		add(button_xacNhan);

	}
}
