package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.JLabel;

public class EmployeeDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Object[] data;
	
	public EmployeeDetail(Object[] data) {
		this.data = data;
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 686, 463);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_12 = new JLabel("Mã nhân viên: " + data[0].toString());
		lblNewLabel_12.setBounds(10, 14, 134, 31);
		panel.add(lblNewLabel_12);
		
		JLabel lblNewLabel = new JLabel("Họ và tên: " + data[1].toString());
		lblNewLabel.setBounds(10, 78, 134, 38);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Giới tính: " + data[2].toString());
		lblNewLabel_2.setBounds(10, 230, 134, 38);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("Ngày sinh: " + data[3].toString());
		lblNewLabel_1.setBounds(10, 146, 134, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Địa chỉ: " + data[4].toString());
		lblNewLabel_3.setBounds(10, 307, 134, 38);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Số điện thoại: " + data[5].toString());
		lblNewLabel_4.setBounds(10, 355, 134, 50);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Dân tộc: " + data[6].toString());
		lblNewLabel_5.setBounds(10, 415, 134, 38);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tôn giáo: " + data[7].toString());
		lblNewLabel_6.setBounds(372, 14, 134, 31);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Email: " + data[8].toString());
		lblNewLabel_7.setBounds(374, 78, 132, 38);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Mã phòng: " + data[9].toString());
		lblNewLabel_8.setBounds(372, 152, 134, 38);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Trình độ: " + data[10].toString());
		lblNewLabel_9.setBounds(372, 227, 134, 44);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Chức vụ: " + data[11].toString());
		lblNewLabel_10.setBounds(372, 297, 134, 38);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Mức lương: " + data[12].toString());
		lblNewLabel_11.setBounds(372, 380, 134, 31);
		panel.add(lblNewLabel_11);
		
		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.setBounds(372, 432, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
		panel.add(btnNewButton);
		
		
	}
}
