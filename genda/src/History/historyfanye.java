package History;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class historyfanye implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="��һҳ"){
			if(historyFrame.dangqian>=historyFrame.yenum)return;
			historyFrame.dangqian++;
		}
		else if(e.getActionCommand()=="��һҳ"){
			if(historyFrame.dangqian<=0)return;
			historyFrame.dangqian--;
		}
		else if(e.getActionCommand()=="������ҳ"){
			historyFrame.dangqian=0;
		}
		else if(e.getActionCommand()=="����βҳ"){
			historyFrame.dangqian=historyFrame.yenum;
		}
		else if(e.getActionCommand()=="��ת"){
			historyFrame.dangqian = Integer.parseInt(historyFrame.dangqianye.getText())-1;
		}
		historyFrame.clear();
		historyFrame.showtable();
		historyFrame.yemashow.setText(String.valueOf(historyFrame.dangqian+1)+"/"+String.valueOf(historyFrame.yenum+1));
	}
}