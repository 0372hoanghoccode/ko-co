package GUI;

import java.awt.CardLayout;
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

import BUS.CHAMCONG_BUS;
import BUS.PHONGBAN_BUS;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ChamCongPage1 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	JTable table=new JTable();
	CHAMCONG_BUS chamcong_BUS= new CHAMCONG_BUS();
	PHONGBAN_BUS phongban_BUS= new PHONGBAN_BUS();
	String[] event_name=new String[] {
			"Phòng Ban","Tháng","Năm"
	};
	String[] columnNames= {"STT", "Mã BCC","Mã nhân viên","Thời gian CC","Ngày làm việc","Nghỉ","Trễ","Giờ làm thêm"};
	Object[][] data=chamcong_BUS.renderChamCongData();
	DefaultTableModel model= new DefaultTableModel(data,columnNames) {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
            return false;
        }
	};
	
	public ChamCongPage1() {
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
				appContent parentForm=(appContent) getParent();
				setVisible(false);
				parentForm.displayContent(9);
			}
		});
		btnNewButton.setBounds(851, 17, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo:");
		lblNewLabel_1.setBounds(20, 53, 89, 14);
		panel.add(lblNewLabel_1);
		
		JComboBox<String> comboBox_2=new JComboBox<>();
		comboBox_2.setEditable(false);
		comboBox_2.setBackground(Color.white);
		comboBox_2.setOpaque(true);
		comboBox_2.setBounds(722, 71, 115, 22);
		for(String i: phongban_BUS.getTenPhongBan())
			comboBox_2.addItem(i);
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
		
		table.setModel(model);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
	
		table.getColumnModel().getColumn(0).setPreferredWidth(30);  // stt
		table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Ma BCC
		table.getColumnModel().getColumn(2).setPreferredWidth(150);   // Ma Nhan Vien
		table.getColumnModel().getColumn(3).setPreferredWidth(70);  // thoi gianCC
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);  // nghi
		table.getColumnModel().getColumn(5).setPreferredWidth(80);  // tre
		table.getColumnModel().getColumn(6).setPreferredWidth(80);   // gio lam them
		
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
		
		
		comboBox_2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED) {
					String selectedOption = (String) comboBox_2.getSelectedItem();
					event_name[0]=selectedOption;
					event_action();
				}
				
			}
		});
		
//		yearChooser.addComponentListener(new ChangeListener() {
//		    @Override
//		    public void stateChanged(ChangeEvent e) {
//		        int selectedYear = yearChooser.getYear();
//		        // Thực hiện các hành động cần thiết khi giá trị của JYearChooser thay đổi
//		        // Ví dụ: Cập nhật dữ liệu hiển thị trên JTable dựa trên năm đã chọn
//		        event_name[2] = String.valueOf(selectedYear);
//		        event_action();
//		    }
//		});
//

	}
	public void event_action() {
		data = chamcong_BUS.changeDataValue(event_name);
    	model.setDataVector(data, columnNames);
        table.setModel(model);
	}
	
}
