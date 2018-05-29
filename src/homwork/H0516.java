package homwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordInfo implements Comparable<WordInfo>{
	String word;
	int count;
	int wordNumber;

	public void setWord(String word) {
		this.word = word;
		this.wordNumber = stringToNumber(word);
	}
	
	public String getWord() {
		return word;
	}
	
	public int getWordNumber() {
		return wordNumber;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public WordInfo getCopy() {
		WordInfo info = new WordInfo(word);
		info.setCount(this.count);
		return info;
	}

	public WordInfo(String word) {
		this.word = word;
		this.count = 1;
	}
	
	private int stringToNumber(String s) {
		int number = 0;
		for(char c : s.toCharArray())
			number += c;
		return number;
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
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public int compareTo(WordInfo o) {
		return this.count - o.count;
	}

	@Override
	public String toString() {
		return "(" + word + ", " + count + ")";
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

	public static void main(String[] args) throws IOException {
		solution1();
		Set<Integer> set = new HashSet<>();
		while(set.size() != 20) {
			int i = new Random().nextInt(20);

			if(!set.contains(i))
				System.out.print(i + " ");

			set.add(i);
		}

		List<Integer> arrList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();
		for(int i = 0; i < 100000; i++) {
			arrList.add(i);
			linkedList.add(i);
		}

		Iterator<Integer> iterArr = arrList.iterator();
		Iterator<Integer> iterLink = linkedList.iterator();

		long time = System.currentTimeMillis();
		while(iterArr.hasNext()) {
			int i = iterArr.next();
			if(i % 2 == 0)
				iterArr.remove();
		}
		System.out.println("ArrayList: " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		while(iterLink.hasNext()) {
			int i = iterLink.next();
			if(i % 2 == 0)
				iterLink.remove();
		}
		System.out.println("LinkedList: " + (System.currentTimeMillis() - time) + "ms");
	}

	public static void solution1() throws FileNotFoundException, IOException {
		System.out.println("solution1: ");
		long time = System.currentTimeMillis();

		Map<String, Integer> map = new HashMap<>();
		String regex = "[A-Za-z]+";
		try(MyScanner scan = MyScannerFactory.makeMyScanner("shakespeare.txt", regex)){
			WordInfo[] arr = new WordInfo[10];
			WordInfo temp = new WordInfo("");
			int count = 0;
			int index;

			while(count < 10 && scan.find()) {
				String s = scan.read().toLowerCase();

				if(map.containsKey(s))
					map.put(s, map.get(s) + 1);
				else
					map.put(s, 1);

				temp.setWord(s);

				if((index = contains(arr, temp)) != -1)
					arr[index].countUp();
				else
					arr[count++] = new WordInfo(s);
			}

			Arrays.sort(arr); // 0번째 인덱스의 값이 제일 작음

			while(scan.find()) {
				String s = scan.read().toLowerCase();

				if(map.containsKey(s))
					map.put(s, map.get(s) + 1);
				else
					map.put(s, 1);

				temp.setWord(s);
				temp.setCount(map.get(s));

				if((index = contains(arr, temp)) == -1) {
					if(arr[0].compareTo(temp) < 0)
						arr[0] = temp.getCopy();
				}
				else {
					arr[index].countUp();
					if(index < 9 && arr[index].compareTo(arr[index + 1]) > 0) {
						WordInfo tmp = arr[index];
						arr[index] = arr[index + 1];
						arr[index + 1] = tmp;
					}
				}

			}

			System.out.println("result: " + Arrays.toString(arr));
			System.out.println(System.currentTimeMillis() - time + "ms");
		}

	}

	public static void solution2() throws FileNotFoundException, IOException {
		System.out.println("solution2: ");
		long time = System.currentTimeMillis();

		List<WordInfo> list = new ArrayList<>();
		String regex = "[A-Za-z]+";
		try(MyScanner scan = MyScannerFactory.makeMyScanner("shakespeare.txt", regex)){
			
			scan.find();
			int index;
			String word;
			
			while((word = scan.read()) != null){
				if((index = findIndex(list, scan.read())) != -1)
			}
			
			System.out.println(System.currentTimeMillis() - time + "ms");
		}
	}

	public static void solution3() {

	}
	
	public static int findIndex(List<WordInfo> list, String word) {
		return findIndex(list, word, 0, list.size() - 1);
	}
	
	public static int findIndex(List<WordInfo> list, String word, int start, int end) {
		if(start > end)
			return -1;
		
		int middle = (start + end) / 2;
		
		WordInfo info = list.get(middle);
		int wordNumber = stringToNumber(word);
		
		if(info.getWord().equals(word))
			return middle;
		else if(info.getWordNumber() < wordNumber) 
			return findIndex(list, word, start, middle - 1);
		else
			return findIndex(list, word, middle + 1, end);
	}
	
	public static int stringToNumber(String s) {
		int number = 0;
		for(char c : s.toCharArray())
			number += c;
		return number;
	}

	public static void scanTxt() throws FileNotFoundException, IOException{
		String regex = "[A-Za-z]+";
		try(MyScanner scan = MyScannerFactory.makeMyScanner("shakespeare.txt", regex)){

			while(scan.find()) {
				System.out.println(scan.read());
			}
		}
	}

	public static int getMinIndex(String[] arr, Map<String, Integer> map) {
		int min = 0;
		for(int i = 1; i < arr.length; i++) {
			if(map.get(arr[min]) > map.get(arr[i]))
				min = i;
		}

		return min;
	}

	public static <T> int contains(T[] arr, T s) {
		for(int i = 0; i < arr.length; i++) {
			if(s.equals(arr[i]))
				return i;
		}

		return -1;
	}
}
