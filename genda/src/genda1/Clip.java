package genda1;

import gendaClient.battleClient;

import java.io.DataOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Login.Login;

import SetWin.SetFrameJinduListener;
import SetWin.SetFrameQianshuiListener;
import Tips.*;
public class Clip extends Thread{

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public void run(){
		// TODO Auto-generated method stub
		while(true){
			try{
				sleep(10);
				if(QQZaiwenListener.zaiwenSign)
					setZaiwenSign();
				if(GendaListener.gendaSign)
					setgendaSign();
			}catch (Exception e) {
				e.printStackTrace();System.out.println("Clip����");}
//			System.out.println(wenbenstr);
//			AchievementListener.setClipboardString(AchievementListener.getClipboardString());
		}
	}
	void setZaiwenSign() throws InterruptedException{
		QQZaiwenListener.zaiwenSign = false;
		QQZaiwenListener.str = AchievementListener.getClipboardString();
		QQZaiwenListener.wenbenstr = QQZaiwenListener.regexText.regetText(QQZaiwenListener.str);
		Window.f3listener.F3();
		try{
			DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
			out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+QQZaiwenListener.wenbenstr+"%0"+"%"+Login.zhanghao.getText());
		}
		catch(Exception ex){
			System.out.println("�޷������ı�����");
		}
		
		System.out.println(QQZaiwenListener.zaiwenSign);
	}
	void setgendaSign() throws InterruptedException{
		GendaListener.gendaSign=false;
		sleep(100);
		GendaListener.sign=2;
		Window.gendaListener.sudu = Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),(int)(Window.gendaListener.mistake));		//�ٶ���ʾ
		Window.gendaListener.zishu.setText("�����������:"+Window.gendaListener.str2.length()+"/��"+Window.gendaListener.mistake);	//������ʾ
		Window.gendaListener.Keylength.setText(String.format("%.2f", Window.gendaListener.KeyNumber/Window.gendaListener.str2.length()));//�볤��ʾ
		Window.gendaListener.deleteNumber = Window.gendaListener.deleteNumber-Window.gendaListener.deleteTextNumber;	//�˸���ʵ����Ҫ��ȥ�ظ�����
		if(Window.gendaListener.deleteNumber<0)Window.gendaListener.deleteNumber = 0;	//��֤�˸�С����
		ReadyListener.BeganSign = 0;			//׼����־
		Window.suduButton.setText(String.format("%.2f",Window.gendaListener.sudu));
		AchievementListener.setClipboardString(Window.gendaListener.achievementlistener.getGeshi()); //���ɼ����������
		if(SetFrameQianshuiListener.qianshui == 0)		//��ΪǱˮ����Ļ����ͳɼ�
			Window.gendaListener.achievementlistener.sendchengji();
		if(SetFrameJinduListener.jindusign==1)//�ж��Ƿ��˽�����
			Window.gendaListener.gendajindu.jindu(Window.gendaListener.dazi.getText().length()+1);
		Window.gendaListener.ChangeAllColor();
		
	}
}