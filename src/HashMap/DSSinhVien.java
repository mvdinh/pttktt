package HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class DSSinhVien {
    private ArrayList<SinhVien> dsSinhVien;
    private HashMap<SinhVien, String> dssv;

    public DSSinhVien(String tenFile) {
        dsSinhVien = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(tenFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {  
                    SinhVien sv = new SinhVien();
                    String[] fields = line.split(",");
                    sv.setHoDem(fields[0]);
                    sv.setTen(fields[1]);
                    sv.setNgaySinh(fields[2]);
                    dsSinhVien.add(sv);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.sort(dsSinhVien);  // Sắp xếp danh sách sinh viên
    }

    public void themSinhVien() {
        dssv = new HashMap<>();
        int i = 0;
        for (SinhVien sinhVien : dsSinhVien) {
            dssv.put(sinhVien, "SV" + (i++));  // Thêm sinh viên vào HashMap với mã SV
        }
    }

    public void inDSSinhVien() {
        if (dsSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
        } else {
            for (SinhVien sv : dsSinhVien) {
                String ma = dssv.get(sv);
                System.out.println(sv.toString() + ", Mã SV: " + ma);  // In thông tin sinh viên và mã SV
            }
        }
    }

    public static void main(String[] args) {
        DSSinhVien ds = new DSSinhVien("F:\\dssv.csv");
        ds.themSinhVien();
        ds.inDSSinhVien();
    }
}
