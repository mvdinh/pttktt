package BTSV;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Diem {
    private Map<String, Integer> diemMon;
    private int hocKy;

   

	public Diem(String mon, int diem , int  hocky ) {
    	Map<String, Integer> diemMon = new HashMap<>();
    	diemMon.put(mon, diem);
    	this.diemMon=diemMon;
    	this.hocKy=hocky;
    }
    public Map<String, Integer> getDiemMon() {
        return diemMon;
    }

    public void setDiemMon(Map<String, Integer> diemMon) {
        this.diemMon = diemMon;
    }

    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

   

   
    public void in() {
        for (Map.Entry<String, Integer> entry : diemMon.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
