package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class E6 {
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= T; i++)
				printAnswer(i, solution(br));
		}
	}
	
	public static long solution(BufferedReader br) throws NumberFormatException, IOException {
		String[] in = br.readLine().split(" ");
		
		int N = Integer.valueOf(in[0]);
		int P = Integer.valueOf(in[1]);
		int M = Integer.valueOf(in[2]);
		
		long[] arr = new long[N - 1];
		in = br.readLine().split(" ");
		
		int first = Integer.valueOf(in[0]);
		
		int n = N - 1;
		
		for(int i = 0; i < n; i++) 
			arr[i] = Long.valueOf(in[i + 1]);
		
		return solution(first, arr, M);
	}
	
	public static long solution(int first, long[] arr, int M) {
		Arrays.sort(arr);
		
		for(int i = 0; i < M; i++)
			arr[i] *= -1;
		
		long sum = 0;
		
		for(int i = 0; i < arr.length; i++)
			sum += arr[i];
		
		return sum + first;
	}
	
	public static void printAnswer(int i, long ans) {
		System.out.println("#" + i + " " + ans);
	}
}
