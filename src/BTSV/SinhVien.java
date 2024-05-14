package BTSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SinhVien {
    private String ten;
    private List<Diem> MonHoc;
    public SinhVien() {
    	super();
        this.ten = ten;
        this.MonHoc = new ArrayList<>();
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<Diem> getMonHoc() {
        return MonHoc;
    }

    public void setMonHoc(List<Diem> monHoc) {
        MonHoc = monHoc;
    }

    public void nhapDiemMon(Diem diem) {
        MonHoc.add(diem);
    }
    public void nhapDiemMonSV() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Số lượng môn học muốn nhập:");
        int soMon = scanner.nextInt();
        scanner.nextLine(); 
        for (int j = 1; j <= soMon; j++) {
            System.out.print("Tên môn học:");
            String tenMon = scanner.nextLine();
            System.out.print("Điểm:");
            int diem = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Học kì:");
            int hk = scanner.nextInt();
            scanner.nextLine(); 
            Diem d= new Diem(tenMon,diem,hk);
            MonHoc.add(d);
        }
    }
    public void inBangDiem() {
        System.out.println("Bảng điểm sinh viên " + ten + ":");
        System.out.println("Học kì 1:");
        for (Diem diem : MonHoc) {
            if(diem.getHocKy()==1) {
            	diem.in();
            }
        }
        System.out.println("Học kì 2:");
        for (Diem diem : MonHoc) {
            if(diem.getHocKy()==2) {
            	diem.in();
            }
        }
    }
    public double tinhDiemTBC() {
        double tongDiem = 0;
        int soMon = 0;
        for (Diem diem : MonHoc) {
            for (int diemMon : diem.getDiemMon().values()) {
                tongDiem += diemMon;
                soMon++;
            }
        }
        if (soMon == 0) {
            return 0.0;
        }
        return tongDiem / soMon;
    }


    public double tinhDiemTBCHocKy(int hocKy) {
        double tongDiem = 0;
        int soMon = 0;
        
        for (Diem diem : MonHoc) {
            if (diem.getHocKy() == hocKy) {
                for (int diemMon : diem.getDiemMon().values()) {
                    tongDiem += diemMon;
                    soMon++;
                }
            }
        }
        
        if (soMon == 0) {
            return 0.0;
        }
        
        return tongDiem / soMon;
    }

    public static void main(String[] args) {
        SinhVien sinhVien = new SinhVien();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap thong tin sinh vien:");
       
        System.out.print("Ten: ");
        String ten = scanner.nextLine();
        
        
        sinhVien.nhapDiemMonSV();
        sinhVien.inBangDiem();
        System.out.println("Diem trung binh cua sinh vien: " + sinhVien.tinhDiemTBC());
        System.out.println("Diem trung binh cua hoc ky " + 1 + ": " + sinhVien.tinhDiemTBCHocKy(1));
        System.out.println("Diem trung binh cua hoc ky " + 2 + ": " + sinhVien.tinhDiemTBCHocKy(2));
    }
    
}
