package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;




import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import BUS.PHONGBAN_BUS;
import DAO.LuongDAO;
import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
//import DTO.SUPPORT;
//import dateChooser.DateChooser;
//import view.button.Button;


public class HopdongPage_Kigiahan extends JPanel{
	private myCombobox<String> cbbPhong;
	private myCombobox<String> cbbSort;
	private myCombobox<String> cbbSort2;
	private ArrayList<JLabel> listlabel;
	private JTextField tfBatDauHopDong;
	private JTextField tfKetThucHopDong;
	private JComboBox<String> cbbThoiHanHopDong;
	private JButton btnKiHopDong;
	private myTable table;
	private DefaultTableModel model;
	private Object[][] data;
	private static String[] columnName = {"STT","Nhân viên","Phòng ban","Thử việc từ"};
	private PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();
	private NhanVienDAO nhanvienDAO = new NhanVienDAO();
	
	public HopdongPage_Kigiahan() {
		listlabel = new ArrayList<>();
		data = nhanvienDAO.getDanhSachNhanVienThuViecToRender();
		model = new DefaultTableModel(data,columnName);
		init();
	}
	
	public void init() {
		this.setLayout(null);
		
		
		JPanel panelFeature = new JPanel();

		panelFeature.setBounds(0,5,645,90);

		panelFeature.setBounds(0,11,640,90);

		panelFeature.setBackground(Color.white);
		this.add(panelFeature);
		panelFeature.setLayout(null);

		
		JLabel titelLabel = new JLabel("DANH SÁCH NHÂN VIÊN THỬ VIỆC");
		titelLabel.setForeground(new Color(0,0,0,140));
		titelLabel.setFont(new Font("arial", 1, 13));
		titelLabel.setBounds(10,11,250,25);
		panelFeature.add(titelLabel);


		
        JComboBox<String> cbbPhong  = new JComboBox<>();
        cbbPhong .setEditable(false);
        cbbPhong .setBackground(Color.WHITE);
        cbbPhong .setOpaque(true);
        cbbPhong .setBounds(10,45,220,26);
        cbbPhong .addItem("Phòng ban");
        for (String i : phongban_BUS.getTenPhongBan()) {
            cbbPhong.addItem(i);
        }
        panelFeature.add(cbbPhong);

		JComboBox<String> cbbSort = new JComboBox<>();
        String[] cbbSortString = {"Sắp xếp theo: Mã nhân viên", "Sắp xếp theo: Ngày thử việc"};
        cbbSort.setModel(new DefaultComboBoxModel<>(cbbSortString));
        cbbSort.setFont(new Font("Arial", Font.BOLD, 12));
        cbbSort.setForeground(new Color(0, 0, 0, 200));
        cbbSort.setBounds(240, 45, 200, 26);
        panelFeature.add(cbbSort);

        JComboBox<String> cbbSort2 = new JComboBox<>();
        String[] cbbSortString2 = {"Tăng dần", "Giảm dần"};
        cbbSort2.setModel(new DefaultComboBoxModel<>(cbbSortString2));
        cbbSort2.setFont(new Font("Arial", Font.BOLD, 12));
        cbbSort2.setForeground(new Color(0, 0, 0, 200));
        cbbSort2.setBounds(450, 45, 100, 26);
        panelFeature.add(cbbSort2);

		// JTextField tfFind = new JTextField();
		// tfFind.setFont(new Font("Arial",0,14));
		// tfFind.setText(" Tìm kiếm nhanh...");
		// tfFind.setForeground(new Color(0,0,0,100));
		// tfFind.setBorder(new LineBorder(Color.decode("#3498db"),2));
		// tfFind.setBounds(810,45,230,26);
		// panelFeature.add(tfFind);
		// JButton btnFind = new JButton("");
		// btnFind.setFont(new Font("sansserif",1,13));
		// btnFind.setIcon(new ImageIcon(getClass().getResource("/assets/appIcon/icons8-reset-24.png")));
		// btnFind.setBounds(1040,45,30,26);
		// btnFind.setBackground(Color.decode("#3498db"));
		// btnFind.setBorderPainted(false);
		// btnFind.setFocusPainted(false);
		// panelFeature.add(btnFind);
		
		
		JPanel panelThuong = new JPanel();
		panelThuong.setBackground(Color.white);
		panelThuong.setBounds(650,0,345,575);
		panelThuong.setLayout(null);
		this.add(panelThuong);
		JLabel titelLabel2 = new JLabel("KÍ HỢP ĐỒNG LAO ĐỘNG");
		titelLabel2.setForeground(new Color(0,0,0,140));
		titelLabel2.setFont(new Font("arial", 1, 13));
		titelLabel2.setBounds(10,10,250,25);
		panelThuong.add(titelLabel2);
		
		JLabel lbt1 = new JLabel("Nhân viên:");
		lbt1.setForeground(new Color(0,0,0,140));
		lbt1.setFont(new Font("arial", 1, 13));
		lbt1.setBounds(10,30,250,25);
		panelThuong.add(lbt1);
		
		
		
		JLabel lbt2 = new JLabel("Ngày sinh:");
		lbt2.setForeground(new Color(0,0,0,140));
		lbt2.setFont(new Font("arial", 1, 13));
		lbt2.setBounds(10,50,250,25);
		panelThuong.add(lbt2);
		JLabel lbt3 = new JLabel("Giới tính:");
		lbt3.setForeground(new Color(0,0,0,140));
		lbt3.setFont(new Font("arial", 1, 13));
		lbt3.setBounds(10,294,250,25);
		panelThuong.add(lbt3);
		JLabel lbt4 = new JLabel("Địa chỉ:");
		lbt4.setForeground(new Color(0,0,0,140));
		lbt4.setFont(new Font("arial", 1, 13));
		lbt4.setBounds(10,75,250,25);
		panelThuong.add(lbt4);
		JLabel lbt5 = new JLabel("Số điện thoại:");
		lbt5.setForeground(new Color(0,0,0,140));
		lbt5.setFont(new Font("arial", 1, 13));
		lbt5.setBounds(10,96,250,25);
		panelThuong.add(lbt5);
		JLabel lbt6 = new JLabel("Email:");
		lbt6.setForeground(new Color(0,0,0,140));
		lbt6.setFont(new Font("arial", 1, 13));
		lbt6.setBounds(10,120,250,25);
		panelThuong.add(lbt6);
		JLabel lbt7 = new JLabel("CMND:");
		lbt7.setForeground(new Color(0,0,0,140));
		lbt7.setFont(new Font("arial", 1, 13));
		lbt7.setBounds(10,143,250,25);
		panelThuong.add(lbt7);
		
		JLabel lbt8 = new JLabel("Học vấn:");
		lbt8.setForeground(new Color(0,0,0,140));
		lbt8.setFont(new Font("arial", 1, 13));
		lbt8.setBounds(10,167,250,25);
		panelThuong.add(lbt8);
		JLabel lbt9 = new JLabel("Chuyên môn:");
		lbt9.setForeground(new Color(0,0,0,140));
		lbt9.setFont(new Font("arial", 1, 13));
		lbt9.setBounds(10,266,250,25);
		panelThuong.add(lbt9);
		JLabel lbt10 = new JLabel("Chuyên ngành:");
		lbt10.setForeground(new Color(0,0,0,140));
		lbt10.setFont(new Font("arial", 1, 13));
		lbt10.setBounds(10,189,250,25);
		panelThuong.add(lbt10);
		JLabel lbt111 = new JLabel("Phòng ban:");
		lbt111.setForeground(new Color(0,0,0,140));
		lbt111.setFont(new Font("arial", 1, 13));
		lbt111.setBounds(10,210,250,25);
		panelThuong.add(lbt111);
		JLabel lbt222 = new JLabel("Chức vụ:");
		lbt222.setForeground(new Color(0,0,0,140));
		lbt222.setFont(new Font("arial", 1, 13));
		lbt222.setBounds(10,239,250,25);
		panelThuong.add(lbt222);
		JLabel lbt333 = new JLabel("Mức lương:");
		lbt333.setForeground(new Color(0,0,0,140));
		lbt333.setFont(new Font("arial", 1, 13));
		lbt333.setBounds(10,319,98,25);
		panelThuong.add(lbt333);
		
		JLabel titelLabel3 = new JLabel("BỔ SUNG THÔNG TIN");
		titelLabel3.setForeground(new Color(0,0,0,140));
		titelLabel3.setFont(new Font("arial", 1, 13));
		titelLabel3.setBounds(10,371,250,25);
		panelThuong.add(titelLabel3);
		
		JLabel lbt444= new JLabel("Bắt đầu hợp đồng");
		lbt444.setForeground(new Color(0,0,0,140));
		lbt444.setFont(new Font("arial", 1, 13));
		lbt444.setBounds(10,413,150,25);
		panelThuong.add(lbt444);
		
		tfBatDauHopDong = new JTextField();
		tfBatDauHopDong.setBounds(10,448,150,28);
		tfBatDauHopDong.setForeground(new Color(0,0,0,140));
		tfBatDauHopDong.setFont(new Font("arial", 1, 13));
		panelThuong.add(tfBatDauHopDong);
		// DateChooser dc = new DateChooser();
		// dc.setTextRefernce(tfBatDauHopDong);
		
		JLabel lbt555= new JLabel("Kết thúc hợp đồng");
		lbt555.setForeground(new Color(0,0,0,140));
		lbt555.setFont(new Font("arial", 1, 13));
		lbt555.setBounds(170,413,150,25);
		panelThuong.add(lbt555);
		
		tfKetThucHopDong = new JTextField();
		tfKetThucHopDong.setBounds(170,448,150,28);
		tfKetThucHopDong.setForeground(new Color(0,0,0,140));
		tfKetThucHopDong.setFont(new Font("arial", 1, 13));
		panelThuong.add(tfKetThucHopDong);
		tfKetThucHopDong.setEnabled(false);
		
		JLabel lbt4444= new JLabel("Thời hạn hợp đồng");
		lbt4444.setForeground(new Color(0,0,0,140));
		lbt4444.setFont(new Font("arial", 1, 13));
		lbt4444.setBounds(10,486,150,25);
		panelThuong.add(lbt4444);
		
		cbbThoiHanHopDong = new myCombobox<>();
		cbbThoiHanHopDong.setModel(new DefaultComboBoxModel<>(new String[] {"1 năm","2 năm","3 năm","4 năm","5 năm"}));
		cbbThoiHanHopDong.setBounds(10,521,150,30);
		cbbThoiHanHopDong.setForeground(new Color(0,0,0,140));
		cbbThoiHanHopDong.setFont(new Font("arial", 1, 13));
		panelThuong.add(cbbThoiHanHopDong);
		
		
		btnKiHopDong = new Button();
		btnKiHopDong.setText("Tạo hợp đồng");
		btnKiHopDong.setBounds(188,521,120,35);
		btnKiHopDong.setForeground(Color.white);
		btnKiHopDong.setFont(new Font("arial", 1, 13));
		btnKiHopDong.setBackground(Color.decode("#0097e6"));
		panelThuong.add(btnKiHopDong);
		btnKiHopDong.setFocusable(false);
		
		
		JScrollPane jsp1 = new JScrollPane();
		jsp1.setBackground(Color.white);
		jsp1.setVerticalScrollBar(new myScrollBar());
		jsp1.setBorder(new EmptyBorder(0,0,0,0));
		jsp1.setBounds(0,96,645,475);
		this.add(jsp1);

		table = new myTable();
		table.setRowHeight(40);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Color selectedColor = Color.decode("#2980b9");
                Color backgroundSelected = new Color(245,245,245);
				
                
                String str = (String)o;
        		str = "  "+str;
        		o = (Object)str;
            	Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                setBorder(noFocusBorder);
                if (selected) {
                	com.setBackground(backgroundSelected);
                	com.setFont(new Font("Arial",Font.PLAIN,13));
                    com.setForeground(selectedColor);
                } else {
                	com.setBackground(Color.WHITE);
                	com.setFont(new Font("Arial",Font.PLAIN,13));
                    com.setForeground(new Color(102, 102, 102));
                }
               return com;
            }
		});
		jsp1.setViewportView(table);

		table.setModel(model);
		

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);  
		table.getColumnModel().getColumn(2).setPreferredWidth(240); 
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(110); 

		
		cbbPhong.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    
					String selectedPhong = (String) cbbPhong.getSelectedItem();
					int selectedSortBy = cbbSort.getSelectedIndex();
					int selectedSortMode = cbbSort2.getSelectedIndex();
                   
					updateTable(selectedPhong, selectedSortBy, selectedSortMode);
                }
            }
        });
		
        cbbSort.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
					String selectedPhong = (String) cbbPhong.getSelectedItem();
					int selectedSortBy = cbbSort.getSelectedIndex();
					int selectedSortMode = cbbSort2.getSelectedIndex();
                   
					updateTable(selectedPhong, selectedSortBy, selectedSortMode);
                }
            }
        });

        cbbSort2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
					String selectedPhong = (String) cbbPhong.getSelectedItem();
					int selectedSortBy = cbbSort.getSelectedIndex();
					int selectedSortMode = cbbSort2.getSelectedIndex();
                    
					updateTable(selectedPhong, selectedSortBy, selectedSortMode);
                }
            }
        });

		tfBatDauHopDong.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	int thoiHan = Integer.valueOf(cbbThoiHanHopDong.getSelectedItem().toString().split(" ")[0]);
				LocalDate ngayKetThuc = toLocalDate(tfBatDauHopDong.getText()).plusYears(thoiHan);
				tfKetThucHopDong.setText(LocalDateToString(ngayKetThuc));
		    }
		    public void removeUpdate(DocumentEvent e) {
		    }
		    public void changedUpdate(DocumentEvent e) {
		    }
		});
		cbbThoiHanHopDong.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int thoiHan = Integer.valueOf(cbbThoiHanHopDong.getSelectedItem().toString().split(" ")[0]);
				LocalDate ngayKetThuc = toLocalDate(tfBatDauHopDong.getText()).plusYears(thoiHan);
				tfKetThucHopDong.setText(LocalDateToString(ngayKetThuc));
			}
		});
		
		
	}
	public JButton getBtnKiHopDong() {
		return this.btnKiHopDong;
	}
	public void setSalaryData(Object[][] data) {
		this.data = data;
		model.setDataVector(this.data, columnName);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);  
		table.getColumnModel().getColumn(2).setPreferredWidth(240); 
	}


    private void updateTable(String selectedPhong, int selectedSortBy, int selectedSortMode) {
        Object[][] newData = nhanvienDAO.getDanhSachNhanVienThuViecToRender(selectedPhong, selectedSortBy, selectedSortMode);
        model.setRowCount(0); 
        for (Object[] row : newData) {
            model.addRow(row); 
        }
    }
    
	
	public myCombobox<String> getCbbPhong() {
		return cbbPhong;
	}
	public myCombobox<String> getCbbSort() {
		return cbbSort;
	}
	public myCombobox<String> getCbbSort2() {
		return cbbSort2;
	}
	public void setDataForCbbPhong(String data[]) {
		cbbPhong.setModel(new DefaultComboBoxModel<>(data));
	}
	public JTable getTable() {
		return this.table;
	}
	public DefaultTableModel getModel() {
		return this.model;
	}
	public void setDataNhanVienKiHopDong(String [] data) {
		for(int i=0;i<data.length;i++) {
			listlabel.get(i).setText(data[i]);
		}
		LocalDate d = LocalDate.now();
		tfBatDauHopDong.setText(LocalDateToString(d));
		tfKetThucHopDong.setText(LocalDateToString(d.plusYears(1)));
		cbbThoiHanHopDong.setSelectedIndex(0);
	}
	public JTextField getTfNgayBatDau() {
		return this.tfBatDauHopDong;
	}
	public JTextField getTfNgayKetThuc() {
		return this.tfKetThucHopDong;
	}
	public JComboBox<String> getCbbThoiHanHopDong(){
		return this.cbbThoiHanHopDong;
	}
	public ArrayList<JLabel> getListLabel(){
		return this.listlabel;
	}

	public static LocalDate toLocalDate(String str) {
		LocalDate localDate4 = LocalDate.parse(str,DateTimeFormatter.ofPattern("dd-LL-yyyy"));
		return localDate4;
	}

	public static String LocalDateToString(LocalDate date) {
		String arr[] = date.toString().split("-");
		return arr[2]+"-"+arr[1]+"-"+arr[0];
		
	}
}
