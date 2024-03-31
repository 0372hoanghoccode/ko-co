package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

public class HopdongPage_Tongquan extends JPanel {

	private static final long serialVersionUID = 1L;

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
		panel.setBounds(0, 0, 980, 45);
		add(panel);
		
		String[] columnNames = {"STT", "Mã", "Tên", "Phòng", "Bắt đầu từ", "Kết thúc", "Lương"};
        Object[][] data = {
        		{"1", "ct01", "Ai", "Mật 01", "1/1/2024", "30/11/2024", "6000000"},
        		{"2", "ct02", "Ai Mot", "Mật 02", "1/1/2024", "30/11/2024", "9000000"},
        		{"2", "ct02", "Ai Mot", "Mật 02", "1/1/2024", "30/11/2024", "9000000"},
        		{"2", "ct02", "Ai Mot", "Mật 02", "1/1/2024", "30/11/2024", "9000000"},
        		{"2", "ct02", "Ai Mot", "Mật 02", "1/1/2024", "30/11/2024", "9000000"},
        		{"3", "ct03", "Ai Hai", "Không mật 01", "1/6/2024", "30/11/2024", "8000000"},
        		{"3", "ct03", "Ai Hai", "Không mật 01", "1/6/2024", "30/11/2024", "8000000"},
        		{"3", "ct03", "Ai Hai", "Không mật 01", "1/6/2024", "30/11/2024", "8000000"},
        		{"3", "ct03", "Ai Hai", "Không mật 01", "1/6/2024", "30/11/2024", "8000000"},
        		{"3", "ct03", "Ai Hai", "Không mật 01", "1/6/2024", "30/11/2024", "8000000"},
        		{"4", "ct04", "Ai Ba", "Mật 01", "1/5/2024", "30/12/2024", "7000000"},
        		{"5", "ct05", "Ai Bon", "Mật 02", "1/1/2024", "30/12/2024", "6500000"},
        		{"5", "ct05", "Ai Bon", "Mật 02", "1/1/2024", "30/12/2024", "6500000"},
        		{"5", "ct05", "Ai Bon", "Mật 02", "1/1/2024", "30/12/2024", "6500000"},
        		{"5", "ct05", "Ai Bon", "Mật 02", "1/1/2024", "30/12/2024", "6500000"},
        		{"5", "ct05", "Ai Bon", "Mật 02", "1/1/2024", "30/12/2024", "6500000"},
        		{"5", "ct05", "Ai Bon", "Mật 02", "1/1/2024", "30/12/2024", "6500000"},
        		{"5", "ct05", "Ai Bon", "Mật 02", "1/1/2024", "30/12/2024", "6500000"},
        		{"6", "ct06", "Ai Nam", "Không mật 01", "1/1/2024", "30/11/2024", "4000000"},
        		{"7", "ct07", "Ai Sau", "Mật 01", "1/1/2024", "30/11/2024", "10000000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        		{"8", "ct08", "Ai Tam", "Mật 01", "1/1/2024", "28/12/2024", "7500000"},
        };
		
        
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 55, 980, 540);
        add(scrollPane);
		
	}

}
