package EmailCheck;

import javax.swing.*;
public class EmailCheckFrame extends JFrame {
  JButton con, confire, mimabutton;
  public static JTextField email, yanzhengma, xgmima, zhanghao;

  public EmailCheckFrame() {
    setLayout(null);
    setBounds(10, 10, 250, 250);
    init();
    setVisible(true);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //���ùرհ�ť
  }
  void init() {
    con = new JButton("��ȡ��֤��");
    confire = new JButton("ȷ��");
    email = new JTextField("������");
    yanzhengma = new JTextField("��֤��");

    zhanghao = new JTextField("�˺�");
    xgmima = new JTextField("�޸�������");
    mimabutton = new JButton("�޸�");

    email.setBounds(20, 20, 120, 30);
    con.setBounds(150, 20, 80, 30);
    yanzhengma.setBounds(20, 70, 120, 30);
    confire.setBounds(150, 70, 80, 30);

    zhanghao.setBounds(20, 120, 200, 30);
    xgmima.setBounds(20, 170, 120, 30);
    mimabutton.setBounds(150, 170, 80, 30);

    SendEmailListener sendemaillistener = new SendEmailListener();
    con.addActionListener(sendemaillistener);
    confire.addActionListener(sendemaillistener);
    mimabutton.addActionListener(sendemaillistener);
    add(con);
    add(confire);
    add(email);
    add(yanzhengma);
    add(zhanghao);
    add(xgmima);
    add(mimabutton);
  }
}
