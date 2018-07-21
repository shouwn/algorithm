package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E2 {
	
	static final int IGNORE = -1;
	static final int VOL = 0;
	static final int MUL = 1;
	static final int SAN = 2;
	static final int CHO = 3;
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= T; i++)
				printAnswer(i, solution(br));
		}
	}
	
	public static int solution(BufferedReader br) throws NumberFormatException, IOException {
		int N = Integer.valueOf(br.readLine());
		
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String[] in = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.valueOf(in[j]);
			}
		}
		
		return solution(N, map);
	}
	
	public static int solution(int N, int[][] map) {
		
		int ans = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
			}
		}
		
		return ans;
	}
	
	public static boolean checkBuild(int[][] map, int r, int c) {
		Boolean vol = false;
		Boolean mul = false;
		Boolean san = false;
		Boolean cho = false;
		
		Boolean[] bols = new Boolean[] {vol, mul, san, cho};
		
		for(int i = r - 1; i <= r + 1; i++) {
			for(int j = c - 1; j < c + 1; j++) {
				
				if(check(bols, state(map, i, j)));
					return false;
			}
		}
		
		
		return mul && san && cho;
	}
	
	public static boolean check(Boolean[] bol, int num) {
		switch(num) {
		case IGNORE: return true;
		case VOL: return false;
		}
		
		return false;
	}
	
	public static int state(int[][] map, int r, int c) {
		int length = map.length;
		
		if(length <= r || length <= c || r < 0 || c < 0)
			return IGNORE;
		
		return map[r][c];
	}
	
	public static void printAnswer(int i, int ans) {
		System.out.println("#" + i + " " + ans);
	}
}
