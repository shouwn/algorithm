package homwork.utils;

import java.util.Comparator;
import java.util.List;

public class MyInsertion {
	public static<T extends Comparable<T>> int insertBinaryUnique(List<T> list, T o, int start, int end) {
		// start는 찾는 값의 바로 다음으로 큰 값의 위치이다. 
		// start 에서 end 까지 원소가 하나일 때 원소보다 작으면 start = middle, 원소보다 크면 start = middle + 1
		// 작으면 원소보다 왼쪽에 있어야 하기 때문에 찾는 값의 바로 다음으로 큰 원소가 middle이고
		// 크면 middle보다 다음 칸에 있고 현재 배열에서 다음 원소의 값은 이 값보다 크기 때문에
		// middle + 1 이 현재 찾는 값 바도 다음으로 큰 값의 위치이다.
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
