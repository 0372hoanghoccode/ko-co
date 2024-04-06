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
import java.awt.Color;

public class LuongThuongPage_LuongThuong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public LuongThuongPage_LuongThuong() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 115, 675, 495);
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
		panel.setBounds(0, 0, 675, 110);
		add(panel);
		panel.setLayout(null);
		
		JLabel label_tieuDe = new JLabel("Tên Thưởng Theo Tháng");
		label_tieuDe.setBounds(10, 10, 221, 20);
		panel.add(label_tieuDe);
		label_tieuDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JComboBox comboBox_nam = new JComboBox();
		comboBox_nam.setBounds(10, 65, 65, 21);
		panel.add(comboBox_nam);
		
		JComboBox comboBox_thang = new JComboBox();
		comboBox_thang.setBounds(85, 65, 65, 21);
		panel.add(comboBox_thang);
		
		JComboBox comboBox_phongBan = new JComboBox();
		comboBox_phongBan.setBounds(160, 65, 147, 21);
		panel.add(comboBox_phongBan);
		
		JComboBox comboBox_thoiGian = new JComboBox();
		comboBox_thoiGian.setBounds(317, 65, 147, 21);
		panel.add(comboBox_thoiGian);
		
		JComboBox comboBox_tangGiam = new JComboBox();
		comboBox_tangGiam.setBounds(474, 65, 65, 21);
		panel.add(comboBox_tangGiam);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(679, 0, 306, 610);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tạo mức Tiền Thưởng");
		lblNewLabel.setBounds(10, 10, 174, 30);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel label_thoiGian = new JLabel("Thời Gian");
		label_thoiGian.setBounds(10, 50, 93, 30);
		panel_1.add(label_thoiGian);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(10, 90, 105, 21);
		panel_1.add(comboBox_5);
		
		JComboBox comboBox_5_1 = new JComboBox();
		comboBox_5_1.setBounds(125, 90, 105, 21);
		panel_1.add(comboBox_5_1);
		
		JLabel label_donViThuHuong = new JLabel("Đơn vị thụ hưởng");
		label_donViThuHuong.setBounds(10, 134, 122, 30);
		panel_1.add(label_donViThuHuong);
		
		JLabel label_donViChiTiet = new JLabel("Đơn vị chi tiết");
		label_donViChiTiet.setBounds(10, 219, 122, 21);
		panel_1.add(label_donViChiTiet);
		
		JComboBox comboBox_5_2 = new JComboBox();
		comboBox_5_2.setBounds(10, 250, 201, 21);
		panel_1.add(comboBox_5_2);
		
		JLabel label_donViThuHuong_1_1 = new JLabel("Đơn vị thụ hưởng");
		label_donViThuHuong_1_1.setBounds(10, 281, 122, 37);
		panel_1.add(label_donViThuHuong_1_1);
		
		JComboBox comboBox_5_2_1 = new JComboBox();
		comboBox_5_2_1.setBounds(10, 171, 201, 21);
		panel_1.add(comboBox_5_2_1);
		
		textField = new JTextField();
		textField.setBounds(10, 328, 204, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton button_xacNhan = new JButton("Xác nhận");
		button_xacNhan.setBounds(166, 548, 113, 27);
		panel_1.add(button_xacNhan);

	}
}
