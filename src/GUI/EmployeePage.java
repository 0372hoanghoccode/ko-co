package GUI;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

public class EmployeePage extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public EmployeePage() {
        init();
    }

    public void init() {
        setLayout(null);
        setBounds(5, 5, 1000, 600);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(20, 9, 970, 123);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Thông tin nhân viên ");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setBounds(10, 10, 146, 17);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo:");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(10, 59, 112, 24);
        panel.add(lblNewLabel_1);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(false);
        comboBox.setBackground(Color.WHITE);
        comboBox.setOpaque(true);
        comboBox.setBounds(20, 83, 193, 30);
        comboBox.addItem("Phòng ban");
        comboBox.addItem("Kế toán");
        comboBox.addItem("Nhân sự");
        comboBox.addItem("Kinh doanh");
        panel.add(comboBox);


        JComboBox<String> comboBox_1 = new JComboBox<>();
        comboBox_1.setBounds(236, 83, 112, 30);
        comboBox_1.addItem("Giới tính");
        comboBox_1.addItem("Nam");
        comboBox_1.addItem("Nữ");
        panel.add(comboBox_1);

        JComboBox<String> comboBox_2 = new JComboBox<>();
        comboBox_2.setBounds(377, 83, 120, 30);
        comboBox_2.addItem("Độ tuổi");
        comboBox_2.addItem("Dưới 25");
        comboBox_2.addItem("25 - 35");
        comboBox_2.addItem("Trên 35");
        panel.add(comboBox_2);

        JLabel lblNewLabel_2 = new JLabel("Sắp xếp theo: ");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(547, 59, 112, 24);
        panel.add(lblNewLabel_2);

        JComboBox<String> comboBox_3 = new JComboBox<>();
        
        comboBox_3.setBounds(547, 83, 123, 30);
        comboBox_3.addItem("Mã nhân viên");
        comboBox_3.addItem("Độ tuổi");
        comboBox_3.addItem("Mức lương");
        panel.add(comboBox_3);

        JComboBox<String> comboBox_4 = new JComboBox<>();
      
        comboBox_4.setBounds(714, 83, 130, 30);
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
        btnNewButton.setBounds(749, 19, 50, 29);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-user-24.png")));
        btnNewButton_1.setBounds(679, 18, 50, 30);
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("");
        btnNewButton_2.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-user-24.png")));
        btnNewButton_2.setBounds(814, 19, 146, 30);
        panel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("");
        btnNewButton_3.setIcon(new ImageIcon(EmployeePage.class.getResource("/assets/appIcon/icons8-reset-24.png")));
        btnNewButton_3.setBounds(895, 82, 50, 31);
        panel.add(btnNewButton_3);

       JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(10, 143, 970, 490);
        add(panel_2);
        panel_2.setLayout(new BorderLayout());

        // Tạo bảng
        String[] columnNames = {"STT", "Tên", "Giới Tính", "Năm Sinh", "SĐT", "Phòng Ban", "Chức Vụ", "Lương"};
        Object[][] data = {
            {"1", "Nguyễn Văn A", "Nam", "1990", "0123456789", "Kế Toán", "Nhân Viên", "10,000,000"},
            {"2", "Trần Thị B", "Nữ", "1995", "0987654321", "Nhân Sự", "Trưởng Phòng", "20,000,000"},
            // Thêm các dòng dữ liệu khác ở đây
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        panel_2.add(scrollPane, BorderLayout.CENTER);
    
    }
}
