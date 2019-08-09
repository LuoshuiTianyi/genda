package denglu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recordnum extends Thread{
	   /**
     * ����ʵ���û���ע��͵�¼
     */
    public static String url="jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";//�������ݿ��url��test�����Լ���һ�����ݿⰡ�����ǡ�
    public  static String user="root";//mysql��¼��
    public  static String pass="951753";//mysql��¼����
    public static Connection con;//
    public  static ServerSocket server;//������
    public static ArrayList<String> banben = new ArrayList<String>();
    
    public static String zxbb = "";
    public static int port = 1230;
//    public static HashMap<String,Socket> online = new HashMap<String,Socket>();
	public static void main(String args[]){
		Recordnum record = new Recordnum(); //���ܵ�¼���ı�������
		record.start();	//����
		LinkMyself linkmyself = new LinkMyself();
		linkmyself.start();	//���������ݿ�����
	}
	public void run() {
		try{
			server = new ServerSocket(port);
			System.out.print("SQL����˿ڿ���\r");
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			System.out.print("���ݿ����ӳɹ�\r");
			
			Dateinit dateinit = new Dateinit();
			dateinit.start();	//ÿ�ո���̬����
			InitAll.getaver();//���¼���һ��ƽ���ٶ�
			resertonline();//���õ�¼״̬
			System.out.print("�ٶ�ƽ���ɹ�\r");
			while(true){
				Socket socket = server.accept();//�����ȴ��ͻ��˵�����
				System.out.println("����");
//				socket.setSoTimeout(0);
				new ReadFirst(socket).start();
			}
        }
		catch(Exception e){System.out.print("���ݿ�ʧ��\r");e.printStackTrace();}  
	}
	void resertonline() throws SQLException{
		String sql="update client set online=?";
    	PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
	    ptmt.setInt(1,0);
	    ptmt.execute();
	}
	class ReadFirst extends Thread{	//��ֹ����
		DataInputStream in;
		DataOutputStream out;
		Socket socket;
		ReadFirst(Socket socket){
			this.socket = socket;
		}
		public void run(){
			try {
				socket.setSoTimeout(1000);
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				InetAddress ip = socket.getInetAddress();
				if(ip.toString().equals("/125.38.13.200")){
					System.out.println(ip.toString());
					socket.close();
					return;
				}
				String what = in.readUTF();
				socket.setSoTimeout(0);
				System.out.println(what);
			
				zxbb = getBanben();
				for(int i = 705;i<709;i++)
					banben.add("�汾1."+String.valueOf(i));
				if(banben.contains(what)){
					out.writeUTF("�汾��ȷ"+what+"���°汾"+zxbb);
					//�µ��߳�
					new RWThread148(socket).start();
				}
				else if(what.equals("����")){System.out.println("����");socket.close();socket = null;}
				else if(what.equals("����1")){
					System.out.println("����1");
					getAll(1);
					socketclose(socket);
				}
				else if(what.equals("����2")){
					System.out.println("����2");
					getAll(2);
					socketclose(socket);
				}
				else if(what.equals("����3")){
					System.out.println("����3");
					getAll(3);
					socketclose(socket);
				}
				else if(what.substring(0,2).equals("��Ӻ���")){
//					addfriend(what.substring(2));
				}
				else if(what.substring(0,2).equals("����")){
					System.out.println("����");
					sendwenben(what);
					socketclose(socket);
				}
				else if(what.equals("�����¼")){
					System.out.println("�����¼");
					getAllhistory();
					socketclose(socket);
				}
				else if(what.equals("��������")){
					System.out.println("��������");
					getRank();
					socketclose(socket);
				}
				else if(what.equals("ȫ���ı���¼")){
					System.out.println("��ȡȫ���ı���¼");
					ObjectOutputStream outputToClient = new ObjectOutputStream(out);
		            ObjectInputStream inputByClient = new ObjectInputStream(in);
					List<String> idlist = (List<String>) inputByClient.readObject();
					
					outputToClient.writeObject(getAllWenben(idlist));
					inputByClient.close();
					outputToClient.close();
					socketclose(socket);
				}
				else{
					out.writeUTF("�汾ǿ�Ƹ��£�\n��Ϊ�汾����δ�ܼ�ʱͬ�����⣬����ͬ���汾\n�Զ���ת���ػ��Ⱥ��974172771");
					socketclose(socket);
				}
			} catch (IOException | ClassNotFoundException e) {
				try {
					socket.close();
					socket = null;
				} catch (IOException e1) {}
			}
		}
		List<String> getAllWenben(List<String> idlist){
			List<String> wenbenlist = new ArrayList<String>();
			try{
				int id;
				String sql="select wenben from history where id=?";
				PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
				for(String i:idlist){
					id = Integer.valueOf(i);
					ptmt.setInt(1, id);
					ResultSet rs=ptmt.executeQuery();
				    if(rs.next()){
				    	String wen = rs.getString(1);
				    	wenbenlist.add(wen);
				    }
				}
			}catch(Exception e){System.out.println("�����ı�ʧ��");e.printStackTrace();}
			return wenbenlist;
		}
		String getBanben(){
			try{
				String sql="SELECT * FROM banben";
				PreparedStatement ptmt;
				ptmt = con.prepareStatement(sql);
				ResultSet rs=ptmt.executeQuery();
				if(rs.next()){
					return rs.getString(1);
				}
			}catch(Exception e){
				
			}
			return "";
		}
		void getRank(){
			try{
				String sql="SELECT * FROM saiwenchengji WHERE date=? ORDER BY sudu DESC";
//				Statement ptmt=Recordnum.con.createStatement();
//				ResultSet rs=ptmt.executeQuery(sql);
				PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
				ptmt.setDate(1,Dateinit.getdate());
				ResultSet rs=ptmt.executeQuery();
				System.out.println("��ȡ��������"+Dateinit.getdate().toString());
				while(rs.next()){
					out.writeUTF(String.valueOf(rs.getString(1)));
	        		out.writeUTF(String.valueOf(rs.getDouble(3)));
	        		out.writeUTF(String.valueOf(rs.getDouble(4)));
	        		out.writeUTF(String.valueOf(rs.getDouble(5)));
	        		out.writeUTF(String.valueOf(rs.getInt(6)));
	        		out.writeUTF(String.valueOf(rs.getInt(7)));
	        		out.writeUTF(String.valueOf(rs.getInt(8)));
	        		out.writeUTF(String.valueOf(rs.getInt(9)));
	        		out.writeUTF(String.valueOf(rs.getInt(10)));
	        		out.writeUTF(String.valueOf(rs.getDouble(11)));
	        		out.writeUTF(String.valueOf(rs.getDouble(12)));
	        		out.writeUTF(String.valueOf(rs.getDouble(13)));
	        		out.writeUTF(String.valueOf(rs.getDouble(14)));
				}
			}catch(Exception e){System.out.println("�����ı�ʧ��");e.printStackTrace();}
		}
		void sendwenben(String message){
			try{
				String sql="select wenben from history where id=?";
				PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
				int pos = message.indexOf('%',0)+1;
				int id = Integer.parseInt(message.substring(pos));
				System.out.println(id);
			    ptmt.setInt(1, id);
			    ResultSet rs=ptmt.executeQuery();
			    if(rs.next()){
			    	String wen = rs.getString(1);
			    	out.writeUTF(wen);
			    	System.out.println(wen);
			    }
			}catch(Exception e){System.out.println("�����ı�ʧ��");e.printStackTrace();}
		}

		void getAll(int i){
			String sql = "";
			if(i==1)
				sql = "SELECT * FROM client ORDER BY num DESC";
			else if(i==2)
				sql = "SELECT * FROM client ORDER BY datenum DESC";
			else if(i==3)
				sql = "SELECT * FROM client ORDER BY aver DESC";
			PreparedStatement ptmt;
			try {
				ptmt = con.prepareStatement(sql);
				ResultSet rs=ptmt.executeQuery();
				System.out.println("��ȡ����");
				while(rs.next()){					
					//����û����Ƿ�ʹ�ù�
		        	try {
		        		out.writeUTF(rs.getString(1));
		        		out.writeUTF(String.valueOf(rs.getInt(3)));
		        		out.writeUTF(String.valueOf(rs.getInt(4)));
		        		out.writeUTF(String.valueOf(rs.getInt(5)));
		        		out.writeUTF(String.valueOf(rs.getInt(6)));
		        		out.writeUTF(String.format("%.2f",rs.getDouble(8)));
		        		out.writeUTF(String.valueOf(rs.getInt(7)));
		        		Date last = rs.getDate(11);
		        		if(last==null)
		        			out.writeUTF("����");
		        		else
		        			out.writeUTF(last.toString());
		        		out.writeUTF(rs.getDate(10).toString());
					} catch (IOException e) {System.out.print("��������ʧ��r");}
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void getAllhistory(){
			try{
				String name;
				String sql = "SELECT * FROM history WHERE name=? ORDER BY id DESC";
				PreparedStatement ptmt;
				try {
					name = in.readUTF();
					ptmt = con.prepareStatement(sql);
					ptmt.setString(1, name);
					ResultSet rs=ptmt.executeQuery();
					while(rs.next()){			
						//����û����Ƿ�ʹ�ù�
			        	try {
			        		out.writeUTF(String.valueOf(rs.getDate(2)));
			        		out.writeUTF(String.valueOf(rs.getDouble(3)));
			        		out.writeUTF(String.valueOf(rs.getDouble(4)));
			        		out.writeUTF(String.valueOf(rs.getDouble(5)));
			        		out.writeUTF(String.valueOf(rs.getInt(6)));
			        		out.writeUTF(String.valueOf(rs.getInt(7)));
			        		out.writeUTF(String.valueOf(rs.getInt(8)));
			        		out.writeUTF(String.valueOf(rs.getInt(9)));
			        		out.writeUTF(String.valueOf(rs.getInt(10)));
			        		out.writeUTF(String.valueOf(rs.getDouble(11)));
			        		out.writeUTF(String.valueOf(rs.getDouble(12)));
			        		out.writeUTF(String.valueOf(rs.getDouble(13)));
			        		out.writeUTF(String.valueOf(rs.getDouble(14)));
			        		out.writeUTF(String.valueOf(rs.getInt(16)));
			        		out.writeUTF(String.valueOf(rs.getInt(18)));
						} catch (IOException e) {System.out.print("��������ʧ��r");}
			        }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		void socketclose(Socket socket){
			try {
				socket.close();
				socket = null;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.print("��ʱ�޷��ر�socket\r");
			}
			System.out.print("���Ӻ�ʱ\r");
		}
//		void addfriend(String username){
//			try{
//				String addfriendname = in.readUTF();
//				String sql = "select * from client where username=?";
//				PreparedStatement ptmt;
//				ptmt = Recordnum.con.prepareStatement(sql);
//				ptmt.setString(1, addfriendname);
//				ResultSet rs=ptmt.executeQuery();
//				if(!rs.next()){out.writeUTF("������");return;}
//				if(!Recordnum.online.containsKey(addfriendname)){out.writeUTF("������");return;}
//				else{
//					Socket addsocket = Recordnum.online.get(addfriendname);
//					DataInputStream innotice = new DataInputStream(addsocket.getInputStream());
//					DataOutputStream outnotice = out = new DataOutputStream(addsocket.getOutputStream());
//					outnotice.writeUTF("��Ӻ���"+username+"Ҫ�����Ϊ����");
//				}
//				sql="select * from client where username=?";
//			}
//			catch(Exception e){e.printStackTrace();}
//		}
	}
}
