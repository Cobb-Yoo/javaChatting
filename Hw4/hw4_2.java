package Hw4;	
import java.net.*;
import java.io.*;
import java.util.*;

public class hw4_2 {
	public static void main(String args[]) {
		URL u;
		URLConnection uc;
		Scanner src = new Scanner(System.in);;
		String adrs;
		String data; 
		String fileName;
		File file = new File("download12");
		
		if(!file.exists()) {
			try {
				file.mkdirs();
			}catch(Exception e) {
				e.fillInStackTrace();
			}
		}
		
		try {
			fileName = getFileNameFromURL(file.toString());
			adrs = src.nextLine();
			
			u = new URL(adrs);
			uc = u.openConnection();
			
			System.out.println("������ ���� : " + uc.getContentType());
			System.out.println("������ ���ڵ� : " + uc.getContentEncoding());
			System.out.println("�������۳�¥ : " + new Date(uc.getDate()));
			System.out.println("����������¥ : " + new Date(uc.getLastModified()));
			System.out.println("�������⳯¥ : " + new Date(uc.getExpiration()));
			System.out.println("�������� : " + uc.getContentLength());
			
			InputStream is = uc.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			BufferedWriter rw = new BufferedWriter(new FileWriter(file + "/" + fileName));
			
			while((data = reader.readLine()) != null) {
				rw.write(data);
				rw.newLine();
				rw.flush();
			}
			
		}catch(MalformedURLException e) {
			System.out.println(e);
		}catch(IOException e) {
			System.out.println(e);
		}
		
		
	}

	private static String getFileNameFromURL(String url) {
		return url.substring(url.lastIndexOf('/')+1,url.length());
	}
}
