package genda1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import keep.*;
public class SystemListener implements ActionListener,MouseListener,MouseMotionListener{
	Window win;
	int x=0,y=0,width=0,height=0,locationPointx,locationPointy;
	int MaxSign = 0;
	Point pressedPoint ;
	SystemListener(Window win){
		this.win = win;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="��"){
			if(win.one.isVisible()){
				JOptionPane.showMessageDialog(new JTextArea(),"���ȹر����߶�ս");return;
			}
			try {
				readWrite.keep(win);//��������
			} catch (IOException e1) {System.out.println("����ʧ��");}
			System.exit(0);
		}
		else if(e.getActionCommand()=="���"){
			if(MaxSign == 0){
				x = win.getX();
				y = win.getY();
				width = win.getWidth();
				height = win.getHeight();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //�õ���Ļ�ֱ���
				win.setLocation(0, 0); 
				win.setSize(screenSize.width,screenSize.height); 
				MaxSign = 1;
			}
			else{
				win.setLocation(x, y); 
				win.setSize(width,height);
				x=0;y=0;width=0;height=0;
				MaxSign = 0;
				
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		win.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR)); //�ı䴰�ڴ�Сָ�� 
	}

	@Override
	public void mouseExited(MouseEvent e) {
		win.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	//�ָ�Ĭ��ָ��
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressedPoint = e.getPoint(); //��¼�������
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = e.getPoint();// ��ȡ��ǰ����
		locationPointx = win.getWidth();
		locationPointy = win.getHeight();
		int i = locationPointx + point.x - pressedPoint.x;// �����ƶ����������
		int j = locationPointy + point.y - pressedPoint.y;
		win.setSize(i, j);// �ı䴰���С
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

}
