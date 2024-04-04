package run;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import GUI.appContent;
import GUI.appHeader;
import GUI.menuNavigation;

public class appFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainContentPanel; //Cái này bao trùm toàn bộ nội dung của cái frame app này
	private appHeader head; //panel head
	private menuNavigation nav;
	private appContent content;
	
	public appFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setLocationRelativeTo(null);

		setUndecorated(true);
		
		mainContentPanel = new JPanel(); 
		mainContentPanel.setBorder(new LineBorder(Color.GRAY,2));
		mainContentPanel.setLayout(new BorderLayout());
		setContentPane(mainContentPanel);
		
		//header
		head = new appHeader();
		head.setPreferredSize(new Dimension(1200, 40));
		mainContentPanel.add(head, BorderLayout.NORTH);
		
		// menubar + chức năng chính
		JPanel fncPanel = new JPanel();
		fncPanel.setLayout(new BorderLayout());
		fncPanel.setPreferredSize(new Dimension(1200,660));
		mainContentPanel.add(fncPanel, BorderLayout.CENTER);

		nav = new menuNavigation(this);
//		nav.setBorder(new LineBorder(Color.PINK,1));
		nav.setPreferredSize(new Dimension(200,660));
		fncPanel.add(nav, BorderLayout.WEST);
		
		content = new appContent();
		content.setPreferredSize(new Dimension(1000, 660));
		fncPanel.add(content, BorderLayout.CENTER);
	}

	public appContent getAppContent() {
		return content;
	}
}
