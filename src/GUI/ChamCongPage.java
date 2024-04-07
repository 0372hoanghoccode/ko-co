package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ChamCongPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ChamCongPage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 9, 980, 123);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bảng chấm công");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(10, 11, 229, 31);
		panel.add(lblNewLabel);
		
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
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(851, 17, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo:");
		lblNewLabel_1.setBounds(20, 53, 89, 14);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox_2 = new JComboBox();		
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Kế toán", "Nhân sự", "Kinh doanh"}));
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setBounds(722, 71, 115, 22);
		panel.add(comboBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Tháng:");
		lblNewLabel_2.setBounds(20, 75, 49, 14);
		panel.add(lblNewLabel_2);
		
		JPanel thangPanel = new JPanel();
		thangPanel.setLayout(null);
		thangPanel.setBounds(90, 71, 107, 22);		
		panel.add(thangPanel);
		
		JMonthChooser monthChooser=new JMonthChooser();
		monthChooser.setBounds(0, 0, 107, 22);
		thangPanel.add(monthChooser);
		
		JLabel lblNewLabel_3 = new JLabel("Năm:");
		lblNewLabel_3.setBounds(275, 75, 49, 14);
		panel.add(lblNewLabel_3);
		
		JPanel namPanel = new JPanel();
		namPanel.setLayout(null);
		namPanel.setBounds(235, 71, 107, 22);		
		panel.add(namPanel);
		
		JYearChooser yearChooser=new JYearChooser();
		yearChooser.setBounds(0, 0, 107, 22);
		namPanel.add(yearChooser);
		
		JLabel lblNewLabel_4 = new JLabel("Phòng ban:");
		lblNewLabel_4.setBounds(618, 75, 94, 14);
		panel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 143, 980, 506);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 980, 335);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "M\u00E3 b\u1EA3ng ch\u1EA5m c\u00F4ng", "M\u00E3 nh\u00E2n vi\u00EAn", "N\u0103m", "Th\u00E1ng", "Ng\u00E0y l\u00E0m vi\u1EC7c", "Ng\u00E0y ngh\u1EC9", "Ng\u00E0y tr\u1EC5", "Gi\u1EDD l\u00E0m th\u00EAm"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Thông tin chi tiết", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 338, 980, 174);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(240, 240, 240));
		textArea.setBounds(36, 35, 910, 143);
		panel_2.add(textArea);

	}
}
