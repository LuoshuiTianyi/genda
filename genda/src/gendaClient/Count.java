package gendaClient;

import genda1.GendaListener;
import genda1.ReadyListener;
import genda1.RegexText;
import genda1.Window;

import javax.swing.JTextArea;

public class Count extends Thread {
	JTextArea communion,dazi;
	public Count(JTextArea communion,JTextArea dazi){
		this.communion = communion;
		this.dazi = dazi;
	}
	public void run(){
		while(true){
			try {
				sleep(1);
			} catch (InterruptedException e1) {}
			if(ReadyListener.BeganSign==1&&battleReadThread.otherready==1){
				communion.append("˫����׼������\n");
				Window.dazi.setText("");
				for(int i = 3;i>=0;i--){//˫��׼������
					if(!(ReadyListener.BeganSign==1&&battleReadThread.otherready==1))break;//�ж���;ȡ��׼��
					try {
						sleep(1000);
					} catch (InterruptedException e) {System.out.println("����ʧ��");}
					if(i!=0){
						communion.append(String.valueOf(i)+"\n");
						dazi.setEditable(false);
					}
					else{
						communion.append("��ʼ����������������\n");
						dazi.setEditable(true);
					}
					communion.setCaretPosition(Window.communion.getText().length());
//					RegexText.duan1 = ReadyListener.ReadyDuan;
					GendaListener.sign = 1;
					GendaListener.comp.setTimeOne();
					GendaListener.record = "";	//�������
				}
				while(ReadyListener.BeganSign==1&&battleReadThread.otherready==1){
					try {
						sleep(100);
					} catch (InterruptedException e1) {}
				}
			}
		}
	}
}
