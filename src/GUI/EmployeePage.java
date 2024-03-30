package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;

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
		panel.setBounds(10, 10, 970, 123);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 83, 120, 30);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(140, 83, 112, 30);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(262, 83, 120, 30);
		panel.add(comboBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Sắp xếp theo: ");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(466, 59, 112, 24);
		panel.add(lblNewLabel_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(466, 83, 123, 30);
		panel.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(599, 83, 130, 30);
		panel.add(comboBox_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setBounds(302, 19, 225, 30);
		panel.add(textArea);
		
		JLabel lblNewLabel_3 = new JLabel("Tìm theo tên");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(217, 19, 75, 30);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBounds(528, 19, 37, 30);
		panel.add(panel_1);
		
		JButton btnNewButton = new JButton("Chi tiết");
		btnNewButton.setBounds(776, 20, 146, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.setBounds(776, 53, 146, 30);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sửa");
		btnNewButton_2.setBounds(776, 83, 146, 30);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Reset");
		btnNewButton_3.setBounds(644, 53, 85, 21);
		panel.add(btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 143, 970, 413);
		add(panel_2);
		
	}
}
