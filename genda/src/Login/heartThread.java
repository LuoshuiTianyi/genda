package Login;

import genda1.Window;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class heartThread extends Thread{
	private DataOutputStream outputStream;
	Socket socket;
	public heartThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	public void run(){
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			while(true){
                try {
					Thread.sleep(5*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//1s����һ������
                outputStream.writeUTF("����");
                outputStream.flush(); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 JOptionPane.showMessageDialog(new JTextArea(),"δ֪ԭ���˺�����");
			 try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 Login.dengluSign = 0;
			 Login.confirm.setText("��¼");
			 Window.denglu.setText("��¼");
		}
		
	}	
}
