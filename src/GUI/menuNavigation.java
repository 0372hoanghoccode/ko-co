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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import run.appFrame;


public class menuNavigation extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private final String[] fnc = {"Trang chính", "Trang 1", "Trang 2", "Trang 3", "Trang 4", "Trang 5"};
	private ArrayList<JButton> btnList;
	private static int index_menu = 0;
	private appFrame app;
	
	public menuNavigation(appFrame app) {
		this.app = app;
		init();
	}
	
	public void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(255, 255, 255));

		btnList = new ArrayList<JButton>();
		for (int i = 0; i < fnc.length; i++) {
			JButton menuBtn = new JButton();
			menuBtn.setContentAreaFilled(false); // Nền trong suốt
			menuBtn.setBorderPainted(false); // Bỏ viền
			menuBtn.setFocusPainted(false); // Bỏ focus
			menuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Con trỏ tay
			menuBtn.setIconTextGap(10);
			Color defaultColor = menuBtn.getBackground();
			
			
			menuBtn.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int index = btnList.indexOf(menuBtn);
					menuNavigation.index_menu = index;
			        // In ra màn hình index
			        System.out.println("Button index: " + index);
					app.getAppContent().displayContent(index);
				}
				public void mouseEntered(MouseEvent e) {
					menuBtn.setBackground(new Color(204, 0, 204));
				}
				public void mouseExited(MouseEvent e) {
					menuBtn.setBackground(defaultColor);
				}
			});
			
			add(menuBtn);
			menuBtn.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/exit_icon_hover.png")).getImage().getScaledInstance(22, 22, Image.SCALE_AREA_AVERAGING)));
			menuBtn.setText(fnc[i]);
			btnList.add(menuBtn);
				
		}
		
	}
	

	@Override
    protected void paintComponent(Graphics g) { //tự động chạy daijobu
        super.paintComponent(g);
        
        // Tạo gradient t
        Color color1 = new Color(100, 244, 220); // Màu xanh biển sáng
        Color color2 = new Color(100, 248, 250); // Màu xanh biển sáng hơn

        
        



       
        
        // Tạo GradientPaint
        GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
        
        // Vẽ gradient lên JPanel
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

	
	public int getIndexMenu() {
		return menuNavigation.index_menu;
	}
	
	

}
