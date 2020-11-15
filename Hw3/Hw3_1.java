package Hw3;
import java.io.*;

public class Hw3_1 {
	public static void main(String args[]) {
		String buf;
		FileReader fin = null;
		FileWriter fout = null;
		
		if(args.length != 2) {
			System.out.println("소스파일 및 대상파일을 지정하십시오.");
			System.exit(1);
		}
		
		try {
			fin = new FileReader (args[0]);
			fout = new FileWriter(args[1]);
		}catch(IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		
		LineNumberReader read = new LineNumberReader(fin);
		PrintWriter write = new PrintWriter(fout);
		
		while(true) {
			try {
				buf=read.readLine();
				if(buf==null) break;
			}catch(IOException e) {
				System.out.println(e);
				break;
			}
			
			buf = read.getLineNumber() + " : " + buf;
			write.println(buf);
		}
		
		try {
			fin.close();
			fout.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
