package TopM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DSSinhVien {
    private ArrayList<SinhVien> dsSinhVien;

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
                    double diemTBC = Double.parseDouble(fields[3]);
                    sv.setDiemTBC(diemTBC);
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
    }

    public void inDSSinhVien() {
        if (dsSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
        } else {
            int i = 0;
            for (SinhVien sv : dsSinhVien) {
                String ma = "SV" + i++;
                System.out.println(sv.toString() + ", Mã SV: " + ma);
            }
        }
    }

    public void inTopMSinhVien(int M, Comparator<SinhVien> comparator) {
        List<SinhVien> sortedList = new ArrayList<>(dsSinhVien);
        sortedList.sort(comparator);
        for (int i = 0; i < Math.min(M, sortedList.size()); i++) {
            SinhVien sv = sortedList.get(i);
            String ma = "SV" + dsSinhVien.indexOf(sv);
            System.out.println(sv.toString() + ", Mã SV: " + ma);
        }
    }

    public static void main(String[] args) {
        DSSinhVien ds = new DSSinhVien("F:\\a.csv");
        System.out.println("Danh sách sinh viên:");
        ds.inDSSinhVien();

        System.out.println("\nTop M sinh viên trẻ nhất:");
        ds.inTopMSinhVien(1, SinhVien.compareByNgaySinh());

        System.out.println("\nTop M sinh viên điểm TBC cao nhất:");
        ds.inTopMSinhVien(1, SinhVien.compareByDiemTBC());

        System.out.println("\nTop M sinh viên theo tên:");
        ds.inTopMSinhVien(1, SinhVien.compareByTen());

        
    }
}
