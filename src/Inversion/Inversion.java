package Inversion;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Inversion {
	private Inversion() {};
	private static <Key extends Comparable<Key>> long merge(Key a[], Key aux[], int l, int m, int h) {
		long inversion = 0;
		for(int i = l; i <= h; i++) {
			aux[i]  = a[i];
		}
		int i = l, j = m + 1;
		for(int k = l; k <= h; k++) {
			if(i > m) a[k] = a[j++];
			else if(j > h) a[k] = aux[i++];
			else if(less(aux[j], aux[i])) {
				a[k] = aux[j++];
				inversion += (m - i + 1);
			} else a[k] = aux[i++];
		}
		return inversion;
	}
	private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
	    if (v == null || w == null) {
	        throw new IllegalArgumentException();
	    }
	    return v.compareTo(w) < 0;
	}
	private static <Key extends Comparable<Key>> long count(Key a[], Key b[], Key aux[], int l, int h) {
		if(h <= l) return 0;
		long inversion = 0;
		int m = l + (h - l)/2;
		inversion += count(a, b, aux, l, m);
		inversion += count(a, b, aux, m + 1, h);
		inversion += merge(b, aux, l, m, h);
		assert inversion == brute(a, l, h);
		return inversion;
	}
	public static <Key extends Comparable<Key>> long count(Key a[]) {
		Key[] b = a.clone();
		Key[] aux = a.clone();
		long inversion = count(a, b, aux, 0, a.length - 1);
		return inversion;
	}
	private static <Key extends Comparable<Key>> void sort(Key a[], Key aux[], int lo, int hi) {
	    if (hi <= lo) return;
	    int mid = lo + (hi - lo) / 2;
	    sort(a, aux, lo, mid); 
	    sort(a, aux, mid + 1, hi);
	    merge(a, aux, lo, mid, hi); 
	}
	public static <Key extends Comparable<Key>> void sort(Key a[]) {
	    Key[] aux = a.clone(); 
	    sort(a, aux, 0, a.length - 1);
	}
	private static <Key extends Comparable<Key>> long brute(Key[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (less(a[j], a[i])) inversions++;
        return inversions;
    }
}