package Login;

import genda1.Example;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import keep.KeyPassword;

public class ZhuceListener implements ActionListener {

	Login login;

	ZhuceListener(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			if (Login.dengluSign == 0) {
				Login.socket = new Socket(Window.IP, Login.port);

				Login.socket.setSoTimeout(500);
				Login.out = new DataOutputStream(Login.socket.getOutputStream());
				Login.in = new DataInputStream(Login.socket.getInputStream());
				// ObjectOutputStream outputToServer = new
				// ObjectOutputStream(Login.out);
				// ObjectInputStream inputByServer = new
				// ObjectInputStream(Login.in);
				Login.out.writeUTF(Login.banben);
				String what = Login.in.readUTF();
				Login.socket.setSoTimeout(0);
				if (!what.substring(0, 4).equals("�汾��ȷ")) {
					UIManager.put("OptionPane.yesButtonText", "�Զ�����");
					UIManager.put("OptionPane.noButtonText", "�ֶ�����");
					int n = JOptionPane.showConfirmDialog(null, what, "������ʾ",
							JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						// ......
						if (Example.systemname.length() >= 7
								&& Example.systemname.substring(0, 7).equals(
										"Windows"))
							Runtime.getRuntime().exec("����.exe");
						else
							Runtime.getRuntime().exec("java -jar update.jar");
						System.exit(0);
					} else if (n == JOptionPane.NO_OPTION) {
						// ......
						Runtime.getRuntime()
								.exec("rundll32 url.dll,FileProtocolHandler http://39.96.83.89/new.zip");
						return;
					}
				}
				String message = "%2%" + Login.zhanghao.getText() + "%"
						+ Login.mima.getText() + "%��" + "%��" + "%��" + "%��";
				message = KeyPassword.convertMD5(message);
				Login.out.writeUTF(message);
				int i = Integer.parseInt(Login.in.readUTF());
				if (i == 1)
					JOptionPane.showMessageDialog(new JTextArea(), "ע��ɹ����¼");
				else if (i == 2)
					JOptionPane.showMessageDialog(new JTextArea(), "�Ѵ�����ͬ�û���");
				else
					JOptionPane.showMessageDialog(new JTextArea(), "δ֪����,��ϵ����");
			} else if (Login.dengluSign == 1) {
				JOptionPane.showMessageDialog(new JTextArea(), "��¼״̬���޷�ע��");
			}
			Login.out.close();
			Login.in.close();
			Login.socket.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JTextArea(), "��������");
		}

	}

}
