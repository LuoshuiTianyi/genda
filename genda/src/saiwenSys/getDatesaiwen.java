package saiwenSys;
import genda1.GendaListener;
import genda1.QQZaiwenListener;
import genda1.RegexText;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Acticle.SendWenben;
import Login.*;
public class getDatesaiwen implements ActionListener {
	Window win;
	public getDatesaiwen(Window win){
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(SendWenben.sendwenSign==1){JOptionPane.showMessageDialog(new JTextArea(),"�Ƚ�������");return;}
		if(Window.everydaySign){JOptionPane.showMessageDialog(new JTextArea(),"���ڽ�������");return;}
		int n = JOptionPane.showConfirmDialog(null, "�ڸ���ÿ������ʱ���ش�ϵͳ�Ƴ��������в������������£������˳���¼�������˳��������ȵ�һ�г��Զ��θ������Ϊ��������ճɼ�����\n�����������뷨���䣬���÷�ֹ�κε�����׼����׼��������?������ǡ���������ʼ��ʱ", "������ʾ", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			// ......
			try {
				Login.socket.setSoTimeout(1000);
				DataOutputStream out = new DataOutputStream(Login.socket.getOutputStream());
				DataInputStream in = new DataInputStream(Login.socket.getInputStream());
				out.writeUTF("��ȡ��������");
				String message = in.readUTF();
				if(message.equals("�Ѵ��")){
					JOptionPane.showMessageDialog(new JTextArea(),"������Ѵ������");return;
				}
				SendWenben.title = "������ÿ������-���ߣ��������";
				QQZaiwenListener.wenbenstr = message;
				Window.everydaySign = true;
				RegexText.duan1 = 0;
//				Window.f3listener.f3caozuo();
				Window.dazi.setEditable(false);
				CountSaiwen cs = new CountSaiwen();
				cs.start();
				win.setAlwaysOnTop(true);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} else if (n == JOptionPane.NO_OPTION) {
			// ......
			
		}
	}
}