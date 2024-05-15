package TopM;

import java.util.Comparator;

import HashMap.VNDate;


public class SinhVien implements Comparable<SinhVien>
{
    private String hoDem;
    private String ten;
    private VNDate ngaySinh;
    private double DiemTBC;
    
    
    
	public String getHoDem() {
		return hoDem;
	}

	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public VNDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = new VNDate(ngaySinh);
	}

	public double getDiemTBC() {
		return DiemTBC;
	}

	public void setDiemTBC(double diemTBC) {
		DiemTBC = diemTBC;
	}

	public SinhVien(String hoDem, String ten, VNDate ngaySinh, double diemTBC) {
		super();
		this.hoDem = hoDem;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		DiemTBC = diemTBC;
	}
	
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SinhVien:" + hoDem +  ten + " ngaySinh=" + ngaySinh +" "+  DiemTBC;
	}
    
	 @Override
	    public int compareTo(SinhVien o) {
	        int ten = this.ten.compareTo(o.ten);
	        if (ten != 0) {
	            return ten;
	        }
	        return this.hoDem.compareTo(o.hoDem);
	    }

	    public static Comparator<SinhVien> compareByNgaySinh() {
	    	return (sv1, sv2) -> {
	            int year = Integer.compare(sv2.ngaySinh.year(), sv1.ngaySinh.year());
	            if (year != 0) {
	                return year;
	            }
	            int month = Integer.compare(sv2.ngaySinh.month(), sv1.ngaySinh.month());
	            if (month!= 0) {
	                return month;
	            }
	            return Integer.compare(sv2.ngaySinh.day(), sv1.ngaySinh.day());
	        };
	        
	    }

	    public static Comparator<SinhVien> compareByDiemTBC() {
	        return (sv1, sv2) -> Double.compare(sv2.DiemTBC, sv1.DiemTBC); // Sắp xếp giảm dần
	    }

	    public static Comparator<SinhVien> compareByTen() {
	        return (sv1, sv2) -> sv1.ten.compareTo(sv2.ten);
	    }

	    
	}
