package GUI;

import javax.swing.JPanel;
import PDF.InPDF;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import BUS.LUONG_BUS;
import BUS.NhanVienBUS;
import BUS.PHONGBAN_BUS;
import PDF.InPDF;

public class LuongPage1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    LUONG_BUS luongBUS = new LUONG_BUS();
    PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();
    private myTable table; 
    private DefaultTableModel model;
    private Object[][] data;
    private String[] columnName = { 
            "STT", "Nhân Viên", "Thời Gian", "Lương cơ bản",
                    "Lương thực tế", "Phụ cấp", "Lương thưởng", 
                    "Các khoản trừ", "Thuế", "Thực lãnh"
        };
    
    /**
     * Create the panel.
     */
    public LuongPage1() {
		init();
	
		data = luongBUS.getDataObjectToRender(); 
        model = new DefaultTableModel(data, columnName);
        table.setModel(model);

	}
    public void init() {
        setBounds(100, 100, 985, 615);
        setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 118, 985, 490);
        add(scrollPane);
        
        
    table = new myTable();
    table.setRowHeight(30);
    String[] event_name = new String[]{
    	"Năm", "Tháng", "Phòng ban", "khoảng lương", "Thứ tự sắp"
    };
        scrollPane.setViewportView(table);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 985, 111);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Bảng Lương Nhân Viên");
        lblNewLabel.setBounds(10, 10, 183, 27);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JComboBox<String> comboBox_nam = new JComboBox<>();
        comboBox_nam.setBounds(72, 62, 62, 31);
        panel.add(comboBox_nam);
        
        //combobox năm
        comboBox_nam.addItem("Năm");

        for (int year = 2020; year <= 2024; year++) {
         comboBox_nam.addItem(String.valueOf(year));
        }

        comboBox_nam.setSelectedItem("Năm");
        //combobox tháng
        JComboBox<String> comboBox_thang = new JComboBox<>();
        comboBox_thang.setBounds(214, 62, 97, 31);
        panel.add(comboBox_thang);

        comboBox_thang.addItem("Tháng");

        for (int month = 1; month <= 12; month++) {
          comboBox_thang.addItem(String.format("Tháng %d", month));
        }

        comboBox_thang.setSelectedItem("Tháng");

        
        JComboBox<String> comboBox_phongBan = new JComboBox<>();
        comboBox_phongBan.setBounds(368, 62, 107, 31);
        panel.add(comboBox_phongBan);

        comboBox_phongBan.setEditable(false);
        comboBox_phongBan.setBackground(Color.WHITE);
        comboBox_phongBan.setOpaque(true);
        comboBox_phongBan.addItem("Phòng ban");
        for (String i : phongban_BUS.getTenPhongBan()) {
            comboBox_phongBan.addItem(i);
        }
        panel.add(comboBox_phongBan);
        JComboBox<String> comboBox_sort = new JComboBox<>();
        comboBox_sort.setBounds(488, 62, 107, 31);
        panel.add(comboBox_sort);
        
        comboBox_sort.addItem("Khoảng lương");
        comboBox_sort.addItem("Dưới 10 củ");
        comboBox_sort.addItem("10 - 20 củ");
        comboBox_sort.addItem("Trên 20 củ");
        
        JComboBox<String> comboBox_order = new JComboBox<>();
        comboBox_order.setBounds(607, 62, 107, 31);
        panel.add(comboBox_order);

        comboBox_order.addItem("Tăng dần");
        comboBox_order.addItem("Giảm dần");
        
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("/assets/appIcon/icons8-search-24.png"));
    
        JButton button_tim = new JButton(searchIcon);
        
        button_tim.setBounds(858, 62, 51, 28);
       
        button_tim.setBorderPainted(false);
       
        panel.add(button_tim);
        

        JTextField textField = new JTextField();
        textField.setBounds(724, 62, 135, 28);
        panel.add(textField);
        
        ImageIcon excel = new ImageIcon(getClass().getResource("/assets/appIcon/icons8-excel-30.png"));
        JButton button_excel = new JButton(excel);
        button_excel.setBounds(829, 12, 63, 31);
        button_excel.setBorderPainted(false);
        panel.add(button_excel);
        
        
        JButton btnExportPDF = new JButton("Xuất PDF");
btnExportPDF.setBounds(273, 12, 103, 25);
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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file PDF");
        int userSelection = fileChooser.showSaveDialog(panel);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }
            
            InPDF pdfExporter = new InPDF();
            pdfExporter.savePDF(table, filePath);
            JOptionPane.showMessageDialog(panel, "Xuất dữ liệu ra file PDF thành công!");
        }
    }
});
panel.add(btnExportPDF);
    }
}
