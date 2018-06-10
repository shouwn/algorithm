package homwork.shakespeare;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import homwork.utils.MyInsertion;
import homwork.utils.MyScanner;
import homwork.utils.MyScannerFactory;

public class Shakespeare {
	
	static WordInfoComparatorByCount comparatorByCount = new WordInfoComparatorByCount();
	static WordInfoComparatorByWord comparatorByWord = new WordInfoComparatorByWord();
	
	// 정규식을 세팅하면 그 정규식에 맞는 다음 문자열을 찾는 클래스

	public static void main(String[] args) throws IOException {
		solution1();
		solution3();
		solution2();
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

			Arrays.sort(arr, comparatorByCount); // 0번째 인덱스의 값이 제일 작음

			while(scan.find()) {
				String s = scan.read().toLowerCase();

				if(map.containsKey(s))
					map.put(s, map.get(s) + 1);
				else
					map.put(s, 1);

				temp.setWord(s);
				temp.setCount(map.get(s));

				if((index = contains(arr, temp)) == -1) {
					if(comparatorByCount.compare(arr[0], temp) < 0)
						arr[0] = temp.getCopy();
				}
				else {
					arr[index].countUp();
					if(index < 9 && comparatorByCount.compare(arr[index], arr[index + 1]) > 0) {
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
		System.out.println("solution2: //10초 정도 걸림");
		long time = System.currentTimeMillis();

		List<WordInfo> list = new ArrayList<>();
		String regex = "[A-Za-z]+";
		try(MyScanner scan = MyScannerFactory.makeMyScanner("shakespeare.txt", regex)){

			WordInfo[] arr = new WordInfo[10];
			WordInfo temp;
			int count = 0;
			int index;

			while(count < 10 && scan.find()) {
				String word = scan.read().toLowerCase();

				temp = insertLinear(list, word);

				if((index = contains(arr, temp)) == -1)
					arr[count++] = temp;
			}

			Arrays.sort(arr, comparatorByCount); // 0번째 인덱스의 값이 제일 작음

			while(scan.find()) {
				String word = scan.read().toLowerCase();

				temp = insertLinear(list, word);

				if((index = contains(arr, temp)) == -1) {
					if(comparatorByCount.compare(arr[0], temp) < 0)
						arr[0] = temp;
				}
				else {
					if(index < 9 && comparatorByCount.compare(arr[index], arr[index + 1]) > 0) {
						WordInfo tmp = arr[index];
						arr[index] = arr[index + 1];
						arr[index + 1] = tmp;
					}
				}

			}
			System.out.println(Arrays.toString(arr));
			System.out.println(System.currentTimeMillis() - time + "ms");
		}
	}

	public static void solution3() throws FileNotFoundException, IOException {
		System.out.println("solution3: ");
		long time = System.currentTimeMillis();

		List<WordInfo> list = new ArrayList<>();
		String regex = "[A-Za-z]+";
		try(MyScanner scan = MyScannerFactory.makeMyScanner("shakespeare.txt", regex)){

			WordInfo[] arr = new WordInfo[10];
			WordInfo temp;
			int count = 0;
			int index;
			
			scan.find();
			list.add(new WordInfo(scan.read().toLowerCase()));
			arr[count++] = list.get(0);

			while(count < 10 && scan.find()) {
				String word = scan.read().toLowerCase();

				temp = insertBinary(list, word);

				if((index = contains(arr, temp)) == -1)
					arr[count++] = temp;
			}
			
			Arrays.sort(arr, comparatorByCount); // 0번째 인덱스의 값이 제일 작음

			while(scan.find()) {
				String word = scan.read().toLowerCase();

				temp = insertBinary(list, word);

				if((index = contains(arr, temp)) == -1) {
					if(comparatorByCount.compare(arr[0], temp) < 0)
						arr[0] = temp;
				}
				else {
					if(index < 9 && comparatorByCount.compare(arr[index], arr[index + 1]) > 0) {
						WordInfo tmp = arr[index];
						arr[index] = arr[index + 1];
						arr[index + 1] = tmp;
					}
				}

			}
			System.out.println(Arrays.toString(arr));
			System.out.println(System.currentTimeMillis() - time + "ms");
		}
	}

	public static WordInfo insertLinear(List<WordInfo> list, String word) {
		for(int i = 0; i < list.size() - 1; i++) {
			WordInfo info = list.get(i);
			if(info.word.equals(word)) {
				info.countUp();
				return info;
			}
		}

		WordInfo info = new WordInfo(word);
		list.add(info);
		return info;
	}
	
	public static WordInfo insertBinary(List<WordInfo> list, String word) {		
		WordInfo temp = list.get(
				MyInsertion.insertBinary(list, new WordInfo(word, 0), comparatorByWord, 0, list.size() - 1)
				);
		temp.countUp();
		
		return temp;
	}

	public static <T> int contains(T[] arr, T s) {
		for(int i = 0; i < arr.length; i++) {
			if(s.equals(arr[i]))
				return i;
		}

		return -1;
	}
}
