package genda1;

import java.awt.event.ActionEvent;


import javax.swing.*;

import gendaClient.battleClient;
import gendaClient.battleSend;
import SetWin.SetFrameJinduListener;
public class F3Listener extends AbstractAction{
	JButton F3;
	JLabel zishu;
	JTextArea dazi,chengji;
	JTextPane wenben;
	GendaJindutiao gendajindu;
	GendaListener gendaListener;
	JScrollBar JSB;
	public void setDaziText(JTextArea t){
		dazi = t;
	}
	public void setChengjiText(JTextArea t){
		chengji = t;
	}
	public void setZishu(JLabel zishu2){
		zishu = zishu2;
	}
	public void setWenben(JTextPane t){
		wenben = t;
	}
	public void setGendaListener(GendaListener t){
		gendaListener = t;
	}
	public void setJSB(JScrollBar t){
		JSB = t;
	}
	public void setJProgressBar(GendaJindutiao t){
		gendajindu = t;
	}
	public void actionPerformed(ActionEvent e) {
		dazi.setText(null);
		dazi.setEditable(true);
		chengji.append("ѡ���ش�\n");
		gendaListener.setSign(0);
		gendaListener.KeyNumber = 0;
		zishu.setText("����:"+wenben.getText().length()+"/�Ѵ�:"+0);
		chengji.setCaretPosition(chengji.getText().length());
		dazi.requestFocusInWindow();
		
		gendaListener.KeyNumber = 0;
		gendaListener.deleteNumber = 0;
		gendaListener.deleteTextNumber = 0;
		gendaListener.left = 0;
		gendaListener.right = 0;
		gendaListener.repeat = 0;
		gendaListener.space = 0;
//		gendaListener.record = "";
		battleSend.Mistake ++;
		if(SetFrameJinduListener.jindusign==1)
			gendajindu.open(wenben.getText().length());
		//����仯
		String wenbenstr = wenben.getText();
		wenben.setText("");
		gendaListener.ChangeFontColor();
		////////////////////
		wenben.setCaretPosition(0);
		//�������
		gendaListener.daciall = 0;
		gendaListener.daci = 0;
//		try{
//			if(!Client2.socket.isClosed()){
//				SendListener.Mistake++;
//				SendListener.out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+wenben.getText()+"%0");		
//			}
//		}
//		catch(Exception ex){
//			System.out.println("�޷������ı�����F3");
//		}
	}
}