package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TaiKhoanPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<JCheckBox> listCheckBox;
	/**
	 * Create the panel.
	 */
	public TaiKhoanPage() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TÀI KHOẢN VÀ PHÂN QUYỀN");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(5, 5, 985, 55);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 65, 390, 590);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("TÀI KHOẢN NGƯỜI DÙNG");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 11, 160, 30);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(190, 15, 161, 22);
		panel.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 432, 367, 148);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Chi tiết tài khoản");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 11, 169, 29);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("001 - Nguyen h");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 60, 169, 29);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quyền tài khoản");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 95, 169, 29);
		panel_2.add(lblNewLabel_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(189, 95, 168, 29);
		panel_2.add(comboBox_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(199, 108, 23, 2);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lưu");
		btnNewButton_1.setBounds(268, 63, 89, 23);
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(400, 65, 305, 590);
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Nhóm Quyền");
		lblNewLabel_5.setBackground(Color.GRAY);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 21, 167, 30);
		panel_3.add(lblNewLabel_5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(20, 68, 1, 4);
		panel_3.add(panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(710, 65, 280, 590);
		add(panel_6);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(168, 26, 89, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_6.setLayout(null);
		panel_6.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(69, 26, 89, 23);
		panel_6.add(btnNewButton_2);
		
	
		
		String[] columnNames = {"STT", "Tên Phòng", "Tên Trưởng Phòng", "Số Nhân Viên", "Lương Trung Bình"};
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
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 370, 310);
        panel. add(scrollPane);
        
        String[] columnNamesNhomQuyen = {"STT", "Tên Phòng", "Tên Trưởng Phòng", "Số Nhân Viên", "Lương Trung Bình"};
        Object[][] dataNhomQuyen = {
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

        DefaultTableModel modelNhomQuyen = new DefaultTableModel(dataNhomQuyen, columnNamesNhomQuyen);
        JTable tableNq = new JTable(modelNhomQuyen);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPaneNq = new JScrollPane(tableNq);
        scrollPaneNq.setBounds(10, 100, 285, 433);
        panel_3. add(scrollPaneNq);
        
     
        
   
//		
//        JScrollPane scrollPane3 = new JScrollPane();
//		scrollPane3.setBackground(Color.white);
//		scrollPane3.setVerticalScrollBar(new myScrollBar());
//		scrollPane3.setBorder(new EmptyBorder(0,0,0,0));
//		scrollPane3.setBounds(10, 68, 247, 426);
//		panel_6.add(scrollPane3);
//		JPanel panelTable = new JPanel();
//		scrollPane3.setColumnHeaderView(panelTable);
//		panelTable.setLayout(null);
//		panelTable.setBackground(Color.white);
//		
//		JLabel ct1 = new JLabel("Quản lý hồ sơ nhân viên");
//		ct1.setForeground(new Color(0,0,0,160));
//		ct1.setFont(new Font("arial", 1, 12));
//		ct1.setBounds(10,10,200,25);
//		panelTable.add(ct1);
//		listCheckBox = new ArrayList<>();
//		String qlnv[] = {"Xem thông tin cá nhân","Xem danh sách nhân viên","Thêm nhân viên","Sửa, xóa nhân viên","Xuất file Excel"};
//		for(int i=0;i<qlnv.length;i++) {
//			JLabel ct2 = new JLabel(qlnv[i]);
//			ct2.setForeground(new Color(0,0,0,140));
//			ct2.setFont(new Font("arial", 1, 12));
//			ct2.setBounds(10,45+i*30,150,25);
//			panelTable.add(ct2);
//			
//			JCheckBox cb = new JCheckBox();
//			cb.setBounds(10,45+i*30,30,25);
//			cb.setBackground(Color.white);
//			panelTable.add(cb);
//			listCheckBox.add(cb);
//		}
//		
//		panel_1.setBounds(10, 49, 367, 303);
	

	}
}
