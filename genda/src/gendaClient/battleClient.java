package gendaClient;
import genda1.*;

import java.io.*;
import java.net.*;
import javax.swing.*;
/*�ͻ�����*/
public class battleClient{
	JTextArea dazi;
	JTextArea accept;
	JTextField name;
	public static Socket socket;
	public static battleSend send;
	Thread readThread,heartThread;
	
	int portNum[] = {1111,2222,3333,4444,5555,6666,7777,8888};
	GendaJindutiao gendajindu;
	public void StratClient(){
		try {
			socket = new Socket(Window.IP,portNum[Window.RoomNum-1]);//�����ͻ���
			send = new battleSend(dazi,accept,socket);
//			((dazi.getDocument())).addDocumentListener(sendlistener);
			readThread = new battleReadThread(socket,accept,gendajindu);//������ȡ�ӷ������˷�������Ϣ
			//			readThread.start();
		}
		catch (UnknownHostException e) {
			System.out.println("�Է����ӶϿ�");
		}
		catch (IOException e) { 
			System.out.println("���󣺷�����δ����������");
		}
	}
	public void setDazi(JTextArea dazi){
		this.dazi = dazi;
	}
	public void setAccept(JTextArea accept2){
		this.accept = accept2;
	}
	public void setGendajindu(GendaJindutiao setGendajindu) {
		// TODO Auto-generated method stub
		this.gendajindu = setGendajindu;
	}
	public void setName(JTextField name){
		this.name = name;
	}
}