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
	int i=0;
//	public static void main(String args[]){
//		new rankFrame();
//	}
	public rankFrame(int i){
		this.i = i;
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرհ�ť
		setBounds(10,10,700,500);
	}
	void init(){
		setLayout(null);
		Object name[]={"","����","������","��ȷ����","��������","���ո���","���ĵȼ�","�������","����¼","ע������"},a[][] = null;
		tableM = new DefaultTableModel(a,name){
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		table = new JTable(tableM);
		tableN = new JScrollPane(table);
		table.setEnabled(false);
		tableN.setBounds(0,0,650,450);
		add(tableN);
		request();
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		this.setResizable(false);
	}
	void request(){
		try {
			Socket socket = new Socket(Window.IP,Login.Login.port);
			socket.setSoTimeout(500);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			if(i==1)
				out.writeUTF("����1");
			else if(i==2)
				out.writeUTF("����2");
			else if(i==3);
				out.writeUTF("����3");
			int n =0;
			while(true){
				Vector vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<5;i++){
					vRow1.add(in.readUTF());
				}
				Double aversudu = Double.parseDouble(in.readUTF());
				String duanwei = "";
				if(i!=3){
					switch ((int)(aversudu/10))
					{
					    case 0:
					    case 1:
					    case 2:duanwei = "һ��";break;
					    case 3:duanwei = "����";break;
					    case 4:duanwei = "����";break;
					    case 5:duanwei = "�ļ�";break;
					    case 6:
					    case 7:duanwei = "�弶";break;
					    case 8:duanwei = "����";break;
					    case 9:duanwei = "�߼�";break;
					    case 10:duanwei = "�˼�";break;
					    case 11:duanwei = "�ż�";break;
					    case 12:duanwei = "һ��";break;
					    case 13:duanwei = "����";break;
					    case 14:duanwei = "����";break;
					    case 15:duanwei = "�Ķ�";break;
					    case 16:duanwei = "���";break;
					    case 17:duanwei = "����";break;
					    case 18:duanwei = "�߶�";break;
					    case 19:duanwei = "�˶�";break;
					    case 20:duanwei = "�Ŷ�";break;
					    case 21:duanwei = "һ��";break;
					    case 22:duanwei = "����";break;
					    case 23:duanwei = "����";break;
					    case 24:duanwei = "����";break;
					    case 25:duanwei = "����";break;
					    case 26:duanwei = "����";break;
					    case 27:duanwei = "����";break;
					    case 28:duanwei = "����";break;
					    case 29:duanwei = "����";break;
					    case 30:duanwei = "һ��";break;
					    case 31:duanwei = "����";break;
					    case 32:duanwei = "����";break;
					    case 33:duanwei = "����";break;
					    case 34:duanwei = "����";break;
					    case 35:duanwei = "����";break;
					    case 36:duanwei = "����";break;
					    case 37:duanwei = "����";break;
					    case 38:duanwei = "����";break;
					    case 39:duanwei = "ʮ��";break;
					    case 40:
					    case 41:
					    case 42:
					    case 43:
					    case 44:duanwei = "����";break;
					    case 45:
					    case 46:
					    case 47:
					    case 48:
					    case 49:duanwei = "����";break;
					    default:duanwei = "����";break;
					}
					vRow1.add(duanwei);
				}
				else
					vRow1.add(aversudu);
				if(in.readUTF().equals("1"))
					vRow1.add("����");
				else
					vRow1.add("������");
				vRow1.add(in.readUTF());
				vRow1.add(in.readUTF());
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
