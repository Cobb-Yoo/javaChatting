package Hw6;

import java.io.*;
import java.net.*;
import java.util.Date;
public class DayTimeServer
{
   public final static int daytimeport=13;
   public static void main(String args[]){
      ServerSocket theServer;
      
      Socket theSocket = null;

      try{
         theServer = new ServerSocket(daytimeport,5); 
         while(true) {
            try{           
               theSocket = theServer.accept();
               ServerThread thread = new ServerThread(theSocket);
               thread.start();
            }catch(IOException e){
               System.out.println(e);
            }
         }
      }catch(IOException e){
         System.out.println(e);
      }
   }
}
class ServerThread extends Thread
{
    BufferedReader reader;
    BufferedWriter writer;
    Socket theSocket=null;
    public ServerThread(Socket theSocket1) {
    	theSocket=theSocket1;
    }
    public void run() {
    	try {
    		OutputStream os;
			os = theSocket.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os));
			Date now = new Date();             
			writer.write(now.toString()+"\r\n");
			writer.flush();
			InputStream in = theSocket.getInputStream();
			reader = new BufferedReader(new InputStreamReader (in));
			System.out.println(reader.readLine());
		} catch (IOException e) {
			System.out.println(e);
		}
    }
}