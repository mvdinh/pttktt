package HashMap;

import java.util.Comparator;




/**
 * Write a description of class SinhVien here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SinhVien implements Comparable<SinhVien>
{
    private String hoDem;
    private String ten;
    private VNDate ngaySinh;
    
    public SinhVien(String hoDem, String ten, VNDate ngaySinh) {
		super();
		this.hoDem = hoDem;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
	}
    
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTen(){
        return ten;
    }
    
    public void setTen(String ten){
        this.ten = ten;
    }
    
    public String getHoDem(){
        return hoDem;
    }
    
    public void setHoDem(String hoDem){
        this.hoDem = hoDem;
    }
    
    public VNDate getNgaySinh(){
        return ngaySinh;
    }
    
    public void setNgaySinh(String ngaySinh){
        this.ngaySinh = new VNDate(ngaySinh);
    }
    
    
    	 @Override
    	    public int compareTo(SinhVien other){
    	        int compareTen = this.getTen().compareTo(other.getTen());
    	        if (compareTen != 0) {
    	            return compareTen;
    	        }
    	                
    	        int compareHoDem = this.getHoDem().compareTo(other.getHoDem());
    	        if (compareHoDem != 0) {
    	            return compareHoDem;
    	        }
    	                
    	        return this.getNgaySinh().compareTo(other.getNgaySinh());
    	    }
    

	@Override
	public String toString() {
		return "SinhVien:" + hoDem +" " +ten + " ngaySinh=" + ngaySinh;
	}
    
    
}