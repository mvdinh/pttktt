package Bai11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class DSLop {
	private ST<String, SET<SinhVien>> stAddress;
	private SinhVien[] arr;
	public DSLop(String nameFile) {
		arr = new SinhVien[14];
		stAddress = new ST<String, SET<SinhVien>>();
		int t = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(nameFile))) {
            String line = reader.readLine();
            Mon mon;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int maSV = Integer.parseInt(data[0]);
                String name = data[1];
                String diachi = data[2];
                Date date = new Date(data[3]);
                arr[t] = new SinhVien(maSV, name, diachi, date);
                if(!stAddress.contains(diachi)) {
                	stAddress.put(diachi, new SET<SinhVien>());
                }
                SET<SinhVien> set = stAddress.get(diachi);
                set.add(arr[t]);
                for(int i = 4; i <= 6; i++) {
                	int Diem = Integer.parseInt(data[i]);
                	mon = new Mon("Môn " + (i - 3), 1);
                	arr[t].NhapdiemmonSV(mon, Diem);
                }
                for(int i = 7; i <= 9; i++) {
                	int Diem = Integer.parseInt(data[i]);
                	mon = new Mon("Môn " + (i - 3), 2);
                	arr[t].NhapdiemmonSV(mon, Diem);
                }
                t++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void inSoSV(int n, int m) {
		Quick<SinhVien> q = new Quick<SinhVien>();
		q.printKey(arr, n, SinhVien.Comparators.TBCKyThu_COMPARATOR(m));
	}
	public void sortName() {
		Quick<SinhVien> q = new Quick<SinhVien>();
		q.sort(arr, SinhVien.Comparators.NAME_COMPARATOR);
	}
	public void sortAge() {
		Quick<SinhVien> q = new Quick<SinhVien>();
		q.sort(arr, SinhVien.Comparators.AGE_COMPARATOR);
	}
	public void queryAddress(String address) {
		if(!stAddress.contains(address)) {
			System.out.println("không tìm thấy địa chỉ");
		} else{
			for(SinhVien s : stAddress.get(address)) {
				System.out.println(s.toString());
			}
		}
	}
	public void output() {
		for (SinhVien sv : arr) {
		    System.out.println(sv);
		}
	}
	public static void main(String[] args) {
		String csvFilePath = "F:\\sinh_vien.csv";
		DSLop d = new DSLop(csvFilePath);
		d.queryAddress("Hà Nội");
	}
}