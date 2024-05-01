package GUI;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
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

public class LuongPage1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    LUONG_BUS luongBUS = new LUONG_BUS();
    PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();
    /**
     * Create the panel.
     */
    public LuongPage1() {
        setBounds(100, 100, 985, 615);
        setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 118, 985, 490);
        add(scrollPane);
        
        String[] columnNames = { 
        "STT", "Nhân Viên", "Thời Gian", "Lương cơ bản",
                "Lương thực tế", "Phụ cấp", "Lương thưởng", 
                "Các khoản trừ", "Thuế", "Thực lãnh"
    };
    Object[][] data = luongBUS.getDataObjectToRender(); 
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable table = new JTable(model);
    
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
    }
}
