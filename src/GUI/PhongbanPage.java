package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import BUS.PHONGBAN_BUS;  // Assuming this package and class are correctly implemented.

public class PhongbanPage extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table, tableNV;
    private PHONGBAN_BUS phongban_bus = new PHONGBAN_BUS();

    public PhongbanPage() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(null);

        // Main panel for department management
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(5, 5, 985, 275);
        panel_1.setLayout(null);
        add(panel_1);

        JLabel lblManageDept = new JLabel("Quản lí phòng ban");
        lblManageDept.setFont(new Font("Arial", Font.PLAIN, 14));
        lblManageDept.setBounds(10, 10, 177, 26);
        panel_1.add(lblManageDept);

        // Table for displaying department information
        Object[][] data = phongban_bus.renderAllPhongBanData();
        String[] columnNames = {"STT", "Tên phòng", "Trưởng phòng", "Số nhân viên", "Lương trung bình"};
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
        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setBounds(10, 46, 965, 219);
        panel_1.add(scrollPane);

        // Buttons for CRUD operations
        JButton btnAdd = new JButton("Thêm");
        btnAdd.setBounds(584, 14, 85, 21);
        panel_1.add(btnAdd);

        JButton btnEdit = new JButton("Sửa");
        btnEdit.setBounds(679, 14, 85, 21);
        panel_1.add(btnEdit);

        JButton btnDelete = new JButton("Xóa");
        btnDelete.setBounds(774, 14, 85, 21);
        panel_1.add(btnDelete);

        JButton btnExport = new JButton("Xuất");
        btnExport.setBounds(869, 14, 85, 21);
        panel_1.add(btnExport);

        // Employee management panel
        JPanel panelEmployee = new JPanel();
        panelEmployee.setBackground(Color.WHITE);
        panelEmployee.setBounds(5, 285, 650, 365);
        panelEmployee.setLayout(null);
        add(panelEmployee);

        JLabel lblManageEmp = new JLabel("Quản lí nhân viên - Phòng ba chấm");
        lblManageEmp.setFont(new Font("Arial", Font.PLAIN, 14));
        lblManageEmp.setBounds(10, 10, 273, 30);
        panelEmployee.add(lblManageEmp);

        // Employee details panel
        JPanel panelDetails = new JPanel();
        panelDetails.setBackground(Color.WHITE);
        panelDetails.setBounds(660, 285, 330, 365);
        add(panelDetails);
        panelDetails.setLayout(null);

        JLabel lblEmpDetails = new JLabel("Thông tin nhân viên");
        lblEmpDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEmpDetails.setBounds(10, 10, 204, 28);
        panelDetails.add(lblEmpDetails);

        JLabel lblName = new JLabel("Tên:");
        lblName.setFont(new Font("Arial", Font.PLAIN, 12));
        lblName.setBounds(10, 85, 127, 21);
        panelDetails.add(lblName);

        JLabel lblID = new JLabel("Mã số:");
        lblID.setFont(new Font("Arial", Font.PLAIN, 12));
        lblID.setBounds(10, 48, 127, 28);
        panelDetails.add(lblID);

        JLabel lblBirthdate = new JLabel("Ngày sinh:");
        lblBirthdate.setFont(new Font("Arial", Font.PLAIN, 12));
        lblBirthdate.setBounds(10, 117, 127, 21);
        panelDetails.add(lblBirthdate);

        JLabel lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setFont(new Font("Arial", Font.PLAIN, 12));
        lblAddress.setBounds(10, 148, 127, 28);
        panelDetails.add(lblAddress);

        JLabel lblPosition = new JLabel("Chức vụ:");
        lblPosition.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPosition.setBounds(10, 186, 127, 28);
        panelDetails.add(lblPosition);

        JLabel lblPhoto = new JLabel("");
        lblPhoto.setOpaque(true);
        lblPhoto.setBounds(194, 48, 121, 157);
        panelDetails.add(lblPhoto);

        // To dynamically update based on selection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        String departmentId = (String) table.getValueAt(selectedRow, 1);
                        updateEmployeeDetails(departmentId);
                    }
                }
            }

            private void updateEmployeeDetails(String departmentId) {
               
                Object[][] employees = phongban_bus.getDataNhanVienCuaPhongById(departmentId);

                if (employees != null && employees.length > 0) {
                    
                    updateLabels(employees[0]); 
                } else {
                    clearLabels();
                }
            }

            private void updateLabels(Object[] details) {
                if (details.length >= 5) {
                    lblName.setText("Tên: " + details[1]);
                    lblID.setText("Mã số: " + details[0]);
                    lblBirthdate.setText("Ngày sinh: " + details[2]);
                    lblAddress.setText("Địa chỉ: " + details[3]);
                    lblPosition.setText("Chức vụ: " + details[4]);
                }
            }

            private void clearLabels() {
                lblName.setText("Tên: N/A");
                lblID.setText("Mã số: N/A");
                lblBirthdate.setText("Ngày sinh: N/A");
                lblAddress.setText("Địa chỉ: N/A");
                lblPosition.setText("Chức vụ: N/A");
            }
        });
    }
}
