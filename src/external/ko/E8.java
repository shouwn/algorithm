package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class E8 {

	public static final int ON = 1;
	public static final int OFF = 0;
	
	static PriorityQueue<Time> queue;
	static String[] digits;
	static boolean[] isDigit;
	static int[][] arr;
	static int[] sums;

	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());

			for(int i = 1; i <= T; i++)
				printAnswer(i, solution(br));
		}
	}

	public static Time solution(BufferedReader br) throws NumberFormatException, IOException {

		arr = new int[4][7];
		sums = new int[4];

		for(int i = 0; i < 4; i++) {

			String[] in = br.readLine().split(" ");

			int sum = 0;

			for(int k = 0, j = i * 7; k < 7; k++, j++) {
				arr[i][k] = Integer.valueOf(in[j]);
				sum += arr[i][k];
			}

			sums[i] = sum;
		}

		return solution(arr, sums);
	}

	public static Time solution(int[][] arr, int[] sums) {
		queue = new PriorityQueue<>((t1, t2) -> {
			int h = t1.hh - t2.hh;
			return h != 0 ? h : t1.mm - t2.mm;
		});

		isDigit = new boolean[4];
		digits = new String[4];

		for(int i = 0; i < 4; i++) {
			if((digits[i] = Digit.checkDigit(sums[i], arr[i])) != null)
				isDigit[i] = true;
		}

		// 0개 오류
		if(isAllTrue(isDigit))
			queue.add(Time.makeTime(digits));

		// 1개 오류
		for(int i = 0; i < 4; i++) {

			String origin = digits[i];

			step1(i);

			digits[i] = origin;
		}

		// 2개 오류
		for(int i = 0; i < 4; i++) { // 1번째 오류 탐색

			String originI = digits[i];
			
			for(int j = 0; j < 4; j++) { // 2번째 오류 탐색
				
				String originJ = digits[j];
				
				step2(i, j);
				
				digits[j] = originJ;
			}
			
			digits[i] = originI;
		}

		return queue.poll();
	}
	
	public static void step1(int i) {
		if(isAllTrue(isDigit, i)) {
			for(int k = 0; k < 8; k++) {

				if(arr[i][k] == ON) {
					arr[i][k] = OFF;
					sums[i]--;

					if((digits[i] = Digit.checkDigit(sums[i], arr[i])) != null) 
						queue.add(Time.makeTime(digits));

					arr[i][k] = ON;
					sums[i]++;
				}
				else {
					arr[i][k] = ON;
					sums[i]++;

					if((digits[i] = Digit.checkDigit(sums[i], arr[i])) != null) 
						queue.add(Time.makeTime(digits));

					arr[i][k] = OFF;
					sums[i]--;
				}
			}
		}
	}
	
	public static void step2(int i, int j) {
		if(isAllTrue(isDigit, i, j)) {
			for(int k = 0; k < 8; k++) { // i 번째 거 바꿈

				if(arr[i][k] == ON) {
					arr[i][k] = OFF;
					sums[i]--;
					
					for(int w = 0; w < 8; w++) { //j 번째 거 바꿈
						if(arr[j][w] == ON) {
							arr[j][w] = OFF;
							sums[j]--;

							if((digits[j] = Digit.checkDigit(sums[j], arr[j])) != null &&
									(digits[i] = Digit.checkDigit(sums[i], arr[i])) != null) 
								queue.add(Time.makeTime(digits));

							arr[j][w] = ON;
							sums[j]++;
						}
						else {
							arr[j][w] = ON;
							sums[j]++;

							if((digits[j] = Digit.checkDigit(sums[j], arr[j])) != null &&
									(digits[i] = Digit.checkDigit(sums[i], arr[i])) != null) 
								queue.add(Time.makeTime(digits));

							arr[j][w] = OFF;
							sums[j]--;
						}
					}

					arr[i][k] = ON;
					sums[i]++;
				}
				else {
					arr[i][k] = ON;
					sums[i]++;
					
					for(int w = 0; w < 8; w++) { //j 번째 거 바꿈
						if(arr[j][w] == ON) {
							arr[j][w] = OFF;
							sums[j]--;

							if((digits[j] = Digit.checkDigit(sums[j], arr[j])) != null &&
									(digits[i] = Digit.checkDigit(sums[i], arr[i])) != null) 
								queue.add(Time.makeTime(digits));

							arr[j][w] = ON;
							sums[j]++;
						}
						else {
							arr[j][w] = ON;
							sums[j]++;

							if((digits[j] = Digit.checkDigit(sums[j], arr[j])) != null &&
									(digits[i] = Digit.checkDigit(sums[i], arr[i])) != null) 
								queue.add(Time.makeTime(digits));

							arr[j][w] = OFF;
							sums[j]--;
						}
					}

					arr[i][k] = OFF;
					sums[i]--;
				}
			}
		}
	}

	public static void printAnswer(int i, Time time) {
		System.out.println("#" + i + " " + time.hh + " " + time.mm);
	}

	public static boolean isAllTrue(boolean[] bols) {
		boolean result = true;

		for(int i = 0; i < bols.length; i++)
			result = result && bols[i];

		return result;
	}

	public static boolean isAllTrue(boolean[] bols, int except) {
		boolean result = true;
		boolean origin = bols[except];

		bols[except] = true;

		for(int i = 0; i < bols.length; i++)
			result = result && bols[i];

		bols[except] = origin;

		return result;
	}

	public static boolean isAllTrue(boolean[] bols, int except1, int except2) {
		boolean result = true;
		boolean origin1 = bols[except1];
		boolean origin2 = bols[except2];

		bols[except1] = true;
		bols[except2] = true;

		for(int i = 0; i < bols.length; i++)
			result = result && bols[i];

		bols[except1] = origin1;
		bols[except2] = origin2;

		return result;
	}

	static class Digit{

		static Map<Integer, List<int[]>> map = new HashMap<>();
		static String[][] digitMap = new String[7][3];

		static {
			// count 2
			map.put(2, Arrays.asList(
					new int[] {0, 0, 0, 0, 1, 1, 0} // 1
					));
			digitMap[2][0] = "1";

			// count 3
			map.put(3, Arrays.asList(
					new int[] {1, 0, 0, 0, 1, 1, 0} // 7
					));
			digitMap[3][0] = "7";

			// count 4
			map.put(4, Arrays.asList(
					new int[] {0, 1, 0, 0, 1, 1, 1} // 4
					));
			digitMap[4][0] = "4";

			// count 5
			map.put(5, Arrays.asList(
					new int[] {1, 0, 1, 1, 0, 1, 1}, // 2
					new int[] {1, 0, 0, 1, 1, 1, 1}, // 3
					new int[] {1, 1, 0, 1, 1, 0, 1}  // 5
					));
			digitMap[5][0] = "2";
			digitMap[5][1] = "3";
			digitMap[5][2] = "5";

			// count 6
			map.put(6, Arrays.asList(
					new int[] {1, 1, 1, 1, 1, 0, 1}, // 6
					new int[] {1, 0, 1, 1, 1, 1, 1}, // 9
					new int[] {1, 1, 1, 1, 1, 1, 0}  // 0
					));
			digitMap[6][0] = "6";
			digitMap[6][1] = "9";
			digitMap[6][2] = "0";

			// count 7
			map.put(7, Arrays.asList(
					new int[] {1, 1, 1, 1, 1, 1, 1} // 8
					));
			digitMap[7][0] = "8";

		}

		public static Integer makeDigit(int count1, int[] arr1, int count2, int[] arr2) {
			String s1 = checkDigit(count1, arr1);

			if(s1 == null) return null;

			String s2 = checkDigit(count2, arr2);

			if(s2 == null) return null;

			return Integer.valueOf(s1 + s2);
		}

		public static Integer makeDigit(int count, int[] arr) {
			String s = checkDigit(count, arr);

			if(s == null)
				return null;
			else
				return Integer.valueOf(s);
		}

		public static String checkDigit(int count, int[] arr) {
			List<int[]> list = map.get(count);

			if(list == null)
				return null;

			for(int i = 0; i < list.size(); i++) {
				if(Arrays.equals(list.get(i),arr)) return digitMap[count][i];
			}

			return null;
		}
	}


	static class Time{
		int hh, mm;

		public Time(int hh, int mm) {
			super();
			this.hh = hh;
			this.mm = mm;
		}

		public static Time makeTime(String[] s) {
			int hh = Integer.valueOf(s[0] + s[1]);
			int mm = Integer.valueOf(s[1] + s[2]);

			return new Time(hh,mm);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + hh;
			result = prime * result + mm;
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
			Time other = (Time) obj;
			if (hh != other.hh)
				return false;
			if (mm != other.mm)
				return false;
			return true;
		}
	}
}
