package Check;

import javax.swing.*;

public class CheckFrame extends JFrame {
  JTextArea checktext;
  JScrollPane checktextjs;
  JButton confirelook, confiregen, confireying;
  CheckFrame() {
    init();
    setBounds(10, 10, 330, 200);
    setVisible(true);
    setResizable(false);
    setTitle("У�鿴��ɼ�");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //���ùرհ�ť
  }
  void init() {
    setLayout(null);
    checktext = new JTextArea("");
    confiregen = new JButton("����У��");
    confirelook = new JButton("����У��");
    confireying = new JButton("Ӣ��У��");

    checktextjs = new JScrollPane(checktext);
    checktext.setLineWrap(true); //���Ŀ��Զ�����
    checktextjs.setBounds(10, 10, 300, 100);
    confiregen.setBounds(10, 120, 70, 30);
    confirelook.setBounds(90, 120, 70, 30);
    confireying.setBounds(170, 120, 70, 30);

    CheckListener checklistener = new CheckListener(checktext);

    confiregen.addActionListener(checklistener);
    confirelook.addActionListener(checklistener);
    confireying.addActionListener(checklistener);
    add(checktextjs);
    add(confiregen);
    add(confirelook);
    add(confireying);
  }
}
