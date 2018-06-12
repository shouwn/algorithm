package test.homework.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import homwork.utils.MyInsertion;

public class MyInsertionTest {
	
	static final int n = 10;
	
	static Integer[] testCase;
	static Integer[] collect;
	static Integer[] uqCollect;
	
	@Before
	public void init() {
		TreeSet<Integer> set = new TreeSet<>();
		List<Integer> list = new ArrayList<>();
		
		Random r = new Random();
		
		for(int i = 0; i < n; i++) {
			int num = r.nextInt(n);
			
			list.add(num);
			set.add(num);
		}
		
		testCase = list.toArray(new Integer[0]);
		uqCollect = set.toArray(new Integer[0]);
		Collections.sort(list);
		collect = list.toArray(new Integer[0]);
	}
	
	@Test
	public void assertEqualsArrayTest(){
		List<Integer> list = new ArrayList<>();
		for(Integer i : testCase)
			list.add(i);
		
		Assertions.assertEquals(collect, collect);
	}
	
	@Test
	public void insertBinaryUniqueTest1() {
		List<Integer> list = new ArrayList<>();
		for(Integer i : testCase) 
			MyInsertion.insertBinaryUnique(list, i, 0, list.size() - 1);
		
		Assertions.assertArrayEquals(uqCollect, list.toArray(new Integer[0]));
	}
}
