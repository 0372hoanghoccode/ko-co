package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BUS.PHONGBAN_BUS;


import javax.swing.JButton;

public class PhongbanPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable tableNV;

	PHONGBAN_BUS phongban_bus = new PHONGBAN_BUS();

	String[] columnNames = {
		"STT", "T\u00EAn ph\u00F2ng", "Tr\u01B0\u1EDFng ph\u00F2ng", "S\u1ED1 nh\u00E2n vi\u00EAn", "L\u01B0\u01A1ng trung b\u00ECnh"
	};
	Object[][] data = {};


	String[] columnNamesNV = {
		"STT", "Mã nhân viên", "Tên nhân viên",  "Chức vụ"
	};
	Object[][] dataNV = phongban_bus.renderChiTietPhongBan(0);



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
		panel_1.setBounds(5, 5, 985, 275);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lí phòng ban");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 177, 26);
		panel_1.add(lblNewLabel);
		
		
		
		data = phongban_bus.renderAllPhongBanData();
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(0, 0);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.white);
		scrollPane.setVerticalScrollBar(new myScrollBar());
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setBounds(10, 46, 965, 219);

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
		panel.setBounds(5, 285, 650, 365);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quản lí nhân viên - Phòng ba chấm");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 10, 273, 30);
		panel.add(lblNewLabel_1);
		
	
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(660, 285, 330, 365);
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
		
		
		// table nhân viên
		DefaultTableModel modelNV = new DefaultTableModel(dataNV, columnNamesNV) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableNV = new JTable(modelNV);
		tableNV.setRowHeight(30);
		tableNV.setFont(new Font("Arial", Font.PLAIN, 12));
		tableNV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (tableNV.getRowCount() > 0) {
			tableNV.setRowSelectionInterval(0, 0);
		}

		JScrollPane scrollPaneNV = new JScrollPane(tableNV);
		scrollPaneNV.setBackground(Color.white);
		scrollPaneNV.setVerticalScrollBar(new myScrollBar());
		scrollPaneNV.setBorder(new EmptyBorder(0,0,0,0));
       	scrollPaneNV.setBounds(10, 50, 630, 305);
		panel.add(scrollPaneNV);
		
		String maNV = (String) tableNV.getValueAt(0, 1);
		
		ArrayList<String> data = phongban_bus.renderChiTietNhanVien(table.getSelectedRow(),maNV);
		lblNewLabel_3.setText("Tên: " + data.get(1));
		lblNewLabel_4.setText("Mã số: " + data.get(0));
		lblNewLabel_5.setText("Ngày sinh: " + data.get(2));
		lblNewLabel_6.setText("Địa chỉ: " + data.get(3));
		lblNewLabel_7.setText("Chức vụ: " + data.get(4));

		// event 

        ListSelectionListener tableNVListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int viewRow = table.getSelectedRow();
                int viewRowNV = tableNV.getSelectedRow();
        
                if (viewRow >= 0 && viewRow < table.getRowCount() && viewRowNV >= 0 && viewRowNV < tableNV.getRowCount()) {
                    String maNV = (String) tableNV.getValueAt(viewRowNV, 1);
                    ArrayList<String> data_temp = phongban_bus.renderChiTietNhanVien(viewRow, maNV);
        
                    lblNewLabel_3.setText("Tên: " + data_temp.get(1));
                    lblNewLabel_4.setText("Mã số: " + data_temp.get(0));
                    lblNewLabel_5.setText("Ngày sinh: " + data_temp.get(2));
                    lblNewLabel_6.setText("Địa chỉ: " + data_temp.get(3));
                    lblNewLabel_7.setText("Chức vụ: " + data_temp.get(4));
                }
            }
        };

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int viewRow = table.getSelectedRow();
                if (viewRow >= 0 && viewRow < table.getRowCount()) {
                    int viewRowNV = tableNV.getSelectedRow();
                    if (viewRowNV >= 0 && viewRowNV < tableNV.getRowCount()) {
                        tableNV.getSelectionModel().removeListSelectionListener(this);
                    }
        
                    dataNV = phongban_bus.renderChiTietPhongBan(viewRow);
                    lblNewLabel_1.setText("Quản lí nhân viên - " + phongban_bus.getList().get(viewRow).getTenPhong());
                    DefaultTableModel modelNV = new DefaultTableModel(dataNV, columnNamesNV) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    tableNV.setModel(modelNV);
                    if (tableNV.getRowCount() > 0) {
                        tableNV.setRowSelectionInterval(0, 0);
                    }
        
                    tableNV.getSelectionModel().addListSelectionListener(tableNVListener);
                }
            }
        });
        
        
        tableNV.getSelectionModel().addListSelectionListener(tableNVListener);
		
		
		

	}

	public String LocalDateToString(LocalDate date) {
		String arr[] = date.toString().split("-");
		return arr[2]+"-"+arr[1]+"-"+arr[0];
		
	}
}
