package homwork.utils;

import java.util.Comparator;
import java.util.List;

public class MyInsertion {
	public static<T extends Comparable<T>> int insertBinaryUnique(List<T> list, T o, int start, int end) {

		if(start > end) {
			list.add(start, o);
			return start;
		}

		int middle = (start + end) / 2;

		int compare = o.compareTo(list.get(middle));

		if(compare == 0) 
			return middle;
		
		else if(compare < 0) 
			return insertBinaryUnique(list, o, start, middle - 1);
		else
			return insertBinaryUnique(list, o, middle + 1, end);
	}
	
	public static<T> int insertBinaryUnique(List<T> list, T o, Comparator<T> c, int start, int end) {
		
		if(start > end) {
			list.add(start, o);
			return start;
		}

		int middle = (start + end) / 2;
		int compare = c.compare(o, list.get(middle));

		if(compare == 0) 
			return middle;
		
		else if(compare < 0) 
			return insertBinaryUnique(list, o, c, start, middle - 1);
		else
			return insertBinaryUnique(list, o, c, middle + 1, end);
	}
}
