package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;

public class HomePageTK3 extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public HomePageTK3() {
        setBackground(new Color(255, 255, 255));
        init();
    }

    public void init() {
        setLayout(null);
        setBounds(5, 300, 940, 300);

        JLabel lblNewLabel = new JLabel("Thống kê chi tiết từng phòng ban ");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setBounds(10, 10, 300, 29);
        add(lblNewLabel);

        // Tạo bảng
        String[] columnNames = {"STT", "Tên Phòng", "Tên Trưởng Phòng", "Số Nhân Viên", "Lương Trung Bình"};
        Object[][] data = {
            {"1", "Phòng A", "Nguyễn Văn A", "25", "4242424"},
            {"2", "Phòng B", "Trần Thị B", "30", "23213131"},
            {"3", "Phòng C", "Lê Văn C", "28", "45242314"},
            {"4", "Phòng D", "Hoàng Văn D", "35", "6342423"},
            {"5", "Phòng E", "Mai Thị E", "27", "3351313"},
            {"6", "Phòng F", "Đỗ Văn F", "31", "8624234"},
            {"7", "Phòng G", "Vũ Thị G", "33", "7842311"},
            {"8", "Phòng H", "Phạm Văn H", "29", "5224123"},
            {"9", "Phòng I", "Bùi Thị I", "26", "7412314"},
            {"10", "Phòng K", "Nguyễn Văn K", "32", "8923123"},
            {"11", "Phòng L", "Trần Văn L", "36", "6532423"},
            {"12", "Phòng M", "Lê Thị M", "29", "4321313"},
            {"13", "Phòng N", "Hoàng Văn N", "30", "7434234"},
            {"14", "Phòng O", "Mai Thị O", "28", "9822311"},
            {"15", "Phòng P", "Đỗ Văn P", "34", "5224123"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 920, 240);
        add(scrollPane);
    }
}
