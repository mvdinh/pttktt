package IndexMaxPQ;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer>{
	private int n;
	private int[] pq;
	private int[] qp;
	private Key[] keys;
	public IndexMaxPQ(int cap) {
		if(cap < 0) throw new IllegalArgumentException();
		n = 0;
		keys = (Key[]) new Comparable[cap + 1];
		pq = new int[cap + 1];
		qp = new int[cap + 1];
		for(int i = 0; i <= cap; i++) {
			qp[i] = -1;
		}
	}
	public boolean isEmpty() {
		return n == 0;
	}
	public boolean contains(int i) {
		return qp[i] != -1;
	}
	public int size() {
		return n;
	}
	public void insert(int i, Key key) {
		if(contains(i)) throw new IllegalArgumentException();
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}
	public int maxIndex() {
		if(n == 0) throw new NoSuchElementException();
		return pq[1];
	}
	public Key maxKey() {
		if(n == 0) throw new NoSuchElementException();
		return keys[pq[1]];
	}
	public int delMax() {
		if(n == 0) throw new NoSuchElementException();
		int min = pq[1];
		exch(1, n--);
		sink(1);
		qp[min] = -1;
		keys[min] = null;
		pq[n + 1] = -1;
		return min;
	}
	public Key keyOf(int i) {
        if (!contains(i)) throw new NoSuchElementException();
        else return keys[i];
    }
	public void changeKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException();
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }
	public void change(int i, Key key) {
        changeKey(i, key);
    }
	public void increaseKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException();
        if (keys[i].compareTo(key) >= 0)
            throw new IllegalArgumentException();
        keys[i] = key;
        swim(qp[i]);
    }
	public void decreaseKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException();
        if (keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException();
        keys[i] = key;
        sink(qp[i]);
    }
	public void delete(int i) {
        if (!contains(i)) throw new NoSuchElementException();
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }
	public boolean less(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}
	public void exch(int i, int j) {
		int tmp = pq[i];
		pq[i] = pq[j];
		pq[j] = tmp;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	public void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k;
			if(j < n && less(j, j + 1)) j++;
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	@Override
	public Iterator<Integer> iterator() {
		return new HeapIterator();
	}
	private class HeapIterator implements Iterator<Integer> {
        private IndexMaxPQ<Key> copy;
        public HeapIterator() {
            copy = new IndexMaxPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }
        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }
	public static void main(String[] args) {
		String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
        IndexMaxPQ<String> pq = new IndexMaxPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }
        for (Integer i : pq) {
			System.out.println(i + " " + pq.keyOf(i));
		}
        System.out.println();
        Random random = new Random();
        for (int i = 0; i < strings.length; i++) {
        	int a = random.nextInt();
            if (a % 2 == 0)
                pq.increaseKey(i, strings[i] + strings[i]);
            else
                pq.decreaseKey(i, strings[i].substring(0, 1));
        }
        while (!pq.isEmpty()) {
            String key = pq.maxKey();
            int i = pq.delMax();
            System.out.println(i + " " + key);
        }
        System.out.println();
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }
        pq.delete(9);
        for (Integer i : pq) {
			System.out.println(i + " " + pq.keyOf(i));
		}
	}
}