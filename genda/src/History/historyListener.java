package History;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class historyListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getActionCommand().equals("�����¼"))
      if (Login.Login.dengluSign == 1)
        new historyFrame().setTitle("�ܸ����¼");
      else
        JOptionPane.showMessageDialog(new JTextArea(), "���ȵ�¼");
    else
      new historythis().setTitle("���θ����¼");
  }
}
