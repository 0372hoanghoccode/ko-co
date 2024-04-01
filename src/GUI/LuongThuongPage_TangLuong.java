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
		label_tieuDe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_tieuDe.setBounds(43, 38, 221, 20);
		add(label_tieuDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 96, 626, 550);
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
		
		JLabel lblNewLabel = new JLabel("Điều chỉnh mức lương");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(674, 45, 213, 37);
		add(lblNewLabel);
		
		JLabel label_thoiGian = new JLabel(".............");
		label_thoiGian.setBounds(686, 124, 201, 37);
		add(label_thoiGian);
		
		JButton button_xacNhan = new JButton("Xác nhận");
		button_xacNhan.setBounds(795, 550, 113, 27);
		add(button_xacNhan);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(683, 171, 204, 19);
		add(textField_1);
		
		JLabel label_thoiGian_1 = new JLabel(".............");
		label_thoiGian_1.setBounds(686, 199, 201, 37);
		add(label_thoiGian_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(683, 257, 204, 19);
		add(textField_2);
		
		JLabel lblNewLabel_1 = new JLabel("Điều chỉnh lương cá nhân");
		lblNewLabel_1.setBounds(686, 337, 201, 27);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã Nhân Viên");
		lblNewLabel_2.setBounds(678, 391, 84, 20);
		add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(674, 421, 204, 27);
		add(textField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mức Lương Điều Chỉnh");
		lblNewLabel_2_1.setBounds(677, 460, 84, 20);
		add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(674, 489, 204, 27);
		add(textField_3);

	}
}
