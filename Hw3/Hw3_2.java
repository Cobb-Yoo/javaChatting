package Hw3;
import java.io.*;

public class Hw3_2 {
	public static void main(String args[]) throws IOException {
		String text;
		InputStreamReader isr = new InputStreamReader(System.in);
		FileWriter fw = new FileWriter("example.txt");
		BufferedReader br = new BufferedReader(isr);
		BufferedWriter bw = new BufferedWriter(fw);
		
		int num = 1;
		
		while((text=br.readLine())!=null) {
			text = num + " : " + text +"\n";
			bw.write(text);
			num++;
		}
		bw.flush();
		bw.close();
		
		FileReader fr = new FileReader("example.txt");
		LineNumberReader read = new LineNumberReader(fr);
		while(true) {
			try {
				text = read.readLine();
				if(text==null)break;
			}catch(IOException e) {
				System.out.println(e);
				break;
			}
			System.out.println(text);
		}
		
		try {
			fr.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
