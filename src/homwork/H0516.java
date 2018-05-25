package homwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordInfo {
	String word;
	int count;

	public WordInfo(String word) {
		this.word = word;
		this.count = 1;
	}

	public void countUp() {
		count++;
	}

	public int getCount() {
		return count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordInfo other = (WordInfo) obj;
		if (count != other.count)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

}

// 정규식을 세팅하면 그 정규식에 맞는 다음 문자열을 찾는 클래스
class MyScanner implements AutoCloseable{

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
	public String read() throws IOException{

		return matcher.group(0);
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}

class MyScannerFactory{
	public static MyScanner makeMyScanner(String filePath, String regex) 
			throws FileNotFoundException, IOException{

		BufferedReader reader = 
				new BufferedReader(new FileReader(new File(filePath)));
		MyScanner scan = new MyScanner();
		scan.setReader(reader);
		scan.setRegex(regex);
		return scan;
	}
}

public class H0516 {

	List<WordInfo> letterList = new ArrayList<>();
	Map<WordInfo, Integer> letterMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		scanTxt();
	}

	public static void solution1() throws FileNotFoundException, IOException {

	}

	public static void solution2() throws FileNotFoundException, IOException {

	}

	public static void solution3() throws FileNotFoundException, IOException {

	}

	public static void scanTxt() throws FileNotFoundException, IOException{
		String regex = "[A-Za-z]+";
		try(MyScanner scan = MyScannerFactory.makeMyScanner("shakespeare.txt", regex)){

			while(scan.find()) {
				System.out.println(scan.read());
			}
		}
	}

	public static int getMin(int[] arr) {
		int min = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(min > arr[i])
				min = arr[i];
		}

		return min;
	}

}
