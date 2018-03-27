package external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Traffic {
	
	static final int OVER_TIME = 0;
	static final int PROCESS_TIME = 1;

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		reader.close();
		String[] logs = input.replaceAll("[“”]", "").replaceAll("2016-09-15 ", "").split(", ");
		input = null;
		int[][] time = new int[logs.length][2];
		
		for(int i = 0; i < logs.length; i++) {
			System.out.println(logs[i]);
		}

	}
	
}

class Time{
	private int overTime; // 응답완료시간(ms)
	private int processTime; // 처리시간(ms)
	
	public Time(String[] arr) {
		int i = 3600;
		
		for(String s : arr[0].split(":")) {
			overTime += Integer.valueOf(s) * i;
			i /= 60;
		}
		
		overTime *= 1000;
		processTime = Integer.valueOf(arr[1].replaceAll("[.s]", ""));
		
	}
	
	
}