package com.codility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OddOccurrencesInArray {

	public static void main(String[] args) {
		System.out.println(solution1(new int[] {9, 3, 9, 3, 9, 7, 9}, 6));
		System.out.println(solution2(new int[] {9, 3, 9, 3, 9, 7, 9}, 6));
	}
	
	public static int solution1(int[] a, int N) {
		return Arrays.stream(a).reduce((pre, cur) -> pre ^ cur).orElse(0);
	}
	
	public static int solution2(int[] a, int N) {
		int result = 0;
		for(int i = 0; i < N; i++)
			result = result ^ a[i];
		return result;
	}
	
	public static int solution3(int[] a, int N) {
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			if(map.containsKey(a[i]))
				map.put(a[i], map.get(a[i]) + 1);
		}
	}
	
	public static int getMode(int[] a, int N) {
		
	}
}
