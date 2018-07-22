package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class E5 {
	
	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= T; i++)
				printAnswer(i, solution(br));
		}
	}
	
	public static int solution(BufferedReader br) throws NumberFormatException, IOException {
		String[] in = br.readLine().split(" ");
		
		int N = Integer.valueOf(in[0]);
		int M = Integer.valueOf(in[1]);
		int K = Integer.valueOf(in[2]);
		
		Map<Integer, List<Integer>> edges = new HashMap<>();
		
		for(int i = 0; i < M; i++) {
			in = br.readLine().split(" ");
			
			int from = Integer.valueOf(in[0]);
			int to = Integer.valueOf(in[1]);
			
			if(edges.get(from) == null)
				edges.put(from, new ArrayList<>());
			
			edges.get(from).add(to);
		}
		
		return solution(N, M, K, edges);
	}
	
	public static int solution(int N, int M, int K, Map<Integer, List<Integer>> edges) {
		boolean[] visited = new boolean[N];
		
		int ans = solution(visited, edges);
		
		if(K < ans)
			ans = -1;
		
		return ans;
	}
	
	public static int solution(boolean[] visited, Map<Integer, List<Integer>> edges) {
		
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(new Vertex(1, 0));
		
		while(!queue.isEmpty()) {
			
			Vertex v = queue.poll();
			
			List<Integer> list = edges.get(v.value);
			
			if(list == null)
				continue;
			
			int sz = list.size();
			
			for(int i = 0; i < sz; i++) {
				int to = list.get(i);

				if(to == visited.length)
					return v.depth + 1;
				
				if(visited[to])
					continue;
				
				
				visited[to] = true;
				queue.add(new Vertex(to, v.depth + 1));
			}
		}
		
		return -1;
	}
	
	public static void printAnswer(int i, int ans) {
		System.out.println("#" + i + " " + ans);
	}
	
	static class Vertex{
		int value;
		int depth;
		
		public Vertex(int value, int depth) {
			super();
			this.value = value;
			this.depth = depth;
		}
	}
}
