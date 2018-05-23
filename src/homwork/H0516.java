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


public class H0516 {
	
	List<WordInfo> letterList = new ArrayList<>();
	Map<WordInfo, Integer> letterMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {

	}
	
	public static void solution1() throws FileNotFoundException, IOException {
	
	}
	
	public static void solution2() throws FileNotFoundException, IOException {

	}
	
	public static void solution3() throws FileNotFoundException, IOException {

	}
	
	public static void scanTxt() throws FileNotFoundException, IOException {
		try(BufferedReader reader = 
				new BufferedReader(new FileReader(new File("")))) {
			
			String regex = "[^A-Za-z]";
			
			System.out.println(reader.readLine().replaceAll(regex, ""));
		}
	}
	
}
