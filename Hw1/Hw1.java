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
		super("ȣ��Ʈ ���� �б�123");
		setLayout( new BorderLayout() );
		enter = new TextField("URL�� �Է��ϼ���!");
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
			contents.setText("������ �д� ���Դϴ�...");
			while((line = input.readLine()) != null)
			{
				buffer.append(line).append("\n");
			}
			contents.setText(buffer.toString());
			input.close();
		}
		catch(MalformedURLException mal)
		{
			contents.setText("URL ������ �߸��Ǿ����ϴ�.");
		}
		catch(IOException io)
		{
			contents.setText(io.toString());
		}
		catch(Exception ex)
		{
			contents.setText("ȣ��Ʈ ��ǻ���� ���ϸ��� �� �� �ֽ��ϴ�.");
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
