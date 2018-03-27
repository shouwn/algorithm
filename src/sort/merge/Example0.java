package sort.merge;

public class Example0 {
	
	public static void main(String[] args) {
		
	}
	
	public static void mergeSort(Comparable[] arr, int start, int end) {
		if(start < end) {
			int middle = (start + end) / 2;
			mergeSort(arr, start, middle);
			mergeSort(arr, middle + 1, end);
			merge(arr, start, middle, end);
		}
	}
	
	public static void merge(Comparable[] arr, int start, int middle, int end) {
		
	}

}
