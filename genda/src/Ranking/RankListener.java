package Ranking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Login.Login;

public class RankListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (Login.dengluSign == 1) {
      if (e.getActionCommand() == "�ܸ�������")
        new rankFrame(1).setTitle("�ܸ�������");
      else if (e.getActionCommand() == "�ո�������")
        new rankFrame(2).setTitle("�ո�������");
      else if (e.getActionCommand() == "����ƽ���ɼ�����")
        new rankFrame(3).setTitle("����ƽ���ɼ�����");
      else if (e.getActionCommand() == "ÿ����������") {
        new EveryDayRank();
      }
    } else {
      JOptionPane.showMessageDialog(new JTextArea(), "���ȵ�¼");
    }
  }
}