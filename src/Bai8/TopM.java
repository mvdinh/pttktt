package Bai8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TopM {
	private TopM() { }
	public static void main(String[] args) throws IOException {
		String nameFile = "F:\\test.txt";
		BufferedReader br = new BufferedReader(new FileReader(nameFile));
		MinPQ<Transaction> pq = new MinPQ<Transaction>();
		int m = 5;
		String line = br.readLine();
		while(!line.isEmpty()) {
			Transaction tr = new Transaction(line);
			pq.insert(tr);
			if(pq.size() > m) {
				pq.delMin();
			}
			line = br.readLine();
		}
		Stack<Transaction> st = new Stack<Transaction>();
		for(Transaction t : pq) {
			st.push(t);
		}
		for(Transaction t : st) {
			System.out.println(t.toString());
		}
	}
}