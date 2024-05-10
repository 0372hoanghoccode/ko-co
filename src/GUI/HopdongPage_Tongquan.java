package GUI;

import javax.swing.*;
import PDF.InPDF;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import GUI.EmployeePage;
import BUS.HOPDONGLAODONG_BUS;
import BUS.PHONGBAN_BUS;
import DAO.HopDongLaoDongDAO;
import DAO.NhanVienDAO;
import EXCEL.XuatNhapExcel;


public class HopdongPage_Tongquan extends JPanel {
    private static final long serialVersionUID = 1L;
    private HOPDONGLAODONG_BUS hopdonglaodong_bus = new HOPDONGLAODONG_BUS();
    private PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();
    private NhanVienDAO nhanvien_DAO = new NhanVienDAO();
    private HopdongPage_Giahan giaHanPage;
    private myTable table; 
    private String[] columnName = {
        "STT", "Mã HĐ", "Mã nv", "Tên", "Phòng", "Bắt đầu", "Kết thúc", "Loại hợp đồng", "Lương"
    };
    private DefaultTableModel model;
    private Object[][] data;

	public HopdongPage_Tongquan() {
		init();
		
        data = hopdonglaodong_bus.getDataObjectToRender();
        model = new DefaultTableModel(data, columnName);
        table.setModel(model);

      
        giaHanPage = new HopdongPage_Giahan();
	}

	public void init() {
		setLayout(null);
		setBackground(new Color(255, 255, 255));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 985, 87);
		add(panel);
		panel.setLayout(null);

		JButton btnGiaHan = new JButton("Gia hạn ");
		btnGiaHan.setBounds(61, 5, 145, 25);
		btnGiaHan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGiaHan.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-user-24.png"))
				.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
		panel.add(btnGiaHan);

		JButton btnHuyHopDong = new JButton("Huỷ hợp đồng");
		btnHuyHopDong.setBounds(211, 5, 140, 25);
		btnHuyHopDong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHuyHopDong.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-user-24.png")).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
		panel.add(btnHuyHopDong);
        
		   JButton btnImportExcel = new JButton("Nhập Excel");
		   btnImportExcel.setBounds(61, 41, 107, 25);
btnImportExcel.setFont(new Font("Tahoma", Font.BOLD, 13));
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
btnExportExcel.setBounds(209, 41, 103, 25);
btnExportExcel.setFont(new Font("Tahoma", Font.BOLD, 13));
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


JButton btnExportPDF = new JButton("Xuất PDF");
btnExportPDF.setBounds(500, 41, 103, 25); 
btnExportPDF.setFont(new Font("Tahoma", Font.BOLD, 13));
ImageIcon pdfIcon = new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-user-24.png"));
Image pdfImg = pdfIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
btnExportPDF.setIcon(new ImageIcon(pdfImg));
btnExportPDF.setHorizontalTextPosition(SwingConstants.RIGHT);
btnExportPDF.setVerticalTextPosition(SwingConstants.CENTER);
panel.add(btnExportPDF);

btnExportPDF.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Lưu file PDF");
        int returnVal = jFileChooser.showSaveDialog(panel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf";
            }
            
            InPDF pdfExporter = new InPDF();
            try {
                pdfExporter.generatePDF(filePath, table);
                JOptionPane.showMessageDialog(panel, "Xuất dữ liệu ra file PDF thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Xuất dữ liệu ra file PDF không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});



        


		btnHuyHopDong.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            String maNhanVien = table.getValueAt(selectedRow, 1).toString(); 
		            String nhanVien = table.getValueAt(selectedRow, 3).toString(); 
		            int result = JOptionPane.showOptionDialog(null, 
		                "Bạn có chắc hủy hợp đồng với nhân viên " + nhanVien + " không?", 
		                "Xác nhận", 
		                JOptionPane.YES_NO_OPTION, 
		                JOptionPane.QUESTION_MESSAGE, 
		                null, null, null);

		            if (result == JOptionPane.YES_OPTION) {
		                nhanvien_DAO.hiddenNhanVien(maNhanVien); 
		                JOptionPane.showMessageDialog(null, "Huỷ hợp đồng thành công!"); 
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hợp đồng để huỷ");
		        }
		    }
		});


		


		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(400, 6, 100, 22);
		comboBox.setEditable(false);
		comboBox.setBackground(Color.WHITE);
		comboBox.setOpaque(true);
		comboBox.addItem("Phòng ban");
		for (String i : phongban_BUS.getTenPhongBan()) {
			comboBox.addItem(i);
		}
		panel.add(comboBox);

		JComboBox<String> comboBox1 = new JComboBox<>();
		comboBox1.setBounds(400, 41, 100, 22);
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
		lblLuongTu.setBounds(627, 9, 62, 17);
		lblLuongTu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblLuongTu);

		JTextField txtLuongTu = new JTextField();
		txtLuongTu.setBounds(694, 7, 96, 20);
		txtLuongTu.setColumns(10);
		panel.add(txtLuongTu);

		JLabel lblDen = new JLabel("đến:");
		lblDen.setBounds(795, 9, 28, 17);
		lblDen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblDen);

		JTextField txtLuongDen = new JTextField();
		txtLuongDen.setBounds(828, 7, 96, 20);
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
        scrollPane.setBounds(0, 98, 985, 510);
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
