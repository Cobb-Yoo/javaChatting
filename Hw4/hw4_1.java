package Hw4;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class hw4_1 extends Frame implements ActionListener{
	private TextField enter;
	private TextArea contents;
	private TextArea textarea;
	
	public hw4_1() {
		super("ȣ��Ʈ ���� �б�");
		setLayout(new BorderLayout());
		
		enter = new TextField("URL�� �Է��ϼ���!");
		enter.addActionListener(this);
		add(enter, BorderLayout.NORTH);
		
		textarea = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		add(textarea, BorderLayout.CENTER);
		
		contents = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		add(contents, BorderLayout.SOUTH);
		
		
		addWindowListener(new WinListener());
		setSize(350, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		URL url;
		InputStream is;
		BufferedReader input;
		String line;
		StringBuffer buffer = new StringBuffer();
		String location = e.getActionCommand();
		try {
			url = new URL(location);
			is = url.openStream();
			input = new BufferedReader(new InputStreamReader(is));
			
			textarea.setText("protocol : "+url.getProtocol() + "\n");
			textarea.append("host name : "+url.getHost() + "\n");
			textarea.append("port no : "+url.getPort() + "\n");
			textarea.append("file name : "+url.getFile() + "\n");
			textarea.append("hash code : "+url.hashCode() + "\n");

			
			contents.setText("������ �д� ���Դϴ�...");
			
			Object o = url.getContent();
			if(o instanceof InputStream) {
				while((line = input.readLine())!= null)
					buffer.append(line).append('\n');
				contents.setText(buffer.toString());
			}else  {
				contents.setText(o.getClass().getName());
			}
			
			input.close();
		}catch(MalformedURLException mal) {
			contents.setText("URL ������ �߸��Ǿ����ϴ�.");
		}catch(IOException io) {
			contents.setText(io.toString());
		}catch(Exception ex) {
			contents.setText("ȣ��Ʈ ��ǻ���� ���ϸ��� �� �� �ֽ��ϴ�.");
		}
	}
	public static void main(String args[]) {
		hw4_1 read = new hw4_1();
	}
	
	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}
}
