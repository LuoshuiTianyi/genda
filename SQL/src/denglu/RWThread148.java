package denglu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.sql.Date;

import Datesaiwen.saiwenSys;

public class RWThread148 extends Thread{
	public Socket socket;
	DataInputStream in = null;
	DataOutputStream out = null;
	int num[] = {0,0,0,0,0,0,0};
	int hisnum[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int dengluSign = 0;
	public int recordnum = 0;
	public int recordNumlast = 0;
	public int recordrightnum = 0;
	public int recordmisnum = 0;
	public int recorddatenum = 0;
	public int recorddatenumlast = 0;
	boolean saiwenSign = false;
	String username;
//	TimingIn timein;
	public RWThread148(Socket socket){
		this.socket = socket;
	}
	public void run(){
		String message;
		try{
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
//			timein = new TimingIn(this);
//			timein.start();
			System.out.print("�û�����\r");
			while(true){
				message = in.readUTF();//���տͻ��˷�������Ϣ
				message = KeyPassword.convertMD5(message);
				if(message.equals("�Ͽ�")){
					out.close();
					socket.close();
					System.out.println("�˳���¼");
					socket.sendUrgentData(0);
				}
				if(message.equals("����")){
					continue;
				}
				else if(message.equals("����")){
//					getfriend();
					continue;
				}
				else if(message.substring(0,2).equals("��ʷ")){
//					System.out.println(message);
					addhistory(message);
					continue;
				}
				else if(message.substring(0,2).equals("�ɼ�")){
					System.out.println(message);
					if(saiwenSign)addchengji(message,0);
					saiwenSign = false;
					continue;
				}
				else if(message.equals("��ȡ��������")){
					System.out.println(message);
					int n = saiwenSys.everydaysaiwen(out,username);
					if(n==1)saiwenSign=true;
					continue;
				}
				else if(message.equals("�ύ���ĳɼ�")){
					System.out.println(message);
					saiwenSys.getachievement(in);
					saiwenSign = false;
					continue;
				}
				else if(message.substring(0,6).endsWith("�ۿ۷��ͳɼ�")){
					SendQQMessage.sendmessage(message.substring(6));
					continue;
				}
				else if(message.substring(0,4).equals("������")){
					addbangemail(message.substring(4));
					continue;
				}
				else if(message.substring(0,4).equals("��֤����")){
					checkemail(message.substring(4));
					continue;
				}
				else if(message.substring(0,4).equals("�޸�����")){
					xgmima(message.substring(4));
					continue;
				}
				else{
					int pos = 0;
					for(int i=0;i<7;i++){				//�����յ���Ϣ,�ֿ黯
						pos =  message.indexOf('%',pos)+1;
						if(pos!=-1)
							num[i] = pos;
						else break;
					}
					if(num[0]==-1)continue;
					int caozuohao = Integer.parseInt(message.substring(num[0],num[1]-1));
					username = message.substring(num[1],num[2]-1);
					String password = message.substring(num[2],num[3]-1);
					String record = message.substring(num[3],num[4]-1);
					String rightnum = message.substring(num[4],num[5]-1);
					String misnum = message.substring(num[5],num[6]-1);
					String datenum = message.substring(num[6]);
					if(!record.equals("��")&&!record.equals("-999"))
						recordnum = Integer.parseInt(record);
					if(!rightnum.equals("��"))
						recordrightnum =Integer.parseInt(rightnum);
					if(!misnum.equals("��"))
						recordmisnum =Integer.parseInt(misnum);
					if(!datenum.equals("��"))
						recorddatenum =Integer.parseInt(datenum);
					if(dengluSign==0){
						System.out.print(username+"�������\r");
						caozuo(caozuohao,username,password,recordnum);
					}
					else{
						recordNumlast = recordnum;//�������һ������
						recorddatenumlast = recorddatenum ;
					}
				}
			}
		}
		catch(Exception e){ 		//�˳�ʱ�������ݿ���������޸�
			System.out.print(username+"�û��˳�\r");
			if(dengluSign==1){
				try {
					System.out.println("�������:"+recordNumlast+":"+recordrightnum+":"+recordmisnum+":"+recorddatenumlast);
//					if(recordNumlast<0)
//						recordNumlast = 0;
					if(recordrightnum>0&&recordNumlast>0)
						changeRecord(recordNumlast,recordrightnum,recordmisnum,recorddatenumlast);
				
					changeonline(0);
					if(saiwenSign)
						addchengji("��",1);
					out.close();
					socket.close();
					socket = null;
				}
				catch (SQLException e1) {
					System.out.print(username+"��������ʧ��\r");}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	public void checkemail(String mess) throws SQLException, IOException{
		String sql="";
		PreparedStatement ptmt;
		sql = "SELECT * FROM client WHERE username=? and email=?";
	    ptmt = Recordnum.con.prepareStatement(sql);
	    String c[] = mess.split("%");
		System.out.println(c[0]+" "+c[1]);
	    ptmt.setString(1, c[0]);
	    ptmt.setString(2, c[1]);
		ResultSet rs=ptmt.executeQuery();
		if(rs.next())out.writeUTF("��֤�ɹ�");
		else out.writeUTF("��֤ʧ��");
	}
	public void xgmima(String mess) throws IOException{
		String sql="update client set password=? where username=?";
		PreparedStatement ptmt;
		try {
			ptmt = Recordnum.con.prepareStatement(sql);
			String c[] = mess.split("%");
			System.out.println(c[0]+" "+c[1]);
			ptmt.setString(1, c[1]);
			ptmt.setString(2, c[0]);
			ptmt.execute();
			out.writeUTF("�޸ĳɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("������ɹ�");
	}
	public void addbangemail(String email){
		String sql="update client set email=? where username=?";
		PreparedStatement ptmt;
		try {
			ptmt = Recordnum.con.prepareStatement(sql);
			String c[] = email.split("%");
			System.out.println(c[0]+" "+c[1]);
			ptmt.setString(1, c[0]);
			ptmt.setString(2, c[1]);
			ptmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������ɹ�");
	}
	public void addchengji(String message,int n){
		String sql="insert into saiwenchengji values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default)";
		Date date;
		int y,m,d;
		try{
			Calendar cal=Calendar.getInstance(); 
			y=cal.get(Calendar.YEAR); 
			m=cal.get(Calendar.MONTH); 
			d=cal.get(Calendar.DATE);
			date = new Date(y-1900,m,d);
		}catch(Exception e){System.out.println("��ȡʱ�����");return;}
		PreparedStatement ptmt;
		if(n==0){
			int pos = 0;
			for(int i=0;i<14;i++){				//�����յ���Ϣ,�ֿ黯
				pos =  message.indexOf('%',pos)+1;
				if(pos!=-1)
					hisnum[i] = pos;
				else break;
			}
			if(hisnum[0]==-1)return;
			String name = message.substring(hisnum[0],hisnum[1]-1);
			double sudu = Double.parseDouble(message.substring(hisnum[1],hisnum[2]-1));
			double key = Double.parseDouble(message.substring(hisnum[2],hisnum[3]-1));
			double keylength = Double.parseDouble(message.substring(hisnum[3],hisnum[4]-1));
			int number = Integer.parseInt(message.substring(hisnum[4], hisnum[5]-1));
			int deletetext = Integer.parseInt(message.substring(hisnum[5],hisnum[6]-1));
			int delete = Integer.parseInt(message.substring(hisnum[6],hisnum[7]-1));
			int mistake = Integer.parseInt(message.substring(hisnum[7],hisnum[8]-1));
			int repeat = Integer.parseInt(message.substring(hisnum[8],hisnum[9]-1));
			double Keyaccuracy = Double.parseDouble(message.substring(hisnum[9],hisnum[10]-1));
			double Keymethod = Double.parseDouble(message.substring(hisnum[10],hisnum[11]-1));
			double dacilv = Double.parseDouble(message.substring(hisnum[11],hisnum[12]-1));
			double time = Double.parseDouble(message.substring(hisnum[12],hisnum[13]-1));
			double nandu = Double.parseDouble(message.substring(hisnum[13]));
			try {
				ptmt=Recordnum.con.prepareStatement(sql);
			    ptmt.setString(1, name);
			    ptmt.setDate(2, date);
			    ptmt.setDouble(3, sudu);
			    ptmt.setDouble(4, key);
			    ptmt.setDouble(5, keylength);
			    ptmt.setInt(6, number);
			    ptmt.setInt(7, deletetext);
			    ptmt.setInt(8, delete);
			    ptmt.setInt(9, mistake);
			    ptmt.setInt(10, repeat);
			    ptmt.setDouble(11, Keyaccuracy);
			    ptmt.setDouble(12, Keymethod);
			    ptmt.setDouble(13, dacilv);
			    ptmt.setDouble(14, time);
			    ptmt.setDouble(15, nandu);
			    ptmt.execute();
			    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
		    try {
		    	ptmt=Recordnum.con.prepareStatement(sql);
			    ptmt.setString(1, username);
			    ptmt.setDate(2, date);
			    ptmt.setDouble(3, 0);
			    ptmt.setDouble(4, 0);
			    ptmt.setDouble(5, 0);
			    ptmt.setInt(6, 0);
			    ptmt.setInt(7, 0);
			    ptmt.setInt(8, 0);
			    ptmt.setInt(9, 0);
			    ptmt.setInt(10, 0);
			    ptmt.setDouble(11, 0);
			    ptmt.setDouble(12, 0);
			    ptmt.setDouble(13, 0);
				ptmt.setDouble(14, 0);
				ptmt.setDouble(15, 0);
				ptmt.execute();
				System.out.println("��������");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void addhistory(String message){
		String sql="insert into history values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default)";
		Date date;
		int y,m,d;
		try{
			Calendar cal=Calendar.getInstance(); 
			y=cal.get(Calendar.YEAR); 
			m=cal.get(Calendar.MONTH); 
			d=cal.get(Calendar.DATE);
			date = new Date(y-1900,m,d);
		}catch(Exception e){System.out.println("��ȡʱ�����");return;}
		
		int pos = 0;
		for(int i=0;i<16;i++){				//�����յ���Ϣ,�ֿ黯
			pos =  message.indexOf('%',pos)+1;
			if(pos!=-1)
				hisnum[i] = pos;
			else break;
		}
		if(hisnum[0]==-1)return;
		String name = message.substring(hisnum[0],hisnum[1]-1);
		double sudu = Double.parseDouble(message.substring(hisnum[1],hisnum[2]-1));
		double key = Double.parseDouble(message.substring(hisnum[2],hisnum[3]-1));
		double keylength = Double.parseDouble(message.substring(hisnum[3],hisnum[4]-1));
		int number = Integer.parseInt(message.substring(hisnum[4], hisnum[5]-1));
		int deletetext = Integer.parseInt(message.substring(hisnum[5],hisnum[6]-1));
		int delete = Integer.parseInt(message.substring(hisnum[6],hisnum[7]-1));
		int mistake = Integer.parseInt(message.substring(hisnum[7],hisnum[8]-1));
		int repeat = Integer.parseInt(message.substring(hisnum[8],hisnum[9]-1));
		double Keyaccuracy = Double.parseDouble(message.substring(hisnum[9],hisnum[10]-1));
		double Keymethod = Double.parseDouble(message.substring(hisnum[10],hisnum[11]-1));
		double dacilv = Double.parseDouble(message.substring(hisnum[11],hisnum[12]-1));
		double time = Double.parseDouble(message.substring(hisnum[12],hisnum[13]-1));
		String wenben = message.substring(hisnum[13],hisnum[14]-1);
		int duan = Integer.parseInt(message.substring(hisnum[14],hisnum[15]-1));
		double nandu = Double.parseDouble(message.substring(hisnum[15]));
//		System.out.println(wenben);
		PreparedStatement ptmt;
		try {
			ptmt=Recordnum.con.prepareStatement(sql);
		    ptmt.setString(1, name);
		    ptmt.setDate(2, date);
		    ptmt.setDouble(3, sudu);
		    ptmt.setDouble(4, key);
		    ptmt.setDouble(5, keylength);
		    ptmt.setInt(6, number);
		    ptmt.setInt(7, deletetext);
		    ptmt.setInt(8, delete);
		    ptmt.setInt(9, mistake);
		    ptmt.setInt(10, repeat);
		    ptmt.setDouble(11, Keyaccuracy);
		    ptmt.setDouble(12, Keymethod);
		    ptmt.setDouble(13, dacilv);
		    ptmt.setDouble(14, time);
		    ptmt.setString(15, wenben);
		    ptmt.setInt(16, duan);
		    ptmt.setDouble(17, nandu);
		    ptmt.execute();
//		    System.out.println(date);
		    //999�����ƽ���ɼ�
		    if(duan==999||duan==0){
			    sql = "SELECT aver,n FROM client WHERE username=?";
			    ptmt = Recordnum.con.prepareStatement(sql);
			    ptmt.setString(1, name);
				ResultSet rs=ptmt.executeQuery();
				Double aver = 0.0;
				int n = 0;
				if(rs.next()){
					aver = rs.getDouble(1);
					n = rs.getInt(2);
					aver = aver*n;
					aver += sudu;
					n += 1;
					aver /= n;
					sql="update client set aver=?,n=? where username=?";
					ptmt = Recordnum.con.prepareStatement(sql);
					ptmt.setDouble(1, aver);
					ptmt.setInt(2, n);
					ptmt.setString(3, username);
					ptmt.execute();
				}
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void changeRecord(int recordnum,int recordrightnum,int recordmisnum,int recorddatenumlast) throws SQLException{
		String sql="update client set num =?,rightnum=?,misnum=?,datenum=? where username=?";//���������û���������������
		PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
        ptmt.setInt(1,recordnum);
        ptmt.setInt(2,recordrightnum);
        ptmt.setInt(3,recordmisnum);
        ptmt.setInt(4,recorddatenumlast);
        ptmt.setString(5, username);
        ptmt.execute();
        System.out.println(recordnum+" "+recordrightnum+" "+recordmisnum+" "+recorddatenumlast);
	}
	public void caozuo(int i,String username,String password,int recordnum) throws SQLException{
        switch(i){
        case 1:
            denglu(username,password,recordnum,this);
            break;
        case 2:
            zhuce(username,password);
            break;
        }
	}
    public void zhuce(String username,String password) throws SQLException{
    	System.out.print(username+"����ע��\r");
    	String sql="select username from client where username=?";//����û����Ƿ�ʹ�ù�
        PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
        ptmt.setString(1, username);
        ResultSet rs=ptmt.executeQuery();
        if(rs.next()){						//����û����Ƿ�ʹ�ù�
        	System.out.print(username+"�û����ѱ�ע��\r");
        	try {
				out.writeUTF("2");
			} catch (IOException e) {System.out.print(username+"֪ͨ��ͬ�û���ʧ��\r");}
        }
        else{
	        sql="insert into client values(?,?,?,?,?,?,?,?,?,?,?,default)";
	        ptmt=Recordnum.con.prepareStatement(sql);
	        ptmt.setString(1, username);
	        ptmt.setString(2, password);
	        ptmt.setInt(3,0);
	        ptmt.setInt(4,0);
	        ptmt.setInt(5,0);
	        ptmt.setInt(6,0);
	        ptmt.setInt(7,0);
	        ptmt.setDouble(8,0.0);
	        ptmt.setInt(9,0);
	        ptmt.setDate(10, Dateinit.getdate());
	        ptmt.setDate(11, Dateinit.getdate());
	        ptmt.execute();
	        try {
	        	System.out.print(username+"ע��ɹ���\r");
				out.writeUTF("1");
			} catch (IOException e) {System.out.print(username+"֪ͨע��ɹ�ʧ��\r");}
        }
    }
    //�û���¼
    public void denglu(String username,String password,int recordnum,Thread thread){
    	int num = 0;
    	int misnum = 0;
    	int rightnum = 0;
    	int datenum = 0;
    	String email;
    	Date date;
    	System.out.print(username+"���ڵ�¼\r");
    	try{
	        String sql="select * from client where username=? and password=?";
	        String sql1="select * from client where username=?";
	        
	        PreparedStatement ptmt=Recordnum.con.prepareStatement(sql1);
	        ptmt.setString(1, username);
	        ResultSet rs=ptmt.executeQuery();
	        if(!rs.next()){		//�����˺��Ƿ����
	        	 System.out.print(username+"�˻������ڣ�\n�����µ�¼��\r");
	            String message = "%3%0%0%0%0%0";
	            try {
					out.writeUTF(message);
				} catch (IOException e) {System.out.print(username+"���͵�¼ʧ��ʧ��\r");}
	            return ;
	        }
	        ptmt=Recordnum.con.prepareStatement(sql);
	        ptmt.setString(1, username);
	        ptmt.setString(2, password);
	        rs=ptmt.executeQuery();
	        //�ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
	        if(rs.next()){			//��֤�����Ƿ���ȷ
	            num = rs.getInt(3);
	            rightnum = rs.getInt(4);
	            misnum = rs.getInt(5);
	            datenum = rs.getInt(6);
	            email = rs.getString(12);
	            if(email==null){
	            	email="0";
	            }
	        	try {
					if(rightnum+misnum!=num){
						int temp = num-rightnum-misnum;
						rightnum += temp;
					}
					recordrightnum = rightnum;
					recordmisnum = misnum;
					recordNumlast = num;
					recorddatenumlast = datenum;
					changeonline(1);
					try{
//						Recordnum.online.put(username, socket);
					}catch(Exception e){e.printStackTrace();}
					String message = "%1%"+String.valueOf(num)+"%"+String.valueOf(rightnum)+"%"+String.valueOf(misnum)+"%"+String.valueOf(datenum)+"%"+email;
					System.out.println(message);
					out.writeUTF(message);
					dengluSign = 1;
					System.out.print(username+"��¼�ɹ���\r");
				} catch (IOException e) {System.out.print(username+"���͵�¼�ɹ�ʧ��\r");}
	        }else{
	            System.out.print(username+"�������������\n�����µ�¼��\r");
	            String message = "%2%0%0%0%0%0";
	            try {
					out.writeUTF(message);
				} catch (IOException e) {System.out.print(username+"���͵�¼ʧ��ʧ��\r");}
	        }
    	}catch(SQLException e){e.printStackTrace();}
    }
    void changeonline(int i) throws SQLException{
    	String sql="update client set online=?,lastdate=? where username=?";
    	PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
    	
	    ptmt.setInt(1,i);
	    ptmt.setDate(2,Dateinit.getdate());
	    ptmt.setString(3,username);
	    ptmt.execute();
    }

//	public void getfriend(){
//	String sql="select * from client where username=?";
//	PreparedStatement ptmt;
//	try {
//		ptmt = Recordnum.con.prepareStatement(sql);
//		 ptmt.setString(1, username);
//	        ResultSet rs=ptmt.executeQuery();
//	        if(rs.next()){
//	        	System.out.println(username+"�������ϵͳ");
//	        	String friends[];
//	        	String f = rs.getString(8);
//	        	System.out.println("�����б�"+f);
//	        	if(f!=null&&f!=""){
//	        		friends = f.split("%");
//	        		for(int i=0;i<friends.length;i++){
//	        			sql="select username from client where username=?";
//	        			ptmt.setString(1, friends[i]);
//	        		    rs=ptmt.executeQuery();
//	        		    if(rs.next()){
//	        		    	try {
//								out.writeUTF(rs.getString(1));
//								out.writeUTF(String.valueOf(rs.getInt(3)));
//				        		out.writeUTF(String.valueOf(rs.getInt(4)));
//				        		out.writeUTF(String.valueOf(rs.getInt(5)));
//				        		out.writeUTF(String.valueOf(rs.getInt(6)));
//				        		out.writeUTF(String.valueOf(rs.getInt(7)));
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//	        		    }
//	        		}
//	        	}
//	        	else{
//	        		System.out.println("�޺���");try {
//						out.writeUTF("%����==0");
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	        	}
//	        }
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} 
//}
}
