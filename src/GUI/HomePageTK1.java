package GUI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

import GUI.BarChart.Chart;
import GUI.BarChart.ModelChart;

import DTO.THONGKENAMNUTUNGNAM;

import DAO.ThongKeDAO;

public class HomePageTK1 extends JPanel {

	private static final long serialVersionUID = 1L;
	Chart chart;
	JPanel panelChart;

	ArrayList<THONGKENAMNUTUNGNAM> data = ThongKeDAO.getInstance().thongKeSoNamGanDay();
	/**
	 * Create the panel.
	 */
	public HomePageTK1() {
		
		init();
		
	}
	// Số nhân viên nam và nữ 3 năm gần đây 
	
	public void init() {
		setLayout(null);
		setBackground(new Color(255, 255, 255));
		setBounds(5, 5, 460, 275);
		
		panelChart = new JPanel();
		panelChart.setBounds(0, 23, 460, 252);
		BoxLayout boxly = new BoxLayout(panelChart, BoxLayout.Y_AXIS);
		panelChart.setLayout(boxly);
		loadChart();
		
		
		
		
		this.add(panelChart);
		
		JLabel lblNewLabel = new JLabel("Số lượng nhân viên qua các năm");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel.setBounds(5, 5, 186, 13);
		add(lblNewLabel);
		
	}


	public void loadChart() {
		panelChart.removeAll();
		chart = new Chart();
		chart.addLegend("Nam", new Color(0, 0, 204));
		chart.addLegend("Nữ", new Color(255, 0, 127));
		for (THONGKENAMNUTUNGNAM thongKe : data) {
			chart.addData(new ModelChart("Năm " + thongKe.getThoigian(), new double[] {thongKe.getNam(), thongKe.getNu()}));
		}
		chart.repaint();
        chart.validate();
		panelChart.add(chart);
		panelChart.repaint();
		panelChart.validate();
	}
}
