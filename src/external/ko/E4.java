package external.ko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class E4 {

	public static void main(String[] args) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.valueOf(br.readLine());

			for(int i = 1; i <= T; i++)
				printAnswer(i, solution(br));
		}
	}

	public static int solution(BufferedReader br) throws IOException {
		String[] in = br.readLine().split(" ");

		int N = Integer.valueOf(in[0]);
		int K = Integer.valueOf(in[1]);
		int L = Integer.valueOf(in[2]);
		
		Consumer[] consumers = new Consumer[N];

		for(int i = 0; i < N; i++) {
			
			String[] s = br.readLine().split(" ");
			consumers[i] = new Consumer(Integer.valueOf(s[0]), Integer.valueOf(s[1]), K);
		}
		
		return solution(N, K, L, consumers);
	}

	public static int solution(int N, int K, int L, Consumer[] consumers) {
		
		int acceptMax = Math.min(L / K, N);
		int acceptMin = 0;
		
		int sum = 0;
		int max = Integer.MIN_VALUE;
		
		PriorityQueue<Consumer> queue = new PriorityQueue<>((c1, c2)-> {
			return c1.unsatis - c2.unsatis; // 내림차 정렬 -3 -3 -2 
		});
		
		int unsatisSum = 0;
		
		for(int i = 0; i < acceptMax; i++) {
			unsatisSum += consumers[i].unsatisSum();
			queue.add(consumers[i]);
		}
		
		for(int i = 0; i < acceptMax; i++) {
			int need = consumers[i].need;

			acceptMin = i;
			//System.out.println("sum: "+ sum); ////////////////////
			//System.out.println("L: "+ L); ////////////////////////
			if(sum + need >= L) {
				if(sum + K > L)
					acceptMin = i - 1;
				break;
			}
			
			sum += need;
		}
		
		//System.out.println("acceptMax: " + acceptMax + " acceptMin: " + acceptMin);////////////
		
		int remain = L - (acceptMax * K);
		

		for(int i = acceptMax - 1; i >= acceptMin; i--) {

			//System.out.println("remain: " + remain); //////////////////////
			
			while(remain != 0 && !queue.isEmpty()) {
				Consumer consumer = queue.poll();
				
				while(consumer != null && consumer.isOutOfBounds)
					consumer = queue.poll();
				
				if(consumer == null)
					break;
				
				int use;
				
				if(remain >= consumer.need - consumer.use)
					use = consumer.need - consumer.use;
				else {
					use = remain;
					queue.add(consumer);
				}
				
				consumer.use += use;
				unsatisSum -= use * consumer.unsatis;
				remain -= use;
			}
			
			max = Math.max(max, 10 * (i + 1) + unsatisSum);

			//System.out.println("max: " + max);/////////////////////////
			Consumer c = consumers[i];
			
			//System.out.print("unsatisSum: " + unsatisSum); /////////////////////////
			
			unsatisSum -= (c.need - c.use) * c.unsatis;
			remain += c.use;
			consumers[i].isOutOfBounds = true;
			
			//System.out.println(" --> unsatisSum: " + unsatisSum);////////////////////////////
		}
		
		return max;
	}

	public static void printAnswer(int i, int ans) {
		System.out.println("#" + i + " " + ans);
	}

	static class Consumer{
		
		int need;
		int unsatis;
		
		int use;
		boolean isOutOfBounds;

		public Consumer(int need, int unsatis, int K) {
			super();
			this.need = need;
			this.unsatis = -unsatis;
			this.use = K;
		}
		
		public int unsatisSum() {
			return (need - use) * unsatis;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + need;
			result = prime * result + unsatis;
			result = prime * result + use;
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
			Consumer other = (Consumer) obj;
			if (need != other.need)
				return false;
			if (unsatis != other.unsatis)
				return false;
			if (use != other.use)
				return false;
			return true;
		}
	}
}
