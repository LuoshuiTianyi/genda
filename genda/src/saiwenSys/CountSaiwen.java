package saiwenSys;

import genda1.ReadyListener;
import genda1.Window;
import gendaClient.battleReadThread;

public class CountSaiwen extends Thread {
	public void run(){
		while(true){
			try {
				sleep(1);
			} catch (InterruptedException e1) {}
			for(int i = 5;i>=0;i--){//˫��׼������
				try {
					sleep(1000);
					Window.wenben.setText(String.valueOf(i));
				} catch (InterruptedException e) {System.out.println("����ʧ��");}
			}
			Window.f3listener.f3caozuo();
			Window.gendaListener.sign = 1;
			System.out.println("���Ŀ�ʼ");
			stop();
		}
	}
}
