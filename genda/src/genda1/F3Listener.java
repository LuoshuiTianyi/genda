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
		F3();
	}
	public void F3(){
		dazi.setText(null);
		dazi.setEditable(true);
		
		gendaListener.setSign(0);
		zishu.setText("����:"+wenben.getText().length()+"/�Ѵ�:"+0);
		
		gendaListener.KeyNumber = 0;
		gendaListener.deleteNumber = 0;
		gendaListener.deleteTextNumber = 0;
		gendaListener.left = 0;
		gendaListener.right = 0;
		gendaListener.repeat = 0;
		gendaListener.space = 0;
		gendaListener.suduButton.setText("0.00");
		gendaListener.KeysuduButton.setText("0.00");
		gendaListener.Keylength.setText("0.00");
//		gendaListener.record = "";
		battleSend.Mistake ++;
		//������
		if(SetFrameJinduListener.jindusign==1)
			gendajindu.open(QQZaiwenListener.wenbenstr.length());//����������
		//��������
		Window.tipschange.changecolortip();
		wenben.setText("");
		Window.gendaListener.ChangeFontColor();
		wenben.setCaretPosition(0);
		//�������
		gendaListener.daciall = 0;
		gendaListener.daci = 0;
		//�����볤
		QQZaiwenListener.lilun = 1.0*Window.tipschange.compalllength()/QQZaiwenListener.wenbenstr.length();
		Window.lilunma.setText("�����볤:"+String.format("%.2f", QQZaiwenListener.lilun));
		Window.zishu.setText("����:"+QQZaiwenListener.wenbenstr.length()+"/�Ѵ�:"+0+"/��"+0);

		dazi.requestFocusInWindow();
	}
}