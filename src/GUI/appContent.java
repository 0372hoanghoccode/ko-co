package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

public class appContent extends JPanel {

    private static final long serialVersionUID = 1L;
    private ArrayList<JPanel> pages;
    private HomePage home;
    private EmployeePage employee;
    private PhongbanPage phongBan;
    private HopdongPage hopDong;
    private LuongThuongPage luongThuongPage; 
    private TaiKhoanPage taiKhoanPage;

    public appContent() {
        init();
    }

    public void init() {
        setLayout(new CardLayout(0, 0));

        home = new HomePage();
        employee = new EmployeePage();
        phongBan = new PhongbanPage();
        hopDong = new HopdongPage();
        luongThuongPage = new LuongThuongPage();
        taiKhoanPage = new TaiKhoanPage();

        add(home, "KHUNG1");
        add(employee, "KHUNG2");
        add(phongBan, "KHUNG3");
        add(hopDong, "KHUNG4");
        add(luongThuongPage, "KHUNG5");
        add(taiKhoanPage, "KHUNG6");

        createPageList();
        displayContent(0);
    }

    private void createPageList() {
        pages = new ArrayList<JPanel>();
        pages.add(home);
        pages.add(employee);
        pages.add(phongBan);
        pages.add(hopDong);
        pages.add(luongThuongPage);
        pages.add(taiKhoanPage);
    }

    public void displayContent(int index) {
        ((CardLayout) this.getLayout()).show(this, "KHUNG" + (index + 1));
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
