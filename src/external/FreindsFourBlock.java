package external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FreindsFourBlock {
	
	public static void main(String[] args) throws IOException {
		int[][] arr = scanInput();


		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	
	}
	
	public static int[][] scanInput() throws IOException{
		int[][] arr;
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
			String s = reader.readLine();
			String[] input = s.split(" ");
			System.out.println(Arrays.toString(input));
			
			int m = Integer.valueOf(input[0]);
			int n = Integer.valueOf(input[1]);
			
			arr = new int[m][n];
			
			input[2] = input[2].substring(1, input[2].length());

			System.out.println(input[2]);
			String[] board = input[2].split(",");
			
			System.out.println(Arrays.toString(board));
			int i = 0;
			for(String line : board) {

				for(int j = 0; j < line.length() - 2; j++)
					arr[i][j] = Integer.valueOf(line.charAt(j + 1));
				i++;
			}
		}
		
		return arr;
	}

}
