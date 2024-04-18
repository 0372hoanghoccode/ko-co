package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

import BUS.HOPDONGLAODONG_BUS;

public class HopdongPage_Tongquan extends JPanel {

	private static final long serialVersionUID = 1L;
	HOPDONGLAODONG_BUS hopdonglaodong_bus = new HOPDONGLAODONG_BUS();
	/**
	 * Create the panel.
	 */
	public HopdongPage_Tongquan() {
		
		init();
	}

	public void init() {
		setLayout(null);
		
		JPanel panel = new JPanel();// tinh nang
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 985, 45);
		add(panel);
		 
		String[] columnNames = {"STT", "Mã HĐ", "Mã nv","Tên", "Phòng", "Bắt đầu", "Kết thúc", "Loại hợp đồng", "Lương"};
        Object[][] data = hopdonglaodong_bus.getDataObjectToRender();
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        
        
        JTable table = new JTable(model);
		table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.white);
		scrollPane.setVerticalScrollBar(new myScrollBar());
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setBounds(0, 50, 985, 555);
        add(scrollPane);
		
	}

}
