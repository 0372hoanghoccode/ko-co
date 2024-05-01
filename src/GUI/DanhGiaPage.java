package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import BUS.DANHGIA_BUS;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class DanhGiaPage extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField searchField;
	private JTextField ngayBDField;
	private JTextField ngayKTField;
	DANHGIA_BUS danhgia_BUS=new DANHGIA_BUS();
	String[] columnNames= {"STT","Mã Đánh Giá","Nhân Viên","Thời Gian","Người Đánh Giá",
			"Điểm Đánh Giá","XL Đánh Giá","Loại Đánh Giá"		
	};
	
	Object[][] data= danhgia_BUS.renderAllDanhGiaData();
	DefaultTableModel model = new DefaultTableModel(data, columnNames) {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable table = new JTable();
	/**
	 * Create the panel.
	 */
	public DanhGiaPage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 9, 980, 169);
		add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Bảng đánh giá");
		title.setFont(new Font("Arial", Font.BOLD, 16));
		title.setForeground(new Color(0, 0, 0));
		title.setBounds(10, 11, 200, 31);
		panel.add(title);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(314, 11, 200, 28);
		searchPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY));
		panel.add(searchPanel);
		searchPanel.setLayout(null);
		
		searchField = new JTextField();
		searchField.setBounds(0, 0, 170, 28);
		searchPanel.add(searchField);
		searchField.setColumns(10);
		
		JLabel searchLabel = new JLabel("");
		searchLabel.setBounds(170, 0, 30, 28);
		searchLabel.setIcon(new ImageIcon(new ImageIcon(DanhGiaPage.class.getResource("/assets/appIcon/icons8-search-24.png")).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
		searchPanel.add(searchLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
		lblNewLabel_1.setBounds(20, 53, 49, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày:");
		lblNewLabel_2.setBounds(37, 78, 49, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Đến:");
		lblNewLabel_3.setBounds(255, 78, 49, 14);
		panel.add(lblNewLabel_3);
		
		JComboBox cbbLoaiDanhGia = new JComboBox();
		cbbLoaiDanhGia.setModel(new DefaultComboBoxModel(new String[] {"Kém", "Trung bình", "Khá", "Giỏi", "Xuất sắc"}));
		cbbLoaiDanhGia.setSelectedIndex(-1);
		cbbLoaiDanhGia.setBounds(655, 74, 89, 22);
		panel.add(cbbLoaiDanhGia);
		
		JLabel lblNewLabel_4 = new JLabel("Sắp xếp theo:");
		lblNewLabel_4.setBounds(20, 119, 82, 14);
		panel.add(lblNewLabel_4);
		
		JComboBox cbbSapXep = new JComboBox();
		cbbSapXep.setModel(new DefaultComboBoxModel(new String[] {"Điểm đánh giá", "Ngày đánh giá"}));
		cbbSapXep.setBounds(96, 115, 114, 22);
		panel.add(cbbSapXep);
		
		JComboBox cbbThuTu = new JComboBox();
		cbbThuTu.setModel(new DefaultComboBoxModel(new String[] {"Giảm dần", "Tăng dần"}));
		cbbThuTu.setBounds(340, 115, 96, 22);
		panel.add(cbbThuTu);
		
		JLabel lblNewLabel_5 = new JLabel("Theo thứ tự:");
		lblNewLabel_5.setBounds(255, 119, 82, 14);
		panel.add(lblNewLabel_5);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appContent parentForm= (appContent) getParent();
				setVisible(false);
				parentForm.displayContent(10);
			}
		});
		btnThem.setBounds(864, 29, 89, 23);
		panel.add(btnThem);
		
		JButton btnChiTiet= new JButton("Chi tiết");
		btnChiTiet.setForeground(new Color(0, 0, 0));
		btnChiTiet.setBackground(new Color(255, 255, 255));
		btnChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChiTiet.setBounds(864, 70, 89, 23);
		panel.add(btnChiTiet);
		
		JLabel loaiDanhGia = new JLabel("Loại đánh giá:");
		loaiDanhGia.setBounds(538, 78, 107, 14);
		panel.add(loaiDanhGia);
		
		
		JPanel ngayBDPanel = new JPanel();
		ngayBDPanel.setLayout(null);
		ngayBDPanel.setBounds(80, 75, 109, 20);
		ngayBDPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY)); // Đặt viền cho searchPanel
		panel.add(ngayBDPanel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(0, 0, 109, 20); // Đặt kích thước và vị trí cho JDateChooser
		ngayBDPanel.add(dateChooser);
		
		JPanel ngayKTPanel = new JPanel();
		ngayKTPanel.setLayout(null);
		ngayKTPanel.setBounds(314, 75, 109, 20);
		ngayKTPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY)); // Đặt viền cho searchPanel
		panel.add(ngayKTPanel);

		JDateChooser dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(0, 0, 109, 20); // Đặt kích thước và vị trí cho JDateChooser
		ngayKTPanel.add(dateChooser1);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 980, 478);
		add(scrollPane);
		
		table.setModel(model);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);  // stt
		table.getColumnModel().getColumn(1).setPreferredWidth(80);  // maDanhGia
		table.getColumnModel().getColumn(2).setPreferredWidth(150);  // nhanVien
		table.getColumnModel().getColumn(3).setPreferredWidth(50);  // thoiGian
		table.getColumnModel().getColumn(4).setPreferredWidth(150);  //  nguoiDanhGia
		table.getColumnModel().getColumn(5).setPreferredWidth(50);  // diemDanhGia
		table.getColumnModel().getColumn(6).setPreferredWidth(80);  // xepLoai
		table.getColumnModel().getColumn(7).setPreferredWidth(50);  // loai
		scrollPane.setViewportView(table);
		
	
	}

}
