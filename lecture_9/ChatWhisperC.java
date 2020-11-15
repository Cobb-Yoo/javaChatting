package asdasd;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ChatWhisperC extends Frame implements ActionListener, KeyListener {

	TextArea display;
	TextField wtext, ltext;
	Label mlbl, wlbl, loglbl;
	Button loginb, temp;
	BufferedWriter output;
	BufferedReader input, br;
	Socket client;
	StringBuffer clientdata;
	String serverdata;
	String ID;
	boolean check = true, flag = true;

	private static final String SEPARATOR = "|";
	private static final int REQ_LOGON = 1001;
	private static final int REQ_SENDWORDS = 1021;
	private static final int REQ_WISPERSEND = 1022;
	private static final int REQ_LOGOUT = 1002;
	private static final int REQ_QUIT = 1003;

	public ChatWhisperC() {
		super("클라이언트");

		mlbl = new Label("채팅 상태를 보여줍니다.");
		add(mlbl, BorderLayout.NORTH);

		display = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		display.setEditable(false);
		add(display, BorderLayout.CENTER);

		Panel ptotal = new Panel(new BorderLayout());

		Panel pword = new Panel(new BorderLayout());
		wlbl = new Label("대화말");
		wtext = new TextField(30);
		wtext.addKeyListener(this); 
		pword.add(wlbl, BorderLayout.WEST);
		pword.add(wtext, BorderLayout.EAST);
		ptotal.add(pword, BorderLayout.CENTER);
		Panel plabel = new Panel(new BorderLayout());
		loglbl = new Label("로그온");
		loginb = new Button("ON");
		ltext = new TextField(30); 
		loginb.addActionListener(this); 
		plabel.add(loglbl, BorderLayout.WEST);
		plabel.add(ltext, BorderLayout.CENTER);
		plabel.add(loginb, BorderLayout.EAST);
		ptotal.add(plabel, BorderLayout.SOUTH);

		add(ptotal, BorderLayout.SOUTH);

		addWindowListener(new WinListener());
		setSize(350, 250);
		setVisible(true);
	}

	public void runClient() {
		try {
			client = new Socket(InetAddress.getLocalHost(), 5000);
			mlbl.setText("연결된 서버이름 : " + client.getInetAddress().getHostName());
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			clientdata = new StringBuffer(2048);
			mlbl.setText("접속 완료 사용할 아이디를 입력하세요.");
			while (true) {
				serverdata = input.readLine();
				display.append(serverdata + "\r\n");
				output.flush();
				check = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().compareTo("ON") == 0) {
			if (!(ltext.getText().equals(""))) {
				ID = ltext.getText();
				try {
					clientdata.setLength(0);
					clientdata.append(REQ_LOGON);
					clientdata.append(SEPARATOR);
					clientdata.append(ID);
					output.write(clientdata.toString() + "\r\n");
					output.flush();
					while (check)
						;
					if (serverdata.compareTo("(에러)중복된 ID") == 0) {
						mlbl.setText("중복된 ID 입니다. 다시 입력해주세요.");
						ltext.setText("");
						check = true;
					} else {
						mlbl.setText(ID + "(으)로 로그인 하였습니다.");
						ltext.setVisible(false);
						flag = false;
						loginb.setLabel("OFF");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (ae.getActionCommand().compareTo("OFF") == 0) {
			ID = ltext.getText();
			try {
				clientdata.setLength(0);
				clientdata.append(REQ_LOGOUT);
				clientdata.append(SEPARATOR);
				clientdata.append(ID);
				output.write(clientdata.toString() + "\r\n");
				output.flush();
				wtext.setText("");
				ltext.setText("");
				ltext.setVisible(true);
				loginb.setLabel("ON");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		ChatWhisperC c = new ChatWhisperC();
		c.runClient();
	}

	class WinListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			try {
				clientdata.setLength(0);
				clientdata.append(REQ_QUIT);
				clientdata.append(SEPARATOR);
				clientdata.append(ID);
				output.write(clientdata.toString() + "\r\n");
				output.flush();
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
			String message = wtext.getText();
			StringTokenizer st = new StringTokenizer(message, " ");
			if (flag) {
				mlbl.setText("다시 로그인 하세요!!!");
				wtext.setText("");
			} else {
				try {
					if (st.nextToken().equals("/w")) {
						message = message.substring(3);
						String WID = st.nextToken();
						String Wmessage = st.nextToken();
						while (st.hasMoreTokens()) {
							Wmessage = Wmessage + " " + st.nextToken();
						}
						clientdata.setLength(0);
						clientdata.append(REQ_WISPERSEND);
						clientdata.append(SEPARATOR);
						clientdata.append(ID);
						clientdata.append(SEPARATOR);
						clientdata.append(WID);
						clientdata.append(SEPARATOR);
						clientdata.append(Wmessage);
						output.write(clientdata.toString() + "\r\n");
						output.flush();
						wtext.setText("");
					} else {
						clientdata.setLength(0);
						clientdata.append(REQ_SENDWORDS);
						clientdata.append(SEPARATOR);
						clientdata.append(ID);
						clientdata.append(SEPARATOR);
						clientdata.append(message);
						output.write(clientdata.toString() + "\r\n");
						output.flush();
						wtext.setText("");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void keyReleased(KeyEvent ke) {
	}

	public void keyTyped(KeyEvent ke) {
	}
}