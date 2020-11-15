package Hw6;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer
{
	public static void main(String args[]) throws IOException 
	{
		ServerSocket theServer;
				
		while (true) {
			theServer = new ServerSocket(5000, 7);
			Socket theSocket = theServer.accept();
			MultiThread client = new MultiThread(theSocket);
			client.start();
		}
	}
}	
class MultiThread extends Thread
{
	ServerSocket theServer;
	Socket sock = null;
	InputStream is;
	BufferedReader reader;
	OutputStream os;
	BufferedWriter writer;
	String data;
	
	MultiThread(Socket sock){
		this.sock = sock;
	}
	
	public void run()
	{
	  	try
		{
			is = sock.getInputStream();
			os = sock.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			writer = new BufferedWriter(new OutputStreamWriter(os));
			
			String tmp = null;
			
			while((tmp = reader.readLine()) != null)
			{
				System.out.println(tmp);
				writer.write(tmp);
				writer.newLine();
				writer.flush();
			}
				
		}
		catch(UnknownHostException e)
		{
			System.err.println(e);
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	  	finally
		{
			try
			{
				sock.close();
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
}