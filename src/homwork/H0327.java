package homwork;

import java.util.Arrays;

public class H0327 {

	public static void main(String[] args) {

		GeneralClass<Integer> list = new GeneralClass<>();
		GeneralClass<String> list2 = new GeneralClass<>();
		
		for(int i = 0; i < 20; i++) {
			list.put(i);
			list2.put(Character.toString((char) ('A' + i)));
		}
			
		StringBuilder temp = new StringBuilder();
		StringBuilder temp2 = new StringBuilder();
		
		for(int i = 0; i < 20; i++) {
			temp.append(list.get(i)).append(" ");
			temp2.append(list2.get(i)).append(" ");
		}
		
		System.out.println(temp.toString());
		System.out.println(temp2.toString());
		
		list.get(25); //Exception occur
	}

}

class GeneralClass<T>{
	private Object[] arr;
	private int size;
	
	public GeneralClass() {
		arr = new Object[10];
	}
	
	public void put(T item) {
		if(size == arr.length) 
			arr = Arrays.copyOf(arr, size * 2);
		
		arr[size++] = item;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T) arr[index];
	}
	
	public int size() { return size; }
}