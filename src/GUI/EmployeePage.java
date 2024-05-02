package GUI;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import BUS.NhanVienBUS;
import BUS.PHONGBAN_BUS;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class EmployeePage extends JPanel {

    private static final long serialVersionUID = 1L;
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();


    String[] event_name = new String[]{
    	"Phòng ban", "Giới tính", "Độ tuổi", "Thuộc tính sắp xếp", "Thứ tự sắp"
    };
    String[] columnNames = { "STT", "Mã - Tên", "Giới Tính", "Năm Sinh", "Địa chỉ", "SĐT", "Phòng Ban", "Chức Vụ", "Lương" };
    Object[][] data = nhanVienBUS.getDataObjectToRender(); 
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable table = new JTable();

    public EmployeePage() {
        init();
    }

    public void init() {
        setLayout(null);
        setBounds(5, 5, 1000, 659);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(5, 5, 985, 123);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Thông tin nhân viên ");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 10, 179, 39);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo:");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(10, 59, 112, 24);
        panel.add(lblNewLabel_1);

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

        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setBounds(200, 83, 88, 30);
        comboBox_1.addItem("Giới tính");
        comboBox_1.addItem("Nam");
        comboBox_1.addItem("Nữ");
        panel.add(comboBox_1);

        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.setBounds(298, 83, 104, 30);
        comboBox_2.addItem("Độ tuổi");
        comboBox_2.addItem("18-25");
        comboBox_2.addItem("26-34");
        comboBox_2.addItem("35-44");
        comboBox_2.addItem("45-54");
        comboBox_2.addItem("55-61");
        
        panel.add(comboBox_2);

        JLabel lblNewLabel_2 = new JLabel("Sắp xếp theo: ");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(547, 59, 112, 24);
        panel.add(lblNewLabel_2);

        JComboBox<String> comboBox_3 = new JComboBox<>();

        comboBox_3.setBounds(547, 83, 123, 30);
        comboBox_3.addItem("Thuộc tính sắp xếp");
        comboBox_3.addItem("Tên nhân viên");
        comboBox_3.addItem("Độ tuổi");
        comboBox_3.addItem("Mức lương");
        panel.add(comboBox_3);

        JComboBox<String> comboBox_4 = new JComboBox<>();

        comboBox_4.setBounds(680, 83, 130, 30);
        comboBox_4.addItem("Tăng dần");
        comboBox_4.addItem("Giảm dần");
        panel.add(comboBox_4);

        // Tạo JPanel để chứa JTextField và JLabel chứa icon tìm kiếm
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(302, 19, 195, 30);
        searchPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY)); // Đặt viền cho searchPanel
        panel.add(searchPanel);

        JTextField searchField = new JTextField();
        searchField.setBounds(0, 0, 165, 30);
        searchPanel.add(searchField);

        JLabel searchIconLabel = new JLabel("");
        searchIconLabel.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-search-24.png")));
        searchIconLabel.setBounds(170, 0, 25, 30);
        searchPanel.add(searchIconLabel);

        JButton btnNewButton = new JButton("");
        btnNewButton.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-reset-24.png")));
        btnNewButton.setBounds(915, 20, 50, 29);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-user-24.png")));
        btnNewButton_1.setBounds(831, 19, 74, 30);
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("");
        btnNewButton_2.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-user-24.png")));
        btnNewButton_2.setBounds(915, 83, 50, 30);
        panel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                String[] maVaTen = ((String) table.getValueAt(viewRow, 1)).split(" - ");
        		Object[] data = nhanVienBUS.renderSelectedNhanVien(maVaTen[0]);
                EmployeeDetail employeeDetail = new EmployeeDetail(data);
                employeeDetail.setVisible(true);
        	}
        });
        btnNewButton_3.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-reset-24.png")));
        btnNewButton_3.setBounds(855, 83, 50, 30);
        panel.add(btnNewButton_3);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(5, 133, 985, 520);
        add(panel_2);
        panel_2.setLayout(new BorderLayout());

        
        table.setModel(model);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
        table.getColumnModel().getColumn(0).setPreferredWidth(30);  // stt
		table.getColumnModel().getColumn(1).setPreferredWidth(150);  // ten
		table.getColumnModel().getColumn(2).setPreferredWidth(65);   // gioitinh
		table.getColumnModel().getColumn(3).setPreferredWidth(70);  // ngaysinh
		table.getColumnModel().getColumn(4).setPreferredWidth(250);  // diachi
		table.getColumnModel().getColumn(5).setPreferredWidth(80);  // Sdt
		table.getColumnModel().getColumn(6).setPreferredWidth(180);   // phongban
		table.getColumnModel().getColumn(7).setPreferredWidth(130);  // chucvu
		table.getColumnModel().getColumn(8).setPreferredWidth(90);  // luong


        JScrollPane scrollPane = new JScrollPane(table);      
        scrollPane.setBackground(Color.white);
		scrollPane.setVerticalScrollBar(new myScrollBar());
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        panel_2.add(scrollPane, BorderLayout.CENTER);



        // ----- EVENT -------
        
        // 3 là reset, 2 là chi tiết

        


        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) comboBox.getSelectedItem();
                    event_name[0] = selectedOption;
                    event_action();
                }
            }
        });
        comboBox_1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) comboBox_1.getSelectedItem();
                    event_name[1] = selectedOption;
                    event_action();
                }
            }
        });
        comboBox_2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) comboBox_2.getSelectedItem();
                    event_name[2] = selectedOption;
                    event_action();
                }
            }
        });
        // Thuộc tính sắp
        comboBox_3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) comboBox_3.getSelectedItem();
                    event_name[3] = selectedOption;
                    event_action2();
                }
            }
        });
        // Thứ tự sắp
        comboBox_4.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) comboBox_4.getSelectedItem();
                    event_name[4] = selectedOption;
                    event_action2();
                }
            }
        });
    }
    
    public void event_action() {
        // thay đổi data tùy theo cá tính riêng của bản thân
    	data = nhanVienBUS.changeDataValue(event_name);
    	model.setDataVector(data, columnNames);
        table.setModel(model);
    }

    public void event_action2() {
        switch(event_name[3]) {
            case "Tên nhân viên":
                if (event_name[4].equals("Tăng dần")) {
                    data = nhanVienBUS.sortNhanVienByNameAscending();
                } else {
                    data = nhanVienBUS.sortNhanVienByNameDescending();
                }
                break;
            case "Độ tuổi":
                if (event_name[4].equals("Tăng dần")) {
                    data = nhanVienBUS.sortNhanVienByAgeAscending();
                } else {
                    data = nhanVienBUS.sortNhanVienByAgeDescending();
                }
                break;
            case "Mức lương":
                if (event_name[4].equals("Tăng dần")) {
                    data = nhanVienBUS.sortNhanVienBySalaryAscending();
                } else {
                    data = nhanVienBUS.sortNhanVienBySalaryDescending();
                }
                break;
        }
        model.setDataVector(data, columnNames);
        table.setModel(model);
    }
    
   
}
