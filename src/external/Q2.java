package external;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q2 {

	static Set<Integer> set = new TreeSet<>();
	static long time;

	static class Dot{
		int position;
		boolean include;

		public Dot(int position, boolean include) {
			super();
			this.position = position;
			this.include = include;
		}
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
		public boolean isInclude() {
			return include;
		}
		public void setInclude(boolean include) {
			this.include = include;
		}
	}

	static class Line{
		Dot left;
		Dot right;

		public Line(Dot left, Dot right) {
			super();
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			if(left.position == Integer.MIN_VALUE) {
				str.append("(-, ");
			}
			else {
				if(left.isInclude())
					str.append("[");
				else
					str.append("(");
				
				str.append(left.getPosition() + ", ");
			}
			
			if(right.position == Integer.MAX_VALUE)
				str.append("+)");
			else {
				str.append(right.getPosition());
				
				if(right.isInclude())
					str.append("]");
				else
					str.append(")");
			}

			return str.toString();
		}
	}

	public static Line step1(List<Line> lines, int left, int right) {
		int leftCount = 0;
		int count = 0;

		Iterator<Line> iter = lines.iterator();

		while(iter.hasNext()) {
			Line line = iter.next();
			int lineLeft = line.left.position;
			int lineRight = line.right.position;

			if(lineLeft <= left && lineRight >= right) {
				count++;
				leftCount++;
			}
			else if(lineRight == left || lineLeft == left)
				leftCount++;

			if(lineRight < left)
				iter.remove();

			if(lineLeft >= right)
				break;
		}

		if(count % 2 == 0) {
			Dot leftDot = new Dot(left, leftCount % 2 == 0);
			Dot rightDot = new Dot(right, false);
			return new Line(leftDot, rightDot);
		}
		else {
			Dot leftDot = new Dot(left, leftCount % 2 == 0);
			return new Line(leftDot, leftDot);
		}
	}

	public static List<String> step2(List<Line> lines) {
		List<String> result = new ArrayList<>();

		boolean back = false;
		Line temp = new Line(null, null);
		Line line = null;

		Iterator<Line> iter = lines.iterator();
		while(iter.hasNext()) {
			line = iter.next();
			if(!line.left.isInclude() && line.left == line.right) {
				if(!back)
					continue;
				else {
					temp.right = line.left;
					result.add(temp.toString());
					back = false;
				}
			}
			else {
				if(!back) {
					if(line.left == line.right) {
						result.add(line.toString());
					}
					else {
						temp.left = line.left;
						back = true;
					}
				}
				else if(line.left == line.right){
					temp.right = line.left;
					result.add(temp.toString());
					back = false;
				}
				else if(!line.left.isInclude()) {
					temp.right = line.left;
					result.add(temp.toString());
					temp.left = line.left;
				}
			}	
		}
		temp.right = line.right;
		result.add(temp.toString());

		return result;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		solution();
	}
	
	public static void solution() throws FileNotFoundException, IOException {
		List<Line> lines = scanLine();
		
		set.add(Integer.MIN_VALUE);
		set.add(Integer.MAX_VALUE);

		Integer[] dots = set.toArray(new Integer[0]);
		set = null;
		List<Line> result = new ArrayList<>();

		for(int i = 0; i < dots.length - 1; i++) {
			result.add(step1(lines, dots[i], dots[i + 1]));
		}
		
		List<String> resultStr = step2(result);
		result = null;
		StringBuilder s = new StringBuilder();
		for(String str : resultStr) 
			s.append(str).append("\n");
		
		System.out.println(s);
		System.out.println(System.currentTimeMillis() - time + "ms");
	}

	public static List<Line> scanLine() throws FileNotFoundException, IOException{
		List<Line> lines = new LinkedList<>();

		try(MyScanner scan = MyScannerFactory.makeMyScanner("[0-9]+")){
			scan.find();
			time = System.currentTimeMillis();
			scan.find();
			int count = scan.read();

			for(int i = 0; i < count; i++) {
				int left, right;

				scan.find(); left = scan.read();
				scan.find(); right = scan.read();

				lines.add(new Line(new Dot(left, true), new Dot(right, true)));
				set.add(left);
				set.add(right);
			}
		}
		return lines;
	}
}

class MyScanner implements AutoCloseable{

	private BufferedReader reader;
	private String regex;
	private Pattern pattern;
	private Matcher matcher;

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	public void setRegex(String regex) throws IOException {
		this.regex = regex;
		pattern = Pattern.compile(this.regex);
		matcher = pattern.matcher(reader.readLine());
	}

	// 정규식에 해당하는 문자열이 있는지 확인하는 메소드
	public boolean find() throws IOException {
		if(matcher.find()) {
			return true;
		}
		else {
			String line;
			while(!matcher.find()) {
				line = reader.readLine();
				if(line == null)
					return false;
				matcher = pattern.matcher(line);
			}

			return true;
		}
	}

	// 찾은 문자열을 return 해주는 메소드
	public int read() throws IOException{

		return Integer.valueOf(matcher.group(0));
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}

class MyScannerFactory{
	public static MyScanner makeMyScanner(String regex) 
			throws FileNotFoundException, IOException{

		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		MyScanner scan = new MyScanner();
		scan.setReader(reader);
		scan.setRegex(regex);
		return scan;
	}
}