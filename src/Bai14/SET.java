package Bai14;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class SET<Key extends Comparable<Key>> implements Iterable<Key>{
	private TreeSet<Key> set;
	public SET() { // Khởi tạo một đối tượng SET mới
		set = new TreeSet<Key>();
	}
	public SET(SET<Key> x) { // Nhận một đối tượng SET khác làm đối số và làm bản sao độc lập của nó
		this.set = new TreeSet<Key>(x.set);
	}
	public void add(Key key) { // Thêm một phần tử vào SET
        if (key == null) throw new IllegalArgumentException();
        set.add(key);
    }
	public boolean contains(Key key) { // Kiểm tra xem trong set có chứ key không
        if (key == null) throw new IllegalArgumentException();
        return set.contains(key);
    }
	public void delete(Key key) { // Xóa một phần tử khỏi tập hợp(Nếu nó tồn tại)
        if (key == null) throw new IllegalArgumentException();
        set.remove(key);
    }
	public int size() { //  Trả về kích thước của tập hợp
        return set.size();
    }
	public boolean isEmpty() { // Kiểm tra xem tập hợp có rỗng không
        return size() == 0;
    }
	public Iterator<Key> iterator() { //  Trả về một trình vòng lặp để lặp qua tất cả phần tử trong tập hợp
        return set.iterator();
    }
	public Key max() { // Trả về phần tử lớn nhất trong tập hợp
        if (isEmpty()) throw new NoSuchElementException();
        return set.last();
    }
	public Key min() { // Trả về phần tử nhỏ nhất trong tập hợp
        if (isEmpty()) throw new NoSuchElementException();
        return set.first();
    }
	public Key ceiling(Key key) { // Trả về phần tử phần tử lớn nhất trong tập hợp mà >= key
        if (key == null) throw new IllegalArgumentException();
        Key k = set.ceiling(key);
        if (k == null) throw new NoSuchElementException();
        return k;
    }
	public Key floor(Key key) { // Trả về phần tử nhỏ nhất trong tập hợp mà <= key
        if (key == null) throw new IllegalArgumentException();
        Key k = set.floor(key);
        if (k == null) throw new NoSuchElementException();
        return k;
    }
	public SET<Key> union(SET<Key> that) { // Trả về một tập hợp là hợp của 2 tập hợp
        if (that == null) throw new IllegalArgumentException();
        SET<Key> c = new SET<Key>();
        for (Key x : this) {
            c.add(x);
        }
        for (Key x : that) {
            c.add(x);
        }
        return c;
    }
	public SET<Key> intersects(SET<Key> that) { // Trả về một tập hợp là giao của 2 tập hợp
        if (that == null) throw new IllegalArgumentException();
        SET<Key> c = new SET<Key>();
        if (this.size() < that.size()) {
            for (Key x : this) {
                if (that.contains(x)) c.add(x);
            }
        }
        else {
            for (Key x : that) {
                if (this.contains(x)) c.add(x);
            }
        }
        return c;
    }
	@Override
    public boolean equals(Object other) { // So sánh hai tập hợp xem chúng có bằng nhau không
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        SET that = (SET) other;
        return this.set.equals(that.set);
    }
	@Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
	@Override
    public String toString() {
        String s = set.toString();
        return "{ " + s.substring(1, s.length() - 1) + " }";
    }
}
