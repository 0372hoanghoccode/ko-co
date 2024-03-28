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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");

        // Content Pane
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Left Panel
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(204, 229, 255)); // Màu xanh nhạt
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        leftPanel.setPreferredSize(new Dimension(400, 400));
        leftPanel.setLayout(new BorderLayout());
        contentPane.add(leftPanel, BorderLayout.WEST);

        // Biểu tượng chào mừng từ FlatIcon
        ImageIcon welcomeIcon = new ImageIcon("https://icons8.com/icon/vB3C82RDvwwa/user");
        welcomeLabel = new JLabel("WELCOME QLNS", welcomeIcon, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        leftPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Right Panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        contentPane.add(rightPanel, BorderLayout.CENTER);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Biểu tượng tài khoản từ FlatIcon
        ImageIcon accountIcon = new ImageIcon("https://www.flaticon.com/svg/static/icons/svg/1077/1077063.svg");
        JLabel lblNewLabel = new JLabel("Tài Khoản", accountIcon, JLabel.LEFT);
        loginPanel.add(lblNewLabel, gbc);
        gbc.gridy++;
        textField = new JTextField();
        textField.setColumns(20);
        loginPanel.add(textField, gbc);
        gbc.gridy++;

        // Biểu tượng mật khẩu từ FlatIcon
        ImageIcon passwordIcon = new ImageIcon("https://www.flaticon.com/svg/static/icons/svg/1077/1077035.svg");
        JLabel lblNewLabel_1 = new JLabel("Mật Khẩu", passwordIcon, JLabel.LEFT);
        loginPanel.add(lblNewLabel_1, gbc);
        gbc.gridy++;
        textField_1 = new JTextField();
        textField_1.setColumns(20);
        loginPanel.add(textField_1, gbc);
        gbc.gridy++;

        // Biểu tượng nút đăng nhập từ FlatIcon
        ImageIcon loginButtonIcon = new ImageIcon("https://www.flaticon.com/svg/static/icons/svg/1077/1077036.svg");
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

    public boolean getIsLogin() {
        return this.isLogin;
    }
}
