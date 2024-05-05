package GUI;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

public class appContent extends JPanel {

    private static final long serialVersionUID = 1L;
    private ArrayList<JPanel> pages;
    private HomePage home;
    private EmployeePage employee;
    private PhongbanPage phongBan;
    private HopdongPage hopDong;
    private ChamCongPage1  chamCong;
    private LuongThuongPage luongThuongPage; 
    private DanhGiaPage danhGia;
    private TaiKhoanPage taiKhoanPage;
    private UngVien ungvienPage; // tuyển dụng nằm trong ứng viên
    private ChamCongPage2 chamCongPage2;
    private DanhGiaPage2 danhGiaPage2;

    public appContent() {
        init();
    }

    public void init() {
        setLayout(new CardLayout(0, 0));

        home = new HomePage();
        employee = new EmployeePage();
        phongBan = new PhongbanPage();
        hopDong = new HopdongPage();
        chamCong= new ChamCongPage1();
        luongThuongPage = new LuongThuongPage();
        danhGia= new DanhGiaPage();
        taiKhoanPage = new TaiKhoanPage();
        ungvienPage = new UngVien();
        chamCongPage2= new ChamCongPage2();
        danhGiaPage2= new DanhGiaPage2();

        add(home, "KHUNG1");
        add(employee, "KHUNG2");
        add(phongBan, "KHUNG3");
        add(hopDong, "KHUNG4");
        add(chamCong, "KHUNG5");
        add(luongThuongPage, "KHUNG6");
        add(danhGia, "KHUNG7");
        add(taiKhoanPage, "KHUNG8");
        add(ungvienPage,"KHUNG9");
        add(chamCongPage2,"KHUNG10");
        add(danhGiaPage2,"KHUNG11");

        createPageList();
        displayContent(0);
    }

    private void createPageList() {
        pages = new ArrayList<JPanel>();
        pages.add(home);
        pages.add(employee);
        pages.add(phongBan);
        pages.add(hopDong);
        pages.add(chamCong);
        pages.add(luongThuongPage);
        pages.add(danhGia);
        pages.add(taiKhoanPage);
        pages.add(ungvienPage);
        pages.add(chamCongPage2);
        pages.add(danhGiaPage2);
    }

    public void displayContent(int index) {
        ((CardLayout) this.getLayout()).show(this, "KHUNG" + (index + 1));
    }

	public ChamCongPage1 getChamCong() {
		return chamCong;
	}

	public void setChamCong(ChamCongPage1 chamCong) {
		this.chamCong = chamCong;
	}
    
    public UngVien getUngvienPage() {
        return ungvienPage;
    }
    
    public appContent getAppContent() {
        return this;
    }

    public UngVien getUngvien() {
        return ungvienPage;
    }   

    public EmployeePage getEmployeePage() {
        return employee;
    }   

}



