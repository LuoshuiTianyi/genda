package denglu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class RWThread146 extends Thread {
	public Socket socket;
	DataInputStream in = null;
	DataOutputStream out = null;
	int num[] = {0,0,0,0};
	int dengluSign = 0;
	public int recordnum = 0;
	public int recordNumlast = 0;
	String username;
	public RWThread146(Socket socket){
		this.socket = socket;
	}
	public void run(){
		String message;
		try{
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			System.out.print("�û�����\r");
			while(true){
				message = in.readUTF();//���տͻ��˷�������Ϣ
//				System.out.println(message);
				if(message.equals("����")){
					continue;
				}
				if(message.equals("�Ͽ�")){
					socket.close();
					System.out.println("�˳���¼");
					socket.sendUrgentData(0);
				}
				int pos = 0;
				for(int i=0;i<4;i++){				//�����յ���Ϣ,�ֿ黯
					pos =  message.indexOf('%',pos)+1;
					if(pos!=-1)
						num[i] = pos;
					else break;
				}
				if(num[0]==-1)continue;
				int caozuohao = Integer.parseInt(message.substring(num[0],num[1]-1));
				username = message.substring(num[1],num[2]-1);
				String password = message.substring(num[2],num[3]-1);
				String record = message.substring(num[3]);
				if(!record.equals("��")&&!record.equals("-999"))
					recordnum = Integer.parseInt(record);
				if(dengluSign==0){
					System.out.print(username+"�������\r");
					caozuo(caozuohao,username,password,recordnum);
				}
				else{
					recordNumlast = recordnum;//�������һ������
				}
			}
		}
		catch(Exception e){ 		//�˳�ʱ�������ݿ���������޸�
			System.out.print(username+"�û��˳�\r");
			if(dengluSign==1){
				try {
					if(recordNumlast<0)
						recordNumlast = 0;
					changeRecord(recordNumlast);
					this.stop();
				} catch (SQLException e1) {System.out.print(username+"��������ʧ��\r");}
			}
		}
	}
	public void changeRecord(int recordnum) throws SQLException{
		String sql="update client set num =? where username=?";//���������û���������������
        PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
        ptmt.setInt(1,recordnum);
        ptmt.setString(2, username);
        ptmt.execute();
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
	        sql="insert into client (username,password,num) values(?,?,?)";
	        ptmt=Recordnum.con.prepareStatement(sql);
	        ptmt.setString(1, username);
	        ptmt.setString(2, password);
	        ptmt.setInt(3,0);
	        ptmt.execute();
	        try {
	        	System.out.print(username+"ע��ɹ���\r");
				out.writeUTF("1");
			} catch (IOException e) {System.out.print(username+"֪ͨע��ɹ�ʧ��\r");}
        }
    }
    //�û���¼
    public void denglu(String username,String password,int recordnum,Thread thread) throws SQLException{
    	int num = 0;
    	System.out.print(username+"���ڵ�¼\r");
        String sql="select * from client where username=? and password=?";
        PreparedStatement ptmt=Recordnum.con.prepareStatement(sql);
        ptmt.setString(1, username);
        ptmt.setString(2, password);
        ResultSet rs=ptmt.executeQuery();
        //�ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
        if(rs.next()){
            System.out.print(username+"��¼�ɹ���\r");
            num = rs.getInt(3);
        	try {
            	String message = "%1%"+String.valueOf(num);
				out.writeUTF(message);
				recordNumlast = num;
				dengluSign = 1;
			} catch (IOException e) {System.out.print(username+"���͵�¼�ɹ�ʧ��\r");}
        }else{
            System.out.print(username+"�������������\n�����µ�¼��\r");
            String message = "%2%0";
            try {
				out.writeUTF(message);
			} catch (IOException e) {System.out.print(username+"���͵�¼ʧ��ʧ��\r");}
        }
    }
}