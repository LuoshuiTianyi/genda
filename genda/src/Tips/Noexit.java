package Tips;

import genda1.AchievementListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Noexit implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(Tips.weizhi!=0){
			AchievementListener.setClipboardString(Tips.weizhistr);
			JOptionPane.showMessageDialog(new JTextArea(),"��������"+Tips.weizhi+"��δ��¼�ַ�:\n"+Tips.weizhistr+"\n�ѷ��������");
		}
		else if(Tips.weizhi==0){
			JOptionPane.showMessageDialog(new JTextArea(),"������û��δ��¼���ַ�");
		}
	}
}
