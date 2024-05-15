package Bai11;

import java.util.Comparator;

public class Quick<Key extends Comparable<Key>>{
	public Quick() {}
	private int partition(Key[] a, int lo, int hi, Comparator<Key> comparator) {
		int i = lo;
		int j = hi + 1;
		Key v = a[lo];
		while(true) {
			while(less(a[++i], v, comparator)) {
				if (i == hi) break;
			}
			while(less(v, a[--j], comparator)) {
				if (j == lo) break;
			}
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
        return j;
	}
	public Key select(Key[] a, int k, Comparator<Key> comparator) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException();
        }
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi, comparator);
            if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }
	public void printKey(Key[] a, int k, Comparator<Key> comparator) {
		Key key = this.select(a, k, comparator);
		for(int i = 0; i < k; i++) {
			System.out.println(a[i].toString());
		}
	}
	private void sort(Key[] a, int lo, int hi, Comparator<Key> comparator) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi, comparator);
        sort(a, lo, j - 1, comparator);
        sort(a, j + 1, hi, comparator);
    }
	public void sort(Key[] a, Comparator<Key> comparator) {
        sort(a, 0, a.length - 1, comparator);
    }
	private boolean less(Key v, Key w, Comparator<Key> comparator) {
        return comparator.compare(v, w) < 0;
    }
	private void exch(Key[] a, int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}