package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UngVien extends JPanel {
    private TuyenDungPage tdv;
    private UngVienPage uvv;
    private JLabel lb1;
    private JLabel lb2;
    private JPanel frameContent;

    public UngVien() {
        init();
    }

    public void init() {
        setLayout(null);
        JPanel frame = new JPanel();
        frame.setBounds(5, 5, 985, 30);
        frame.setBackground(Color.white);
        frame.setLayout(null);
        add(frame);

        lb1 = new JLabel("ỨNG VIÊN");
        lb1.setFont(new Font("Arial", Font.BOLD, 13));
        lb1.setBounds(15, 0, 100, 30);
        lb1.setHorizontalAlignment(SwingConstants.CENTER);
        lb1.setForeground(new Color(0, 0, 0, 140));
        frame.add(lb1);

        lb2 = new JLabel("TUYỂN DỤNG");
        lb2.setFont(new Font("Arial", Font.BOLD, 13));
        lb2.setBounds(115, 0, 130, 30);
        lb2.setHorizontalAlignment(SwingConstants.CENTER);
        lb2.setForeground(new Color(0, 0, 0, 60));
        frame.add(lb2);

        frameContent = new JPanel();
        frameContent.setBounds(5, 40, 985, 615);
        frameContent.setLayout(new CardLayout());
        add(frameContent);

        tdv = new TuyenDungPage();
        uvv = new UngVienPage();
        
        frameContent.add(uvv, "ỨNG VIÊN");
        frameContent.add(tdv, "TUYỂN DỤNG");

        lb1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showPage("ỨNG VIÊN");
            }
        });

        lb2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showPage("TUYỂN DỤNG");
            }
        });

        // Show the initial page
        showPage("ỨNG VIÊN");
    }

    private void showPage(String cardName) {
        CardLayout cl = (CardLayout) (frameContent.getLayout());
        cl.show(frameContent, cardName);

        // Update foreground colors for tabs
        if (cardName.equals("ỨNG VIÊN")) {
            lb1.setForeground(new Color(0, 0, 0, 140));
            lb2.setForeground(new Color(0, 0, 0, 60));
        } else {
            lb1.setForeground(new Color(0, 0, 0, 60));
            lb2.setForeground(new Color(0, 0, 0, 140));
        }
    }

    public TuyenDungPage getTuyenDungView() {
        return this.tdv;
    }

    public UngVienPage getUngVienView() {
        return this.uvv;
    }

    public JLabel getLb1() {
        return this.lb1;
    }

    public JLabel getLb2() {
        return this.lb2;
    }
}
