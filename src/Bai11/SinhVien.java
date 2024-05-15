package Bai11;

import java.util.Comparator;


public class SinhVien implements Comparable<SinhVien>{
	private int maSV;
	private String name, address;
	private ST<Mon, Integer> st;
	private Date age;
	public SinhVien(int maSV, String name, String address, Date date) {
		super();
		this.maSV = maSV;
		this.name = name;
		this.address = address;
		this.st = new ST<Mon, Integer>();
		this.age = date;
	}
	public int getMaSV() {
		return maSV;
	}
	public void setMaSV(int maSV) {
		this.maSV = maSV;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ST<Mon, Integer> getKetqua() {
		return st;
	}
	public void setKetqua(ST<Mon, Integer> ketqua) {
		this.st = ketqua;
	}
	public Date getDate() {
		return age;
	}
	public void setDate(Date date) {
		this.age = date;
	}
	public void NhapdiemmonSV(Mon m, Integer diem) {
		if(!st.contains(m)) st.put(m, diem);
		else {
			st.delete(m);
			st.put(m, diem);
		}
	}
	public double TBCHocKy(Integer hocky) {
		int sum = 0, cnt = 0;
		for(Mon m : st) {
			if(m.getHocKy() == hocky) {
				sum += st.get(m);
				cnt++;
			}
		}
		if(cnt == 0) {
			return -1;
		}
		return sum*1.0/cnt;
	}
	public void TBC() {
		int sum = 0, cnt = 0;
		for(Mon m : st) {
			sum += st.get(m);
			cnt++;
		}
		if(cnt == 0) {
			System.out.println("Chưa có kết quả");
		} else {
			System.out.println(sum*1.0/cnt);
		}
	}
	@Override
	public String toString() {
		return this.maSV + " " + this.name + " " + this.age.toString();
	}
	@Override
	public int compareTo(SinhVien o) {
		return Comparators.NAME_COMPARATOR.compare(this, o);
	}
	public static class Comparators {
        public static Comparator<SinhVien> NAME_COMPARATOR = new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
                String[] ten1 = sv1.name.split("\\s+");
                String[] ten2 = sv2.name.split("\\s+");
                String ho1 = ten1[ten1.length - 1];
                String ho2 = ten2[ten2.length - 1];
                int compareTen = ho1.compareTo(ho2);
                if (compareTen != 0) {
                    return compareTen;
                }
                String tenDem1 = (ten1.length > 1) ? ten1[ten1.length - 2] : "";
                String tenDem2 = (ten2.length > 1) ? ten2[ten2.length - 2] : "";
                int compareTenDem = tenDem1.compareTo(tenDem2);
                if (compareTenDem != 0) {
                    return compareTenDem;
                }
                return sv1.maSV - sv2.maSV;
            }
        };

        public static Comparator<SinhVien> AGE_COMPARATOR = new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
            	int ss = sv1.age.compareTo(sv2.age);
            	if(ss == 0) {
            		return sv2.maSV - sv1.maSV;
            	}
                return sv1.age.compareTo(sv2.age);
            }
        };
        
        public static Comparator<SinhVien> TBCKyThu_COMPARATOR(int hocKy) {
            return new Comparator<SinhVien>() {
                @Override
                public int compare(SinhVien sv1, SinhVien sv2) {
                	double tb1 = sv1.TBCHocKy(hocKy);
                    double tb2 = sv2.TBCHocKy(hocKy);
                    if(Double.compare(tb2, tb1) != 0) {
                    	return Double.compare(tb2, tb1);
                    }
                	return sv1.maSV - sv2.maSV;
                }
            };
        }
    }
}