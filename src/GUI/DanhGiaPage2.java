package GUI;

import java.awt.EventQueue;

import javax.naming.InitialContext;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import BUS.DANHGIA_BUS;
import DAO.DanhGiaDAO;
// import DAO.access_BANGDANHGIA;
import DTO.DANHGIA;
// import DTO.SUPPORT;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DanhGiaPage2 extends JPanel {

	private JTable objectTable= new JTable();
	private JScrollPane scrollPane1;
	private JButton btnLuu;
	private JLabel titleDanhGia; 
	ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
	private JLabel lbdiem;
	private JPanel panel;
	private  JLabel maDanhGia;
	private JLabel hoTen;
	private JLabel maNhanVien;
	private  JLabel chucVu;
	private  JLabel maPhongBan;
	private JScrollBar verticalScrollBar;
	//private JTextField TuyenDung_Find;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	private String chuoiLuaChon;
	private JButton btnBack;
	private JTextField tfNgayDanhGia;
	
	DanhGiaDAO danhGia_Dao=new DanhGiaDAO();
	DANHGIA_BUS danhgia_BUS=new DANHGIA_BUS();
	String[] columnNames= {"STT","Nhân Viên"};
	Object[][] data=danhgia_BUS.renderDSNhanVienCanDG();
	DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
	
//	public Object[][] getData() {
//		return data;
//	}

	public DanhGiaPage2() {
	    init();
	}
	
	public void setData(Object[][] data) {
        // This method sets new data for the table
        this.data = data;
        tableModel.setDataVector(data, columnNames);
    }
	
	
	public JTextField getTfNgayDanhGia() {
		return tfNgayDanhGia;
	}
	

	public void setTfNgayDanhGia(JTextField tfNgayDanhGia) {
		this.tfNgayDanhGia = tfNgayDanhGia;
	}
	
	

	public JLabel getLbdiem() {
		return lbdiem;
	}

	public void setLbdiem(JLabel lbdiem) {
		this.lbdiem = lbdiem;
	}

	
	
	public JLabel getHoTen() {
		return hoTen;
	}

	public void setHoTen(JLabel hoTen) {
		this.hoTen = hoTen;
	}

	public void init() {
		this.setLayout(null);
	      
        JPanel panelNhanVien = new JPanel();
        panelNhanVien.setBounds(10,10,311,600);
        panelNhanVien.setBackground(Color.white);
        panelNhanVien.setLayout(null);
        this.add(panelNhanVien);
        
        
		
        //Phiếu đánh giá
        JPanel panelDanhGia = new JPanel();
        panelDanhGia.setBackground(Color.white);
        panelDanhGia.setBounds(331, 10, 665, 606);
        panelDanhGia.setLayout(null);
        this.add(panelDanhGia);
        
        titleDanhGia = new JLabel("BẢNG ĐÁNH GIÁ NHÂN VIÊN");
        titleDanhGia.setForeground(new Color(0,0,0,200));
        titleDanhGia.setFont(new Font("Arial",1,15));
        titleDanhGia.setBounds(260,20,220,30);
        panelDanhGia.add(titleDanhGia);
        
        
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBar(new myScrollBar());
		scrollPane.setBounds(10, 60, panelDanhGia.getWidth()-20, panelDanhGia.getHeight()-50);
		panelDanhGia.add(scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10); // thiết lập tốc độ cuộn
		scrollPane.getVerticalScrollBar().setBlockIncrement(50); // thiết lập tốc độ cuộn
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        
        panel= new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(641,1300));
		//PhieuDanhGia_title
		title(panel);
		//jradio button
		jradio(panel);
		// Số lượng JRadioButton trong mỗi nhóm ButtonGroup
		int groupSize = 4;
		// Duyệt qua danh sách các JRadioButton
		ButtonGroup currentGroup = null;
		for (int i = 0; i < radioButtons.size(); i++) {
			radioButtons.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JRadioButton rd = (JRadioButton)e.getSource();
					lbdiem.setText(calculateScore()+"");
				}
			});
		    // Nếu đây là JRadioButton đầu tiên hoặc số lượng JRadioButton đã thêm vào nhóm hiện tại đạt giới hạn
		    if (i == 0 || i % groupSize == 0) {
		        // Tạo một nhóm ButtonGroup mới
		        currentGroup = new ButtonGroup();
		    }
		    // Thêm JRadioButton hiện tại vào nhóm ButtonGroup hiện tại
		    currentGroup.add(radioButtons.get(i));
		}
		
		
		
		
		//Table
		btnBack= new JButton("Quay lại");
		Image imgBack=new ImageIcon(this.getClass().getResource("/assets/appIcon/icons8-back-50.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnBack.setBounds(5,5,120,30);
		btnBack.setBorderPainted(false);
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("arial",0,14));
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appContent parentForm=(appContent) getParent();
				parentForm.displayContent(6);
				
			}
		});
		panelNhanVien.add(btnBack);
		
		scrollPane1 = new JScrollPane();
        scrollPane1.setVerticalScrollBar(new myScrollBar());
        scrollPane1.setBounds(10,90,300,485);
        panelNhanVien.add(scrollPane1);
        scrollPane1.setBorder(new EmptyBorder(0,0,0,0));
		
		objectTable.setModel(tableModel);
		objectTable.setRowHeight(30);
		objectTable.setFont(new Font("Arial", Font.PLAIN, 12));
		
		objectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (objectTable.getRowCount() > 0) {
			objectTable.setRowSelectionInterval(0, 0);
		}
		
		objectTable.getColumnModel().getColumn(0).setPreferredWidth(30);  // stt
		objectTable.getColumnModel().getColumn(1).setPreferredWidth(150);  // ten
		
		scrollPane1.setViewportView(objectTable);
		
		JLabel lblblb = new JLabel("Danh sách nhân viên");
		lblblb.setBounds(15,50,200,30);
		lblblb.setFont(new Font("Arial",1,13));
		lblblb.setForeground(new Color(0,0,0,160));
		panelNhanVien.add(lblblb);
		//formatSizeColumn();
		this.setVisible(false);
	}
	
	public int calculateScore() {
		int diem = 0;
		for(int i=0;i<radioButtons.size();i++) {
			if(radioButtons.get(i).isSelected()) {
				diem += (i%4) + 1;
			}
		}
		return diem;
	}
	
	public void setScorePlus(String Name) {
		char lastChar = Name.substring(Name.length() - 1).charAt(0);
    	double score = Double.valueOf(lastChar+"");
    	lbdiem.setText(Double.parseDouble(lbdiem.getText())+score+"");
		
	}
	public void setScoreMinus(String Name) {
		char lastChar = Name.substring(Name.length() - 1).charAt(0);
    	double score = Double.valueOf(lastChar+"");
    	lbdiem.setText(Double.parseDouble(lbdiem.getText())-score+"");
	}
	
	
	public void title(JPanel panel) {
		Color textColor = Color.decode("#2980b9");
		Font font = new Font("Arial", Font.BOLD, 14);
		maDanhGia = new JLabel("Nhân viên:");
		maDanhGia.setFont(new Font("Arial", 1, 14));
		maDanhGia.setBounds(10, 20, 197, 31);
		maDanhGia.setForeground(new Color(0,0,0,160));
		panel.add(maDanhGia);
		
		hoTen = new JLabel("");
		hoTen.setFont(new Font("Arial", 1, 14));
		hoTen.setBounds(100, 20, 250, 31);
		hoTen.setForeground(new Color(0,0,0,160));
		panel.add(hoTen);
		
		JLabel ngayDanhGia = new JLabel("Ngày đánh giá:");
		ngayDanhGia.setFont(new Font("Arial", Font.BOLD, 14));
		ngayDanhGia.setBounds(400, 20, 200, 31);
		ngayDanhGia.setForeground(new Color(0,0,0,160));
		panel.add(ngayDanhGia);
		
		
		tfNgayDanhGia = new JTextField();
		tfNgayDanhGia.setBounds(530,23,100,24);
		tfNgayDanhGia.setFont(new Font("Arial",1,12));
		tfNgayDanhGia.setBackground(Color.white);
		panel.add(tfNgayDanhGia);
		
	
		
		
		JLabel lblSst = new JLabel("STT");
		lblSst.setForeground(textColor);
		lblSst.setFont(font);
		lblSst.setBounds(10, 84, 40, 31);
		panel.add(lblSst);
		
		JLabel lblNiDungnh = new JLabel("Nội dung đánh giá");
		lblNiDungnh.setForeground(textColor);
		lblNiDungnh.setFont(font);
		lblNiDungnh.setBounds(56, 84, 147, 31);
		panel.add(lblNiDungnh);
		
		JLabel lblKm = new JLabel("Xuất sắc");
		lblKm.setForeground(textColor);
		lblKm.setFont(new Font("Arial", Font.BOLD, 14));
		lblKm.setHorizontalAlignment(SwingConstants.LEFT);
		lblKm.setBounds(554, 84, 76, 31);
		panel.add(lblKm);
		
		JLabel lblKmim = new JLabel("Kém");
		lblKmim.setForeground(textColor);
		lblKmim.setFont(new Font("Arial", Font.BOLD, 14));
		lblKmim.setHorizontalAlignment(SwingConstants.LEFT);
		lblKmim.setBounds(350, 84, 36, 31);
		panel.add(lblKmim);
		
		JLabel lblKm_1_1 = new JLabel("Khá");
		lblKm_1_1.setForeground(textColor);
		lblKm_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblKm_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblKm_1_1.setBounds(490, 84, 36, 31);
		panel.add(lblKm_1_1);
		
		JLabel lblKm_1_2 = new JLabel("TB");
		lblKm_1_2.setForeground(textColor);
		lblKm_1_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblKm_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblKm_1_2.setBounds(420, 84, 30, 31);
		panel.add(lblKm_1_2);
		
		JLabel lblSst_1 = new JLabel("1");
		lblSst_1.setForeground(textColor);
		lblSst_1.setFont(font);
		lblSst_1.setBounds(20, 120, 30, 31);
		panel.add(lblSst_1);
		
		JLabel a_1 = new JLabel("Tuân thủ nội quy");
		a_1.setBackground(new Color(0, 0, 0));
		a_1.setFont(font);
		a_1.setBounds(56, 120, 147, 31);
//		a_1.setForeground(new Color(0, 128, 255));
		a_1.setForeground(textColor);
		
		panel.add(a_1);
		
		JLabel a_1_1 = new JLabel("Tuân thủ nội quy lao động của công ty");
		a_1_1.setForeground(new Color(128, 128, 128));
		a_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		a_1_1.setBounds(56, 150, 237, 31);
		panel.add(a_1_1);

		JLabel a_1_2 = new JLabel("Tuân thủ quy chế - quy định làm việc");
		a_1_2.setForeground(new Color(128, 128, 128));
		a_1_2.setFont(new Font("Arial", Font.PLAIN, 13));
		a_1_2.setBounds(56, 175, 237, 31);
		panel.add(a_1_2);
		
		JLabel lblTcPhong = new JLabel("Tác phong");
		lblTcPhong.setForeground(new Color(0, 128, 255));
		lblTcPhong.setForeground(textColor);
		
		lblTcPhong.setFont(font);
		lblTcPhong.setBounds(56, 205, 147, 31);
		panel.add(lblTcPhong);
		JLabel lblTunThNi_1_1_1 = new JLabel("<html>Ăn mặc gọn gàng, sạch sẽ, đúng đồng phục quy</br> định</html>");
		lblTunThNi_1_1_1.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1.setBounds(56, 235, 260, 31);
		panel.add(lblTunThNi_1_1_1);
		
		JLabel lblTunThNi_1_1_1_1 = new JLabel("Giữ gìn vệ sinh chung và vệ sinh nơi làm việc");
		lblTunThNi_1_1_1_1.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1.setBounds(56, 270, 317, 31);
		panel.add(lblTunThNi_1_1_1_1);
		
		JLabel lblTunThNi_1_1_1_1_1 = new JLabel("Nhanh nhẹn, linh hoạt");
		lblTunThNi_1_1_1_1_1.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_1.setBounds(56, 295, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_1);
		
		JLabel lblTunThNi_1_1_1_1_3 = new JLabel("Với cấp trên, đồng nghiệp và khách hàng");
		lblTunThNi_1_1_1_1_3.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_3.setBounds(56, 355, 306, 31);
		panel.add(lblTunThNi_1_1_1_1_3);
		
		JLabel lblTunThNi_1_1_1_1_4 = new JLabel("<html>Giải quyết yêu cầu của khách hàng: nhanh chóng,</br> kịp thời</html>");
		lblTunThNi_1_1_1_1_4.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_4.setBounds(56, 385, 276, 31);
		panel.add(lblTunThNi_1_1_1_1_4);
		
		JLabel lblTunThNi_1_1_1_1_5 = new JLabel("<html>Thái độ chăm sóc khách hàng: cẩn thận, chu đáo,</br> thỏa mãn nhu cầu của khách hàng</html>");
		lblTunThNi_1_1_1_1_5.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_5.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_5.setBounds(56, 410, 258, 57);
		panel.add(lblTunThNi_1_1_1_1_5);
		
		JLabel lblTunThNi_1_1_1_1_6 = new JLabel("Tinh thần hợp tác trong công việc");
		lblTunThNi_1_1_1_1_6.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6.setBounds(56, 490, 249, 31);
		panel.add(lblTunThNi_1_1_1_1_6);
		
		JLabel lblQuanHCng = new JLabel("Quan hệ công việc");
		lblQuanHCng.setFont(font);
		lblQuanHCng.setBounds(56, 325, 147, 31);
		lblQuanHCng.setForeground(textColor);
		panel.add(lblQuanHCng);
		
		JLabel lblTrongCngVic = new JLabel("Trong công việc");
		lblTrongCngVic.setFont(font);
		lblTrongCngVic.setForeground(textColor);
		lblTrongCngVic.setBounds(56, 460, 147, 31);
		panel.add(lblTrongCngVic);
		
		JLabel lblTunThNi_1_1_1_1_6_1 = new JLabel("Thao tác thực hiện công việc");
		lblTunThNi_1_1_1_1_6_1.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_1.setBounds(56, 515, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_1);
		
		JLabel lblTunThNi_1_1_1_1_6_2 = new JLabel("Chất lượng, số lượng công việc hoàn thành");
		lblTunThNi_1_1_1_1_6_2.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_2.setBounds(56, 540, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_2);
		
		JLabel lblTunThNi_1_1_1_1_6_3 = new JLabel("Mức độ hiểu biết về công việc được giao");
		lblTunThNi_1_1_1_1_6_3.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_3.setBounds(56, 567, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_3);
		
		JLabel lblTunThNi_1_1_1_1_6_4 = new JLabel("Khả năng tiếp thu công việc");
		lblTunThNi_1_1_1_1_6_4.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_4.setBounds(56, 590, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_4);
		
		JLabel lblTunThNi_1_1_1_1_6_5 = new JLabel("Hiểu rõ các nghiệp vụ của công việc");
		lblTunThNi_1_1_1_1_6_5.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_5.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_5.setBounds(56, 615, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_5);
		
		JLabel lblTunThNi_1_1_1_1_6_6 = new JLabel("Kiến thức chuyên môn phù hợp với công việc");
		lblTunThNi_1_1_1_1_6_6.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_6.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_6.setBounds(56, 640, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_6);
		
		JLabel lblTunThNi_1_1_1_1_6_7 = new JLabel("Mức độ tin cậy");
		lblTunThNi_1_1_1_1_6_7.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_7.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_7.setBounds(56, 665, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_7);
		
		JLabel lblTunThNi_1_1_1_1_6_8 = new JLabel("Tính kỷ luật");
		lblTunThNi_1_1_1_1_6_8.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_8.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_8.setBounds(56, 690, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_8);
		
		JLabel lblTunThNi_1_1_1_1_6_9 = new JLabel("<html>Khả năng làm việc độc lập và sự chủ động </br>trong công việc</html>");
		lblTunThNi_1_1_1_1_6_9.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_9.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_9.setBounds(56, 720, 262, 31);
		panel.add(lblTunThNi_1_1_1_1_6_9);
		
		JLabel lblTunThNi_1_1_1_1_6_10 = new JLabel("Sự sáng tạo trong công việc");
		lblTunThNi_1_1_1_1_6_10.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_10.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_10.setBounds(56, 755, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_10);
		
		JLabel lblTunThNi_1_1_1_1_6_11 = new JLabel("Hiểu biết về sản phẩm dịch vụ của công ty");
		lblTunThNi_1_1_1_1_6_11.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_11.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_11.setBounds(56, 780, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_11);
		
		JLabel lblTunThNi_1_1_1_1_6_12 = new JLabel("Tinh thần học hỏi và cầu tiến");
		lblTunThNi_1_1_1_1_6_12.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_12.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_12.setBounds(56, 805, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_12);
		
		JLabel lblTunThNi_1_1_1_1_6_13 = new JLabel("Chấp hành mệnh lệnh của người quản lý");
		lblTunThNi_1_1_1_1_6_13.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_13.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_13.setBounds(56, 830, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_13);
		
		JLabel lblTunThNi_1_1_1_1_6_15 = new JLabel("Kỹ năng giao tiếp");
		lblTunThNi_1_1_1_1_6_15.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_15.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_15.setBounds(56, 880, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_15);
		
		JLabel lblTunThNi_1_1_1_1_6_16 = new JLabel("Kỹ năng làm việc nhóm");
		lblTunThNi_1_1_1_1_6_16.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_16.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_16.setBounds(56, 905, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_16);																																													
																																															
		JLabel lblSst_1_1 = new JLabel("2");
		lblSst_1_1.setForeground(textColor);
		lblSst_1_1.setFont(font);
		lblSst_1_1.setBounds(20, 205, 30, 31);
		panel.add(lblSst_1_1);
		
		JLabel lblSst_1_1_1 = new JLabel("3");
		lblSst_1_1_1.setForeground(textColor);
		lblSst_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblSst_1_1_1.setBounds(20, 325, 30, 31);
		panel.add(lblSst_1_1_1);
		
		JLabel lblKNng = new JLabel("Kỹ năng");
		lblKNng.setFont(font);
		lblKNng.setBounds(56, 855, 147, 31);
		lblKNng.setForeground(textColor);
		panel.add(lblKNng);
		
		JLabel lblSst_1_1_1_1 = new JLabel("4");
		lblSst_1_1_1_1.setForeground(textColor);
		lblSst_1_1_1_1.setFont(font);
		lblSst_1_1_1_1.setBounds(20, 460, 30, 31);
		panel.add(lblSst_1_1_1_1);
		
		JLabel lblTunThNi_1_1_1_1_6_16_1 = new JLabel("<html>Thao tác thực hiện các kỹ năng mềm: giao tiếp,  đàm phán, thuyết phục,…</html>");
		lblTunThNi_1_1_1_1_6_16_1.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_16_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_16_1.setBounds(56, 930, 269, 31);
		panel.add(lblTunThNi_1_1_1_1_6_16_1);
		
		JLabel lblTunThNi_1_1_1_1_6_16_2 = new JLabel("Kỹ năng giải quyết vấn đề, tình huống phát sinh");
		lblTunThNi_1_1_1_1_6_16_2.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_16_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_16_2.setBounds(56, 960, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_16_2);
		
		JLabel lblTunThNi_1_1_1_1_6_16_3 = new JLabel("Kỹ năng lập kế hoạch, quản lý công việc");
		lblTunThNi_1_1_1_1_6_16_3.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_16_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_16_3.setBounds(56, 985, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_16_3);
		
		JLabel lblTunThNi_1_1_1_1_6_16_4 = new JLabel("Kỹ năng thích ứng với công việc/áp lực công việc");
		lblTunThNi_1_1_1_1_6_16_4.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_16_4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_16_4.setBounds(58, 1010, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_16_4);
		
		JLabel lblTunThNi_1_1_1_1_6_16_5 = new JLabel("\tSử dụng thành thạo các máy móc thiết bị");
		lblTunThNi_1_1_1_1_6_16_5.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_16_5.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_16_5.setBounds(56, 1070, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_16_5);
		
		JLabel lblTunThNi_1_1_1_1_6_16_6 = new JLabel("Có tinh thần sử dụng tiết kiệm tài sản của công ty");
		lblTunThNi_1_1_1_1_6_16_6.setForeground(new Color(128, 128, 128));
		lblTunThNi_1_1_1_1_6_16_6.setFont(new Font("Arial", Font.PLAIN, 13));
		lblTunThNi_1_1_1_1_6_16_6.setBounds(56, 1095, 317, 31);
		panel.add(lblTunThNi_1_1_1_1_6_16_6);
		
		JLabel lblSDngTrang = new JLabel("Sử dụng trang thiết bị");
		lblSDngTrang.setFont(font);
		lblSDngTrang.setBounds(58, 1040, 221, 31);
		lblSDngTrang.setForeground(textColor);
		panel.add(lblSDngTrang);
		
		JLabel lblSst_1_1_1_1_1 = new JLabel("5");
		lblSst_1_1_1_1_1.setForeground(textColor);
		lblSst_1_1_1_1_1.setFont(font);
		lblSst_1_1_1_1_1.setBounds(20, 855, 30, 31);
		panel.add(lblSst_1_1_1_1_1);
		
		JLabel lblSst_1_1_1_1_2 = new JLabel("6");
		lblSst_1_1_1_1_2.setForeground(textColor);
		lblSst_1_1_1_1_2.setFont(font);
		lblSst_1_1_1_1_2.setBounds(20, 1040, 30, 31);
		panel.add(lblSst_1_1_1_1_2);
		
		JLabel lblTngim = new JLabel("TỔNG ĐIỂM");
		lblTngim.setForeground(new Color(255, 0, 0));
		lblTngim.setFont(new Font("Arial", Font.BOLD, 14));
		lblTngim.setBounds(58, 1180, 221, 31);
		panel.add(lblTngim);
		lbdiem = new JLabel("0");
		lbdiem.setForeground(Color.red);
		lbdiem.setFont(new Font("Arial", Font.BOLD, 18));
		lbdiem.setBounds(565, 1180, 56, 31);
		panel.add(lbdiem);
		
		JLabel lblim = new JLabel("(1 Điểm)");
		lblim.setHorizontalAlignment(SwingConstants.LEFT);
		lblim.setForeground(textColor);
		lblim.setFont(new Font("Arial", Font.BOLD, 13));
		lblim.setBounds(340, 102, 83, 31);
		panel.add(lblim);
		
		JLabel lblim_3 = new JLabel("(2 Điểm)");
		lblim_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblim_3.setForeground(textColor);
		lblim_3.setFont(new Font("Arial", Font.BOLD, 13));
		lblim_3.setBounds(410, 102, 83, 31);
		panel.add(lblim_3);
		
		JLabel lblim_4 = new JLabel("(3 Điểm)");
		lblim_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblim_4.setForeground(textColor);
		lblim_4.setFont(new Font("Arial", Font.BOLD, 13));
		lblim_4.setBounds(480, 102, 83, 31);
		panel.add(lblim_4);
		
		JLabel lblim_1 = new JLabel("(4 Điểm)");
		lblim_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblim_1.setForeground(textColor);
		lblim_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblim_1.setBounds(560, 102, 83, 31);
		panel.add(lblim_1);
				
		btnLuu = new JButton();
		btnLuu.setText("Lưu");
		btnLuu.setFocusable(false);
		btnLuu.setFont(new Font("Arial", 1, 14));
		btnLuu.setBounds(521, 1240, 100, 34);
		panel.add(btnLuu);
		
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
					int index = objectTable.getSelectedRow();
					if(index<0) {
						JOptionPane.showMessageDialog(null,"Vui lòng chọn nhân viên","Thông báo",JOptionPane.WARNING_MESSAGE);
						return;
					}
					String str2 = getSelectedBangDanhGia();
					if(str2.length()<59) {
						JOptionPane.showMessageDialog(null,"Vui lòng đánh giá đủ các tiêu chí","Thông báo",JOptionPane.WARNING_MESSAGE);
						return;
					}
					String maNhanVien = tableModel.getValueAt(index, 1).toString().split(" - ")[0];
					String maNguoiDanhGia = "HIHI";
					LocalDate ngayDanhGia = LocalDate.now();
					String ngayDanhGiaString = ngayDanhGia.toString(); // Chuyển đổi thành chuỗi
					tfNgayDanhGia.setText(ngayDanhGiaString);			
					String maDanhGia = "DG"+maNhanVien+localDatetoStringDanhGia(ngayDanhGia) +maNguoiDanhGia;
					int diem = Integer.valueOf(getLbdiem().getText());
					String xepLoai = "";
					if(diem>=110) {
						xepLoai = "Xuất sắc";
					}else if(diem>= 90) {
						xepLoai = "Giỏi";
					}else if(diem>=70) {
						xepLoai = "Khá";
					}else if(diem>=50) {
						xepLoai = "Trung bình";
					}else {
						xepLoai = "Điểm";
					}
					String loaiDanhGia = "";
					if(maNhanVien.equals(maNguoiDanhGia)) {
						loaiDanhGia = "Tự đánh giá";
					}else {
						loaiDanhGia = "Được đánh giá";
					}
					DANHGIA danhGia=new DANHGIA(maDanhGia, maNhanVien, maNguoiDanhGia,ngayDanhGia, diem, loaiDanhGia, maDanhGia, loaiDanhGia, loaiDanhGia);
					if (danhGia_Dao.insert(danhGia)!=0) {
						
					}else if(danhGia_Dao.update(danhGia)!=0) {
						
					}
					danhgia_BUS.getList();
					DanhGiaPage danhGiaPage= new DanhGiaPage();
					danhGiaPage.setData(danhgia_BUS.renderAllDanhGiaData());
					JOptionPane.showMessageDialog(null,"Đã lưu vào danh sách đánh giá!","Thông báo ",JOptionPane.OK_OPTION);
				}
			
				
			
		});
		
	}
	
	public static String localDatetoStringDanhGia(LocalDate date) {
		String d,m,y;
		if(date.getDayOfMonth()<9) {
			d = "0"+date.getDayOfMonth();
		}else {
			d = date.getDayOfMonth()+"";
		}
		if(date.getMonthValue()<9) {
			m = "0"+date.getMonthValue();
		}else {
			m = date.getMonthValue()+"";
		}
		if(date.getYear()<9) {
			y = "0"+date.getYear();
		}else {
			y = date.getYear()+"";
		}
		
		return d+m+y;
	}
	
	public void jradio(JPanel panel) {
		JRadioButton r1_1_1 = new JRadioButton("");
		r1_1_1.setBackground(Color.WHITE);
		r1_1_1.setBounds(350, 155, 23, 23);
		panel.add(r1_1_1);
		r1_1_1.setName("0");
				
		JRadioButton r1_1_2 = new JRadioButton("");
		r1_1_2.setBackground(Color.WHITE);
		r1_1_2.setBounds(420, 155, 23, 23);
		panel.add(r1_1_2);
		r1_1_2.setName("1");
		
		JRadioButton r1_1_3 = new JRadioButton("");
		r1_1_3.setBackground(Color.WHITE);
		r1_1_3.setBounds(490, 155, 23, 23);
		panel.add(r1_1_3);
		r1_1_3.setName("2");		
				
		JRadioButton r1_1_4 = new JRadioButton("");
		r1_1_4.setBackground(Color.WHITE);
		r1_1_4.setBounds(560, 155, 23, 23);		
		panel.add(r1_1_4);
		r1_1_4.setName("3");			
						
		JRadioButton r1_2_1 = new JRadioButton("");
		r1_2_1.setBackground(Color.WHITE);
		r1_2_1.setBounds(350, 179, 23, 23);
		panel.add(r1_2_1);
		r1_2_1.setName("0");
		
		JRadioButton r1_2_2 = new JRadioButton("");
		r1_2_2.setBackground(Color.WHITE);
		r1_2_2.setBounds(420, 179, 23, 23);
		panel.add(r1_2_2);
		r1_2_2.setName("1");
									
		JRadioButton r1_2_3 = new JRadioButton("");
		r1_2_3.setBackground(Color.WHITE);
		r1_2_3.setBounds(490, 179, 23, 23);
		panel.add(r1_2_3);
		r1_2_3.setName("2");
		
		JRadioButton r1_2_4 = new JRadioButton("");
		r1_2_4.setBackground(Color.WHITE);
		r1_2_4.setBounds(560, 183, 23, 23);
		panel.add(r1_2_4);
		r1_2_4.setName("3");
		
		JRadioButton r2_1_1 = new JRadioButton("");
		r2_1_1.setBackground(Color.WHITE);
		r2_1_1.setBounds(350, 240, 23, 23);
		panel.add(r2_1_1);
		r2_1_1.setName("0");
		
		JRadioButton r2_1_2 = new JRadioButton("");
		r2_1_2.setBackground(Color.WHITE);
		r2_1_2.setBounds(420, 240, 23, 23);
		panel.add(r2_1_2);
		r2_1_2.setName("1");
		
		JRadioButton r2_1_3 = new JRadioButton("");
		r2_1_3.setBackground(Color.WHITE);
		r2_1_3.setBounds(490, 240, 23, 23);
		panel.add(r2_1_3);
		r2_1_3.setName("2");
		
		JRadioButton r2_1_4 = new JRadioButton("");
		r2_1_4.setBackground(Color.WHITE);
		r2_1_4.setBounds(560, 243, 23, 23);
		panel.add(r2_1_4);
		r2_1_4.setName("3");
		
		JRadioButton r2_2_1 = new JRadioButton("");
		r2_2_1.setBackground(Color.WHITE);
		r2_2_1.setBounds(350, 273, 23, 23);
		panel.add(r2_2_1);
		r2_2_1.setName("0");
		
		JRadioButton r2_2_2 = new JRadioButton("");
		r2_2_2.setBackground(Color.WHITE);
		r2_2_2.setBounds(420, 273, 23, 23);
		panel.add(r2_2_2);
		r2_2_2.setName("1");
		
		JRadioButton r2_2_3 = new JRadioButton("");
		r2_2_3.setBackground(Color.WHITE);
		r2_2_3.setBounds(490, 273, 23, 23);
		panel.add(r2_2_3);
		r2_2_3.setName("2");
		
		JRadioButton r2_2_4 = new JRadioButton("");
		r2_2_4.setBackground(Color.WHITE);
		r2_2_4.setBounds(560, 273, 23, 23);
		panel.add(r2_2_4);
		r2_2_4.setName("3");
		
		JRadioButton r2_3_1 = new JRadioButton("");
		r2_3_1.setBackground(Color.WHITE);
		r2_3_1.setBounds(350, 300, 23, 23);
		panel.add(r2_3_1);
		r2_3_1.setName("0");
		
		JRadioButton r2_3_2 = new JRadioButton("");
		r2_3_2.setBackground(Color.WHITE);
		r2_3_2.setBounds(420, 300, 23, 23);
		panel.add(r2_3_2);
		r2_3_2.setName("1");
		
		JRadioButton r2_3_3 = new JRadioButton("");
		r2_3_3.setBackground(Color.WHITE);
		r2_3_3.setBounds(490, 300, 23, 23);
		panel.add(r2_3_3);
		r2_3_3.setName("2");
		
		JRadioButton r2_3_4 = new JRadioButton("");
		r2_3_4.setBackground(Color.WHITE);
		r2_3_4.setBounds(560, 304, 23, 23);
		panel.add(r2_3_4);
		r2_3_4.setName("3");
		
		JRadioButton r3_1_1 = new JRadioButton("");
		r3_1_1.setBackground(Color.WHITE);
		r3_1_1.setBounds(350, 357, 23, 23);
		panel.add(r3_1_1);
		r3_1_1.setName("0");
		
		JRadioButton r3_1_2 = new JRadioButton("");
		r3_1_2.setBackground(Color.WHITE);
		r3_1_2.setBounds(420, 357, 23, 23);
		panel.add(r3_1_2);
		r3_1_2.setName("1");
		
		JRadioButton r3_1_3 = new JRadioButton("");
		r3_1_3.setBackground(Color.WHITE);
		r3_1_3.setBounds(490, 357, 23, 23);
		panel.add(r3_1_3);
		r3_1_3.setName("2");
		
		JRadioButton r3_1_4 = new JRadioButton("");
		r3_1_4.setBackground(Color.WHITE);
		r3_1_4.setBounds(560, 357, 23, 23);
		panel.add(r3_1_4);
		r3_1_4.setName("3");
		
		JRadioButton r3_2_1 = new JRadioButton("");
		r3_2_1.setBackground(Color.WHITE);
		r3_2_1.setBounds(350, 390, 23, 23);
		panel.add(r3_2_1);
		r3_2_1.setName("0");
		
		JRadioButton r3_2_2 = new JRadioButton("");
		r3_2_2.setBackground(Color.WHITE);
		r3_2_2.setBounds(420, 390, 23, 23);
		panel.add(r3_2_2);
		r3_2_2.setName("1");
		
		JRadioButton r3_2_3 = new JRadioButton("");
		r3_2_3.setBackground(Color.WHITE);
		r3_2_3.setBounds(490, 390, 23, 23);
		panel.add(r3_2_3);
		r3_2_3.setName("2");
		
		JRadioButton r3_2_4 = new JRadioButton("");
		r3_2_4.setBackground(Color.WHITE);
		r3_2_4.setBounds(560, 390, 23, 23);
		panel.add(r3_2_4);
		r3_2_4.setName("3");
		
		JRadioButton r3_3_1 = new JRadioButton("");
		r3_3_1.setBackground(Color.WHITE);
		r3_3_1.setBounds(350, 425, 23, 23);
		panel.add(r3_3_1);
		r3_3_1.setName("0");
		
		JRadioButton r3_3_2 = new JRadioButton("");
		r3_3_2.setBackground(Color.WHITE);
		r3_3_2.setBounds(420, 425, 23, 23);
		panel.add(r3_3_2);
		r3_3_2.setName("1");
		
		JRadioButton r3_3_3 = new JRadioButton("");
		r3_3_3.setBackground(Color.WHITE);
		r3_3_3.setBounds(490, 425, 23, 23);
		panel.add(r3_3_3);
		r3_3_3.setName("2");
		
		JRadioButton r3_3_4 = new JRadioButton("");
		r3_3_4.setBackground(Color.WHITE);
		r3_3_4.setBounds(560, 425, 23, 23);
		panel.add(r3_3_4);
		r3_3_4.setName("3");
		
		JRadioButton r4_1_2 = new JRadioButton("");
		r4_1_2.setBackground(Color.WHITE);
		r4_1_2.setBounds(420, 495, 23, 23);
		panel.add(r4_1_2);
		r4_1_2.setName("1");
		
		JRadioButton r4_1_1 = new JRadioButton("");
		r4_1_1.setBackground(Color.WHITE);
		r4_1_1.setBounds(350, 495, 23, 23);
		panel.add(r4_1_1);
		r4_1_1.setName("0");
		
		JRadioButton r4_1_3 = new JRadioButton("");
		r4_1_3.setBackground(Color.WHITE);
		r4_1_3.setBounds(490, 495, 23, 23);
		panel.add(r4_1_3);
		r4_1_3.setName("2");
		
		JRadioButton r4_1_4 = new JRadioButton("");
		r4_1_4.setBackground(Color.WHITE);
		r4_1_4.setBounds(560, 495, 23, 23);
		panel.add(r4_1_4);
		r4_1_4.setName("3");
		
		JRadioButton r4_2_2 = new JRadioButton("");
		r4_2_2.setBackground(Color.WHITE);
		r4_2_2.setBounds(420, 520, 23, 23);
		panel.add(r4_2_2);
		r4_2_2.setName("1");
		
		JRadioButton r4_2_1 = new JRadioButton("");
		r4_2_1.setBackground(Color.WHITE);
		r4_2_1.setBounds(350, 520, 23, 23);
		panel.add(r4_2_1);
		r4_2_1.setName("0");
		
		JRadioButton r4_2_3 = new JRadioButton("");
		r4_2_3.setBackground(Color.WHITE);
		r4_2_3.setBounds(490, 520, 23, 23);
		panel.add(r4_2_3);
		r4_2_3.setName("2");
														
		JRadioButton r4_2_4 = new JRadioButton("");
		r4_2_4.setBackground(Color.WHITE);
		r4_2_4.setBounds(560, 520, 23, 23);
		r4_2_4.setName("3");
		panel.add(r4_2_4);
		
		JRadioButton r4_3_2 = new JRadioButton("");
		r4_3_2.setBackground(Color.WHITE);
		r4_3_2.setBounds(420, 544, 23, 23);
		r4_3_2.setName("1");
		panel.add(r4_3_2);
		
		JRadioButton r4_3_1 = new JRadioButton("");
		r4_3_1.setBackground(Color.WHITE);
		r4_3_1.setBounds(350, 544, 23, 23);
		panel.add(r4_3_1);
		r4_3_1.setName("0");
		
		JRadioButton r4_3_3 = new JRadioButton("");
		r4_3_3.setBackground(Color.WHITE);
		r4_3_3.setBounds(490, 544, 23, 23);
		panel.add(r4_3_3);
		r4_3_3.setName("2");
		
		JRadioButton r4_3_4 = new JRadioButton("");
		r4_3_4.setBackground(Color.WHITE);
		r4_3_4.setBounds(560, 544, 23, 23);
		panel.add(r4_3_4);
		r4_3_4.setName("3");
																
		JRadioButton r4_4_2 = new JRadioButton("");
		r4_4_2.setBackground(Color.WHITE);
		r4_4_2.setBounds(420, 570, 23, 23);
		panel.add(r4_4_2);
		r4_4_2.setName("1");
		
		JRadioButton r4_4_1 = new JRadioButton("");
		r4_4_1.setBackground(Color.WHITE);
		r4_4_1.setBounds(350, 570, 23, 23);
		panel.add(r4_4_1);
		r4_4_1.setName("0");
		
		JRadioButton r4_4_3 = new JRadioButton("");
		r4_4_3.setBackground(Color.WHITE);
		r4_4_3.setBounds(490, 570, 23, 23);
		panel.add(r4_4_3);
		r4_4_3.setName("2");
		
		JRadioButton r4_4_4 = new JRadioButton("");
		r4_4_4.setBackground(Color.WHITE);
		r4_4_4.setBounds(560, 570, 23, 23);
		panel.add(r4_4_4);
		r4_4_4.setName("3");
		
		JRadioButton r4_5_2 = new JRadioButton("");
		r4_5_2.setBackground(Color.WHITE);
		r4_5_2.setBounds(420, 595, 23, 23);
		panel.add(r4_5_2);
		r4_5_2.setName("1");
		
		JRadioButton r4_5_1 = new JRadioButton("");
		r4_5_1.setBackground(Color.WHITE);
		r4_5_1.setBounds(350, 595, 23, 23);
		panel.add(r4_5_1);
		r4_5_1.setName("0");
		
		JRadioButton r4_5_3 = new JRadioButton("");
		r4_5_3.setBackground(Color.WHITE);
		r4_5_3.setBounds(490, 595, 23, 23);
		panel.add(r4_5_3);
		r4_5_3.setName("2");
																		
		JRadioButton r4_5_4 = new JRadioButton("");
		r4_5_4.setBackground(Color.WHITE);
		r4_5_4.setBounds(560, 595, 23, 23);
		panel.add(r4_5_4);
		r4_5_4.setName("3");
		
		JRadioButton r4_6_2 = new JRadioButton("");
		r4_6_2.setBackground(Color.WHITE);
		r4_6_2.setBounds(420, 620, 23, 23);
		panel.add(r4_6_2);
		r4_6_2.setName("1");
																				
		JRadioButton r4_6_1 = new JRadioButton("");
		r4_6_1.setBackground(Color.WHITE);
		r4_6_1.setBounds(350, 620, 23, 23);
		panel.add(r4_6_1);
		r4_6_1.setName("0");
																						
		JRadioButton r4_6_3 = new JRadioButton("");
		r4_6_3.setBackground(Color.WHITE);
		r4_6_3.setBounds(490, 620, 23, 23);
		panel.add(r4_6_3);
		r4_6_3.setName("2");
		
		JRadioButton r4_6_4 = new JRadioButton("");
		r4_6_4.setBackground(Color.WHITE);
		r4_6_4.setBounds(560, 620, 23, 23);
		panel.add(r4_6_4);
		r4_6_4.setName("3");
		
		JRadioButton r4_7_2 = new JRadioButton("");
		r4_7_2.setBackground(Color.WHITE);
		r4_7_2.setBounds(420, 645, 23, 23);
		panel.add(r4_7_2);
		r4_7_2.setName("1");
		
		JRadioButton r4_7_1 = new JRadioButton("");
		r4_7_1.setBackground(Color.WHITE);
		r4_7_1.setBounds(350, 645, 23, 23);
		panel.add(r4_7_1);
		r4_7_1.setName("0");
		
		JRadioButton r4_7_3 = new JRadioButton("");
		r4_7_3.setBackground(Color.WHITE);
		r4_7_3.setBounds(490, 645, 23, 23);
		panel.add(r4_7_3);
		r4_7_3.setName("2");
		
		JRadioButton r4_7_4 = new JRadioButton("");
		r4_7_4.setBackground(Color.WHITE);
		r4_7_4.setBounds(560, 645, 23, 23);
		panel.add(r4_7_4);
		r4_7_4.setName("3");
		
		JRadioButton r4_8_2 = new JRadioButton("");
		r4_8_2.setBackground(Color.WHITE);
		r4_8_2.setBounds(420, 670, 23, 23);
		panel.add(r4_8_2);
		r4_8_2.setName("1");
		
		JRadioButton r4_8_1 = new JRadioButton("");
		r4_8_1.setBackground(Color.WHITE);
		r4_8_1.setBounds(350, 670, 23, 23);
		panel.add(r4_8_1);
		r4_8_1.setName("0");
		
		JRadioButton r4_8_3 = new JRadioButton("");
		r4_8_3.setBackground(Color.WHITE);
		r4_8_3.setBounds(490, 670, 23, 23);
		panel.add(r4_8_3);
		r4_8_3.setName("2");
		
		JRadioButton r4_8_4 = new JRadioButton("");
		r4_8_4.setBackground(Color.WHITE);
		r4_8_4.setBounds(560, 670, 23, 23);
		panel.add(r4_8_4);
		r4_8_4.setName("3");
		
		JRadioButton r4_9_2 = new JRadioButton("");
		r4_9_2.setBackground(Color.WHITE);
		r4_9_2.setBounds(420, 692, 23, 23);
		panel.add(r4_9_2);
		r4_9_2.setName("1");
		
		JRadioButton r4_9_1 = new JRadioButton("");
		r4_9_1.setBackground(Color.WHITE);
		r4_9_1.setBounds(350, 692, 23, 23);
		panel.add(r4_9_1);
		r4_9_1.setName("0");

		JRadioButton r4_9_3 = new JRadioButton("");
		r4_9_3.setBackground(Color.WHITE);
		r4_9_3.setBounds(490, 692, 23, 23);
		panel.add(r4_9_3);
		r4_9_3.setName("2");
						
		JRadioButton r4_9_4 = new JRadioButton("");
		r4_9_4.setBackground(Color.WHITE);
		r4_9_4.setBounds(560, 692, 23, 23);
		panel.add(r4_9_4);
		r4_9_4.setName("3");
		
		JRadioButton r4_10_2 = new JRadioButton("");
		r4_10_2.setBackground(Color.WHITE);
		r4_10_2.setBounds(420, 722, 23, 23);
		panel.add(r4_10_2);
		r4_10_2.setName("1");
		
		JRadioButton r4_10_1 = new JRadioButton("");
		r4_10_1.setBackground(Color.WHITE);
		r4_10_1.setBounds(350, 722, 23, 23);
		panel.add(r4_10_1);
		r4_10_1.setName("0");
		
		JRadioButton r4_10_3 = new JRadioButton("");
		r4_10_3.setBackground(Color.WHITE);
		r4_10_3.setBounds(490, 722, 23, 23);
		panel.add(r4_10_3);
		r4_10_3.setName("2");
		
		JRadioButton r4_10_4 = new JRadioButton("");
		r4_10_4.setBackground(Color.WHITE);
		r4_10_4.setBounds(560, 722, 23, 23);
		panel.add(r4_10_4);
		r4_10_4.setName("3");
		
		JRadioButton r4_11_1 = new JRadioButton("");
		r4_11_1.setBackground(Color.WHITE);
		r4_11_1.setBounds(350, 756, 23, 23);
		panel.add(r4_11_1);
		r4_11_1.setName("0");
		
		JRadioButton r4_11_2 = new JRadioButton("");
		r4_11_2.setBackground(Color.WHITE);
		r4_11_2.setBounds(420, 756, 23, 23);
		panel.add(r4_11_2);
		r4_11_2.setName("1");
		
		JRadioButton r4_11_3 = new JRadioButton("");
		r4_11_3.setBackground(Color.WHITE);
		r4_11_3.setBounds(490, 756, 23, 23);
		panel.add(r4_11_3);
		r4_11_3.setName("2");
		
		JRadioButton r4_11_4 = new JRadioButton("");
		r4_11_4.setBackground(Color.WHITE);
		r4_11_4.setBounds(560, 756, 23, 23);
		panel.add(r4_11_4);
		r4_11_4.setName("3");
		
		JRadioButton r4_12_1 = new JRadioButton("");
		r4_12_1.setBackground(Color.WHITE);
		r4_12_1.setBounds(350, 782, 23, 23);
		panel.add(r4_12_1);
		r4_12_1.setName("0");
				
						
		JRadioButton r4_12_2 = new JRadioButton("");
		r4_12_2.setBackground(Color.WHITE);
		r4_12_2.setBounds(420, 782, 23, 23);
		panel.add(r4_12_2);
		r4_12_2.setName("1");
						
		JRadioButton r4_12_3 = new JRadioButton("");
		r4_12_3.setBackground(Color.WHITE);
		r4_12_3.setBounds(490, 782, 23, 23);
		panel.add(r4_12_3);
		r4_12_3.setName("2");
						
		JRadioButton r4_12_4 = new JRadioButton("");
		r4_12_4.setBackground(Color.WHITE);
		r4_12_4.setBounds(560, 782, 23, 23);
		panel.add(r4_12_4);
		r4_12_4.setName("3");
						
		JRadioButton r4_13_1 = new JRadioButton("");
		r4_13_1.setBackground(Color.WHITE);
		r4_13_1.setBounds(350, 810, 23, 23);
		panel.add(r4_13_1);
		r4_13_1.setName("0");
						
		JRadioButton r4_13_2 = new JRadioButton("");
		r4_13_2.setBackground(Color.WHITE);
		r4_13_2.setBounds(420, 810, 23, 23);
		panel.add(r4_13_2);
		r4_13_2.setName("1");
						
		JRadioButton r4_13_3 = new JRadioButton("");
		r4_13_3.setBackground(Color.WHITE);
		r4_13_3.setBounds(490, 810, 23, 23);
		panel.add(r4_13_3);
		r4_13_3.setName("2");
						
		JRadioButton r4_13_4 = new JRadioButton("");
		r4_13_4.setBackground(Color.WHITE);
		r4_13_4.setBounds(560, 810, 23, 23);
		panel.add(r4_13_4);
		r4_13_4.setName("3");
							
		JRadioButton r4_14_1 = new JRadioButton("");
		r4_14_1.setBackground(Color.WHITE);
		r4_14_1.setBounds(350, 835, 23, 23);
		panel.add(r4_14_1);
		r4_14_1.setName("0");
		
		JRadioButton r4_14_2 = new JRadioButton("");
		r4_14_2.setBackground(Color.WHITE);
		r4_14_2.setBounds(420, 835, 23, 23);
		panel.add(r4_14_2);
		r4_14_2.setName("1");
		
		JRadioButton r4_14_3 = new JRadioButton("");
		r4_14_3.setBackground(Color.WHITE);
		r4_14_3.setBounds(490, 835, 23, 23);
		panel.add(r4_14_3);
		r4_14_3.setName("2");
		
		JRadioButton r4_14_4 = new JRadioButton("");
		r4_14_4.setBackground(Color.WHITE);
		r4_14_4.setBounds(560, 835, 23, 23);
		panel.add(r4_14_4);
		r4_14_4.setName("3");
		
		JRadioButton r5_1_1 = new JRadioButton("");
		r5_1_1.setBackground(Color.WHITE);
		r5_1_1.setBounds(350, 883, 23, 23);
		panel.add(r5_1_1);
		r5_1_1.setName("0");
		
		JRadioButton r5_1_2 = new JRadioButton("");
		r5_1_2.setBackground(Color.WHITE);
		r5_1_2.setBounds(420, 883, 23, 23);
		panel.add(r5_1_2);
		r5_1_2.setName("1");
		
		JRadioButton r5_1_3 = new JRadioButton("");
		r5_1_3.setBackground(Color.WHITE);
		r5_1_3.setBounds(490, 883, 23, 23);
		panel.add(r5_1_3);
		r5_1_3.setName("2");
		
		JRadioButton r5_1_4 = new JRadioButton("");
		r5_1_4.setBackground(Color.WHITE);
		r5_1_4.setBounds(560, 883, 23, 23);
		panel.add(r5_1_4);
		r5_1_4.setName("3");
		
		JRadioButton r5_2_1 = new JRadioButton("");
		r5_2_1.setBackground(Color.WHITE);
		r5_2_1.setBounds(350, 910, 23, 23);
		panel.add(r5_2_1);
		r5_2_1.setName("0");
							
		JRadioButton r5_2_2 = new JRadioButton("");
		r5_2_2.setBackground(Color.WHITE);
		r5_2_2.setBounds(420, 910, 23, 23);
		panel.add(r5_2_2);
		r5_2_2.setName("1");
									
		JRadioButton r5_2_3 = new JRadioButton("");
		r5_2_3.setBackground(Color.WHITE);
		r5_2_3.setBounds(490, 910, 23, 23);
		panel.add(r5_2_3);
		r5_2_3.setName("2");
		
		JRadioButton r5_2_4 = new JRadioButton("");
		r5_2_4.setBackground(Color.WHITE);
		r5_2_4.setBounds(560, 910, 23, 23);
		panel.add(r5_2_4);
		r5_2_4.setName("3");
		
		JRadioButton r5_3_1 = new JRadioButton("");
		r5_3_1.setBackground(Color.WHITE);
		r5_3_1.setBounds(350, 932, 23, 23);
		panel.add(r5_3_1);
		r5_3_1.setName("0");
		
		JRadioButton r5_3_2 = new JRadioButton("");
		r5_3_2.setBackground(Color.WHITE);
		r5_3_2.setBounds(420, 932, 23, 23);
		panel.add(r5_3_2);
		r5_3_2.setName("1");
		
		JRadioButton r5_3_3 = new JRadioButton("");
		r5_3_3.setBackground(Color.WHITE);
		r5_3_3.setBounds(490, 932, 23, 23);
		panel.add(r5_3_3);
		r5_3_3.setName("2");
		
		JRadioButton r5_3_4 = new JRadioButton("");
		r5_3_4.setBackground(Color.WHITE);
		r5_3_4.setBounds(560, 932, 23, 23);
		panel.add(r5_3_4);
		r5_3_4.setName("3");
		
		JRadioButton r5_4_1 = new JRadioButton("");
		r5_4_1.setBackground(Color.WHITE);
		r5_4_1.setBounds(350, 962, 23, 23);
		panel.add(r5_4_1);
		r5_4_1.setName("0");
		
		JRadioButton r5_4_2 = new JRadioButton("");
		r5_4_2.setBackground(Color.WHITE);
		r5_4_2.setBounds(420, 962, 23, 23);
		panel.add(r5_4_2);
		r5_4_2.setName("1");
		
		JRadioButton r5_4_3 = new JRadioButton("");
		r5_4_3.setBackground(Color.WHITE);
		r5_4_3.setBounds(490, 962, 23, 23);
		panel.add(r5_4_3);
		r5_4_3.setName("2");
		
		JRadioButton r5_4_4 = new JRadioButton("");
		r5_4_4.setBackground(Color.WHITE);
		r5_4_4.setBounds(560, 962, 23, 23);
		panel.add(r5_4_4);
		r5_4_4.setName("3");
		
		JRadioButton r5_5_1 = new JRadioButton("");
		r5_5_1.setBackground(Color.WHITE);
		r5_5_1.setBounds(350, 988, 23, 23);
		panel.add(r5_5_1);
		r5_5_1.setName("0");
		
		JRadioButton r5_5_2 = new JRadioButton("");
		r5_5_2.setBackground(Color.WHITE);
		r5_5_2.setBounds(420, 988, 23, 23);
		panel.add(r5_5_2);
		r5_5_2.setName("1");
		
		JRadioButton r5_5_3 = new JRadioButton("");
		r5_5_3.setBackground(Color.WHITE);
		r5_5_3.setBounds(490, 988, 23, 23);
		panel.add(r5_5_3);
		r5_5_3.setName("2");
		
		JRadioButton r5_5_4 = new JRadioButton("");
		r5_5_4.setBackground(Color.WHITE);
		r5_5_4.setBounds(560, 988, 23, 23);
		panel.add(r5_5_4);
		r5_5_4.setName("3");
		
		JRadioButton r5_6_1 = new JRadioButton("");
		r5_6_1.setBackground(Color.WHITE);
		r5_6_1.setBounds(350, 1014, 23, 23);
		panel.add(r5_6_1);
		r5_6_1.setName("0");
		
		JRadioButton r5_6_2 = new JRadioButton("");
		r5_6_2.setBackground(Color.WHITE);
		r5_6_2.setBounds(420, 1014, 23, 23);
		panel.add(r5_6_2);
		r5_6_2.setName("1");
		
		JRadioButton r5_6_3 = new JRadioButton("");
		r5_6_3.setBackground(Color.WHITE);
		r5_6_3.setBounds(490, 1014, 23, 23);
		panel.add(r5_6_3);
		r5_6_3.setName("2");
		
		JRadioButton r5_6_4 = new JRadioButton("");
		r5_6_4.setBackground(Color.WHITE);
		r5_6_4.setBounds(560, 1014, 23, 23);
		panel.add(r5_6_4);
		r5_6_4.setName("3");
		
		JRadioButton r6_1_1 = new JRadioButton("");
		r6_1_1.setBackground(Color.WHITE);
		r6_1_1.setBounds(350, 1072, 23, 23);
		panel.add(r6_1_1);
		r6_1_1.setName("0");
		
		JRadioButton r6_1_2 = new JRadioButton("");
		r6_1_2.setBackground(Color.WHITE);
		r6_1_2.setBounds(420, 1072, 23, 23);
		panel.add(r6_1_2);
		r6_1_2.setName("1");
		
		JRadioButton r6_1_3 = new JRadioButton("");
		r6_1_3.setBackground(Color.WHITE);
		r6_1_3.setBounds(490, 1072, 23, 23);
		panel.add(r6_1_3);
		r6_1_3.setName("2");
		
		JRadioButton r6_1_4 = new JRadioButton("");
		r6_1_4.setBackground(Color.WHITE);
		r6_1_4.setBounds(560, 1072, 23, 23);
		panel.add(r6_1_4);
		r6_1_4.setName("3");
		
		JRadioButton r6_2_1 = new JRadioButton("");
		r6_2_1.setBackground(Color.WHITE);
		r6_2_1.setBounds(350, 1098, 23, 23);
		panel.add(r6_2_1);
		r6_2_1.setName("0");
		
		JRadioButton r6_2_2 = new JRadioButton("");
		r6_2_2.setBackground(Color.WHITE);
		r6_2_2.setBounds(420, 1098, 23, 23);
		panel.add(r6_2_2);
		r6_2_2.setName("1");
		
		JRadioButton r6_2_3 = new JRadioButton("");
		r6_2_3.setBackground(Color.WHITE);
		r6_2_3.setBounds(490, 1098, 23, 23);
		panel.add(r6_2_3);
		r6_2_3.setName("2");
		
		JRadioButton r6_2_4 = new JRadioButton("");
		r6_2_4.setBackground(Color.WHITE);
		r6_2_4.setBounds(560, 1098, 23, 23);
		panel.add(r6_2_4);
		r6_2_4.setName("3");
		
		radioButtons.add(r1_1_1);
		radioButtons.add(r1_1_2);
		radioButtons.add(r1_1_3);
		radioButtons.add(r1_1_4);
		
		radioButtons.add(r1_2_1);
		radioButtons.add(r1_2_2);
		radioButtons.add(r1_2_3);
		radioButtons.add(r1_2_4);
		
		radioButtons.add(r2_1_1);
		radioButtons.add(r2_1_2);
		radioButtons.add(r2_1_3);
		radioButtons.add(r2_1_4);
		
		radioButtons.add(r2_2_1);
		radioButtons.add(r2_2_2);
		radioButtons.add(r2_2_3);
		radioButtons.add(r2_2_4);
		
		radioButtons.add(r2_3_1);
		radioButtons.add(r2_3_2);
		radioButtons.add(r2_3_3);
		radioButtons.add(r2_3_4);
		
		radioButtons.add(r3_1_1);
		radioButtons.add(r3_1_2);
		radioButtons.add(r3_1_3);
		radioButtons.add(r3_1_4);
		
		radioButtons.add(r3_2_1);
		radioButtons.add(r3_2_2);
		radioButtons.add(r3_2_3);
		radioButtons.add(r3_2_4);
		
		radioButtons.add(r3_3_1);
		radioButtons.add(r3_3_2);
		radioButtons.add(r3_3_3);
		radioButtons.add(r3_3_4);
		
		radioButtons.add(r4_1_1);
		radioButtons.add(r4_1_2);
		radioButtons.add(r4_1_3);
		radioButtons.add(r4_1_4);
		
		radioButtons.add(r4_2_1);
		radioButtons.add(r4_2_2);
		radioButtons.add(r4_2_3);
		radioButtons.add(r4_2_4);
		
		radioButtons.add(r4_3_1);
		radioButtons.add(r4_3_2);
		radioButtons.add(r4_3_3);
		radioButtons.add(r4_3_4);
		
		radioButtons.add(r4_4_1);
		radioButtons.add(r4_4_2);
		radioButtons.add(r4_4_3);
		radioButtons.add(r4_4_4);
		
		radioButtons.add(r4_5_1);
		radioButtons.add(r4_5_2);
		radioButtons.add(r4_5_3);
		radioButtons.add(r4_5_4);
											
		radioButtons.add(r4_6_1);
		radioButtons.add(r4_6_2);
		radioButtons.add(r4_6_3);
		radioButtons.add(r4_6_4);
		
		radioButtons.add(r4_7_1);
		radioButtons.add(r4_7_2);
		radioButtons.add(r4_7_3);
		radioButtons.add(r4_7_4);
		
		radioButtons.add(r4_8_1);
		radioButtons.add(r4_8_2);
		radioButtons.add(r4_8_3);
		radioButtons.add(r4_8_4);
		
		radioButtons.add(r4_9_1);
		radioButtons.add(r4_9_2);
		radioButtons.add(r4_9_3);
		radioButtons.add(r4_9_4);
		
		radioButtons.add(r4_10_1);
		radioButtons.add(r4_10_2);
		radioButtons.add(r4_10_3);
		radioButtons.add(r4_10_4);
		
		radioButtons.add(r4_11_1);
		radioButtons.add(r4_11_2);
		radioButtons.add(r4_11_3);
		radioButtons.add(r4_11_4);
		
		radioButtons.add(r4_12_1);
		radioButtons.add(r4_12_2);
		radioButtons.add(r4_12_3);
		radioButtons.add(r4_12_4);
		
		radioButtons.add(r4_13_1);
		radioButtons.add(r4_13_2);
		radioButtons.add(r4_13_3);
		radioButtons.add(r4_13_4);
		
		radioButtons.add(r4_14_1);
		radioButtons.add(r4_14_2);
		radioButtons.add(r4_14_3);
		radioButtons.add(r4_14_4);
		
		radioButtons.add(r5_1_1);
		radioButtons.add(r5_1_2);
		radioButtons.add(r5_1_3);
		radioButtons.add(r5_1_4);
		
		radioButtons.add(r5_2_1);
		radioButtons.add(r5_2_2);
		radioButtons.add(r5_2_3);
		radioButtons.add(r5_2_4);

		radioButtons.add(r5_3_1);
		radioButtons.add(r5_3_2);
		radioButtons.add(r5_3_3);
		radioButtons.add(r5_3_4);
		
		radioButtons.add(r5_4_1);
		radioButtons.add(r5_4_2);
		radioButtons.add(r5_4_3);
		radioButtons.add(r5_4_4);
		
		radioButtons.add(r5_5_1);
		radioButtons.add(r5_5_2);
		radioButtons.add(r5_5_3);
		radioButtons.add(r5_5_4);
		
		radioButtons.add(r5_6_1);
		radioButtons.add(r5_6_2);
		radioButtons.add(r5_6_3);
		radioButtons.add(r5_6_4);
		
		radioButtons.add(r6_1_1);
		radioButtons.add(r6_1_2);
		radioButtons.add(r6_1_3);
		radioButtons.add(r6_1_4);
		
		
		radioButtons.add(r6_2_1);
		radioButtons.add(r6_2_2);
		radioButtons.add(r6_2_3);
		radioButtons.add(r6_2_4);
		
		
	}
	
	
	
	public String chuyenArrayListSangString(ArrayList<String> list) {
		String str= "";
		for(String i: list) {
			if(list.indexOf(i)!=list.size()-1) {
				str=str+i+",";
			}
			else {
				str=str+i;

			}
		}
		return str;
	}
//	public void formatSizeColumn() {
//		objectTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		objectTable.getColumnModel().getColumn(0).setPreferredWidth(70);  // stt
//		objectTable.getColumnModel().getColumn(1).setPreferredWidth(255);  // anh
//		
//	}
//	
	public String getSelectedBangDanhGia() {		
		String str="";
		for(int i=0;i<radioButtons.size();i++) {
			if(radioButtons.get(i).isSelected()) {
				int temp = i%4;
				str += temp+",";				
			}
		}
		if(str.length()>0) {
			return str.substring(0,str.length()-1);
		}
		return str;
		
	}
	public void resetRadio() {
		for(JRadioButton i : radioButtons) {
			i.setSelected(false);
		}
	}
	
	
}
