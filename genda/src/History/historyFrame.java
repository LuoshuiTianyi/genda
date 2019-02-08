package History;

import genda1.Window;
import Login.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class historyFrame extends JFrame implements ActionListener{
	public static JTable table;
	public static DefaultTableModel tableM;
	JScrollPane tableN;
	JButton lookwenben;
	JButton shaixuansaiwen,showall;
	JTextField lookcow;
	public static JTextField datef,suduf,dayf;
	JTextField dangqianye;
	JButton shaixuandate,shaixuansudu,next,before,go,gofirst,golast;
	static JButton yemashow;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	Vector vRow1;
	static int fenye = 50;
	static int dangqian = 0;
	static int yenum = 0;
	public static List<String> id = new ArrayList<String>();
	public static List<Vector> allhistory = new ArrayList<Vector>();
	public historyFrame(){
		init();
		setVisible(true);
		setTitle("�����¼");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرհ�ť
		setBounds(10,10,850,850);
	}
	void init(){
		setLayout(null);
		Object name[]={"","����","�ٶ�","����","�볤","����","�ظ�","�˸�","����","ѡ��","��׼","�����","ʱ��(��)","����"},a[][] = null;
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
		
		before = new JButton("��һҳ");
		next = new JButton("��һҳ");
		dangqianye = new JTextField("��ת");
		go = new JButton("��ת");
		gofirst = new JButton("������ҳ");
		golast = new JButton("����βҳ");
		yemashow = new JButton("");
		before.setBounds(10, 700, 120, 30);
		yemashow.setBounds(140,700,60,30);
		next.setBounds(210, 700, 120, 30);
		dangqianye.setBounds(340, 700, 60, 30);
		go.setBounds(410, 700, 60, 30);
		gofirst.setBounds(480,700,100,30);
		golast.setBounds(590,700,100,30);
		
		lookcow = new JTextField("Ҫ�鿴���ݵ�����");
		lookcow.setBounds(10, 735, 120, 30);
		lookwenben = new JButton("�鿴��������");
		lookwenben.setBounds(140,735,120,30);
		datef = new JTextField("��������");
		suduf = new JTextField("�ٶȴ���");
		dayf = new JTextField("");
		shaixuandate = new JButton("ɸѡ����");
		shaixuansudu = new JButton("ɸѡ�ٶ�");
		shaixuansaiwen = new JButton("ɸѡ���ĳɼ�");
		showall = new JButton("ȫ");
		datef.setBounds(270, 735, 60, 30);
		suduf.setBounds(450, 735, 60, 30);
		
		shaixuandate.setBounds(340,735,90,30);
		shaixuansudu.setBounds(520, 735,90, 30);
		shaixuansaiwen.setBounds(620,735,120,30);
		showall.setBounds(750,735,40,30);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		
		historywenbenListener historywenbenlistener = new historywenbenListener(lookcow);
		lookwenben.addActionListener(historywenbenlistener);
		historyselect historysl = new historyselect();
		historyfanye historyfanye = new historyfanye();
		
		shaixuandate.addActionListener(historysl);
		shaixuansudu.addActionListener(historysl);
		shaixuansaiwen.addActionListener(historysl);
		next.addActionListener(historyfanye);
		before.addActionListener(historyfanye);
		showall.addActionListener(this);
		add(tableN);
		add(lookwenben);
		add(lookcow);
		add(datef);
		add(suduf);
		add(dayf);
		add(shaixuandate);
		add(shaixuansudu);
		add(shaixuansaiwen);
		add(next);
		add(before);
		add(go);
		add(dangqianye);
		add(golast);
		add(gofirst);
		add(yemashow);
		add(showall);
		request();
		this.setResizable(false);
	}
	void request(){
		try{
			socket = new Socket(Window.IP,1232);
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
			out.writeUTF("�����¼");
			out.writeUTF(Login.zhanghao.getText());
			id.clear();
			allhistory.clear();
			clear();
			int n =0;
			while(true){
				vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<13;i++){
					vRow1.add(in.readUTF());
				}
				id.add(in.readUTF());
				allhistory.add(vRow1);
				yenum++;
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
		yenum = (yenum/fenye);
		yemashow.setText(String.valueOf(dangqian+1)+"/"+String.valueOf(yenum+1));
	}
	public static void showtable(){
		for(int i=fenye*dangqian;i<fenye*(dangqian+1);i++)
			try{
				tableM.addRow(allhistory.get(i));
			}catch(Exception e){System.out.println("��ʾ������ʷ����");break;}
	}
	public static void clear(){
		while (historyFrame.table.getRowCount()>0)
		     historyFrame.tableM.removeRow(0);  
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		request();
	}
}
