package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E1 {
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= T; i++)
				printAnswer(i, solution(br.readLine()));
		}
	}
	
	public static int solution(String s) {
		String[] in = s.split(" ");
		
		return solution(
				Integer.valueOf(in[0]),
				Integer.valueOf(in[1]),
				Integer.valueOf(in[2]),
				Integer.valueOf(in[3]),
				Integer.valueOf(in[4]),
				Integer.valueOf(in[5])
				);
	}
	
	public static int solution(int N, int M, int r1, int c1, int r2, int c2) {
		
		int rowSpace = Math.abs(r1 - r2);
		int colSpace = Math.abs(c1 - c2);
		
		return Math.min(rowSpace, N - rowSpace)
				+ Math.min(colSpace, M - colSpace);
		
	}
	
	public static void printAnswer(int i, int ans) {
		System.out.println("#" + i + " " + ans);
	}
}
