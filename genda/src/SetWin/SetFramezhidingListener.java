package SetWin;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetFramezhidingListener implements ActionListener {
	int zhidingsign = 0;
	JButton zhiding;
	Window win;
	SetFramezhidingListener(JButton zhiding,Window win){
		this.zhiding = zhiding;
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(zhidingsign == 0){
			zhidingsign = 1;
			zhiding.setText("�����ö�\"��\"");
			win.setAlwaysOnTop(true);
		}
		else{
			zhidingsign = 0;
			zhiding.setText("�����ö�\"��\"");
			win.setAlwaysOnTop(false);
		}
	}
}
