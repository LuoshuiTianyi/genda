package Login;

import genda1.Window;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class heartThread extends Thread{
	private DataOutputStream outputStream;
	Date date1 = getdate(),date2 = getdate();
	public void run(){
		while(true){
            try {
            	Thread.sleep(5*1000);datenum();
            	outputStream = new DataOutputStream(Login.socket.getOutputStream());
				outputStream.writeUTF("����");
	            outputStream.flush(); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 if(Login.dengluSign==1)
						JOptionPane.showMessageDialog(new JTextArea(),"δ֪ԭ���˺�����");
					 try {
					 	Login.socket.close();
					 	link();
					 } catch (IOException e1) {
					 	// TODO Auto-generated catch block
					 	e1.printStackTrace();
					 }
					 Login.dengluSign = 0;
					 Login.confirm.setText("��¼");
					 Window.denglu.setText("��¼");
			}//1s����һ������
		}
	}
	void link(){
		try {
			Login.socket = new Socket(Window.IP,Login.port);
			Login.socket.setSoTimeout(1000);
			DataOutputStream out = new DataOutputStream(Login.socket.getOutputStream());
			DataInputStream in = new DataInputStream(Login.socket.getInputStream());
			out.writeUTF(Login.banben);
			String what = in.readUTF();
			Login.socket.setSoTimeout(0);
			if(!what.equals("�汾��ȷ")){
				UIManager.put("OptionPane.yesButtonText", "�Զ�����");
				UIManager.put("OptionPane.noButtonText", "�ֶ�����");
				int n = JOptionPane.showConfirmDialog(null, what, "������ʾ", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					// ......
					Runtime.getRuntime().exec("����.exe");
					System.exit(0);
				} else if (n == JOptionPane.NO_OPTION) {
					// ......
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://39.96.83.89/new.zip");
					System.exit(0);
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(new JTextArea(),"�����쳣");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(new JTextArea(),"�����쳣");
			e.printStackTrace();
		}
	}
	void datenum(){
		date2 = date1;
		date1 = getdate();
		if(date1.toString().equals(date2.toString())){}
		else 
			Window.datenum = 0;
	}
	Date getdate(){
		int y,m,d;
		Calendar cal;
		cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH); 
		d=cal.get(Calendar.DATE);
		return new Date(y-1900,m,d);
	}
}
