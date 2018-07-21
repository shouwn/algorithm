package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class E3 {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= T; i++)
				printAnswer(i, solution(br.readLine(), br.readLine()));
		}
	}
	
	public static int solution(String s1, String s2) {
		String[] in = s1.split(" ");
		int N = Integer.valueOf(in[0]);
		int S = Integer.valueOf(in[1]);
		int J = Integer.valueOf(in[2]) + 1;
		
		Set<Integer> set1 = new HashSet<>();
		
		for(int i = S; i <= N; i += J) {
			set1.add(i);
		}
		
		Set<Integer> set2 = new HashSet<>();
		for(String s : s2.split(" ")) 
			set2.add(Integer.valueOf(s));
		
		set1.retainAll(set2);
		
		return set1.size();
	}
	
	public static void printAnswer(int i, int ans) {
		System.out.println("#" + i + " " + ans);
	}
}
