package gendaClient;
import genda1.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class RoomListener implements ActionListener{

	JTextArea accept;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="һ��"){
			accept.setText("��ѡ��һ�ŷ�\n");
			Window.RoomNum = 1;
		}
		else if(e.getActionCommand()=="����"){
			accept.setText("��ѡ�ж��ŷ�\n");
			Window.RoomNum = 2;
		}
		else if(e.getActionCommand()=="����"){
			accept.setText("��ѡ�����ŷ�\n");
			Window.RoomNum = 3;
		}
		else if(e.getActionCommand()=="�ķ�"){
			accept.setText("��ѡ���ĺŷ�\n");
			Window.RoomNum = 4;
		}
		else if(e.getActionCommand()=="�巿"){
			accept.setText("��ѡ����ŷ�\n");
			Window.RoomNum = 5;
		}
		else if(e.getActionCommand()=="����"){
			accept.setText("��ѡ�����ŷ�\n");
			Window.RoomNum = 6;
		}
		else if(e.getActionCommand()=="�߷�"){
			accept.setText("��ѡ���ߺŷ�\n");
			Window.RoomNum = 7;
		}
		else if(e.getActionCommand()=="�˷�"){
			accept.setText("��ѡ�а˺ŷ�\n");
			Window.RoomNum = 8;
		}		
	}
	public void setAccept(JTextArea t){
		accept = t;
	}
}
