package homwork;

import java.util.Arrays;
import java.util.Random;

public class H0320 {
	
	public static void main(String[] args) {
		Random rand = new Random();
		
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++) 
			arr[i] = rand.nextInt(10);
		
		evenSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void evenSort(int[] arr) {
		int start = 0, end = arr.length - 1;
		
		while(true) {
			while(arr[start] % 2 == 0)
				start++;
			while(arr[end] % 2 == 1)
				end--;
			
			if(start < end)
				swap(arr, start, end);
			else
				break;
		}
	}
	
	public static void swap(int[] arr, int p, int q) {
		int temp = arr[p];
		arr[p] = arr[q];
		arr[q] = temp;
	}

}
