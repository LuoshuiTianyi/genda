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

import keep.KeyPassword;

public class heartThread extends Thread{
	Date date1 = getdate(),date2 = getdate();
	public void run(){
		try{

		}catch(Exception e){}
		while(true){
            try {
            	Thread.sleep(5*1000);
            	datenum();
            	String message = KeyPassword.convertMD5("����");
            	System.out.println("����");
				Login.out.writeUTF(message); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 if(Login.dengluSign==1)
						JOptionPane.showMessageDialog(new JTextArea(),"δ֪ԭ���˺�����");
					 try {
					 	Login.socket.close();
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
