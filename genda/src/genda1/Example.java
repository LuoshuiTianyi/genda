package genda1;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import keep.readWrite;
import Login.*;
import Ranking.rankFrame;
public class Example{
	public static String systemname;
	public static void main(String args[]){
//		int readsign;
		Window win = new Window();
		
		win.setTitle("����");
		win.setBounds(100,100,710,515);
		
		Properties props = System.getProperties();
		systemname = props.getProperty("os.name");
		System.out.println("����ϵͳ�����ƣ�" + props.getProperty("os.name"));
        System.out.println("����ϵͳ�İ汾��" + props.getProperty("os.version"));
		
		try {
			readWrite.read(win);//��ȡ���������
		} catch (IOException e1) {System.out.println("��ȡ����ʧ��");}
		String message = "�Ժ����ʱ���ⲿ����jre,set����Ҫ���滻��ֻ��Ҫ�滻�ڲ��ġ�������1.xx���ļ���\n��¼��Ḳ�ǵ�����ԭ������\n����ָ�������������ϵ����\n��ӭʹ�ã�����������Ⱥ974172771\n����";
		try{
			if(readWrite.readsign()==0){
				JOptionPane.showMessageDialog(new JTextArea(),message);
//				JOptionPane.showMessageDialog(new JTextArea(),update);
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