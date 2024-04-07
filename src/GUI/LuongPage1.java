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

public class LuongPage1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTable table;

    /**
     * Create the panel.
     */
    public LuongPage1() {
        setBounds(100, 100, 985, 615);
        setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 118, 985, 490);
        add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "STT", "Nhân Viên", "Thời Gian", "Lương cơ bản",
                "Lương thực tế", "Phụ cấp", "Lương thưởng", 
                "Các khoản trừ", "Thuế", "Thực lãnh"
            }
        ));
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
        
        JComboBox<Integer> comboBox_nam = new JComboBox<>();
        comboBox_nam.setBounds(72, 62, 62, 31);
        panel.add(comboBox_nam);
        // Thêm dữ liệu vào comboBox_nam (các năm gần đây)
        int currentYear = LocalDate.now().getYear();
        for (int year = currentYear; year >= currentYear - 10; year--) {
            comboBox_nam.addItem(year);
        }
        // Thiết lập giá trị mặc định cho comboBox_nam
        comboBox_nam.setSelectedItem(currentYear); // Chọn năm hiện tại
        
        JComboBox<Integer> comboBox_thang = new JComboBox<>();
        comboBox_thang.setBounds(214, 62, 97, 31);
        panel.add(comboBox_thang);
        // them dư lieu 12 tháng vào
        for (int month = 1; month <= 12; month++) {
            comboBox_thang.addItem(month);
        }
        // thiet lap mac dinh
        comboBox_thang.setSelectedItem(LocalDate.now().getMonthValue()); // chọn tháng hiện tại
        
        JComboBox<String> comboBox_phongBan = new JComboBox<>();
        comboBox_phongBan.setBounds(368, 62, 107, 31);
        panel.add(comboBox_phongBan);
        
        comboBox_phongBan.addItem("Phòng A");
        comboBox_phongBan.addItem("Phòng B");
        comboBox_phongBan.addItem("Phòng C");
        
        JComboBox<String> comboBox_sort = new JComboBox<>();
        comboBox_sort.setBounds(488, 62, 107, 31);
        panel.add(comboBox_sort);
        
        comboBox_sort.addItem("Thời gian");
        comboBox_sort.addItem("Lương thưởng");
        comboBox_sort.addItem("Thực lãnh");
        
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
