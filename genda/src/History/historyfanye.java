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
		historyFrame.clear();
		historyFrame.showtable();
		historyFrame.yemashow.setText(String.valueOf(historyFrame.dangqian+1)+"/"+String.valueOf(historyFrame.yenum+1));
	}
}