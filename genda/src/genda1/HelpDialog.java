package genda1;

import javax.swing.*;

import java.awt.event.*;
public class HelpDialog implements ActionListener{
	JTextArea a = new JTextArea();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(a,
				"F1���ı����ڵ�Ⱥ���ͳɼ�(���߶�սʱ��������ύ�ɼ�)\n"
				+"F2ѡ�񱾵ط���\n"
				+"F3�ش�\n"
				+"F4����\n"
				+"F5��Ⱥ\n"
				+"F7���߶�ս׼��\n"
				+"F9������\n"
				+"alt+L������\n"
				+"alt+P�����з�����һ��\n"
				+"alt+O�����ȡ������һ��\n"
				+"alt+S����������\n"
				+"alt+X���߸���׼��\n"
				+"alt+Z���ģʽ�½�������\n"
				+"\n��ɫ��ʾ\n"
				+"��ɫ������ɫ���򣬻�ɫȫ����,б���ѡ\n"
				+"\n���߸����裺\n"
				+"�����߸���֮��ѡ��һ��������룬�ȴ��Է����룬��ʾ˭Ϊ����\n"
				+"��������֮��˫��׼����ϵͳ��������ʾ�Է��Ƿ�׼��,\n"
				+"�ڶԷ����㶼�Ѿ�׼�������뵹����\n"
				+"��������֮��Ϳ��Կ�ʼ����\n"
				+"�������֮�󣬱���F1�ύ�ɼ��������޷��Ʒ�\n"
				+"ע�����߶�սʱ����������������Ķ���һ�£���˫���Ķ�����һ�µ�\n���������Ѷ�ս�ľ���\n"
				+"\n\t������Ʒ"
				,"ʹ�ð���",JOptionPane.PLAIN_MESSAGE);
		
	}
}