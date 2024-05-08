package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

import BUS.PHONGBAN_BUS;

public class HomePageTK3 extends JPanel {

    private static final long serialVersionUID = 1L;
    private myTable table;
    private DefaultTableModel model;
    private PHONGBAN_BUS phongban_bus = new PHONGBAN_BUS();
    private String[] columnNames = {
        "STT", "Tên phòng", "Trưởng phòng", "Số nhân viên", "Lương trung bình"
    };

    public HomePageTK3() {
        setBackground(Color.WHITE);
        init();
        fetchAndUpdateData();
    }

    public void init() {
        setLayout(null);
        setBounds(5, 300, 985, 346);

        JLabel titleLabel = new JLabel("Thống kê chi tiết từng phòng ban");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 0, 0, 140));
        titleLabel.setBounds(10, 10, 300, 20);
        add(titleLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 37, 965, 299);
        scrollPane.setVerticalScrollBar(new myScrollBar());
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        table = new myTable();
        table.setBackground(Color.WHITE);
        scrollPane.setViewportView(table);
    }

    private void fetchAndUpdateData() {
        Object[][] data = phongban_bus.renderAllPhongBanData();
        setTableData(data);
    }

    public void setTableData(Object[][] data) {
        model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        formatColumn();
    }

    private void formatColumn() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(90);  
        table.getColumnModel().getColumn(1).setPreferredWidth(285);
        table.getColumnModel().getColumn(2).setPreferredWidth(230); 
        table.getColumnModel().getColumn(3).setPreferredWidth(150); 
        table.getColumnModel().getColumn(4).setPreferredWidth(200); 

    }
}
