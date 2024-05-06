package GUI;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;

import BUS.PHONGBAN_BUS;

public class HomePageTK3 extends JPanel {

    private static final long serialVersionUID = 1L;
    JTable table;
	PHONGBAN_BUS phongban_bus = new PHONGBAN_BUS();
    String[] columnNames = {
        "STT", "T\u00EAn ph\u00F2ng", "Tr\u01B0\u1EDFng ph\u00F2ng", "S\u1ED1 nh\u00E2n vi\u00EAn", "L\u01B0\u01A1ng trung b\u00ECnh"
    };
    Object[][] data = {};
    /**
     * Create the panel.
     */
    public HomePageTK3() {
        setBackground(new Color(255, 255, 255));
        init();
    }

    public void init() {
        setLayout(null);
        setBounds(5, 300, 985, 346);

        JLabel lblNewLabel = new JLabel("Thống kê chi tiết từng phòng ban ");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setBounds(10, 10, 300, 17);
        add(lblNewLabel);
        
        

        

        // Tạo bảng
       

        data = phongban_bus.renderAllPhongBanData();
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
		scrollPane.setVerticalScrollBar(new myScrollBar());
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setBounds(10, 37, 965, 299);
        
        add(scrollPane);
    }
}
