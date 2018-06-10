package homwork.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyScannerFactory{
	public static MyScanner makeMyScanner(String filePath, String regex) 
			throws FileNotFoundException, IOException{

		BufferedReader reader = 
				new BufferedReader(new FileReader(new File(filePath)));
		MyScanner scan = new MyScanner();
		scan.setReader(reader);
		scan.setRegex(regex);
		return scan;
	}
}