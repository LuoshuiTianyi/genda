package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import keep.KeyPassword;

public class ZhuceListener implements ActionListener {

	Login login;
	ZhuceListener(Login login){
		this.login = login;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			if(Login.dengluSign == 0){
				Login.out = new DataOutputStream(Login.socket.getOutputStream());
				Login.in =  new DataInputStream(Login.socket.getInputStream());
				String message = "%2%"+Login.zhanghao.getText()+"%"+Login.mima.getText()+"%��"+"%��"+"%��"+"%��";
				message = KeyPassword.convertMD5(message);
				Login.out.writeUTF(message);
				int i = Integer.parseInt(Login.in.readUTF());
				if(i==1)
					JOptionPane.showMessageDialog(new JTextArea(),"ע��ɹ����¼");
				else if(i==2)
					JOptionPane.showMessageDialog(new JTextArea(),"�Ѵ�����ͬ�û���");
				else
					JOptionPane.showMessageDialog(new JTextArea(),"δ֪����,��ϵ����");
			}
			else if (Login.dengluSign == 1){
				JOptionPane.showMessageDialog(new JTextArea(), "��¼״̬���޷�ע��");
			}
		}
		catch(Exception e){JOptionPane.showMessageDialog(new JTextArea(),"��������");}
	}

}
