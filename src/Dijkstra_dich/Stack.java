package Dijkstra_dich;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
	private int n;
	private Node<Item> first;
	public static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	public Stack() {
		this.n = 0;
		this.first = null;
	}
	public int size() {
		return this.n;
	}
	public boolean isEmpty() {
		return this.n == 0;
	}
	public void push(Item item) {
		Node<Item> tmp = this.first;
		this.first = new Node<Item>();
		this.first.item = item;
		this.first.next = tmp;
		n++;
	}
	public Item pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Node<Item> tmp = this.first;
		this.first = this.first.next;
		n--;
		return tmp.item;
	}
	public Item peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return this.first.item;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Item i : this) {
			s.append(i);
			s.append(" ");
		}
		return s.toString();
	}
	@Override
	public Iterator<Item> iterator() {
        return new ListIterator<Item>(this.first);
    }
    private class ListIterator<Item> implements Iterator<Item>{
    	private Node<Item> tmp;
		public ListIterator(Node<Item> tmp) {
			this.tmp = tmp;
		}
		@Override
		public boolean hasNext() {
			return tmp != null;
		}
		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = this.tmp.item;
			this.tmp = this.tmp.next;
			return item;
		}
    }
}