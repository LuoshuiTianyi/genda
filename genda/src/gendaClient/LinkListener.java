package gendaClient;
import genda1.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JTextArea;

public class LinkListener implements ActionListener {
  battleClient client;
  JTextArea communion;
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    try {
      if (Window.Linksign) {
        if (Window.state.equals("��ս")) {
          client.StratClient();
          client.readThread.start();
          DataOutputStream out = new DataOutputStream(client.socket.getOutputStream());
          out.writeUTF(Window.state);
          communion.append("�����ӵ�" + Window.RoomNum + "�ŷ�\n�ȴ��Է�����\n");
          Window.Linksign = battleClient.socket.isClosed();
          //					ReadyListener.ReadyDuan = 1;
        } else if (Window.state.equals("��ս")) {
        }
      } else {
        communion.append("������" + Window.state + ",�����ظ�����\n");
        Window.Linksign = client.socket.isClosed();
      }

    } catch (Exception ex) {
      communion.setText("����ѡ�񷿼䣬�ٽ�������\n");
    }
  }
  public void setClient(battleClient client) {
    this.client = client;
    //		this.client.StratClient();
  }
  public void setCommunion(JTextArea t) {
    communion = t;
  }
}