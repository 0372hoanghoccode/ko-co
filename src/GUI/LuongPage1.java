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

public class LuongPage1 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTable table;

    /**
     * Create the panel.
     */
    public LuongPage1() {
        setBounds(100, 100, 970, 737);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 10, 970, 123);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Bảng Lương Nhân Viên");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(44, 35, 183, 27);
        add(lblNewLabel);
        
        JComboBox comboBox_nam = new JComboBox();
        comboBox_nam.setBounds(69, 72, 62, 31);
        add(comboBox_nam);
        
        JLabel label_nam = new JLabel("Năm");
        label_nam.setBounds(30, 72, 62, 27);
        add(label_nam);
        
        JLabel label_thang = new JLabel("Tháng");
        label_thang.setBounds(174, 74, 62, 27);
        add(label_thang);
        
        JComboBox comboBox_thang = new JComboBox();
        comboBox_thang.setBounds(233, 72, 97, 31);
        add(comboBox_thang);
        
        JLabel label_phongBan = new JLabel("Phòng Ban");
        label_phongBan.setBounds(370, 72, 62, 27);
        add(label_phongBan);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("New label");
        lblNewLabel_1_1_2.setBounds(614, 72, 62, 27);
        add(lblNewLabel_1_1_2);
        
        JComboBox comboBox_phongBan = new JComboBox();
        comboBox_phongBan.setBounds(442, 72, 107, 31);
        add(comboBox_phongBan);
        
        JComboBox comboBox_1_1_1 = new JComboBox();
        comboBox_1_1_1.setBounds(681, 72, 67, 31);
        add(comboBox_1_1_1);
        
        textField = new JTextField();
        textField.setBounds(758, 72, 135, 28);
        add(textField);
        textField.setColumns(10);
        
        JButton button_tim = new JButton("Tìm");
        button_tim.setBounds(903, 72, 51, 29);
        add(button_tim);
        
        JButton button_excel = new JButton("Xuất Excel");
        button_excel.setBounds(861, 40, 85, 21);
        add(button_excel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 149, 916, 467);
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
    }

}
