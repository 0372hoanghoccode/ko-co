package run;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import GUI.RoundJPasswordField;
import GUI.RoundJTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.lang.classfile.instruction.NewMultiArrayInstruction;

public class loginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RoundJTextField textField;
    private JPasswordField passwordField;
    private boolean isLogin = false;
    Color FontColor = new Color(108, 188, 231);

    public loginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 450);
        setUndecorated(true);
        setLocationRelativeTo(null);
        
        setTitle("Login");


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

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Label "Đăng nhập hệ thống"
        JLabel loginSystemLabel = new JLabel("Đăng nhập hệ thống");        
        loginSystemLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); 
        loginSystemLabel.setForeground(Color.BLACK);
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginSystemLabel, gbc);
        gbc.gridy++;

        ImageIcon accountIcon = new ImageIcon("src/assets/appIcon/icons8-user-24.png");
        accountIcon = resizeIcon(accountIcon, 20, 20);
        JLabel lblNewLabel = new JLabel("Tài Khoản", accountIcon, JLabel.LEFT);
        gbc.anchor=  GridBagConstraints.WEST;
        loginPanel.add(lblNewLabel, gbc);
        gbc.gridy++;
        textField = new RoundJTextField(25); 
        textField.setBorder(new RoundedBorder(10));        
        textField.setPreferredSize(new Dimension(250, 30));
        loginPanel.add(textField, gbc);
        gbc.gridy++;

        ImageIcon passwordIcon = new ImageIcon("src/assets/appIcon/icons8-password-50.png");
        passwordIcon = resizeIcon(passwordIcon, 20, 20);
        JLabel lblNewLabel_1 = new JLabel("Mật Khẩu", passwordIcon, JLabel.LEFT);
        loginPanel.add(lblNewLabel_1, gbc);
        gbc.gridy++;
        passwordField= new JPasswordField();
        passwordField.setBorder(new RoundedBorder(10));
        passwordField.setPreferredSize(new Dimension(250, 30));           
        loginPanel.add(passwordField, gbc);
        gbc.gridy++;

        // Biểu tượng nút đăng nhập từ file cục bộ, được điều chỉnh kích thước
        ImageIcon loginButtonIcon = new ImageIcon("src/assets/appIcon/icons8-login-50.png");
        loginButtonIcon = resizeIcon(loginButtonIcon, 20, 20);
        JButton btnNewButton = new JButton("Đăng Nhập", loginButtonIcon);
        btnNewButton.setPreferredSize(new Dimension(250,30));
        btnNewButton.setBorderPainted(false);      
       //btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorder(new RoundedBorder(10));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0);
        //gbc.gridwidth = 5; // Tăng chiều dài của nút đăng nhập lên 2 ô
        
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent evt) {
        		btnNewButton.setBackground(FontColor);
        		btnNewButton.setForeground(Color.black);
            }
        	
        	@Override
            public void mouseExited(MouseEvent evt) {
        		btnNewButton.setBackground(new Color(46, 129, 223));
        		btnNewButton.setForeground(Color.white);
            }
		});
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
        btnNewButton.setBackground(new Color(46, 129, 223));
        btnNewButton.setForeground(Color.BLACK);
        loginPanel.add(btnNewButton, gbc);
        rightPanel.add(loginPanel, BorderLayout.CENTER);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public boolean getIsLogin() {
        return this.isLogin;
    }
    
    private static class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius, this.radius , this.radius , this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.BLACK);
            ((Graphics2D) g).draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
        }
    }
    
}
