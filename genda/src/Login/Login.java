package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import genda1.*;

import javax.swing.*;

import Acticle.SendWenben;

public class Login extends JFrame implements ActionListener{
	static JButton confirm;
	JButton resert;
	JButton zhuce;
	JButton wangmi;
	JButton tuichu;
	public static String banben = "�汾1.494";
	public static int port = 1230;
	public static JTextField zhanghao;
	public static JPasswordField mima;
	public static DengluListener denglulistener;
	public static ZhuceListener zhucelistener;
	Window win;
	public static Socket socket ;
	public static DataOutputStream out;
	public static DataInputStream in;
	public static int dengluSign = 0;
	JPanel p = new JPanel();
	public Login(Window win){
		this.win = win;
		link();
		setTitle("��¼");

//		com.sun.awt.AWTUtilities.setWindowOpaque(this,true);
		setBounds(win.getX()+win.getWidth()/4,win.getY()+win.getHeight()/4,300,300);
		add(p);
		p.setLayout(null);
		zhanghao = new JTextField("�˺�");
		zhanghao.setBounds(20,20,190,30);
		p.add(zhanghao);
		
		mima = new JPasswordField("����");
		mima.setBounds(20,70,190,30);
		p.add(mima);
		
		confirm = new JButton("��¼");
		confirm.setBounds(20,110,90,30);
		p.add(confirm);
		
		resert = new JButton("�������");
		resert.setBounds(120,110,90,30);
		p.add(resert);
		
		zhuce = new JButton("ע��");
		zhuce.setBounds(20,150,90,30);
		p.add(zhuce);
		
		wangmi = new JButton("��������");
		wangmi.setBounds(120,150,90,30);
		p.add(wangmi);
		
		denglulistener = new DengluListener(this);
		confirm.addActionListener(denglulistener);
		
		zhucelistener = new ZhuceListener(this);
		zhuce.addActionListener(zhucelistener);
		
		QingkongListener qingkonglistener = new QingkongListener(mima,zhanghao);
		resert.addActionListener(qingkonglistener);
		
		ForgetListener forgetlistener = new ForgetListener();
		wangmi.addActionListener(forgetlistener);
		
		setVisible(true);
	}
	void link(){
		try {
//			socket.setSoTimeout(1000);
			socket = new Socket(Window.IP,port);
			socket.setSoTimeout(1000);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			out.writeUTF(Login.banben);
			String what = in.readUTF();
			socket.setSoTimeout(0);
			if(!what.equals("�汾��ȷ")){
				UIManager.put("OptionPane.yesButtonText", "�Զ�����");
				UIManager.put("OptionPane.noButtonText", "�ֶ�����");
				int n = JOptionPane.showConfirmDialog(null, what, "������ʾ", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					// ......
					Runtime.getRuntime().exec("����.exe");
					System.exit(0);
				} else if (n == JOptionPane.NO_OPTION) {
					// ......
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://39.96.83.89/new.zip");
					System.exit(0);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JTextArea(),"�����쳣");
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setBounds(win.getX()+win.getWidth()/2,win.getY()+win.getHeight()/2,300,300);
		this.setVisible(true);
	}
}
