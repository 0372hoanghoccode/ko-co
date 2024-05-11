package GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.regex.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;




import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import BUS.PHONGBAN_BUS;
import DTO.NHANVIEN;
import DTO.DIACHI;
import DTO.TRINHDO;

public class EmployeeDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Object[] data;
	
	PHONGBAN_BUS phongban_BUS = new PHONGBAN_BUS();
	
	ArrayList<customTextField> listTextField = new ArrayList<customTextField>();


	private String[] tongiao= {"Không","Phật giáo","Công giáo","Tin lành","Hòa Hảo","Cao Đài","Khác"};
	private String[] chuyenMonString = {"Không","Cử nhân","Kĩ sư","Thạc sĩ","Tiến sĩ"};
	private String[] hocVan = {"Không","9/12","12/12"};

	@SuppressWarnings({ "rawtypes" })
	JComboBox cbbGioiTinh;

	private String maNhanVien;

	@SuppressWarnings({ "unchecked", "rawtypes" }) // Suppress warnings for JComboBox


	public EmployeeDetail(Object[] data, EmployeePage employeePage) {
		this.data = data;
		this.maNhanVien = data[0].toString();
		setBounds(100, 100, 760, 602);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 750, 550);
		getContentPane().add(panel);
		panel.setLayout(null);


		JLabel lblNewLabel_13 = new JLabel("Thông tin cá nhân ");
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_13.setBounds(10, 10, 132, 35);
		panel.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Liên lạc");
		lblNewLabel_14.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_14.setBounds(10, 358, 157, 36);
		panel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Thông tin phòng ban ");
		lblNewLabel_15.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_15.setBounds(354, 10, 157, 35);
		panel.add(lblNewLabel_15);


		
		JLabel lblNewLabel_12 = new JLabel("Mã nhân viên: "); // 0
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(10, 56, 90, 20);
		panel.add(lblNewLabel_12);
		customTextField textField_maNV = new customTextField();
		textField_maNV.setText(data[0].toString());
		textField_maNV.setBounds(109, 50, 90, 40);
		panel.add(textField_maNV);
		listTextField.add(textField_maNV);

		JLabel lblNewLabel = new JLabel("Họ và tên: "); // 1
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 105, 90, 20);
		panel.add(lblNewLabel);
		customTextField textField_hoTen = new customTextField();
		textField_hoTen.setText(data[1].toString());
		textField_hoTen.setBounds(109, 99, 180, 40);
		panel.add(textField_hoTen);
		listTextField.add(textField_hoTen);
		
		JLabel lblNewLabel_2 = new JLabel("Giới tính: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 209, 60, 20);
		panel.add(lblNewLabel_2);
		
		
		cbbGioiTinh = new JComboBox();
		cbbGioiTinh.setBounds(109, 203, 90, 30);
		cbbGioiTinh.addItem("Nam");
		cbbGioiTinh.addItem("Nữ");
		cbbGioiTinh.setEditable(false);
		if (data[2].toString().equals("Nam")) {
			cbbGioiTinh.setSelectedIndex(0);
		} else {
			cbbGioiTinh.setSelectedIndex(1);
		}
		panel.add(cbbGioiTinh);
		


		JLabel lblNewLabel_1 = new JLabel("Ngày sinh: "); // 2
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 163, 90, 20);
		panel.add(lblNewLabel_1);
		customTextField textField_ngaySinh = new customTextField();
		textField_ngaySinh.setText(data[3].toString());
		textField_ngaySinh.setBounds(109, 149, 180, 40);
		panel.add(textField_ngaySinh);
		listTextField.add(textField_ngaySinh);
		
		JLabel lblNewLabel_3 = new JLabel("Địa chỉ: "); // 3
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 501, 47, 20);
		panel.add(lblNewLabel_3);
		customTextField textField_diaChi = new customTextField();
		textField_diaChi.setText(data[4].toString());
		textField_diaChi.setBounds(109, 495, 400, 40);
		panel.add(textField_diaChi);
		listTextField.add(textField_diaChi);
		
		JLabel lblNewLabel_4 = new JLabel("Số điện thoại: "); //4
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 456, 90, 20);
		panel.add(lblNewLabel_4);
		customTextField textField_sdt = new customTextField();
		textField_sdt.setText(data[5].toString());
		textField_sdt.setBounds(109, 456, 180, 40);
		panel.add(textField_sdt);
		listTextField.add(textField_sdt);
		
		JLabel lblNewLabel_5 = new JLabel("Dân tộc: "); // 5
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 313, 90, 20);
		panel.add(lblNewLabel_5);
		customTextField textField_danToc = new customTextField();
		textField_danToc.setText(data[6].toString());
		textField_danToc.setBounds(109, 307, 180, 40);
		panel.add(textField_danToc);
		listTextField.add(textField_danToc);
		
		JLabel lblNewLabel_6 = new JLabel("Tôn giáo: ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 259, 90, 20);
		panel.add(lblNewLabel_6);
		
		JComboBox<String> cbbTonGiao = new JComboBox<>();
		for (String i : tongiao) {
			if (i.equals(data[7].toString())) {
				cbbTonGiao.addItem(i);
				cbbTonGiao.setSelectedItem(i);
			}
			else {
				cbbTonGiao.addItem(i);
			}
		}
		cbbTonGiao.setBounds(109, 259, 115, 30);
		panel.add(cbbTonGiao);
		
		JLabel lblNewLabel_7 = new JLabel("Email: "); // 6
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(10, 409, 90, 20);
		panel.add(lblNewLabel_7);
		customTextField textField_email = new customTextField();
		textField_email.setText(data[8].toString());
		textField_email.setBounds(109, 409, 180, 40);
		panel.add(textField_email);
		listTextField.add(textField_email);
		
		JLabel lblNewLabel_8 = new JLabel("Tên phòng");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(354, 56, 90, 20);
		panel.add(lblNewLabel_8);
		JComboBox<String> phongBan = new JComboBox<String>();
		phongBan.setBounds(448, 50, 180, 30);
		for (String pb : phongban_BUS.getTenPhongBan()) {
			if (pb.equals(data[9].toString())) {
				phongBan.addItem(pb);
				phongBan.setSelectedItem(pb);
			}
			else {
				phongBan.addItem(pb);
			}
			
		}
		panel.add(phongBan);
		

		JLabel lblNewLabel_16 = new JLabel("Trình độ học vấn");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_16.setBounds(354, 263, 115, 20);
		panel.add(lblNewLabel_16);
		JComboBox<String> cbbHocVan = new JComboBox<>();
		cbbHocVan.setBounds(506, 255, 122, 30);
		for (String hv : hocVan) {
			if (data[10].toString().equals(hv)) {
				cbbHocVan.addItem(hv);
				cbbHocVan.setSelectedItem(hv);
			}
			else 
				cbbHocVan.addItem(hv);
		}
		panel.add(cbbHocVan);

		JLabel lblNewLabel_17 = new JLabel("Trình độ chuyên môn");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_17.setBounds(354, 318, 126, 20);
		panel.add(lblNewLabel_17);
		JComboBox<String> cbbChuyenMon = new JComboBox<>();
		cbbChuyenMon.setBounds(506, 310, 122, 30);
		for (String cm : chuyenMonString) {
			if (data[11].toString().equals(cm)) {
				cbbChuyenMon.addItem(cm);
				cbbChuyenMon.setSelectedItem(cm);
			}
			else 
				cbbChuyenMon.addItem(cm);
		}
		panel.add(cbbChuyenMon);

		
		JLabel lblNewLabel_9 = new JLabel("Chuyên ngành"); // 7 thoải mái cái tên
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(354, 105, 90, 20);
		panel.add(lblNewLabel_9);
		customTextField textField_trinhDo = new customTextField();
		textField_trinhDo.setText(data[12].toString());
		textField_trinhDo.setBounds(443, 99, 185, 40);
		panel.add(textField_trinhDo);
		listTextField.add(textField_trinhDo);
		
		JLabel lblNewLabel_10 = new JLabel("Chức vụ: ");  // 8 thoải mái cái tên 
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(354, 156, 90, 20);
		panel.add(lblNewLabel_10);
		customTextField textField_chucVu = new customTextField();
		textField_chucVu.setText(data[13].toString());
		textField_chucVu.setBounds(443, 149, 185, 40);
		panel.add(textField_chucVu);
		listTextField.add(textField_chucVu);
	
		JLabel lblNewLabel_11 = new JLabel("Mức lương: "); // 9
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(354, 209, 90, 20);
		panel.add(lblNewLabel_11);
		customTextField textField_mucLuong = new customTextField();
		textField_mucLuong.setText(data[14].toString());
		textField_mucLuong.setBounds(443, 203, 185, 40);
		panel.add(textField_mucLuong);
		listTextField.add(textField_mucLuong);
		
		

		setNonEditable();

		JButton btnNewButton = new JButton("Thoát");
		btnNewButton.setBounds(343, 457, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
		panel.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("Chỉnh sửa");
		btnNewButton_1.setBounds(448, 457, 85, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnNewButton_1.getText().equals("Chỉnh sửa")) {
					setEditable();
					btnNewButton_1.setText("Lưu");
				}
				else {
					setNonEditable();
					NHANVIEN nv = DAO.NhanVienDAO.getInstance().getNhanVien(maNhanVien);
					if(!checkInformation()) {
						btnNewButton_1.setText("Chỉnh sửa");
						return;
					}
					btnNewButton_1.setText("Chỉnh sửa");
					nv.setHoTen(listTextField.get(1).getText());
					nv.setGioiTinh(cbbGioiTinh.getSelectedItem().toString());
					String ngaySinh = listTextField.get(2).getText();
					nv.setNgaySinh(toLocalDate(ngaySinh));
					nv.setSdt(listTextField.get(4).getText());
					nv.setEmail(listTextField.get(6).getText());
					String[] diachi = listTextField.get(3).getText().split(", ");
					System.out.println(diachi.length);
					nv.setDiaChi(new DIACHI(diachi[0], diachi[1], diachi[2], diachi[3], diachi[4]));
					nv.setTrinhDo(new TRINHDO(nv.getTrinhDo().getMaTrinhDo(), cbbHocVan.getSelectedItem().toString(), cbbChuyenMon.getSelectedItem().toString(), listTextField.get(7).getText()));
					nv.setDanToc(listTextField.get(5).getText());
					nv.setTonGiao(cbbTonGiao.getSelectedItem().toString());
					
					
					// nv.setMucLuongChung(Double.parseDouble(listTextField.get(8).getText().replaceAll(",", "")));
					
					
					employeePage.setData();

					DAO.NhanVienDAO.getInstance().update(nv);
				}
			}
		});
		panel.add(btnNewButton_1);
		
		
		
		
	}

	public Object[] getData() {
		return data;
	}

	public void setNonEditable() {
		for (customTextField textField : listTextField) {
			textField.setEditable(false);
			textField.hideCaret();
			cbbGioiTinh.setEditable(false);
		}
	}

	public void setEditable() {
		for (customTextField textField : listTextField) {
			textField.setEditable(true);
			textField.showCaret();
			cbbGioiTinh.setEditable(true);

		}
	}


	public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

	public LocalDate toLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, formatter);
	}
	public boolean isValidDateOfBirth(String dateOfBirthString) {
		try {
			LocalDate currentDate = LocalDate.now();
			return !toLocalDate(dateOfBirthString).isAfter(currentDate);
		} 
		catch (Exception e) {
			return false;
		}
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^0\\d{8,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

	public boolean checkAddressFormat(String address) {
        String regex = "^.+(,\\s+.+){3}$"; 
        return address.matches(regex);
    }

	public boolean checkSalary(String salary) {

		try {
			String salaryString = salary.replaceAll(",", "");
			double value = Double.valueOf(salaryString);
			if(value<4000000)	return false;
		}catch (Exception e) {
			return false;
		}
		return true;
	}


	public boolean checkInformation() {
		
		if (listTextField.get(1).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập họ tên nhân viên","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (listTextField.get(2).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập ngày sinh","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!isValidDateOfBirth(listTextField.get(2).getText())) {
			JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		
		if (listTextField.get(3).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!checkAddressFormat(listTextField.get(3).getText())) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không hợp lệ","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}


		if (listTextField.get(4).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!isValidPhoneNumber(listTextField.get(4).getText())) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (listTextField.get(5).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập dân tộc","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (listTextField.get(6).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập email","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!isValidEmail(listTextField.get(6).getText())) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (listTextField.get(7).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập chuyên ngành","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (listTextField.get(8).getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mức lương","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}	

		if (!checkSalary(listTextField.get(9).getText())) {
			JOptionPane.showMessageDialog(this, "Mức lương không hợp lệ (lương dưới 4 củ và phải đúng định dạng)","Thông báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
		// xong khuc nay thi lay nhan vien roi set lai 

		
	}
}
