package Example13_4;
import java.io.*;
import java.net.*;
import java.util.*;
public class Server {
  public static void main(String args[]) {
    ServerSocket server = null;
    ServerThread thread;
    Socket you = null;
    while (true) {
      try {
        server = new ServerSocket(2010);
      } catch (IOException el) {
        System.out.println("���ڼ���");
      }
      try {
        System.out.println(" �ȴ��ͻ�����:");
        you = server.accept();
        System.out.println("�ͻ��ĵ�ַ:" + you.getInetAddress());
      } catch (IOException e) {
        System.out.println("���ڵȴ��ͻ�");
      }
      if (you != null) {
        new ServerThread(you).start();
      }
    }
  }
}