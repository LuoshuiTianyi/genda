package denglu;

import java.io.BufferedReader;
import java.sql.*;
import java.util.*;

public class Client {
  /**
   * ����ʵ���û���ע��͵�¼
   */
  private static String username; //�û���¼ע�������
  private static String password; //�û�����
  private static String url =
      "jdbc:mysql://localhost:3306/students?useUnicode=true&characterEncoding=utf-8"; //�������ݿ��url��test�����Լ���һ�����ݿⰡ�����ǡ�
  private static String user = "root"; // mysql��¼��
  private static String pass = "951753"; // mysql��¼���루д�Լ�֮ǰ���õģ�
  private static Connection con; //
  static Scanner input = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    //�������ݿ���������������
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(url, user, pass);

    System.out.println("********�û�����********");
    System.out.println("��ѡ��\n 1:�û���¼\n 2���û�ע��");
    System.out.println("**********************");

    int i = input.nextInt();
    switch (i) {
      case 1:
        denglu();
        break;
      case 2:
        zhuce();
        break;
      default:
        System.out.println("��������");
        System.exit(0);
    }
  }
  //�û�ע��
  public static void zhuce() throws SQLException {
    System.out.println("���������������");
    username = input.next();
    System.out.println("��������ĵ�¼���룺");
    String p1 = input.next();
    System.out.println("���ٴ��������ȷ�����룺");
    String p2 = input.next();
    if (p1.equals(p2)) {
      //���������������ͬ�ſ���ע��
      password = p1;
      String sql = "insert into client (username,password) values(?,?)";
      PreparedStatement ptmt = con.prepareStatement(sql);
      ptmt.setString(1, username);
      ptmt.setString(2, password);
      ptmt.execute();
      System.out.println("ע��ɹ���\n���¼��");
      denglu();
    } else {
      System.out.println("�������������ȷ�����벻�����������ע�᣺");
      zhuce();
    }
  }
  //�û���¼
  public static void denglu() throws SQLException {
    System.out.println("���������������");
    username = input.next();
    System.out.println("������������룺");
    password = input.next();

    String sql = "select username,password from client where username=? and password=?";
    PreparedStatement ptmt = con.prepareStatement(sql);
    ptmt.setString(1, username);
    ptmt.setString(2, password);
    ResultSet rs = ptmt.executeQuery();
    //�ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
    if (rs.next()) {
      System.out.println("��¼�ɹ���");
    } else {
      System.out.println("�������������\n�����µ�¼��");
      denglu();
    }
  }
}