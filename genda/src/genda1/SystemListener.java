package genda1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import keep.*;
public class SystemListener implements ActionListener,MouseListener,MouseMotionListener{
	static Window win;
	static int x=0;
	static int y=0;
	static int width=0;
	static int height=0;
	int locationPointx;
	int locationPointy;
	static int MaxSign = 0;
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
			if(MaxSign==1){
				JOptionPane.showMessageDialog(new JTextArea(),"���ȹر�ȫ��ģʽ");return;
			}
			UIManager.put("OptionPane.yesButtonText", "�ر�");
			UIManager.put("OptionPane.noButtonText", "����");
			int n = JOptionPane.showConfirmDialog(null, "Ҫ�رո�����������ѡ�����ص�����", "�ر���ʾ", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					readWrite.keep(win);//��������
				} catch (IOException e1) {System.out.println("����ʧ��");}
				System.exit(0);
			}
			else{
				win.setVisible(false);
			}
		}
		else if(e.getActionCommand()=="���"){
			if(MaxSign == 0){
//				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //�õ���Ļ�ֱ���
//				GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
//				Rectangle screenSize=ge.getMaximumWindowBounds(); 
//				int w=screenSize.width; 
//				int h=screenSize.height;
//				win.setLocation(0, 0); 
//				win.setSize(screenSize.width,screenSize.height); 		
				max();
			}
			else{
				min();
			}
		}
		else if(e.getActionCommand().equals("��С��")){
			System.out.println("��С��");
			win.setExtendedState(JFrame.ICONIFIED); 
		}
	}
	public static void max(){
		x = win.getX();
		y = win.getY();
		width = win.getWidth();
		height = win.getHeight();
		Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();   
		Rectangle   bounds   =   new   Rectangle(screenSize);   
		Insets   insets   =   Toolkit.getDefaultToolkit().getScreenInsets(win.getGraphicsConfiguration());   
		bounds.x   +=   insets.left;   
		bounds.y   +=   insets.top;   
		bounds.width   -=   insets.left   +   insets.right;   
		bounds.height   -=   insets.top   +   insets.bottom;   
		win.setBounds(bounds);
		MaxSign = 1;
	}
	public static void min(){
		win.setLocation(x, y); 
		win.setSize(width,height);
		x=0;y=0;width=0;height=0;
		MaxSign = 0;
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
		if(MaxSign==0){
			Point point = e.getPoint();// ��ȡ��ǰ����
			locationPointx = win.getWidth();
			locationPointy = win.getHeight();
			int i = locationPointx + point.x - pressedPoint.x;// �����ƶ����������
			int j = locationPointy + point.y - pressedPoint.y;
			win.setSize(i, j);// �ı䴰���С
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

}
