package sort.comparator;

import java.util.Comparator;

public class Sort {
	
    // 배열 a의 start 위치부터 끝까지에서 가장 작은 값의 위치(index)를 리턴한다.
    private static int findMin(int[] a, int start) {
    	int min = start;
    	for(int i = start + 1; i < a.length; i++) {
    		if(a[min] > a[i])
    			min = i;
    	}
    	
    	return min;
    }
    
    // 배열 a의 start 위치부터 끝까지에서 가장 작은 값의 위치(index)를 리턴한다.
    private static <T> int findMin(T[] a, int start) {
    	int min = start;
    	for(int i = start + 1; i < a.length; i++) {
    		Comparable<T> temp = (Comparable<T>) a[min];
    		if(temp.compareTo(a[i]) > 0)
    			min = i;
    	}
    	
    	return min;
    }
    
    // 배열 a의 start 위치부터 끝까지에서 가장 작은 값의 위치(index)를 리턴한다.
    private static <T> int findMin(T[] a, Comparator<? super T> comparator, int start) {
    	int min = start;
    	for(int i = start + 1; i < a.length; i++) {
    		if(comparator.compare(a[min], a[i]) > 0)
    			min = i;
    	}
    	
    	return min;
    }
    
    // selection sort
    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            int minIndex = findMin(a, i); // 배열 a의 i 위치부터 끝까지에서 가장 작은 값을 찾아서
            swap(a, i, minIndex);         // 그 값을 i 위치로 이동한다
        }
    }
    
    // selection sort
    public static <T> void selectionSort(T[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            int minIndex = findMin(a, i); // 배열 a의 i 위치부터 끝까지에서 가장 작은 값을 찾아서
            swap(a, i, minIndex);         // 그 값을 i 위치로 이동한다
        }
    }
    
    // selection sort
    public static <T> void selectionSort(T[] a, Comparator<? super T> comparator) {
        for (int i = 0; i < a.length - 1; ++i) {
            int minIndex = findMin(a, comparator, i); // 배열 a의 i 위치부터 끝까지에서 가장 작은 값을 찾아서
            swap(a, i, minIndex);         // 그 값을 i 위치로 이동한다
        }
    }

	public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; ++i) {
            int value = a[i];             // 삽입할 값 보관
            int j;
            for (j = i - 1; j >= 0; --j)  // 뒤로 이동
                if (a[j] > value)
                    a[j + 1] = a[j];
                else
                    break;
            a[j + 1] = value;             // 값 삽입
        }
    }
	
	public static <T> void insertionSort(T[] a) {
        for (int i = 1; i < a.length; ++i) {
            T value = a[i];             // 삽입할 값 보관
            int j;
            for (j = i - 1; j >= 0; --j) {  // 뒤로 이동
            	Comparable<T> temp = (Comparable<T>) a[j];
                if (temp.compareTo(value) > 0)
                    a[j + 1] = a[j];
                else
                    break;
            }
            a[j + 1] = value;             // 값 삽입
        }
    }
	
	public static <T> void insertionSort(T[] a, Comparator<? super T> comparator) {
        for (int i = 1; i < a.length; ++i) {
            T value = a[i];             // 삽입할 값 보관
            int j;
            for (j = i - 1; j >= 0; --j) {  // 뒤로 이동
            	if (comparator.compare(a[j], value) > 0)
                    a[j + 1] = a[j];
                else
                    break;
            }
            a[j + 1] = value;             // 값 삽입
        }
    }
	
    // 배열 a에서 i 위치와 j 위치의 값을 서로 바꾼다
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }    
    
    // 배열 a에서 i 위치와 j 위치의 값을 서로 바꾼다
    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // bubble sort
    public static void bubbleSort(int[] a) {
        for (int i = a.length - 1; i >= 1; --i) {
            boolean 완료 = true;
            for (int j = 0; j < i; ++j) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    완료 = false;
                }
            }
            if (완료) break;
        }
    }
    
    // bubble sort
    public static <T> void bubbleSort(T[] a) {
        for (int i = a.length - 1; i >= 1; --i) {
            boolean 완료 = true;
            for (int j = 0; j < i; ++j) {
				Comparable<T> current = (Comparable<T>) a[j];
                if (current.compareTo(a[j + 1]) > 0) {
                    swap(a, j, j + 1);
                    완료 = false;
                }
            }
            if (완료) break;
        }
    }
    
    // bubble sort
    public static <T> void bubbleSort(T[] a, Comparator<? super T> comparator) {
        for (int i = a.length - 1; i >= 1; --i) {
            boolean 완료 = true;
            for (int j = 0; j < i; ++j) {
                if (comparator.compare(a[j], a[j + 1]) > 0) {
                    swap(a, j, j + 1);
                    완료 = false;
                }
            }
            if (완료) break;
        }
    }
}
