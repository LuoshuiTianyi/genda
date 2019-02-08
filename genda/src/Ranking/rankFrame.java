package Ranking;

import genda1.Window;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class rankFrame extends JFrame {
	JTable table;
	DefaultTableModel tableM;
	JScrollPane tableN;
//	public static void main(String args[]){
//		new rankFrame();
//	}
	public rankFrame(){
		init();
		setVisible(true);
		setTitle("�ܸ�������");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرհ�ť
		setBounds(10,10,500,500);
	}
	void init(){
		setLayout(null);
		Object name[]={"","����","������"},a[][] = null;
		tableM = new DefaultTableModel(a,name);
		table = new JTable(tableM);
		tableN = new JScrollPane(table);
		table.setEnabled(false);
		tableN.setBounds(0,0,450,450);
		add(tableN);
		request();
		this.setResizable(false);
	}
	void request(){
		try {
			Socket socket = new Socket(Window.IP,1232);
			socket.setSoTimeout(500);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			out.writeUTF("����");
			int n =0;
			while(true){
				Vector vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<2;i++){
					vRow1.add(in.readUTF());
				}
				tableM.addRow(vRow1);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("��������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("��������");
		}
	}
}
