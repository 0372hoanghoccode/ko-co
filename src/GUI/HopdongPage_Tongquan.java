package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import BUS.HOPDONGLAODONG_BUS;
import BUS.PHONGBAN_BUS;

public class HopdongPage_Tongquan extends JPanel {
    private static final long serialVersionUID = 1L;
    private HOPDONGLAODONG_BUS hopdonglaodong_bus = new HOPDONGLAODONG_BUS();
    private PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();
    private HopdongPage_Giahan giaHanPage;
    private myTable table; 
    private String[] columnName = {
        "STT", "Mã HĐ", "Mã nv", "Tên", "Phòng", "Bắt đầu", "Kết thúc", "Loại hợp đồng", "Lương"
    };
    private DefaultTableModel model;
    private Object[][] data;

	public HopdongPage_Tongquan() {
		init();
		// Khởi tạo trang gia hạn
        data = hopdonglaodong_bus.getDataObjectToRender();
        model = new DefaultTableModel(data, columnName);
        table.setModel(model);

        // Initialize Gia Han page
        giaHanPage = new HopdongPage_Giahan();
	}

	public void init() {
		setLayout(null);
		setBackground(new Color(255, 255, 255));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 985, 45);
		add(panel);

		JButton btnGiaHan = new JButton("Gia hạn hợp đồng");
		btnGiaHan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGiaHan.setBounds(10, 180, 180, 30);
		btnGiaHan.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-user-24.png"))
				.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
		panel.add(btnGiaHan);

		JButton btnHuyHopDong = new JButton("Huỷ hợp đồng");
		btnHuyHopDong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHuyHopDong.setBounds(200, 180, 180, 30);
		btnHuyHopDong.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-user-24.png"))
				.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
		panel.add(btnHuyHopDong);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setEditable(false);
		comboBox.setBackground(Color.WHITE);
		comboBox.setOpaque(true);
		comboBox.setBounds(10, 83, 180, 30);
		comboBox.addItem("Phòng ban");
		for (String i : phongban_BUS.getTenPhongBan()) {
			comboBox.addItem(i);
		}
		panel.add(comboBox);

		JComboBox<String> comboBox1 = new JComboBox<>();
		comboBox1.setBounds(200, 83, 88, 30);
		comboBox1.addItem("Thời hạn hợp đồng");
		comboBox1.addItem("1 năm");
		comboBox1.addItem("2 năm");
		comboBox1.addItem("3 năm");
		comboBox1.addItem("4 năm");
		comboBox1.addItem("5 năm");
		comboBox1.addItem("6 năm");
		comboBox1.addItem("7 năm");
		panel.add(comboBox1);

		JLabel lblLuongTu = new JLabel("Lương từ:");
		lblLuongTu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLuongTu.setBounds(10, 140, 80, 25);
		panel.add(lblLuongTu);

		JTextField txtLuongTu = new JTextField();
		txtLuongTu.setBounds(100, 140, 150, 25);
		txtLuongTu.setColumns(10);
		panel.add(txtLuongTu);

		JLabel lblDen = new JLabel("đến:");
		lblDen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDen.setBounds(260, 140, 50, 25);
		panel.add(lblDen);

		JTextField txtLuongDen = new JTextField();
		txtLuongDen.setBounds(300, 140, 150, 25);
		txtLuongDen.setColumns(10);
		panel.add(txtLuongDen);


		btnGiaHan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Nút Gia hạn được nhấn");
				displayGiaHanPage();
			}
		});

        table = new myTable();
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object[][] selectedData = new Object[1][table.getColumnCount()];
                        for (int i = 0; i < table.getColumnCount(); i++) {
                            selectedData[0][i] = table.getValueAt(selectedRow, i);
                        }
                        giaHanPage.setData(selectedData);
                    }
                }
            }
        });

        // Add scroll pane containing the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBounds(0, 50, 985, 555);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);
    }

	private void displayGiaHanPage() {
		JFrame frame = new JFrame("Gia hạn hợp đồng");
		frame.setContentPane(giaHanPage);
		frame.setSize(800, 660);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
