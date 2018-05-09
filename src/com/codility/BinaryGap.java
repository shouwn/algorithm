package com.codility;

import java.util.Scanner;

public class BinaryGap {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		int n = input;
		scan.close();
		boolean start = false;
		
		int max = 0;
		int count = 0;
		
		while(!start && input != 0) {
			int bit = input & 1;
			input >>>= 1;
			if(bit == 1) {
				start = true;
			}
		}
		
		while(input != 0) {
			int bit = input & 1;
			input >>= 1;
			if(bit == 1) {
				max = Math.max(count, max);
				count = 0;
			}else {
				count++;
			}
		}
		
		System.out.println(Integer.toBinaryString(n));
		System.out.println("count: " + max);
	}

}
