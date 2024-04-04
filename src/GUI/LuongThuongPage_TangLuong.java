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

public class LuongThuongPage_TangLuong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public LuongThuongPage_TangLuong() {
		setLayout(null);
		
		JLabel label_tieuDe = new JLabel("Tên Thưởng Theo Tháng");
		label_tieuDe.setOpaque(true);
		label_tieuDe.setBackground(new Color(255, 255, 255));
		label_tieuDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_tieuDe.setBounds(10, 10, 626, 56);
		add(label_tieuDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 626, 519);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Nhân Viên", "Thời Gian",
				"Mức lương", "Đánh giá", "Xếp loại"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(646, 10, 324, 585);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Điều chỉnh mức lương");
		lblNewLabel.setBounds(10, 10, 213, 30);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel label_thoiGian = new JLabel(".............");
		label_thoiGian.setBounds(10, 49, 201, 24);
		panel.add(label_thoiGian);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 83, 204, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_thoiGian_1 = new JLabel(".............");
		label_thoiGian_1.setBounds(10, 112, 201, 37);
		panel.add(label_thoiGian_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 159, 204, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Điều chỉnh lương cá nhân");
		lblNewLabel_1.setBounds(10, 199, 201, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã Nhân Viên");
		lblNewLabel_2.setBounds(10, 236, 84, 20);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(10, 266, 204, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mức Lương Điều Chỉnh");
		lblNewLabel_2_1.setBounds(10, 336, 84, 20);
		panel.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 383, 204, 27);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton button_xacNhan = new JButton("Xác nhận");
		button_xacNhan.setBounds(201, 461, 113, 27);
		panel.add(button_xacNhan);

	}
}
