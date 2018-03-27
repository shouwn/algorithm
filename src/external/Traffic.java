package external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Traffic {
	
<<<<<<< HEAD
	static final int OVER_TIME = 0;
	static final int PROCESS_TIME = 1;
=======
	public static int[] log2Time(String[] log) {
		int overTime = 0, processTime = 0;
		
		int i = 3600;
		
		String[] time = log[0].split("\\.");
		overTime = Integer.valueOf(time[1]);
		
		for(String s : time[0].split(":")) {
			overTime += Integer.valueOf(s) * i;
			i /= 60;
		}
		
		int temp = 100;
		
		overTime *= 1000;
		time = log[1].split("\\.");
		processTime += Integer.valueOf(time[0]) * 1000;
		time[1] = time[1].replace("s", "");
		
		for(String s : time[1].split("")) {
			processTime += Integer.valueOf(s) * temp;
			temp /= 10;
		}
		
		
		return new int[] {overTime, processTime};
	}
	
	public static boolean checkInclude(int[] range, int[] other) {
		
		if(range[0] <= other[0] && range[1 ] >= other[0])
			return true;
		
		if(range[0] <= other[1] && range[1] >= other[1])
			return true;
		
		if(range[0] >= other[0] && range[1] <= other[1])
			return true;
		
		return false;
	}
>>>>>>> 21bc42370fbad8c4bbc0f5b7681eb738d2331220

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		reader.close();
		
		String[] logs = input.replaceAll("[“”]", "").replaceAll("2016-09-15 ", "").split(", ");
		input = null;
<<<<<<< HEAD
		int[][] time = new int[logs.length][2];
=======
		int[][] times = new int[logs.length][2];
>>>>>>> 21bc42370fbad8c4bbc0f5b7681eb738d2331220
		
		for(int i = 0; i < logs.length; i++) {
			System.out.println(logs[i]);
		}
<<<<<<< HEAD

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
		
=======
		
		int max = 0;
		
		for(int i = 0; i < logs.length; i++) {
			times[i] = log2Time(logs[i].split(" "));
		}
		
		for(int i = 0; i < times.length; i++) {
			int count1 = 0;
			int count2 = 0;
			
			int start = times[i][0] - times[i][1] - 1;
			int end = times[i][0];
			
			int[] startRange = {start - 999, start};
			int[] endRange = {end, end + 999};
			
			for(int j = 0; j < times.length; j++) {
				int[] range = new int[] {times[j][0] - times[j][1] - 1, times[i][0]};
				
				if(checkInclude(startRange, range))
					count1++;
				if(checkInclude(endRange, range))
					count2++;
			}
			
			if(max < count1)
				max = count1;
			if(max < count2)
				max = count2;
		}
		
		System.out.println(max);

>>>>>>> 21bc42370fbad8c4bbc0f5b7681eb738d2331220
	}
	
	
}