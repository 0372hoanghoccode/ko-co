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

public class LuongPage1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTable table;

    /**
     * Create the panel.
     */
    public LuongPage1() {
        setBounds(100, 100, 979, 599);
        setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 121, 959, 468);
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
        panel.setBounds(10, 10, 959, 101);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Bảng Lương Nhân Viên");
        lblNewLabel.setBounds(10, 10, 183, 27);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JLabel label_nam = new JLabel("Năm");
        label_nam.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_nam.setHorizontalAlignment(SwingConstants.CENTER);
        label_nam.setBounds(0, 64, 62, 27);
        panel.add(label_nam);
        
        JComboBox comboBox_nam = new JComboBox();
        comboBox_nam.setBounds(72, 62, 62, 31);
        panel.add(comboBox_nam);
        
        JLabel label_thang = new JLabel("Tháng");
        label_thang.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_thang.setBounds(169, 64, 62, 27);
        panel.add(label_thang);
        
        JComboBox comboBox_thang = new JComboBox();
        comboBox_thang.setBounds(214, 62, 97, 31);
        panel.add(comboBox_thang);
        
        JLabel label_phongBan = new JLabel("Phòng Ban");
        label_phongBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_phongBan.setBounds(368, 64, 62, 27);
        panel.add(label_phongBan);
        
        JComboBox comboBox_phongBan = new JComboBox();
        comboBox_phongBan.setBounds(449, 63, 107, 31);
        panel.add(comboBox_phongBan);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("New label");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1_2.setBounds(588, 64, 62, 27);
        panel.add(lblNewLabel_1_1_2);
        
        JComboBox comboBox_1_1_1 = new JComboBox();
        comboBox_1_1_1.setBounds(660, 63, 67, 31);
        panel.add(comboBox_1_1_1);
        
        textField = new JTextField();
        textField.setBounds(753, 65, 135, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton button_tim = new JButton("Tìm");
        button_tim.setBounds(898, 64, 51, 29);
        panel.add(button_tim);
        
        JButton button_excel = new JButton("Xuất Excel");
        button_excel.setBounds(832, 15, 85, 21);
        panel.add(button_excel);
    }
}
