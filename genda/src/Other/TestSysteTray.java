package Other;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TestSysteTray {
	public static void main(String args[]) {
		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) // �ж�ϵͳ�Ƿ�֧��ϵͳ����
		{
			SystemTray tray = SystemTray.getSystemTray(); // ����ϵͳ����


			Image image = Toolkit.getDefaultToolkit().getImage("images/config_3.ico");//����ͼƬ ͼƬλ���ǳ������ڵ�Ŀ¼
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ����һ������
					JFrame frame = new JFrame();
					frame.setBounds(400, 400, 200, 200);
					JLabel label = new JLabel("welcome   JDK1.6");
					frame.add(label);
					frame.setVisible(true);
				}
			};

			// ���������˵�
			PopupMenu popup = new PopupMenu();//������Ҽ����ܴ����Ĳ˵�
			MenuItem defaultItem = new MenuItem("��");
			defaultItem.addActionListener(listener);
			MenuItem exitItem = new MenuItem("�˳�");
			exitItem.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			popup.add(defaultItem);
			popup.add(exitItem);
			trayIcon = new TrayIcon(image, "��С������", popup);// ����trayIcon
			trayIcon.addActionListener(listener);//��Сͼ����ϼ�������Ĭ�ϵľ��Ǽ���˫����
//���ż���������ɶ��  �ͼ�mouselistener
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}

}