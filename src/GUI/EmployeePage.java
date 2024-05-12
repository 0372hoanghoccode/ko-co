package GUI;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import java.util.ArrayList;
import BUS.NhanVienBUS;
import BUS.PHONGBAN_BUS;
import DAO.NhanVienDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import EXCEL.XuatNhapExcel;


public class EmployeePage extends JPanel {

    private static final long serialVersionUID = 1L;
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();
    JButton btnNewButton_2;

    private myTable table;
    private DefaultTableModel model;
    private Object[][] data;
     String[] event_name = new String[]{
    	"Phòng ban", "Giới tính", "Độ tuổi", "Thuộc tính sắp xếp", "Thứ tự sắp"
    };
    private String[] columnNames = { "STT", "Mã - Tên", "Giới Tính", "Năm Sinh", "Địa chỉ", "SĐT", "Phòng Ban", "Chức Vụ", "Lương" };
    
    
    public void setData() {
        data = nhanVienBUS.getDataObjectToRender(); 
        model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    public EmployeePage() {
    	init();
		setData();
        
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
        lblNewLabel.setBounds(11, 9, 179, 39);
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
        ImageIcon searchIcon = new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-search-24.png"));

        ImageIcon resetIcon = new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-reset-24.png"));

       
        



       
        //nhập excel
        JButton btnImportExcel = new JButton("Nhập Excel");
        btnImportExcel.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnImportExcel.setBounds(640, 19, 150, 29);
        ImageIcon importIcon = new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-reset-24.png"));
        Image importImg = importIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        btnImportExcel.setIcon(new ImageIcon(importImg));
        btnImportExcel.setHorizontalTextPosition(SwingConstants.RIGHT);  
        btnImportExcel.setVerticalTextPosition(SwingConstants.CENTER);
        panel.add(btnImportExcel);

        btnImportExcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Chọn file Excel để nhập");
                int returnVal = jFileChooser.showOpenDialog(panel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                
                    XuatNhapExcel.readExcelToTable(file, table);
                }
            }
        });



        //xuất excel
        JButton btnExportExcel = new JButton("Xuất Excel");
        btnExportExcel.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnExportExcel.setBounds(820, 20, 150, 29);
        ImageIcon exportIcon = new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-user-24.png"));
        Image exportImg = exportIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        btnExportExcel.setIcon(new ImageIcon(exportImg));
        btnExportExcel.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnExportExcel.setVerticalTextPosition(SwingConstants.CENTER);
        panel.add(btnExportExcel);

        btnExportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Lưu file Excel");
                int returnVal = jFileChooser.showSaveDialog(panel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    String filePath = file.getAbsolutePath();
                    if (!filePath.endsWith(".xlsx")) {
                        filePath += ".xlsx";
                    }
                    
                    XuatNhapExcel.exportDataToExcel(table, filePath, "Tên Sheet");
                    JOptionPane.showMessageDialog(panel, "Xuất dữ liệu ra file Excel thành công!");
                }
            }
        });


        btnNewButton_2 = new JButton(""); // chi tiết
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                int viewRow = table.getSelectedRow();
                String[] maVaTen = ((String) table.getValueAt(viewRow, 1)).split(" - ");
        		Object[] data = nhanVienBUS.renderSelectedNhanVien(maVaTen[0]);
                EmployeeDetail employeeDetail = new EmployeeDetail(data, getEmployeePage());
                employeeDetail.setVisible(true);
        	}
       });
        btnNewButton_2.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-user-24.png")));
        btnNewButton_2.setBounds(915, 83, 50, 30);
        panel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("");
        btnNewButton_3.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-reset-24.png")));
        btnNewButton_3.setBounds(855, 83, 50, 30);
        panel.add(btnNewButton_3);
        
                JButton searchButton = new JButton();
                searchButton.setBounds(532, 18, 50, 30);
                panel.add(searchButton);
                searchButton.setIcon(searchIcon);
                
                        JTextField searchField = new JTextField();
                        searchField.setBounds(368, 19, 165, 30);
                        panel.add(searchField);
                        
                        JButton resetButton = new JButton();
                        resetButton.setBounds(447, 83, 45, 30);
                        panel.add(resetButton);
                        
                                resetButton.setIcon(resetIcon);
                                
                                        resetButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                setData();
                                            }
                                        });
                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String searchText = searchField.getText();
                        data = nhanVienBUS.filterData(searchText);
                        model.setDataVector(data, columnNames);
                        table.setModel(model);
                    }
                });
        

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(5, 133, 985, 520);
        add(panel_2);
        panel_2.setLayout(new BorderLayout());

        
        table = new myTable();
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
//      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//       
//      table.getColumnModel().getColumn(0).setPreferredWidth(30);  // stt
//		table.getColumnModel().getColumn(1).setPreferredWidth(150);  // ten
//		table.getColumnModel().getColumn(2).setPreferredWidth(65);   // gioitinh
//		table.getColumnModel().getColumn(3).setPreferredWidth(70);  // ngaysinh
//		table.getColumnModel().getColumn(4).setPreferredWidth(250);  // diachi
//		table.getColumnModel().getColumn(5).setPreferredWidth(80);  // Sdt
//		table.getColumnModel().getColumn(6).setPreferredWidth(180);   // phongban
//		table.getColumnModel().getColumn(7).setPreferredWidth(130);  // chucvu
//		table.getColumnModel().getColumn(8).setPreferredWidth(90);  // luong


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
    
    public JButton getButton_ChiTiet() {
        return btnNewButton_2;
    }

    public EmployeePage getEmployeePage() {
        return this;
    }
}
