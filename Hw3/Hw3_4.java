package Hw3;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.applet.*;

public class Hw3_4 extends Frame implements ActionListener{
	TextField hostname;
	Button getinfor;
	TextArea display;
	TextArea display_2;
	public static void main(String args[]) {
		Hw3_4 host = new Hw3_4("InetAddress 클래스");
		host.setVisible(true);
	}
	public Hw3_4(String str) {
		super(str);
		addWindowListener(new WinListener());
		setLayout(new BorderLayout());
		
		Panel inputpanel = new Panel();
		inputpanel.setLayout(new BorderLayout());
		inputpanel.add("North", new Label("호스트 이름:"));
		hostname = new TextField("",30);
		getinfor = new Button("호스트 정보 얻기");
		inputpanel.add("Center",hostname);
		inputpanel.add("South",getinfor);
		getinfor.addActionListener(this);
		add("North",inputpanel);
		
		Panel outputpanel = new Panel();
		outputpanel.setLayout(new BorderLayout());
		display = new TextArea("",24,20);
		display.setEditable(false);
		outputpanel.add("North",new Label("인터넷 주소"));
		outputpanel.add("Center",display);
		add("Center", outputpanel);
		
		Panel thirdpanel = new Panel();
		thirdpanel.setLayout(new BorderLayout());
		display_2 = new TextArea("",24,20);
		display_2.setEditable(false);
		thirdpanel.add("North",new Label("호스트의 대표 IP 주소"));
		thirdpanel.add("Center",display_2);
		add("South", thirdpanel);
		
		setSize(500, 800);
	}
	public void actionPerformed(ActionEvent c) {
		String name = hostname.getText();
		try {
			InetAddress inet = InetAddress.getByName(name);
			String ip = inet.getHostName()+"\n";
			display.append(ip);
			InetAddress ips[] = InetAddress.getAllByName(name);
			for(int i=0;i<ips.length;i++)
				display.append(ips[i].toString()+"\n");
			
			display_2.append(Character.toString(ipClass(inet.getAddress()))+"\n");
			display_2.append(Integer.toString(inet.hashCode())+"\n");
			
		}catch(UnknownHostException ue) {
			String ip = name + ": 해당 호스트가 없습니다.\n";
			display.append(ip);
		}
	}
	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}
	static char ipClass(byte[] ip) {
		int highByte = 0xff & ip[0];
		return (highByte<128) ? 'A' : (highByte<129) ? 'B' : (highByte<244) ? 'C' : (highByte<240) ? 'D' : 'E';
	}
}
