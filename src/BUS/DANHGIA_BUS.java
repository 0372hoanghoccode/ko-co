package BUS;

import java.util.ArrayList;

import DAO.DanhGiaDAO;
import DAO.NhanVienDAO;
import DTO.CHAMCONG;
import DTO.DANHGIA;
import DTO.NHANVIEN;

public class DANHGIA_BUS {
	private final DanhGiaDAO danhGia_dao= new DanhGiaDAO();
	private ArrayList<DANHGIA> list_danhGia= new ArrayList<>();
	private ArrayList<DANHGIA> list_nhanVienChuaDG=new ArrayList<>();
	
	
	public DANHGIA_BUS() {
		list_danhGia=danhGia_dao.getList();
		list_nhanVienChuaDG=danhGia_dao.getListNVChuaDG();
	}
	
	public ArrayList<DANHGIA> getList(){
		return list_danhGia;
	}
	
	public int insertDanhGia(DANHGIA dg) {
		int result= danhGia_dao.insert(dg);
		if (result > 0) {
			list_danhGia.add(dg);
		}
		return result;
	}
	
	public int updateDanhGia(DANHGIA dg) {
		int result = danhGia_dao.update(dg);
		if (result >0) {
			list_danhGia.set(getMaDanhGia(dg.getMaDanhGia()), dg);
		}
		return result;
	}
	
	public int delDanhGia(String maDanhgia) {
		int result = danhGia_dao.del(maDanhgia);
		if (result > 0) {
			list_danhGia.remove(getMaDanhGia(maDanhgia));
		}
		return result;
	}
	
	private int getMaDanhGia(String maDanhGia) {
		int i=0;
		int vitri =-1;
		while (i< this.list_danhGia.size() && vitri==-1) {
			if (list_danhGia.get(i).getMaDanhGia().equals(maDanhGia))
				vitri=i;
			else 
				i++;
		}
		return vitri;
	}
	
	public Object[][] renderAllDanhGiaData(){
		Object[][] data= new Object[list_danhGia.size()][];
		for (int i=0; i< list_danhGia.size(); i++) {
			DANHGIA temp_dg=list_danhGia.get(i);
			String nguoiDanhGia=danhGia_dao.getNguoiDanhGia(temp_dg.getMaDanhGia());
			String tenNhanVien=danhGia_dao.getTenNhanVien(temp_dg.getMaDanhGia());
			data[i]= new Object[] {
					i+1+"",
					temp_dg.getMaDanhGia(),
					tenNhanVien,
					temp_dg.getNgayDanhGia(),
					nguoiDanhGia,
					temp_dg.getDiemDanhGia(),
					temp_dg.getXepLoaiDanhGia(),
					temp_dg.getLoaiDanhGia(),
			};
		}
		return data;
	}
	
	public Object[][] renderDSNhanVienCanDG(){
		Object[][] data= new Object[list_nhanVienChuaDG.size()][];
		for (int i=0; i<list_nhanVienChuaDG.size(); i++) {
			DANHGIA temp_dg=list_nhanVienChuaDG.get(i);
			String tenNhanVien=danhGia_dao.getTenNVChuaDG(temp_dg.getMaNhanVien());
			data[i] = new Object[] {
					i+1+"",
					tenNhanVien,
			};
		}
		return data;
	}
	
	public Object[][] renderChiTietDanhGia(String maDanhGia){
		Object[][] data= new Object[list_danhGia.size()][];
		for (int i=0; i<list_danhGia.size(); i++) {
			if (maDanhGia.equals(list_danhGia.get(i).getMaDanhGia())) {
				DANHGIA temp_dg=list_danhGia.get(i);
				String tenNhanVien=danhGia_dao.getTenNhanVien(maDanhGia);
				data[i] = new Object[] {
						i+1+"",
						tenNhanVien,
				};
				break;
			}
		}
		return data;
	}
	
	public Object[][] changeDatavalue(String[] event_name){
		String[] default_val= new String[] {
				"Xếp loại đánh giá","Loại đánh giá","Sắp xếp","Thứ tự"
		};
		ArrayList<DANHGIA> list_render= danhGia_dao.renderChangeValue(default_val,event_name);
		int n=list_render.size();
		Object[][] data= new Object[n][];
		for (int i=0; i<n; i++) {
			DANHGIA temp_dg=list_render.get(i);
			String nguoiDanhGia=danhGia_dao.getNguoiDanhGia(temp_dg.getMaDanhGia());
			String tenNhanVien=danhGia_dao.getTenNhanVien(temp_dg.getMaDanhGia());
			data[i]= new Object[] {
					i+1+"",
					temp_dg.getMaDanhGia(),
					tenNhanVien,
					temp_dg.getNgayDanhGia(),
					nguoiDanhGia,
					temp_dg.getDiemDanhGia(),
					temp_dg.getXepLoaiDanhGia(),
					temp_dg.getLoaiDanhGia(),
			};
		}
		return data;	
	}
	
	
}
