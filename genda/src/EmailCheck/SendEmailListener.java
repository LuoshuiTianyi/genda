package EmailCheck;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import keep.KeyPassword;

import Login.Login;

public class SendEmailListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getActionCommand().equals("��ȡ��֤��")) {
      try {
        String message =
            "��֤����" + EmailCheckFrame.zhanghao.getText() + "%" + EmailCheckFrame.email.getText();
        message = KeyPassword.convertMD5(message);
        Login.out.writeUTF(message);
        if (Login.in.readUTF().equals("��֤�ɹ�")) {
          if (Window.Email.equals(EmailCheckFrame.email.getText())) {
            emailchecksend d = new emailchecksend();
            d.start();
          }
          JOptionPane.showMessageDialog(null, "�ѷ���");
        } else {
          JOptionPane.showMessageDialog(null, "�����䲻���˺Ű�����");
        }

      } catch (Exception ex) {
        // TODO Auto-generated catch block
        ex.printStackTrace();
      }
    } else if (e.getActionCommand().equals("ȷ��")) {
      if (emailchecksend.str.equals(EmailCheckFrame.yanzhengma.getText())) {
        JOptionPane.showMessageDialog(null, "��֤����ȷ");
      }
    } else if (e.getActionCommand().equals("�޸�")) {
      //			if(EmailCheckFrame.zhanghao)
      if (emailchecksend.str.equals(EmailCheckFrame.yanzhengma.getText())) {
        try {
          Login.in = new DataInputStream(Login.socket.getInputStream());
          String message = "�޸�����" + EmailCheckFrame.zhanghao.getText() + "%"
              + EmailCheckFrame.xgmima.getText();
          message = KeyPassword.convertMD5(message);
          Login.out.writeUTF(message);
          if (Login.in.readUTF().equals("�޸ĳɹ�"))
            JOptionPane.showMessageDialog(null, "�޸�����ɹ�");
          else
            JOptionPane.showMessageDialog(null, "�޸�����ʧ��");
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }
  }
}