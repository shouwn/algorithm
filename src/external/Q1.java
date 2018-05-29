package external;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q1 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(MyScanner scan = MyScannerFactory.makeMyScanner("[0-9]+")){
			scan.find();
			CircularQueue queue = new CircularQueue();
			queue.addToN(scan.read());
			scan.find();
			int k = scan.read();
			scan.find();
			int l = scan.read();
			
			for(int i = k; queue.isOver(); i += l) {
				queue.findAndRemove(i);
				System.out.println(queue.list);
			}
			
			System.out.println(queue.getLast());
		}
	}
	
	static class CircularQueue {
		List<Integer> list = new ArrayList<>();
		
		int index;
		
		public void addToN(int n) {
			for(int i = 1; i <= n; i++)
				list.add(i);
		}
		
		public void findAndRemove(int n) {
			index = (n + index) % list.size();
			list.remove(index--);
		}
		
		public boolean isOver() {
			return list.size() != 1;
		}
		
		public int getLast() {
			return list.get(0);
		}
			
	}
	
	static class MyScanner implements AutoCloseable{

		private BufferedReader reader;
		private String regex;
		private Pattern pattern;
		private Matcher matcher;

		public void setReader(BufferedReader reader) {
			this.reader = reader;
		}
		public void setRegex(String regex) throws IOException {
			this.regex = regex;
			pattern = Pattern.compile(this.regex);
			matcher = pattern.matcher(reader.readLine());
		}

		// 정규식에 해당하는 문자열이 있는지 확인하는 메소드
		public boolean find() throws IOException {
			if(matcher.find()) {
				return true;
			}
			else {
				String line;
				while(!matcher.find()) {
					line = reader.readLine();
					if(line == null)
						return false;
					matcher = pattern.matcher(line);
				}

				return true;
			}
		}

		// 찾은 문자열을 return 해주는 메소드
		public int read() throws IOException{

			return Integer.valueOf(matcher.group(0));
		}

		@Override
		public void close() throws IOException {
			reader.close();
		}
	}

	static class MyScannerFactory{
		public static MyScanner makeMyScanner(String regex) 
				throws FileNotFoundException, IOException{

			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(System.in));
			MyScanner scan = new MyScanner();
			scan.setReader(reader);
			scan.setRegex(regex);
			return scan;
		}
	}
	
}

