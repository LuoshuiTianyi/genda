package FriendSys;

import genda1.Window;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import History.historyFrame;
import Login.Login;

public class FriendSys extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FriendSys(){
		init();
		setVisible(true);
		setTitle("����ϵͳ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرհ�ť
		setBounds(10,10,850,850);
	}
	public static JTable table;
	public static DefaultTableModel tableM;
	JScrollPane tableN;
	static Vector vRow1;
	int n;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	JTextField addfriendname;
	JButton addfriend;
	public static List<String> id = new ArrayList<String>();
	public static List<Vector> all = new ArrayList<Vector>();
	void init(){
		setLayout(null);
		Object name[]={"","����","������","��ȷ����","��������","���ո���","�������"},a[][] = null;
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
		tableN.setBounds(0,0,800,700);
		addfriend = new JButton("�������");
		addfriendname = new JTextField("�������");
		addfriend.setBounds(10, 710, 120,30 );
		addfriendname.setBounds(140, 710, 120, 30);
		
		addFriends addf = new addFriends(addfriendname);
		
		add(tableN);
		add(addfriend);
		add(addfriendname);
		addfriend.addActionListener(addf);
		
		request();
	}
	String temp;
	void request(){
		try{
			socket = new Socket(Window.IP,Login.port);
			socket.setSoTimeout(100);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("�����¼�������1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�����¼�������2");
		}
		try {
			System.out.println("����ϵͳ");
			out.writeUTF("����");
			while(true){
				vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<5;i++){
					temp = in.readUTF();
					if(temp.equals("%����==0")){JOptionPane.showMessageDialog(new JTextArea(),"�����б��޺���");return;}
					vRow1.add(temp);
				}
				if(in.readUTF().equals("1"))
					vRow1.add("����");
				else
					vRow1.add("������");
//				id.add(in.readUTF());
				all.add(vRow1);
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
		showtable();
	}
	public static void showtable(){
		clear();
		for(int i=0;i<all.size();i++)
			try{
				tableM.addRow(all.get(i));
			}catch(Exception e){System.out.println("��ʾ����");break;}
	}
	public static void clear(){
		while (historyFrame.table.getRowCount()>0)
		     historyFrame.tableM.removeRow(0);  
	}
}
