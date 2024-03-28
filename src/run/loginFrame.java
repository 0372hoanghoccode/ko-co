package run;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private String taikhoan = "002";
	private String matkhau = "abc12345";	
	
	private boolean isLogin = false;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public loginFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(450, 300);
		setUndecorated(true);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tài Khoản");
		lblNewLabel.setBounds(227, 54, 62, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu");
		lblNewLabel_1.setBounds(227, 116, 62, 35);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(227, 83, 155, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 151, 155, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Đăng Nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputTaiKhoan = textField.getText();
				String inputMatKhau = textField_1.getText();
				
				if (inputTaiKhoan.equals("") && inputMatKhau.equals("")) {
					JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
				else if (inputTaiKhoan.equals(taikhoan)) {
					System.out.println("đúng tài khoản");
					
					if (inputMatKhau.equals(matkhau)) {
						System.out.println("Đúng mật khẩu");
						dispose();
						appFrame app = new appFrame();
						app.setVisible(true);
						isLogin = true;
					}
					
				}
				
			}
		});
		btnNewButton.setBounds(227, 204, 95, 36);
		contentPane.add(btnNewButton);
		
		
	}
	
	public boolean getIsLogin() {
		return this.isLogin;
		
	}
	
	
	
}
