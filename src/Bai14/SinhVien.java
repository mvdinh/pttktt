package Bai14;

import java.util.Comparator;

public class SinhVien implements Comparable<SinhVien>{
	private int maSV;
	private String name, address;
	private Date age;
	private double tbc;
	public SinhVien(int maSV, String name, String address, Date date, double tbc) {
		super();
		this.maSV = maSV;
		this.name = name;
		this.address = address;
		this.age = date;
		this.tbc = tbc;
	}
	public Date getAge() {
		return age;
	}
	public void setAge(Date age) {
		this.age = age;
	}
	public double getTbc() {
		return tbc;
	}

	public void setTbc(double tbc) {
		this.tbc = tbc;
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
	public Date getDate() {
		return age;
	}
	public void setDate(Date date) {
		this.age = date;
	}
	@Override
	public String toString() {
		return this.maSV + " " + this.name + " " + this.age.toString() + " "+address+" " + this.tbc;
	}
	@Override
	public int compareTo(SinhVien o) {
		return Comparators.TBC_COMPARATOR.compare(this, o);
	}
	public static class Comparators {
        public static Comparator<SinhVien> NAME_COMPARATOR = new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
                String[] ten1 = sv1.name.split(" ");
                String[] ten2 = sv2.name.split(" ");
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
        
        public static Comparator<SinhVien> TBC_COMPARATOR = new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
            	if(Double.compare(sv1.tbc, sv2.tbc) != 0) {
            		return Double.compare(sv1.tbc, sv2.tbc);
            	}
            	return sv2.maSV - sv1.maSV;
            }
        };
    }
}