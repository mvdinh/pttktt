package BTSV;


import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lop {
    private static final String WorkbookFactory = null;
	private List<SinhVien> danhSachSinhVien;

    public Lop() {
        this.danhSachSinhVien = new ArrayList<>();
    }

    public void addSinhVien(SinhVien sv) {
        danhSachSinhVien.add(sv);
    }

    public void docDuLieuTuFileExcel(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = Workbook.getWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        // Bỏ qua dòng tiêu đề
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            String hoDem = "";
            String ten = "";
            String ngaySinh = "";
            String queQuan = "";
            double diemToan = 0;
            double diemLy = 0;
            double diemHoa = 0;
            int hocky = 0;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        hoDem = cell.getStringCellValue();
                        break;
                    case 1:
                        ten = cell.getStringCellValue();
                        break;
                    case 2:
                        ngaySinh = cell.getStringCellValue();
                        break;
                    case 3:
                        queQuan = cell.getStringCellValue();
                        break;
                    case 4:
                        diemToan = cell.getNumericCellValue();
                        break;
                    case 5:
                        diemLy = cell.getNumericCellValue();
                        break;
                    case 6:
                        diemHoa = cell.getNumericCellValue();
                        break;
                    case 7:
                        hocky = (int) cell.getNumericCellValue();
                        break;
                    default:
                        break;
                }
            }

            SinhVien sv = new SinhVien(hoDem, ten, ngaySinh, queQuan);
            // Thêm điểm cho từng môn học và học kỳ
            sv.themDiem("Toan", Integer.toString(hocky), diemToan);
            sv.themDiem("Ly", Integer.toString(hocky), diemLy);
            sv.themDiem("Hoa", Integer.toString(hocky), diemHoa);

            addSinhVien(sv);
        }

        fis.close();
    }

    public void tinhTBCChoLop() {
        for (SinhVien sv : danhSachSinhVien) {
            double tbc = sv.tinhDiemTBC();
            System.out.println("Sinh vien " + sv.getTen() + " co diem trung binh cong la: " + tbc);
        }
    }

    public static void main(String args[]) throws IOException {
        Lop lop = new Lop();
        String filePath = "F:\\Book1.xlsx";
        lop.docDuLieuTuFileExcel(filePath);

        lop.tinhTBCChoLop();
    }
}
