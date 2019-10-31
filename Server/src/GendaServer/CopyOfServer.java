package GendaServer;

import java.io.*;
import java.net.*;
import java.util.*;

/*��������*/
public class CopyOfServer {
  public List<Socket> socketList = new ArrayList<Socket>(); //����������ӵĿͻ��˵ļ���
  public ServerSocket server; //������
  public int portNum; //�˿ں�
  public static void main(String[] args) {
    int portNum = 8888; //�����������Ķ˿ں�
    CopyOfServer server = new CopyOfServer(portNum);
    server.innit();
  }
  public CopyOfServer(int portNum) {
    this.portNum = portNum;
  }
  public void innit() {
    try {
      server = new ServerSocket(portNum);
      System.out.println("�����������ɹ���");
      int socketNum = 0;
      while (true) {
        Socket socket = server.accept(); //�����ȴ��ͻ��˵�����
        socketNum++;
        System.out.println("��" + socketNum + "���ͻ������ӳɹ�����");
        socketList.add(socket); //���ӵĿͻ��˴�ŵ���������
        new RWThread(socket, socketNum - 1).start();
      }

    } catch (IOException e) {
      //		   e.printStackTrace();
      System.out.println("�ͻ��Ͽ�");
    }
  }
  class RWThread extends Thread { //���պͷ�����Ϣ���߳�
    public Socket socket;
    public int socketNum;
    PrintWriter pw;
    public RWThread(Socket socket, int sockerNum) {
      this.socket = socket;
      this.socketNum = sockerNum;
    }
    public void run() {
      super.run();
      try {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        while (true) {
          String message = br.readLine(); //���տͻ��˷�������Ϣ
          for (int i = 0; i < socketList.size(); i++) { //���͸��������ӵĿͻ���
            if (i != socketNum) {
              pw = new PrintWriter(new OutputStreamWriter(socketList.get(i).getOutputStream()));
              pw.println(message);
              pw.flush();
            }
          }
        }
      } catch (IOException e) {
        for (int i = 0; i < socketList.size(); i++) { //���͸��������ӵĿͻ���
          if (i != socketNum) {
            try {
              pw = new PrintWriter(
                  new OutputStreamWriter(socketList.get(i).getOutputStream(), "utf-8"));
              pw.println("��" + (socketNum + 1) + "�ͻ�"
                  + "�Ͽ�");
              pw.flush();
            } catch (IOException e1) {
              System.out.println("�޷���ͻ�" + i + "���Ϳͻ�" + socketNum + "�Ͽ���Ϣ");
            }
          }
        }
        System.out.println("��" + (socketNum + 1) + "�ͻ�"
            + "�Ͽ�");
      }
    }
  }
}