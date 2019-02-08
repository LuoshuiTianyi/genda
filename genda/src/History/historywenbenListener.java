package History;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class historywenbenListener implements ActionListener {
	JTextField lookcow;
	String a;
	DataOutputStream out;
	DataInputStream in;
	historywenbenListener(JTextField lookcow){
		this.lookcow = lookcow;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int lookrownum = 0;
		try{
		lookrownum = Integer.parseInt(historyFrame.id.get(Integer.parseInt(lookcow.getText())-1));
		System.out.println(lookrownum);
		}catch(Exception e){	JOptionPane.showMessageDialog(new JTextArea(),"�������");return;}
		try{
			Socket socket = new Socket(Window.IP,1232);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			out.writeUTF("����%"+String.valueOf(lookrownum));
			String wen = in.readUTF();
			System.out.println(wen);
			socket = null;
			new ShowWen(wen);
		}
		catch(Exception e){System.out.println("���ͻ�ȡ����ʧ��");e.printStackTrace();}
	}
}
