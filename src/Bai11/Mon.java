package Bai11;


public class Mon implements Comparable<Mon> {
    private String name;
    private int hocKy;
    public Mon(String name, int hocKy) {
        this.name = name;
        this.hocKy = hocKy;
    }
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHocKy() {
        return hocKy;
    }
    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }
    @Override
    public int compareTo(Mon otherMon) {
        int compareByName = this.name.compareTo(otherMon.getName());
        if (compareByName == 0) {
            return Integer.compare(this.hocKy, otherMon.getHocKy());
        }
        return compareByName;
    }
}