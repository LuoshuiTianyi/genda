package EmailSys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Email implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("������"))
			if(Login.Login.dengluSign==1)
				new EmailFrame().setTitle("������");
			else
				JOptionPane.showMessageDialog(new JTextArea(),"���ȵ�¼");
	}
}
