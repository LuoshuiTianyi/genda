package genda1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import keep.readWrite;
import Login.*;
import Ranking.rankFrame;
public class Example{
	public static void main(String args[]){
		int readsign;
		Window win = new Window();
		win.setTitle("����");
		win.setBounds(100,100,1110,515);
		
		try {
			readWrite.read(win);//��ȡ���������
		} catch (IOException e1) {System.out.println("��ȡ����ʧ��");}
		String message = "�Ժ����ʱ���ⲿ����jre,set����Ҫ���滻��ֻ��Ҫ�滻�ڲ��ġ�������1.xx���ļ���\n�����˵�¼���ܣ���¼��Ḳ�ǵ�����ԭ������\n����ָ�������������ϵ����\n����";
		String update = "���θ������������а��ڲ˵�����\n������ʾҪ�滻��ԭ���滻������ļ�";
		try{
			if(readWrite.readsign()==0){
				JOptionPane.showMessageDialog(new JTextArea(),message);
				JOptionPane.showMessageDialog(new JTextArea(),update);
				readWrite.setreadsign();
			}
		}catch(IOException e2){System.out.println("��ȡ�ļ���ʾʧ��");}
		try{
			readWrite.readzm();
			Login.denglulistener.denglu();
		}
		catch(Exception e){System.out.println("��ȡ�˺�����ʧ��");}
	}
}