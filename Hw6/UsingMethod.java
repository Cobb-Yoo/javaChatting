package Hw6;

import java.util.*;
import java.io.*;
import java.net.*;

public class UsingMethod
{
	public static void main(String[] args) throws IOException
	{
		Socket thesocket = new Socket();
		
		System.out.println("Send Buffer Size: " + thesocket.getSendBufferSize());
        System.out.println("Receive Buffer size: "+thesocket.getReceiveBufferSize());
        System.out.println("Keep Alive="+ thesocket.getKeepAlive());
        System.out.println("Tcp No Delay="+ thesocket.getTcpNoDelay());
        System.out.println("Reuse Address="+ thesocket.getReuseAddress());
        
        System.out.println("");
	
		thesocket.setSendBufferSize(40);
		thesocket.setReceiveBufferSize(789);
		thesocket.setKeepAlive(true);
		thesocket.setTcpNoDelay(true);
		
		System.out.println("Send Buffer Size: " + thesocket.getSendBufferSize());
        System.out.println("Receive Buffer size: "+thesocket.getReceiveBufferSize());
        System.out.println("Keep Alive="+ thesocket.getKeepAlive());
        System.out.println("Tcp No Delay="+ thesocket.getTcpNoDelay());
        System.out.println("Reuse Address="+ thesocket.getReuseAddress());
	}
}

