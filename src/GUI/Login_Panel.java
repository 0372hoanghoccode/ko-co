package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import run.loginFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login_Panel extends JPanel{
	private JButton btn;
	private JLabel lb1;
	private TextField username;
	private JTextField pass;
	private JButton btnClose;
	private loginFrame loginFrameRef; // tham chiếu đến loginFrame

	public void setLoginFrame(loginFrame frame) {
        this.loginFrameRef = frame;
    }
	
	public TextField getUsername() {
		return username;
	}
	public void setUsername(TextField username) {
		this.username = username;
	}
	public JTextField getPass() {
		return pass;
	}
	public void setPass(TextField pass) {
		this.pass = pass;
	}
	public JLabel getLb1() {
		return lb1;
	}
	public void setLb1(JLabel lb1) {
		this.lb1 = lb1;
	}
	public JButton getBtnClose() {
		return btnClose;
	}
	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}
	public Login_Panel() {
		init();
	}
	
	public JButton getBtn() {
		return btn;
	}
	public void setBtn(JButton btn) {
		this.btn = btn;
	}
	public void init() {
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(600, 0, 400, 550);
		this.setBackground(Color.white);
		
		JLabel label = new JLabel("Đăng Nhập Vào Hệ Thống");
		label.setFont(new Font("Arial",Font.PLAIN,23));
		label.setBounds(69,74,290,30);
		this.add(label);
		
		username = new TextField();
		username.setLabelText("Tên Đăng Nhập");
		username.setBounds(50,180,300,50);
		this.add(username);
		
		pass = new PasswordField();
		((PasswordField)pass).setLabelText("Mật Khẩu");
		pass.setBounds(50,240,300,50);
		this.add(pass);

		btn = new JButton("Đăng Nhập");
        btn.setFont(new Font("Arial",Font.PLAIN,15));
        btn.setForeground(Color.white);
        btn.setBounds(50,340,300,40);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.decode("#4CAF12"));
        btn.addMouseListener(new MouseAdapter() {
        public void mouseExited(MouseEvent e) {
           btn.setBackground(Color.decode("#4CAF50"));
        }
        public void mouseEntered(MouseEvent e) {
           btn.setBackground(Color.decode("#4CAF79"));
        }
    });
        btn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        loginFrameRef.checkLogin();
        }
    });
    this.add(btn);

	}
}