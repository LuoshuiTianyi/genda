package GendaServer;



import java.io.*;
import java.net.*;
import java.util.*;



/*��������*/
public class Server extends Thread{
	public List<Socket> socketList = new ArrayList<Socket>();//����������ӵĿͻ��˵ļ���
	public List<Socket> guanzhanList = new ArrayList<Socket>();//����������ӵĿͻ��˵ļ���
	public ServerSocket server;//������
	public int portNum;//�˿ں�
	public int socketSum = 0;  //�˿���
	public int mumber[] = {0,0};//������ʲô��� �������Ա
	public double sudu[] = {0.0,0.0}; //�����ٶ�
	public int win[] = {0,0};  //����Ӯ�Ĵ���
	public String name[] = {"",""};
	public ComputeSpeed comp[]= {null,null}; //����ʱ��
	public Server(int portNum){
		this.portNum = portNum;
	}
	public void run(){
		try { 
			server = new ServerSocket(portNum);
			System.out.print("�����Ϊ"+portNum+"�����ɹ�\r");
			while(true){
				Socket socket = server.accept();//�����ȴ��ͻ��˵�����
				socket.setSoTimeout(0);
				new ReadFirst(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("�˿�����ر�\r");
		}
	}
	class ReadFirst extends Thread{	//��ֹ����
		Socket socket;
		DataOutputStream out = null;
		DataInputStream in = null;
		ReadFirst(Socket t){
			try{
				socket = t;
				socket.setSoTimeout(1000);
				in = new DataInputStream(socket.getInputStream());
				String what = in.readUTF();
				socket.setSoTimeout(0);
				if(what.equals("��ս")){
					if(socketSum<2){
						socketSum++;//�ö˿��е����û���
						System.out.print("��"+socketSum+"����ս�ͻ�������"+portNum+"�ɹ�����\r");
						socketList.add(socket);//���ӵĿͻ��˴�ŵ���������
						new RWThread(socket).start();//�µ��߳�
						if(socketList.size()==2){
							for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���
								//������ͷ��socket��Ϊ����
								if(socketList.lastIndexOf(socketList.get(i))==0){
									try {
										win[0]=0;win[1]=0;
										out = new DataOutputStream(socketList.get(i).getOutputStream());
										out.writeUTF("%0%��%��%��%��%������������׼����ʼ,���ĺ�ȴ��Է�������հ�\n�Է��������ʱ���Է����ĳɹ���������ʼ����\n���Ƿ���\n%��");
									} catch (IOException e1) {
										//e1.printStackTrace();
										System.out.print("�޷�֪ͨ����Ϊ����\r");
									}
								}
								//�����е�������Ա��Ϊ��ͨ��Ա.
								else{
									mumber[socketList.lastIndexOf(socketList.get(i))] = 1;
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%0%��%��%��%��%������������׼����ʼ,���ĺ�ȴ��Է�������հ�\n�Է��������ʱ���Է����ĳɹ���������ʼ����\n%��");
								}
							}
						}
					}
					else {
						out = new DataOutputStream(socket.getOutputStream());
						out.writeUTF("%0%��%��%��%��%��������\n%��");
						socket.close();
					}
				}
				else if(what.equals("����")){System.out.println("����");}
				else if(what.equals("��ս")){
					guanzhanList.add(socket);
				}
			}
			catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.print("��ʱ�޷��ر�socket\r");
				}
				System.out.print("���Ӻ�ʱ\r");
			}
		}
	}
	class RWThread extends Thread{//���պͷ�����Ϣ���߳�   ����1 ���� 2������� 3�ı�  4�Ʒ� 5ϵͳ 6��ʼ
		
		public Socket socket;
		DataInputStream in = null;
		DataOutputStream out = null;
		int num[] = {0,0,0,0,0};
		String system = "";
		int began = 0;
		public RWThread(Socket socket){
			this.socket = socket;
		}
		@SuppressWarnings("unused")
		public void run() {
			super.run();
			String message;
			try {
				in = new DataInputStream(socket.getInputStream());
				while(true){
					message = in.readUTF();//���տͻ��˷�������Ϣ
					System.out.println(message);
					int pos = 0;
					for(int i=0;i<5;i++){
						pos =  message.indexOf('%',pos)+1;
						if(pos!=-1)
							num[i] = pos;
						else break;
					}
					if(began==0){
						began =Integer.parseInt(message.substring(num[0],num[1]-1));
						if(began==1){
							system = "�Է���׼��\n";
							for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���;
								if(socket!=socketList.get(i)){ 
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%��"+"%��"+"%��"+"%��%"+system+"%��");
								}
							}	
						}
					}
					else {
						began = Integer.parseInt(message.substring(num[0],num[1]-1));
						if(began==0){
							system = "�Է�ȡ��׼��\n";
							for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���;
								if(socket!=socketList.get(i)){ 
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%��"+"%��"+"%��"+"%��%"+system+"%��");
								}
							}
						}
					}
					String jindu = message.substring(num[1],num[2]-1);
					String wenben = message.substring(num[2],num[3]-1);
					sudu[socketList.lastIndexOf(socket)] = Double.parseDouble(message.substring(num[3],num[4]-1));
					name[socketList.lastIndexOf(socket)] = message.substring(num[4]);
					if(sudu[0]>sudu[1]&&sudu[0]!=0&&sudu[1]!=0){//����Ӯ�ߣ������ٶ�		
						win[0]++;
						sudu[0] = 0.0;sudu[1]=0.0;
						System.out.println(sudu[0]+":"+sudu[1]);
						for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���;
							out = new DataOutputStream(socketList.get(i).getOutputStream());
							out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+jindu+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%��"+"%��");
						}
						
					}
					else if(sudu[0]<sudu[1]&&sudu[0]!=0&&sudu[1]!=0){
						System.out.println(sudu[1]);
						win[1]++;
						System.out.println(name[0]+":"+name[1]+"/"+sudu[0]+":"+sudu[1]);
						sudu[0] = 0.0;sudu[1]=0.0;
						for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���;
							out = new DataOutputStream(socketList.get(i).getOutputStream());
							out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+jindu+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%��"+"%��");
						}
						
					}
					if(message!=null){//�ж��Ƿ�Ҫ������Ϣ
						if(began==1){
							for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���;
								if(socket!=socketList.get(i)){ 
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+jindu+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%��"+"%��");
								}
							}						
						}
						else{
							for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���;
								if(socket!=socketList.get(i)){
									out = new DataOutputStream(socketList.get(i).getOutputStream());
									out.writeUTF("%"+String.valueOf(mumber[socketList.lastIndexOf(socket)])+"%"+"%"+wenben+"%"+name[0]+":"+name[1]+"/%"+String.valueOf(win[0])+":"+String.valueOf(win[1])+"%��"+"%��");
								}
							}
						}
					}
					else{
						socket.close();
						socket.sendUrgentData(0);
					}
				}
			} catch (IOException e) {
				//e.printStackTrace();
				for(int i=0;i<socketList.size();i++){//���͸��������ӵĿͻ���
					if(socket!=socketList.get(i)){
						try {
							out = new DataOutputStream(socketList.get(i).getOutputStream());
							out.writeUTF("%0%��%��%��%��%��һ��ҶϿ�\n%��");
						} catch (IOException e1) {
							System.out.print("�޷������"+i+"�������"+(socketSum-1)+"�Ͽ���Ϣ\r");
						}
					}
				}
				System.out.print("����뿪"+portNum/1111+"����"+portNum/1111+"ʣ��"+(--socketSum)+"���\r");//�ͻ��Ͽ���sockeSum��һ
				socketList.remove(socket);//�ӷ���������ɾ���ѶϿ��Ŀͻ�
			}
		}
	}
}
