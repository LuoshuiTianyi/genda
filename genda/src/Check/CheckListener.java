package Check;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CheckListener implements ActionListener {
	JTextArea checkstext;
	public CheckListener(JTextArea checkstext){
		this.checkstext = checkstext;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("����У��"))
			if(DoCheck.checkstr(checkstext.getText(),"kanda"))
				JOptionPane.showMessageDialog(new JTextArea(),"��");
			else
				JOptionPane.showMessageDialog(new JTextArea(),"��");
		else if(e.getActionCommand().equals("����У��"))
			if(DoCheck.checkstr(checkstext.getText(),"genda"))
				JOptionPane.showMessageDialog(new JTextArea(),"��");
			else
				JOptionPane.showMessageDialog(new JTextArea(),"��");
		else if(e.getActionCommand().equals("Ӣ��У��"))
			if(DoCheck.checkstr(checkstext.getText(),"yingda"))
				JOptionPane.showMessageDialog(new JTextArea(),"��");
			else
				JOptionPane.showMessageDialog(new JTextArea(),"��");
	}
}