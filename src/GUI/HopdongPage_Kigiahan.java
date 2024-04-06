package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;

public class HopdongPage_Kigiahan extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HopdongPage_Kigiahan() {
		
		init();
	}


	public void init() {
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 515, 80);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(520, 0, 465, 610);
		add(panel_1);
		
	
		
		
		String[] columnNames = {"STT", "Mã", "Tên", "Phòng", "Bắt đầu thử việc từ"};

		Object[][] data = {
			{"1", "Nv01", "Rodri", "MC", "01/01/2023"},
			{"2", "Nv01", "Rodri", "MC", "01/01/2023"},
			{"3", "Nv01", "Rodri", "MC", "01/01/2023"},
			{"5", "Nv01", "Rodri", "MC", "01/01/2023"},
			{"6", "Nv01", "Rodri", "MC", "01/01/2023"},
			{"7", "Nv01", "Rodri", "MC", "01/01/2023"},
			{"8", "Nv01", "Rodri", "MC", "01/01/2023"},
			{"9", "Nv01", "Rodri", "MC", "01/01/2023"},
			// thiếu gì ko ?

		};

		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 85, 515, 520);
        add(scrollPane);
	}

}
