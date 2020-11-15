package Hw1;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class Hw1 extends Frame implements ActionListener
{
	private TextField enter;
	private TextArea contents;
	

	public Hw1()
	{
		super("호스트 파일 읽기123");
		setLayout( new BorderLayout() );
		enter = new TextField("URL를 입력하세요!");
		enter.addActionListener(this);
		add(enter, BorderLayout.NORTH);
		contents = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		add(contents,BorderLayout.CENTER);
		addWindowListener(new WinListener());
		setSize(350,400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		URL url;
		URLConnection urlconn;
		InputStream is;
		BufferedReader input;
		String line;
		StringBuffer buffer = new StringBuffer();
		String location = e.getActionCommand();
		
		try
		{
			url = new URL(location);
			urlconn = url.openConnection();
			is = urlconn.getInputStream();
			input = new BufferedReader(new InputStreamReader(is));
			contents.setText("파일을 읽는 중입니다...");
			while((line = input.readLine()) != null)
			{
				buffer.append(line).append("\n");
			}
			contents.setText(buffer.toString());
			input.close();
		}
		catch(MalformedURLException mal)
		{
			contents.setText("URL 형식이 잘못되었습니다.");
		}
		catch(IOException io)
		{
			contents.setText(io.toString());
		}
		catch(Exception ex)
		{
			contents.setText("호스트 컴퓨터의 파일만을 열 수 있습니다.");
		}
	}
	
	public static void main(String args[])
	{
		Hw1 read = new Hw1();
	}
	class WinListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent we) 
		{
			System.exit(0);
		}
	}
}
