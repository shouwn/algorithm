package sort.comparator;

import java.util.Arrays;
import java.util.Random;

public class Main {
    
    // 배열 a에서 i 위치와 j 위치의 값을 서로 바꾼다
    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void setRand(Object[] arr) {
		Random rand = new Random();
		
		for(int i = 0; i < arr.length; i++) {
			int q = rand.nextInt(arr.length);
			int p = rand.nextInt(arr.length);
			
			swap(arr, p, q);
		}
    }

	public static void main(String[] args) {
		
		String[] arr1 = new String[] {"a", "b", "c", "d", "e"};
		Person[] arr2 = new Person[] {
				new Person("aaa", 20), new Person("bbb", 15), 
				new Person("ccc", 10), new Person("ddd", 8)
		};
		
		setRand(arr1); setRand(arr2);
		
		System.out.println(Arrays.toString(arr1) + "\n" + Arrays.toString(arr2));
		
		Sort.bubbleSort(arr1);
		Sort.bubbleSort(arr2, Person.getComparator(Person.NAME));
		
		System.out.println("\nbubbleSort\n" + 
				Arrays.toString(arr1) + "\n" + Arrays.toString(arr2));
		
		setRand(arr1); setRand(arr2);
		
		Sort.selectionSort(arr1);
		Sort.selectionSort(arr2, Person.getComparator(Person.NAME));

		System.out.println("\nselectionSort\n" + 
				Arrays.toString(arr1) + "\n" + Arrays.toString(arr2));

		setRand(arr1); setRand(arr2);
		
		Sort.insertionSort(arr1);
		Sort.insertionSort(arr2, Person.getComparator(Person.NAME));
		
		System.out.println("\ninsertionSort\n" + 
				Arrays.toString(arr1) + "\n" + Arrays.toString(arr2));
	}

}
