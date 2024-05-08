package GUI;

import java.awt.Color;
import java.awt.Cursor;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import run.appFrame;

public class menuNavigation extends JPanel {
    
    private static final long serialVersionUID = 1L;
	private static final String FlatRobotoFont = null;
    public final String[] fnc = {
        "Trang chính",  // 0
        "Nhân viên",   // 1
        "Phòng ban",   // 2
        "Hợp đồng",    // 3
        "Chấm công",   // 4
        "Lương thưởng", // 5
        "Đánh giá",    // 6
        "Tài khoản",   // 7
        "Tuyển dụng"  //8
    };
    public final String[] iconMenu = {
            "/assets/appIcon/icons8-home-24.png",
            "/assets/appIcon/icons8-staff-50.png",
            "/assets/appIcon/icons8-department-50.png",
            "/assets/appIcon/icons8-contract-24.png",
            "/assets/appIcon/icons8-calendar-48.png",
            "/assets/appIcon/icons8-salary-50.png",
            "/assets/appIcon/icons8-favorite-64.png",
            "/assets/appIcon/icons8-login-50.png",
            "/assets/appIcon/icons8-salary-50.png"};
    private ArrayList<JButton> btnList;
    private static int index_menu = 0;
    private appFrame app;

    private String[] fnc_use = {};
    private String[] iconMenu_use = {};

    public menuNavigation(appFrame app) {
        init();
        this.app = app;
    }

    public void init() {
        setLayout(null);
        setOpaque(false);
        
        Render_nav();
        // Cập nhật màu sắc của nút khi khởi tạo
        updateButtonColor(index_menu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color color1 = new Color(20, 96, 183);
        //Color color2 = new Color(100, 250, 250);

        GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color1);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public int getIndexMenu() {
        return index_menu;
    }

    public void updateButtonColor(int index) {
        for (int i = 0; i < btnList.size(); i++) {
            JButton button = btnList.get(i);
            button.setBackground(i == index ? new Color(131, 189, 247 ) : new Color(20, 96, 183));
        }
    }

    public void Render_nav() {
        btnList = new ArrayList<JButton>();
        for (int i = 0; i < fnc_use.length; i++) {
            JButton menuBtn = new JButton();
            menuBtn.setContentAreaFilled(true);
            menuBtn.setBorderPainted(false);
            menuBtn.setFocusPainted(false);
            menuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            menuBtn.setIconTextGap(10);
            menuBtn.setBackground(new Color(20, 96, 183)); // Màu mặc định
            menuBtn.setBounds(0, 40 * i, 200, 40);
            menuBtn.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int index = btnList.indexOf(menuBtn);
                    index_menu = index;
                    app.getAppContent().displayContent(index);
                    updateButtonColor(index);
                }
                public void mouseEntered(MouseEvent e) {
                    menuBtn.setBackground(new Color(209, 223, 230)); 
                }

                public void mouseExited(MouseEvent e) {
                    if (getIndexMenu() != btnList.indexOf(menuBtn)) {
                        menuBtn.setBackground(new Color(20, 96, 183)); // Màu mặc định
                    }
                }
            });

            add(menuBtn);
            menuBtn.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(iconMenu_use[i % iconMenu_use.length])).getImage().getScaledInstance(22, 22, Image.SCALE_AREA_AVERAGING)));
            menuBtn.setText(fnc_use[i]);
            menuBtn.setHorizontalAlignment(SwingConstants.LEFT);

            btnList.add(menuBtn);
        }
    }

    public void set_fnc_use(String[] fnc_use) {
        this.fnc_use = fnc_use;
    }   

    public void set_iconMenu_use(String[] iconMenu_use) {
        this.iconMenu_use = iconMenu_use;
    }
}
