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

//		setUndecorated(true);
		
		mainContentPanel = new JPanel(); 
		mainContentPanel.setBorder(new LineBorder(Color.PINK,1));
		mainContentPanel.setLayout(new BorderLayout());
		setContentPane(mainContentPanel);
		
		
		/*Giải thích sơ qua thì mainContentPanel là trùm 
		rồi tới 2 thằng là phần menubar + phần chức năng chính và phần head */
		
		
		
		
		//header
		head = new appHeader();
		head.setPreferredSize(new Dimension(1190, 42));
		mainContentPanel.add(head, BorderLayout.NORTH);
		
		
		
		
		// menubar + chức năng chính
		
		JPanel fncPanel = new JPanel();
		fncPanel.setLayout(new BorderLayout());
		fncPanel.setPreferredSize(new Dimension(1100,760));
		mainContentPanel.add(fncPanel, BorderLayout.CENTER);
		



		nav = new menuNavigation(this);
		nav.setPreferredSize(new Dimension(200,700));
		fncPanel.add(nav, BorderLayout.WEST);
		
		content = new appContent();
		content.setPreferredSize(new Dimension(1000, 650));
		fncPanel.add(content, BorderLayout.CENTER);


	}

	public appContent getAppContent() {
		return content;

	}

}
