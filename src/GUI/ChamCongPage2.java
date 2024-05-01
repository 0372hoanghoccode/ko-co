package GUI;

import javax.swing.JPanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChamCongPage2 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    /**
     * Create the panel.
     */
    public ChamCongPage2() {
        setLayout(null);
        
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 63, 982, 535);
        add(panel_1);
        panel_1.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 68, 982, 348);
        panel_1.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, "T2", "T3", "T4", "T5", "T6", "T7", "CN", "T2", "T3", "T4", "T5", "T6", "T7", "CN", "T2", "T3", "T4", "T5", "T6", "T7", "CN", "T2", "T3", "T4", "T5", "T6", "T7", "cn", "T2", "T3"},
            },
            new String[] {
                "STT", "Họ Tên", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"
            }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        scrollPane.setViewportView(table);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 0, 982, 41);
        add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton = new JButton("Quay lại");
        Image imgBack=new ImageIcon(this.getClass().getResource("/assets/appIcon/icons8-back-50.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnNewButton.setIcon(new ImageIcon(imgBack));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		appContent parentForm=(appContent) getParent();
        		setVisible(false);
        		parentForm.displayContent(4);
        	}
        });
        btnNewButton.setBounds(23, 11, 97, 23);
        panel.add(btnNewButton);

    }
}
