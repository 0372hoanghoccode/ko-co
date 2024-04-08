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
    private ChamCongPage  chamCong;
    private LuongThuongPage luongThuongPage; 
    private DanhGiaPage danhGia;
    private TaiKhoanPage taiKhoanPage;
    private TuyenDungPage tuyendungPage;

    public appContent() {
        init();
    }

    public void init() {
        setLayout(new CardLayout(0, 0));

        home = new HomePage();
        employee = new EmployeePage();
        phongBan = new PhongbanPage();
        hopDong = new HopdongPage();
        chamCong= new ChamCongPage();
        luongThuongPage = new LuongThuongPage();
        danhGia= new DanhGiaPage();
        taiKhoanPage = new TaiKhoanPage();
        tuyendungPage = new TuyenDungPage();

        add(home, "KHUNG1");
        add(employee, "KHUNG2");
        add(phongBan, "KHUNG3");
        add(hopDong, "KHUNG4");
        add(chamCong, "KHUNG5");
        add(luongThuongPage, "KHUNG6");
        add(danhGia, "KHUNG7");
        add(taiKhoanPage, "KHUNG8");
        add(tuyendungPage,"KHUNG9");

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
        pages.add(tuyendungPage);
    }

    public void displayContent(int index) {
        ((CardLayout) this.getLayout()).show(this, "KHUNG" + (index + 1));
    }

}



