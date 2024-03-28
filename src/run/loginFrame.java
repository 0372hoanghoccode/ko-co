package run;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel welcomeLabel;
    private Timer timer;

    private String taikhoan = "002";
    private String matkhau = "abc12345";

    private boolean isLogin = false;

    public loginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 450);
        setLocationRelativeTo(null);
        setTitle("Login");

        // Content Pane
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Background Panel
        ImageIcon backgroundIcon = new ImageIcon("img/background-login.jpg"); // Thay đổi đường dẫn tới ảnh nền
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        contentPane.add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new BorderLayout());

        // Left Panel
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(204, 229, 255));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        leftPanel.setPreferredSize(new Dimension(400, 400));
        leftPanel.setLayout(new BorderLayout());
        backgroundLabel.add(leftPanel, BorderLayout.WEST);

        ImageIcon welcomeIcon = new ImageIcon("path/to/welcome.png");
        welcomeIcon = resizeIcon(welcomeIcon, 50, 50);
        welcomeLabel = new JLabel("WELCOME QLNS", welcomeIcon, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        leftPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Right Panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        backgroundLabel.add(rightPanel, BorderLayout.CENTER);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        
        ImageIcon accountIcon = new ImageIcon("img/icons8-user-24.png");
        accountIcon = resizeIcon(accountIcon, 20, 20);
        JLabel lblNewLabel = new JLabel("Tài Khoản", accountIcon, JLabel.LEFT);
        loginPanel.add(lblNewLabel, gbc);
        gbc.gridy++;
        textField = new JTextField();
        textField.setColumns(20);
        loginPanel.add(textField, gbc);
        gbc.gridy++;

        
        ImageIcon passwordIcon = new ImageIcon("img/icons8-password-50.png");
        passwordIcon = resizeIcon(passwordIcon, 20, 20);
        JLabel lblNewLabel_1 = new JLabel("Mật Khẩu", passwordIcon, JLabel.LEFT);
        loginPanel.add(lblNewLabel_1, gbc);
        gbc.gridy++;
        textField_1 = new JTextField();
        textField_1.setColumns(20);
        loginPanel.add(textField_1, gbc);
        gbc.gridy++;

        // Biểu tượng nút đăng nhập từ file cục bộ, được điều chỉnh kích thước
        ImageIcon loginButtonIcon = new ImageIcon("img/icons8-login-50.png");
        loginButtonIcon = resizeIcon(loginButtonIcon, 20, 20);
        JButton btnNewButton = new JButton("Đăng Nhập", loginButtonIcon);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputTaiKhoan = textField.getText();
                String inputMatKhau = textField_1.getText();

                if (inputTaiKhoan.equals("") && inputMatKhau.equals("")) {
                    JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (inputTaiKhoan.equals(taikhoan)) {
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
        loginPanel.add(btnNewButton, gbc);

        rightPanel.add(loginPanel, BorderLayout.CENTER);

        // Timer để thay đổi màu của chữ
        timer = new Timer(1000, new ActionListener() {
            boolean isBlue = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isBlue) {
                    welcomeLabel.setForeground(new Color(0, 102, 204));
                    isBlue = false;
                } else {
                    welcomeLabel.setForeground(Color.BLACK);
                    isBlue = true;
                }
            }
        });
        timer.start();
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public boolean getIsLogin() {
        return this.isLogin;
    }
}
