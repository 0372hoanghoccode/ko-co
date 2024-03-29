package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class appHeader extends JPanel {

	private static final long serialVersionUID = 1L;

	public appHeader() {
		init();
	}
	
	public void init() {
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lí nhân sự");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 10, 114, 17);
		add(lblNewLabel);
		
		JButton btnClose = new JButton();
		btnClose.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/exit_icon.png")).getImage().getScaledInstance(22, 22, Image.SCALE_AREA_AVERAGING)));
		btnClose.setBounds(1130, 6, 22, 22);
		
		
		btnClose.setContentAreaFilled(false); 
		btnClose.setBorderPainted(false);
		btnClose.setFocusPainted(false);
		add(btnClose);

		JLabel webIconLabel = new JLabel();
		webIconLabel.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/exit_icon_hover.png")).getImage().getScaledInstance(22, 22, Image.SCALE_AREA_AVERAGING)));
		webIconLabel.setBounds(10, 6, 22, 22);
		add(webIconLabel);
	
		
		btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnClose.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			public void mouseEntered(MouseEvent e) {
				btnClose.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/exit_icon_hover.png")).getImage().getScaledInstance(22, 22, Image.SCALE_AREA_AVERAGING)));
			}
			public void mouseExited(MouseEvent e) {
				btnClose.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/exit_icon.png")).getImage().getScaledInstance(22, 22, Image.SCALE_AREA_AVERAGING)));
			}
		});
		
	}
	
	
	
	@Override
    protected void paintComponent(Graphics g) { //tự động chạy daijobu
        super.paintComponent(g);
        
        // Tạo gradient t
        Color color1 = new Color(183, 255, 212); 
        Color color2 = new Color(254, 183, 255); 
       
        
        // Tạo GradientPaint
        GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
        
        // Vẽ gradient lên JPanel
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
