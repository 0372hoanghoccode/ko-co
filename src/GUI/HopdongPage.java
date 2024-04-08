package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HopdongPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private HopdongPage_Tongquan tongQuan;
	private HopdongPage_Kigiahan kiGiahan;

	/**
	 * Create the panel.
	 */
	public HopdongPage() {
		
		init();
	}

	public void init() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(5, 5, 985, 30);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tổng quan");
		
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 64, 128));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 100, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kí / Gia hạn");
		
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(100, 0, 130, 30);
		panel.add(lblNewLabel_1);
		
		
		
		
		
		
		tongQuan = new HopdongPage_Tongquan();
		kiGiahan = new HopdongPage_Kigiahan();
	
		JPanel panel_1 = new JPanel(); // chua may cai kia
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(5, 40, 985, 610);
		add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		panel_1.add(tongQuan, 0);
		panel_1.add(kiGiahan, 1);

		
		
		
		lblNewLabel.addMouseListener(new MouseAdapter() { // tong quan
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(0);
			}
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setBackground(new Color(204, 0, 204));
            }
            public void mouseExited(MouseEvent e) {
            	lblNewLabel.setBackground(new Color(0, 64, 128));
            }
		});
		lblNewLabel_1.addMouseListener(new MouseAdapter() { // ki / gia han
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(1);
			}
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setBackground(new Color(204, 0, 204));
            }
            public void mouseExited(MouseEvent e) {
            	lblNewLabel_1.setBackground(new Color(0, 128, 128));
            }
		});
		

		showPage(0);
	}

	public void showPage(int index) {
		if (index == 0) {
			tongQuan.setVisible(true);
			kiGiahan.setVisible(false);
			
		}
		if (index == 1) {
			tongQuan.setVisible(false);
			kiGiahan.setVisible(true);
			
		}
		
		
	}


}
