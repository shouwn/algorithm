package homwork.utils;

import java.util.Comparator;
import java.util.List;

public class MyInsertion {
	public static<T extends Comparable<T>> int insertBinary(List<T> list, T o, int start, int end) {

		if(start > end) {
			list.add(start, o);
			return start;
		}

		int middle = (start + end) / 2;

		int compare = list.get(middle).compareTo(o);

		if(compare == 0) 
			return middle;
		
		else if(compare < 0) 
			return insertBinary(list, o, start, middle - 1);
		else
			return insertBinary(list, o, middle + 1, end);
	}
	
	public static<T> int insertBinary(List<T> list, T o, Comparator<T> c, int start, int end) {
		
		if(start > end) {
			list.add(start, o);
			return start;
		}

		int middle = (start + end) / 2;
		int compare = c.compare(list.get(middle), o);

		if(compare == 0) 
			return middle;
		
		else if(compare < 0) 
			return insertBinary(list, o, c, start, middle - 1);
		else
			return insertBinary(list, o, c, middle + 1, end);
	}
}
