package FriendSys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Login.Login;

public class addFriends implements ActionListener{
	JTextField addfriendname;
	public addFriends(JTextField addfriendname) {
		// TODO Auto-generated constructor stub
		this.addfriendname = addfriendname;
	}
	DataOutputStream out;
	DataInputStream in;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			out = new DataOutputStream(Login.socket.getOutputStream());
			in = new DataInputStream(Login.socket.getInputStream());
		}catch (UnknownHostException e) {
			System.out.println("�����¼�������1");
		} catch (IOException e) {
			System.out.println("�����¼�������2");
		}
		try {
			out.writeUTF("��Ӻ���");
			out.writeUTF(addfriendname.getText());
			String message = in.readUTF();
			if(message.equals("������"))
				JOptionPane.showMessageDialog(new JTextArea(),"�Է�������");
			else if(message.equals("������"))
				JOptionPane.showMessageDialog(new JTextArea(),"û�и��û�");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
