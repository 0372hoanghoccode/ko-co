package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class PhongbanPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable tableNV;

	/**
	 * Create the panel.
	 */
	public PhongbanPage() {
		init();
	}
	
	public void init() {
		setLayout(null);
		
		JPanel panel_1 = new JPanel(); // alo panel này chứa mục riêng của tổng quan vs quản lí
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 10, 980, 247);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lí phòng ban");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 177, 26);
		panel_1.add(lblNewLabel);
		
		
		
		String[] columnNames = {
				"STT", "T\u00EAn ph\u00F2ng", "Tr\u01B0\u1EDFng ph\u00F2ng", "S\u1ED1 nh\u00E2n vi\u00EAn", "L\u01B0\u01A1ng trung b\u00ECnh"
			};
		
		Object[][] data = {
	            {"1", "Phòng A", "Nguyễn Văn A", "25", "4242424"},
	            {"2", "Phòng B", "Trần Thị B", "30", "23213131"},
	            {"3", "Phòng C", "Lê Văn C", "28", "45242314"},
	            {"4", "Phòng D", "Hoàng Văn D", "35", "6342423"},
	            {"5", "Phòng E", "Mai Thị E", "27", "3351313"},
	            {"6", "Phòng F", "Đỗ Văn F", "31", "8624234"},
	            {"7", "Phòng G", "Vũ Thị G", "33", "7842311"},
	            {"8", "Phòng H", "Phạm Văn H", "29", "5224123"},
	            {"9", "Phòng I", "Bùi Thị I", "26", "7412314"},
	            {"10", "Phòng K", "Nguyễn Văn K", "32", "8923123"},
	            {"11", "Phòng L", "Trần Văn L", "36", "6532423"},
	            {"12", "Phòng M", "Lê Thị M", "29", "4321313"},
	            {"13", "Phòng N", "Hoàng Văn N", "30", "7434234"},
	            {"14", "Phòng O", "Mai Thị O", "28", "9822311"},
	            {"15", "Phòng P", "Đỗ Văn P", "34", "5224123"}
	        };
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 46, 960, 211);

		panel_1.add(scrollPane);
		
		JButton btnNewButton = new JButton("Thêm ");
		btnNewButton.setBounds(584, 14, 85, 21);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.setBounds(679, 14, 85, 21);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.setBounds(774, 14, 85, 21);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Xuất");
		btnNewButton_3.setBounds(869, 14, 85, 21);
		panel_1.add(btnNewButton_3);
		
		
		
		
		
		
		
		JPanel panel = new JPanel(); // panel nv phòng ban
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 267, 645, 323);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quản lí nhân viên - Phòng ba chấm");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 10, 273, 30);
		panel.add(lblNewLabel_1);
	
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(665, 267, 325, 323);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Thông tin nhân viên");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 10, 204, 28);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tên: ");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 85, 127, 21);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mã số: ");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 48, 127, 28);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ngày sinh:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 117, 127, 21);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Địa chỉ:");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 148, 127, 28);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Chức vụ:");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 186, 127, 28);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setBounds(194, 48, 121, 157);
		panel_2.add(lblNewLabel_8);
		
		String[] columnNamesNV = {
				"STT", "Tên nhân viên", "Chức vụ", "Bảo hiểm",
		};
		Object[][] dataNV = {
				{"1", "Ai", "Trùm cuối", "Có"},
				{"2", "Ai1", "Trùm phụ", "Có"},
				{"3", "Ai2", "Trùm", "Có"},
				{"4", "Ai3", "Trùm phụ", "Có"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"5", "Ai4", "Trùm phụ", "Không"},
				{"6", "Ai5", "Trùm phụ", "Có"},
				{"7", "Ai6", "Trùm phụ", "Có"},
				{"8", "Ai7", "Trùm phụ", "Có"},
		};
		
		
		DefaultTableModel modelNV = new DefaultTableModel(dataNV, columnNamesNV);
		tableNV = new JTable(modelNV);
		tableNV.setFont(new Font("Arial", Font.PLAIN, 14));

		JScrollPane scrollPaneNV = new JScrollPane(tableNV);
        scrollPaneNV.setBounds(10, 50, 625, 263);

		panel.add(scrollPaneNV);
		
		//
		
		
	}
}
