package FileSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileFrequencyIndex {
	private ST<String, ST<File, Integer>> st;
	
	public FileFrequencyIndex(String[] files){
		//hàm tạo là chuỗi các file cần xử lí
	}
	public int query(String word) { Tìm kiếm xem ‘key’ xuất hiện trong các tệp nào và tần suất xuất hiện trong mỗi tệp
		if(st.contains(word)) {
			return st.get(word);
		}
		return 0;
	}
	public static void main(String[] args) throws IOException {
		String name = "F:\\tiny.txt.txt";
		FileFrequencyIndex f = new FileFrequencyIndex(name);
		System.out.println(f.query("jaw"));
	}
}