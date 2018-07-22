package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class E7 {
	
	static final int SUM = 1;
	static final int MIN = 0;
	
	static Map<Key, long[]> memo;
	static Key key = new Key();
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= T; i++)
				printAnswer(i, solution2(br));
		}
	}
	public static long solution2(BufferedReader br) throws NumberFormatException, IOException {
		
		int N = Integer.valueOf(br.readLine());
		String[] in = br.readLine().split(" ");
		
		PriorityQueue<Long> queue = new PriorityQueue<>();
		
		long ans = 0;
		
		for(int i = 0; i < N; i++) 
			queue.add(Long.valueOf(in[i]));
		
		while(queue.size() != 1) {
			long num = queue.poll() + queue.poll();
			
			ans += num;
			queue.add(num);
		}
		
		return ans;
	}
	
	public static long solution(BufferedReader br) throws NumberFormatException, IOException {
		
		int N = Integer.valueOf(br.readLine());
		String[] in = br.readLine().split(" ");
		
		long[] arr = new long[N];
		
		for(int i = 0; i < N; i++) 
			arr[i] = Long.valueOf(in[i]);
		
		return solution(N, arr);
	}
	
	public static long solution(int N, long[] arr) {
		
		memo = new HashMap<>();
		
		return solution(0, arr.length - 1, arr)[MIN];
	}
	
	public static long[] solution(int start, int end, long[] arr) {

		if(memo.containsKey(key.set(start, end)))
			return memo.get(key);

		if(start == end) {
			long[] temp = new long[] {0, arr[start]};
			memo.put(key, temp);
			return temp;
		}
		
		long[] left = solution(start, start, arr);
		long[] right = solution(start + 1, end, arr);
		
		long temp = left[MIN] + right[MIN];
		
		long sum = left[SUM] + right[SUM];

		long min = temp + sum;
		
		for(int middle = start + 1; middle < end; middle++) {
			left = solution(start, middle, arr);
			right = solution(middle + 1, end, arr);
			
			temp = sum + left[MIN] + right[MIN];
			
			min = Math.min(temp, min);
		}
		
		long[] result = new long[] {min, sum};
		memo.put(key.set(start, end), result);
		
		return result;
	}
	
	public static void printAnswer(int i, long ans) {
		System.out.println("#" + i + " " + ans);
	}
	
	static class Key{
		int start, end;
		
		public Key set(int start, int end) {
			this.start = start;
			this.end = end;
			
			return this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + end;
			result = prime * result + start;
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
			Key other = (Key) obj;
			if (end != other.end)
				return false;
			if (start != other.start)
				return false;
			return true;
		}
	}
}
