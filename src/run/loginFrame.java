package run;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.CardLayout;

import GUI.Login_Panel;
import DAO.TaiKhoanDAO;
import DTO.TAIKHOAN;

public class loginFrame extends JFrame {
    private JLabel lb1;
    private Login_Panel loginForm;
    private static String[] text = {"QLNS","phần mềm quản lý tốt nhất"};
    private Timer timer1;
    private boolean isLogin = false;

    public loginFrame() {
        loginForm = new Login_Panel();
        init();
    }

    public void init() {
        loginForm = new Login_Panel();
        loginForm.setLoginFrame(this);

        this.setTitle("Login");
        this.setSize(1000,550);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(2,2,1096,596);
        contentPane.setLayout(null);
        this.add(contentPane);

        JPanel panelRight = new JPanel();
        panelRight.setBounds(600,0,400,550);
        contentPane.add(panelRight);
        panelRight.setLayout(new CardLayout());

        panelRight.add(loginForm);

        JPanel panelLogo = new JPanel();
        panelLogo.setBounds(0,0,600,550);
        panelLogo.setLayout(null);
        panelLogo.setBackground(Color.decode("#4CAF50"));
        contentPane.add(panelLogo);

        JLabel wellCome = new JLabel("CHÀO MỪNG");
        wellCome.setForeground(Color.white);
        wellCome.setBackground(Color.BLUE);
        wellCome.setFont(new Font("Arial", 1, 50));
        wellCome.setBounds(40,105,400,70);
        panelLogo.add(wellCome);

        JPanel panel_logo_1 = new JPanel();
        panel_logo_1.setLayout(null);
        panel_logo_1.setBounds(40,175,550,50);
        panelLogo.add(panel_logo_1);

        JLabel label_1 = new JLabel("Tới");
        label_1.setForeground(Color.white);
        label_1.setFont(new Font("Arial", 0, 30));
        label_1.setBounds(0, 0, 50, 50);
        label_1.setOpaque(true);
        label_1.setBackground(Color.decode("#4CAF50"));
        panel_logo_1.add(label_1);

        lb1 = new JLabel();
        lb1.setFont(new Font("Arial", 0, 30));
        lb1.setForeground(Color.white);
        lb1.setOpaque(true);
        lb1.setBackground(Color.decode("#4CAF50"));
        lb1.setBounds(50, 0, 580, 50);
        panel_logo_1.add(lb1);
        lb1.setBorder(null);

        timer1 = new Timer(100, new ActionListener() {
            int index = 0;
            int i = 0;
            boolean isDeleting = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isDeleting) {
                    lb1.setText(text[i].substring(0, index));
                    index++;
                    if (index > text[i].length()) {
                        isDeleting = true;
                        index = text[i].length();
                    }
                } else {
                    lb1.setText(text[i].substring(0, index));
                    index--;
                    if (index < 0) {
                        isDeleting = false;
                        index = 0;
                        if (i == 1)
                            i--;
                        else
                            i++;
                    }
                    if (index == 0 && !isDeleting) {
                        if (i == 0) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        timer1.start();
    }

    public boolean getIsLogin() {
        return this.isLogin;
    }

    public void checkLogin() {
        String input_taikhoan = loginForm.getUsername().getText();
        String input_maukhau = new String(loginForm.getPass().getText());
        String input_maukhau_str = new String(input_maukhau);
        if (input_taikhoan.equals("") || input_maukhau_str.equals("")) 
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        else {
            TAIKHOAN tk = TaiKhoanDAO.getInstance().getTAIKHOAN(input_taikhoan);
            if (tk == null)
                JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            else {
                if (tk.getPass().equals(input_maukhau_str)) {
                    // login
                    dispose();
                    appFrame app = new appFrame();
                    app.setVisible(true);
                    app.setAccountCurrenLogin(tk);
                    app.setEventFnc(DAO.NhomQuyenDAO.getInstance().getChucNangTaiKhoan(tk.getUsername()));
                    app.StartingApp();
                    isLogin = true;
                }
                else 
                    JOptionPane.showMessageDialog(this, "Mật khẩu không khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            }
        }

        char[] input_maukhauArray = input_maukhau.toCharArray();
        Arrays.fill(input_maukhauArray, '0');
    }
}
