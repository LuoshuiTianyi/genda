package Acticle;

import genda1.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import QQ.QQ;

public class ShareListener extends AbstractAction {
	JLabel qqName;
	public ShareListener(JLabel qqName){
		this.qqName = qqName;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(SendWenben.sendwenSign==0){JOptionPane.showMessageDialog(new JTextArea(),"���Ĺ���ֻ֧�ַ���״̬��ʹ��");return;}
		String share = SendWenben.title+"\n"+Window.wenben.getText()+"\n-----"+"��"+String.valueOf(RegexText.duan1)+"��-��"+String.valueOf(ActicleListener.length-ActicleListener.fontweizhi-ActicleListener.fontnum)+"��";
		AchievementListener.setClipboardString(share); //���ɼ����������
		
		try {
			QQ.sendMessage(2,qqName.getText());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("������ʧ��");
		}
	}

}
