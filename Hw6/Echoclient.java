package Hw6;

import java.io.*;
import java.net.*;

public class Echoclient
{
	public static void main(String args[])
	{
		Socket theSocket = null;
		String host;
		InputStream is;
		BufferedReader reader, userInput;
		OutputStream os;
		BufferedWriter writer;
		String theLine;
		
		if(args.length>0)
		{
			host=args[0];
		}
		else
		{
			host="localhost";
		}
		
		try
		{
			theSocket = new Socket(host, 5000);
			is = theSocket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			userInput = new BufferedReader(new InputStreamReader(System.in));
			os = theSocket.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
			System.out.println("전송할 문장을 입력하십시오.");
			
			while(true)
			{
				theLine = userInput.readLine();
				if(theLine.contentEquals("quit"))
					break;
				writer.write(theLine+'\r'+'\n');
				writer.flush();
				System.out.println(reader.readLine());
			}
		}
		catch(UnknownHostException e)
		{
			System.out.println(args[0]);
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
		finally
		{
			try
			{
				theSocket.close();
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
}
