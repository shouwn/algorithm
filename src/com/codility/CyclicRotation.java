package com.codility;

import java.util.Arrays;

public class CyclicRotation {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {3, 8, 9, 7, 6}, 3)));
	}
	
	public static int[] solution(int[] A, int K) {
		
		if(A.length == 0)
			return A;
		
		int front = 0;
		int rear = A.length - 1;
		
		front = (front + K) % A.length;
		rear = (rear + K) % A.length;
		
		int[] result = new int[A.length];
		
		for(int i = 0; i < A.length; i++) {
			result[front] = A[i];
			front = ++front % A.length;
		}
		
		return result;
	}
}
