package Hw6;

import java.io.*;
import java.net.*;
public class DayTimeClient
{
   public static void main(String args[]){
      Socket theSocket = null;
      String host;
      InputStream is;
      OutputStream out;
      BufferedReader reader;
      BufferedWriter writer;
      if(args.length>0){
         host=args[0];
      }else{
         host="localhost";
      }
      try{
         theSocket = new Socket(host, 13);
         is = theSocket.getInputStream();
         out = theSocket.getOutputStream();
         reader = new BufferedReader(new InputStreamReader(is));
         writer = new BufferedWriter(new OutputStreamWriter(out));
         String theTime = reader.readLine();
         System.out.println("ȣ��Ʈ�� �ð��� "+theTime+"�̴�");
         writer.write("Thank You!\r\n");
         writer.flush();
         while(true)
        	 ;
      }catch(UnknownHostException e){
         System.err.println(args[0]+" ȣ��Ʈ�� ã�� �� �����ϴ�.");
      }catch(IOException e){
         System.err.println(e);
      }
   }
}