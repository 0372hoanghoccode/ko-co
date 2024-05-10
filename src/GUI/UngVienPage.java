package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.UngVienBUS;

public class UngVienPage extends JPanel {
		private UngVienPage_Them uvv_t;
		private JButton button_UngVien_SapXep;
		private JButton button_UngVien_Them;
		private JButton button_UngVien_NhapFile;
		private JButton button_UngVien_XuatFile;
		private JButton button_Timkiem;
		private JButton button_TuyenUngVien;
		private JTextField UngVien_Find;
		private myTable table;
		private JScrollPane scrollPane;
		private DefaultTableModel model;
		private Object[][] data;
		
		private JButton button_UngVien_Xoa;
		private JButton button_UngVien_Sua;
		private JComboBox<String> comboBox_MaTuyenDung;
		private ArrayList<String> string_ComboBox = new ArrayList<String>();
		private JPanel info_TuyenDung;
		private JPanel info_UngVien;
		private JLabel label_avt;
		private ArrayList<JLabel> listThongTinTuyenDung;
		private ArrayList<JLabel> listThongTinUngVien;
		private UngVienPage_TuyenUngVien tuyenUngVien;
		private UngVienBUS ungvienBus = new UngVienBUS();

		private static final String titleInfoTuyenDung[] = {"Chức vụ","Giới tính","Độ tuổi","Hạn nộp","Hồ sơ đã nộp","Hồ sơ đã tuyển","Lương tối thiểu","Lương tối đa"};
		private static final String[] columnName={ "Mã tuyển dụng","Họ và tên","Số điện thooại","Email","Chức vụ","Trình độ","Mức lương Deal","Trạng thái"};
		private static final String [] titleInfoUngVien = {"Tên ứng viên","Giới tính","Ngày sinh","Số điện thoại","Email","Số nhà","Đường","Phường xã","Quận/Huyện","Tỉnh/TP","CMND","Trình độ","Dân tộc","Tôn giáo","Hôn nhân"};
		
		public UngVienPage() {
			init();
			uvv_t= new UngVienPage_Them();
			uvv_t.setVisible(false);
			model = new DefaultTableModel(data, columnName);
			table.setModel(model);
			data = ungvienBus.getObject();
			tuyenUngVien= new UngVienPage_TuyenUngVien();
			tuyenUngVien.setVisible(false);
		}
		
		
		public void init() {
			this.setLayout(null);
			Font font = new Font("Arial",1,13);
			
			// Thông tin tuyển dụng
			info_TuyenDung= new JPanel();
			info_TuyenDung.setLayout(null);
			info_TuyenDung.setBounds(10, 65, 998, 75);
			info_TuyenDung.setOpaque(true);
			info_TuyenDung.setBackground(Color.WHITE);
			info_TuyenDung.setBorder(new LineBorder(Color.decode("#dfe4ea"),3));
			info_TuyenDung.setVisible(true);
			this.add(info_TuyenDung);
			
			listThongTinTuyenDung = new ArrayList<>();
			for(int i=0;i<4;i++) {
				JLabel label_hocVan= new JLabel(titleInfoTuyenDung[i]);
				label_hocVan.setBounds(15+250*i,10,300,30);
				label_hocVan.setForeground(new Color(0,0,0,200));
				label_hocVan.setFont(font);
				info_TuyenDung.add(label_hocVan);
				listThongTinTuyenDung.add(label_hocVan);
			}
			for(int i=0;i<4;i++) {
				JLabel temp= new JLabel(titleInfoTuyenDung[4+i]);
				temp.setBounds(15+250*i,45,300,30);
				temp.setFont(font);
				temp.setForeground(new Color(0,0,0,200));
				info_TuyenDung.add(temp);
				listThongTinTuyenDung.add(temp);
			}

			//JPanel Thông tin Ứng viên
			info_UngVien= new JPanel();
			info_UngVien.setLayout(null);
			info_UngVien.setOpaque(true);
			info_UngVien.setBackground(Color.white);
			info_UngVien.setBounds(10, 365, 998, 200);
			info_UngVien.setBorder(new LineBorder(Color.decode("#dfe4ea"),3));
			info_UngVien.setVisible(true);
			this.add(info_UngVien);
			
			label_avt= new JLabel();
			label_avt.setBounds(22,11,120,150);
			label_avt.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-back-50.png")).getImage().getScaledInstance(label_avt.getWidth(), label_avt.getHeight(), Image.SCALE_AREA_AVERAGING)));
			
			label_avt.setFont(new Font("Arial",Font.BOLD,13));
			info_UngVien.add(label_avt);
			
			listThongTinUngVien = new ArrayList<>();
			for(int i=0;i<5;i++) {
				JLabel temp= new JLabel(titleInfoUngVien[i]);
				temp.setBounds(180,20+i*30,280,30);
				temp.setFont(new Font("Arial",Font.BOLD,13));
				temp.setForeground(new Color(0,0,0,200));
				info_UngVien.add(temp);
				listThongTinUngVien.add(temp);
			}
			for(int i=0;i<5;i++) {
				JLabel temp= new JLabel(titleInfoUngVien[5+i]);
				temp.setBounds(450,20+i*30,280,30);
				temp.setFont(new Font("Arial",Font.BOLD,13));
				temp.setForeground(new Color(0,0,0,200));
				info_UngVien.add(temp);
				listThongTinUngVien.add(temp);
			}
			for(int i=0;i<5;i++) {
				JLabel temp= new JLabel(titleInfoUngVien[10+i]);
				temp.setBounds(730,20+i*30,280,30);
				temp.setFont(new Font("Arial",Font.BOLD,13));
				temp.setForeground(new Color(0,0,0,200));
				info_UngVien.add(temp);
				listThongTinUngVien.add(temp);
			}


			
		

		
			comboBox_MaTuyenDung= new myCombobox<String>();
			this.add(comboBox_MaTuyenDung);
			comboBox_MaTuyenDung.setFont(new Font("Arial",1,12));
			comboBox_MaTuyenDung.setBounds(10,20,140,30);
			((myCombobox<String>)comboBox_MaTuyenDung).showArrow();
			
			
			
			
			UngVien_Find= new JTextField("Nhập thông tin tìm kiếm",JTextField.CENTER);
			UngVien_Find.setFont(new Font("Arial", 0, 13));
			this.add(UngVien_Find);
			UngVien_Find.setBounds(249,20,300,30);
			
			JLabel find= new JLabel();
			find.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-back-50.png")).getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT)));
			find.setBounds(548,20,30,30);
			find.setOpaque(true);
			find.setBackground(new Color(204,229,255));
			this.add(find);
			
			button_UngVien_Them= new JButton("Thêm");
			button_UngVien_Them.setFont(new Font("Arial", 0, 13));
			this.add(button_UngVien_Them);
			button_UngVien_Them.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-back-50.png")).getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT)));
			button_UngVien_Them.setHorizontalAlignment(SwingConstants.LEFT);
			button_UngVien_Them.setBounds(855,24,90,30);
			
			
			
			button_UngVien_Xoa= new JButton("Xóa");
			button_UngVien_Xoa.setFont(new Font("Arial", 0, 13));
			this.add(button_UngVien_Xoa);
			button_UngVien_Xoa.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-back-50.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
			button_UngVien_Xoa.setHorizontalAlignment(SwingConstants.LEFT);
			button_UngVien_Xoa.setForeground(new Color(128,128,128));
			button_UngVien_Xoa.setBounds(742,24,90,30);
			
			button_TuyenUngVien= new JButton("Tuyển");
			button_TuyenUngVien.setHorizontalAlignment(SwingConstants.LEFT);
			button_TuyenUngVien.setFont(new Font("Arial",0,13));
			this.add(button_TuyenUngVien);
			button_TuyenUngVien.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-back-50.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
			button_TuyenUngVien.setBounds(619,24,100,30);
			
			//Table
	       
	        table = new myTable() ;
	        table.setRowHeight(30);
	        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
	                Color selectedColor = Color.decode("#2980b9");
	                	
	                	Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
	                	com.setFont(new Font("Arial",Font.PLAIN,13));
	                    setBorder(noFocusBorder);
	                    if(i1==7) {
	                		String str = (String)o;
	                		if(str.equalsIgnoreCase("Đã tuyển")) {
	                			 com.setForeground(Color.decode("#4cd137"));
	                		}else {
	                			 com.setForeground(Color.decode("#e84118"));
	                		}
	                		if (selected) {
		                    	com.setBackground(new Color(0,0,0,20));
		                    } else {
		                    	com.setBackground(Color.WHITE);
		                    }
	                		return com;
	                    }else if(i1==1) {
	                    	String str = (String)o;
	                		com.setFont(new Font("Arial",1,13));
	                		com.setForeground(new Color(0,0,0,170));
	                		
	                		if (selected) {
		                    	com.setBackground(new Color(0,0,0,20));
		                    } else {
		                    	com.setBackground(Color.WHITE);
		                    }
	                		return com;
	                    }
	                    if (selected) {
	                    	com.setBackground(new Color(0,0,0,20));
	                        com.setForeground(selectedColor);
	                    } else {
	                    	com.setBackground(Color.WHITE);
	                    	
	                        com.setForeground(new Color(102, 102, 102));
	                    }
	                   return com;
	                }
			});

            
	        
	        
	        scrollPane = new JScrollPane(table);
	        scrollPane.setVerticalScrollBar(new myScrollBar());
	        scrollPane.setBounds(10,150,998,211);
	        scrollPane.setBorder(new LineBorder(Color.decode("#dfe4ea"),3));
	        this.add(scrollPane);
	        
			this.setBounds(220,46,1013,576);
			this.setBackground(new Color(255, 255, 255));
			this.setVisible(false);
		}
		public JButton getButtonTuyenUngVien() {
			return this.button_TuyenUngVien;
		}

		public JButton getButton_Timkiem() {
			return button_Timkiem;
		}
		public JPanel getInfo_TuyenDung() {
			return info_TuyenDung;
		}

		public JComboBox<String> getComboBox_MaTuyenDung() {
			return comboBox_MaTuyenDung;
		}
		public void setComboBox_MaTuyenDung(JComboBox<String> comboBox_MaTuyenDung) {
			this.comboBox_MaTuyenDung = comboBox_MaTuyenDung;
		}
		public ArrayList<String> getString_ComboBox() {
			return string_ComboBox;
		}
		public UngVienPage_Them getUvv_t() {
			return uvv_t;
		}
		public JButton getButton_UngVien_SapXep() {
			return button_UngVien_SapXep;
		}

		public JButton getButton_UngVien_Them() {
			return button_UngVien_Them;
		}
		public JButton getButton_UngVien_NhapFile() {
			return button_UngVien_NhapFile;
		}
		public JButton getButton_UngVien_XuatFile() {
			return button_UngVien_XuatFile;
		}

		public JTextField getUngVien_Find() {
			return UngVien_Find;
		}
		public JButton getButton_UngVien_Xoa() {
			return button_UngVien_Xoa;
		}
		public JButton getButton_UngVien_Sua() {
			return button_UngVien_Sua;
		}
		public myTable getTable() {
			return table;
		}
		public void setTable(myTable table) {
			this.table = table;
		}
		public DefaultTableModel model() {
			return model;
		}
		public Object[][] getData() {
			return data;
		}
	
		public String[] columnName() {
			return columnName;
		}
		public UngVienPage_TuyenUngVien getFormTuyenUngVien() {
			return tuyenUngVien;
		}
		
		
		public void setMaTuyenDung_UngVienView_UngVienView_Them(String str[]) {
			comboBox_MaTuyenDung.setModel(new DefaultComboBoxModel<>(str));
		}
		public void setDataInfoUngVien(String data[]) {
			for(int i=0;i<15;i++) {
				listThongTinUngVien.get(i).setText(titleInfoUngVien[i]+":    "+data[i]);
			}
		}
		public void setDataInfotuyenDung(String data[]) {
			for(int i=0;i<8;i++) {
				listThongTinTuyenDung.get(i).setText(titleInfoTuyenDung[i]+":    "+data[i]);
			}
		}
		
		public void formatSizeColumn() {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(100);  
			table.getColumnModel().getColumn(1).setPreferredWidth(190);  
			table.getColumnModel().getColumn(2).setPreferredWidth(100);   
			table.getColumnModel().getColumn(3).setPreferredWidth(150);  
			table.getColumnModel().getColumn(4).setPreferredWidth(170); 
			table.getColumnModel().getColumn(5).setPreferredWidth(90);  
			table.getColumnModel().getColumn(6).setPreferredWidth(110);   
			table.getColumnModel().getColumn(7).setPreferredWidth(80);   
		}
}
