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
    private JPasswordField passwordField;
    private boolean isLogin = false;

    public loginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 450);
        setLocationRelativeTo(null);
        setTitle("Login");
        setUndecorated(true);

        // Content Pane
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

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
        contentPane.add(leftPanel, BorderLayout.WEST);

       
        ImageIcon welcomeIcon = new ImageIcon("src/assets/appIcon/R.png");
        welcomeIcon = resizeIcon(welcomeIcon, 300, 300);
        JLabel welcomeLabel = new JLabel(welcomeIcon, JLabel.CENTER);
        leftPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Right Panel
        JPanel rightPanelContainer = new JPanel(new BorderLayout());
        contentPane.add(rightPanelContainer, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanelContainer.add(rightPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Tạo panel mới để chứa nút "Thoát"
        rightPanelContainer.add(buttonPanel, BorderLayout.EAST); // Đặt panel chứa nút "Thoát" bên phải của rightPanel

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Label "Đăng nhập hệ thống"
        JLabel loginSystemLabel = new JLabel("Đăng nhập hệ thống");
        loginSystemLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        loginSystemLabel.setForeground(Color.BLACK);
        gbc.gridy++;

        ImageIcon accountIcon = new ImageIcon("src/assets/appIcon/icons8-user-24.png");
        accountIcon = resizeIcon(accountIcon, 20, 20);
        JLabel lblNewLabel = new JLabel("Tài Khoản", accountIcon, JLabel.LEFT);
        loginPanel.add(lblNewLabel, gbc);
        gbc.gridy++;
        textField = new JTextField();
        textField.setColumns(20);
        loginPanel.add(textField, gbc);
        gbc.gridy++;

        ImageIcon passwordIcon = new ImageIcon("src/assets/appIcon/icons8-password-50.png");
        passwordIcon = resizeIcon(passwordIcon, 20, 20);
        JLabel lblNewLabel_1 = new JLabel("Mật Khẩu", passwordIcon, JLabel.LEFT);
        loginPanel.add(lblNewLabel_1, gbc);
        gbc.gridy++;
        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        loginPanel.add(passwordField, gbc);
        gbc.gridy++;

        // Biểu tượng nút đăng nhập từ file cục bộ, được điều chỉnh kích thước
        ImageIcon loginButtonIcon = new ImageIcon("src/assets/appIcon/icons8-login-50.png");
        loginButtonIcon = resizeIcon(loginButtonIcon, 20, 20);
        JButton btnNewButton = new JButton("Đăng Nhập", loginButtonIcon);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputTaiKhoan = textField.getText();
                String inputMatKhau = passwordField.getText();

                if (inputTaiKhoan.equals("") && inputMatKhau.equals("")) {
                    JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (inputTaiKhoan.equals("1")) { 
                    System.out.println("đúng tài khoản");

                    if (inputMatKhau.equals("1")) { 
                        System.out.println("Đúng mật khẩu");
                        dispose();
                        appFrame app = new appFrame();
                        app.setVisible(true);
                        isLogin = true;
                    }
                }
            }
        });
        btnNewButton.setBackground(Color.BLUE); 
        btnNewButton.setForeground(Color.BLACK);
        loginPanel.add(btnNewButton, gbc);

        rightPanel.add(loginPanel, BorderLayout.CENTER);

        // nút tháo vào panel chứa nút bên phải
        ImageIcon exitIcon = new ImageIcon("src/assets/appIcon/icons8-close-24.png");
        JButton exitButton = new JButton(exitIcon);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

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
