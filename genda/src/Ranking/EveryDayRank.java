package Ranking;

import genda1.Window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Login.Login;

public class EveryDayRank extends JFrame {
	public static JTable table;
	public static DefaultTableModel tableM;
	JScrollPane tableN;
	static Vector vRow1;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	public EveryDayRank(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(10,10,855,screenSize.height*2/4);
		init();
		setVisible(true);
		setTitle("ÿ����������");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرհ�ť
//		setResizable(false);
	}
	void init(){
		Object name[]={"","�û���","�ٶ�","����","�볤","����","�ظ�","�˸�","����","ѡ��","��׼","����","�����","ʱ��(��)"},a[][] = null;
		tableM = new DefaultTableModel(a,name) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		table = new JTable(tableM);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		tableN = new JScrollPane(table);
		
		tableN.setBounds(0,0,845,this.getHeight()-40);
		
		add(tableN);
		request();
	}
	void request(){
		int n = 0;
		try{
			socket = new Socket(Window.IP,Login.port);
			socket.setSoTimeout(100);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("��������1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("��������2");
		}
		try {
			out.writeUTF("��������");
			while(true){
				vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<13;i++){
					vRow1.add(in.readUTF());
				}
				tableM.addRow(vRow1);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("��������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("��������");
		}
		if(n==0)JOptionPane.showMessageDialog(new JTextArea(),"�������޳ɼ�");
	}
}
