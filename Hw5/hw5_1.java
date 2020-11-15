package Hw5;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class hw5_1 extends Frame implements ActionListener{
	private TextField enter;
	private TextArea textarea;
	
	public hw5_1() {
		super("호스트 파일 읽기");
		setLayout(new BorderLayout());
		
		enter = new TextField("URL을 입력하세요.");
		enter.addActionListener(this);
		add(enter, BorderLayout.NORTH);
		
		textarea = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		add(textarea, BorderLayout.CENTER);
		
		addWindowListener(new WinListener());
		setSize(350, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		URL url;
		HttpURLConnection uc;
		InputStream is;
		BufferedReader input;
		String line;
		StringBuffer buffer = new StringBuffer();
		String location = e.getActionCommand();
		try {
			url = new URL(location);
			uc = (HttpURLConnection) url.openConnection();
			is = url.openStream();
			int cnt = 0;
			input = new BufferedReader(new InputStreamReader(is));
			
			while(uc.getHeaderField(cnt) != null) {
				if(uc.getHeaderFieldKey(cnt) != null)
					buffer.append(uc.getHeaderFieldKey(cnt) + " : " +uc.getHeaderField(cnt)).append('\n');
				else
					buffer.append(uc.getHeaderField(cnt)).append('\n');
				cnt++;
			}
			
			Object o = url.getContent();
			if(o instanceof InputStream) {
				while((line = input.readLine())!= null)
					buffer.append(line).append('\n');
				textarea.setText(buffer.toString());
			}else  {
				textarea.setText(buffer.toString());
			}
			
			input.close();
		}catch(MalformedURLException mal) {
			textarea.setText("URL 형식이 잘못되었습니다.");
		}catch(IOException io) {
			textarea.setText(io.toString());
		}catch(Exception ex) {
			textarea.setText("호스트 컴퓨터의 파일만을 열 수 있습니다.");
		}
	}
	public static void main(String args[]) {
		hw5_1 read = new hw5_1();
	}
	
	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent we) {
			System.exit(0);
		}
	}
}
