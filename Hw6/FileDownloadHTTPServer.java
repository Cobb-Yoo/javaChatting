package Hw6;

import java.io.*;
import java.net.*;

import java.io.*;
import java.net.*;

public class FileDownloadHTTPServer {
	public static void main(String args[]) {
		int b, port=80;
		String tmp;
		
		String contenttype = "text/plain";
		try {
			ServerSocket server = new ServerSocket(port);
			while(true) {
				Socket connection = null;
				FileDownload client = null;
				try {
					connection = server.accept();
					client = new FileDownload(connection, contenttype, port);
					client.start();
				}catch(IOException e){
					System.out.println(e);
				}
			}			
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}

class FileDownload extends Thread{
	private byte[] header;
	private int port;
	Socket connection;
	BufferedOutputStream out;
	BufferedInputStream in;
	int b;
	
	public FileDownload(Socket connection, String MIMEType, int port) throws UnsupportedEncodingException{
		this.connection = connection;
		this.port = port;
		String header = "HTTP 1.0 200 OK\r\n"+"Server: OneFile 1.0\r\n"+"\r\n"+"Content-type: "+MIMEType+"\r\n\r\n";
		this.header = header.getBytes("ASCII");
	}
	
	public void run() {
		try {
			out = new BufferedOutputStream(connection.getOutputStream());
			in = new BufferedInputStream(connection.getInputStream());
			
			StringBuffer request = new StringBuffer(80);
			while(true) {
				int c = in.read();
				if(c=='\r' || c=='\n' || c == 1) break;
				request.append((char)c);
			}
			
			String str = request.toString();
			str = str.substring(str.indexOf("/")+1, str.lastIndexOf(" "));
			
			System.out.println("요청된 파일은 : " + str + " 입니다.");
 
			byte[] arr;
			
			try {
				FileInputStream fin = new FileInputStream(str);
				ByteArrayOutputStream fout = new ByteArrayOutputStream();
				
				
				if(fin != null) {
					while((b=fin.read()) != -1) out.write(b);
					arr=fout.toByteArray();
				
					out.write(arr);
					out.flush();
				}
			}catch(IOException e) {
				System.out.println(e);
			}			
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}