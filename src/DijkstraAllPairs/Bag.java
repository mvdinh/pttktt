package DijkstraAllPairs;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
	private Node<Item> first;
	private int n;
	private class Node<Item>{
		private Item elem;
		private Node<Item> next;
	}
	public Bag() {
		this.first = null;
		this.n = 0;
	}
	public int size() {
		return n;
	}
	public boolean empty() {
		return n == 0;
	}
	public void add(Item item) {
		Node<Item> tmp = this.first;
		this.first = new Node<Item>();
		this.first.elem = item;
		this.first.next = tmp;
		this.n++;
	}
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		public ListIterator(Node<Item> first) {
            current = first;
        }
		@Override
		public boolean hasNext() {
			return current != null;
		}
		@Override
		public Item next() {
			Item item = current.elem;
			current = current.next;
			return item;
		}
	}
}