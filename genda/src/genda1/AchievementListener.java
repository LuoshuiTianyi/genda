package genda1;

import gendaClient.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;



import javax.swing.*;
import Tips.*;
import keep.KeyPassword;
import Login.Login;
import QQ.QQ;

public class AchievementListener extends AbstractAction{
	JLabel qqName;
	JButton achievementButton;
	GendaListener gendaListener;
	JTextArea chengji,dazi;
	JTextPane wenben;
	double sudu,second,length,Keymethod,right,left,KeyNumber,mistake,deleteNumber,deleteTextNumber,repeat,fengzhi;
	public static double Keyaccuracy;
	public static double dacilv;
	String gendageshi;
	TableAdd table;
	int space;
	private Window win;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			sendchengji();
//			dazi.setText("");
//			dazi.setEditable(true);
//			dazi.requestFocusInWindow();
//			wenben.setCaretPosition(0);
//			gendaListener.setSign(0);
//			gendaListener.KeyNumber = 0;
//			gendaListener.deleteNumber = 0;
//			gendaListener.deleteTextNumber = 0;
//			gendaListener.left = 0;
//			gendaListener.right = 0;
//			gendaListener.repeat = 0;
//			gendaListener.record = "";
		
	}
	public void sendchengji(){
		if(gendaListener.sign==2){
			try{			//�ж϶�ս��
				if(!battleClient.socket.isClosed()){
					if(Window.reducesudu.getText()!="")
						battleSend.out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+Window.wenben.getText()+"%"+String.valueOf(sudu-30*battleSend.Mistake-Integer.parseInt(Window.reducesudu.getText()))+"%"+Login.zhanghao.getText());	
					else
						battleSend.out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+Window.wenben.getText()+"%"+String.valueOf(sudu-30*battleSend.Mistake)+"%"+Login.zhanghao.getText());	
				}
			}
			catch(Exception ex){
				System.out.println("�޷������ı�����F1");
			}
			setClipboardString(gendageshi);//���ɼ��η�����а�
			QQ qq = new QQ();
			try {			//���ͳɼ���QQ����
				qq.sendMessage(2,qqName.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("���Ĵ���");
			}
		}
	}
	public String getGeshi(){
		sudu = gendaListener.getSudu();
		KeyNumber = gendaListener.getKeyNumber();
		second = gendaListener.getSecond();
		length = gendaListener.getLength();
		mistake = gendaListener.getMistake();
		deleteNumber = gendaListener.getDeleteNumber();
		deleteTextNumber = gendaListener.getDeleteTextNumber();
		right = gendaListener.getRight();
		left = gendaListener.getLeft();
		repeat = gendaListener.getRepeat();
		space = gendaListener.getSpace();
		fengzhi = gendaListener.getFenzhi();
		if(right!=0)
			Keymethod = left/right;
		else 
			Keymethod = 1;
		Keyaccuracy = (KeyNumber-deleteNumber*2-deleteTextNumber*(1.0*Window.tipschange.alllength/QQZaiwenListener.wenbenstr.length()))/KeyNumber;
		dacilv = ((double)(gendaListener.daciall)/(QQZaiwenListener.wenbenstr.length()+deleteTextNumber));
		gendageshi = 
				"��"+RegexText.duan1+
				"�� �ٶ�"+String.format("%.2f", sudu)+
				" ����"+String.format("%.2f",KeyNumber/second)+
				" �볤"+String.format("%.2f", KeyNumber/length)+
				" �궥����"+String.format("%.2f", Tips.dingalllength/QQZaiwenListener.wenbenstr.length())+
				" �����Ѷ�"+String.format("%.2f", Tips.dengji)+
				" ����"+(int)(length)+" �ظ�"+(int)(deleteTextNumber)+
				" �˸�"+(int)(deleteNumber)+
				" ����"+(int)(mistake)+
				" ����"+(int)(KeyNumber)+
				" ѡ��"+(int)(repeat)+
				" ��׼"+String.format("%.2f",Keyaccuracy*100)+
				"% ����"+String.format("%.2f",Keymethod*100)+
				"%(��"+String.valueOf((int)left)+":��"+String.valueOf((int)right)+":�ո�"+String.valueOf(space)+")"+
				" �����"+String.format("%.2f", dacilv*100)+
				"% ѡ����"+String.format("%.2f", repeat/length*100)+
//				"% ��ֵ"+String.format("%.2f", fengzhi)+
				"% ������������v1.49";
//		ReadyListener.ReadyDuan++;
		table.addRow();
		try{
			sendhistory();
		}catch(Exception e){
			System.out.println("��ʷ�ɼ�����");
		}
		try{
			if(Window.everydaySign){
				sendsaiwen();
				win.setAlwaysOnTop(false);
				Window.everydaySign = false;
			}
		}catch(Exception e){
			System.out.println("ÿ�����ĳɼ����� ACHI 112");
		}
		try{			//�ж϶�ս��
			if(!battleClient.socket.isClosed()){
				gendageshi = gendageshi+" ����"+Window.reducesudu.getText()+
						" �ش�"+battleSend.Mistake+
						" �����ٶ�(��ʾ�ٶ�-�ش�*30-����)"+String.format("%.2f",sudu-30*battleSend.Mistake-Integer.parseInt(Window.reducesudu.getText()));
				gendageshi = gendageshi+" ���ڶ�ս�� "+battleReadThread.Whowin;
			}
		}
		catch(Exception ex){
			System.out.println("�޷����ö�ս��׺");
		}
		return gendageshi;
	}
	public void sendsaiwen(){
		if(Login.dengluSign == 1){
			String message = "�ɼ�"+
					"%"+Login.zhanghao.getText()+
					"%"+String.format("%.2f", sudu)+
					"%"+String.format("%.2f",KeyNumber/second)+
					"%"+String.format("%.2f", KeyNumber/length)+
					"%"+(int)(length)+
					"%"+(int)(deleteTextNumber)+
					"%"+(int)(deleteNumber)+
					"%"+(int)(mistake)+
					"%"+(int)(repeat)+
					"%"+String.format("%.2f",Keyaccuracy*100)+
					"%"+String.format("%.2f",Keymethod*100)+
					"%"+String.format("%.2f", dacilv*100)+
					"%"+String.valueOf(GendaListener.comp.getSecond())+
					"%"+String.format("%.2f", Tips.dengji);
			message = KeyPassword.convertMD5(message);
			try {
				Login.out.writeUTF(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void sendhistory(){
		if(Login.dengluSign == 1){
			String message = "��ʷ"+
					"%"+Login.zhanghao.getText()+			
					"%"+String.format("%.2f", sudu)+
					"%"+String.format("%.2f",KeyNumber/second)+
					"%"+String.format("%.2f", KeyNumber/length)+
					"%"+(int)(length)+
					"%"+(int)(deleteTextNumber)+
					"%"+(int)(deleteNumber)+
					"%"+(int)(mistake)+
					"%"+(int)(repeat)+
					"%"+String.format("%.2f",Keyaccuracy*100)+
					"%"+String.format("%.2f",Keymethod*100)+
					"%"+String.format("%.2f", dacilv*100)+
					"%"+String.valueOf(GendaListener.comp.getSecond())+
					"%"+QQZaiwenListener.wenbenstr+
					"%"+String.valueOf(RegexText.duan1)+
					"%"+String.format("%.2f", Tips.dengji);//14
			message = KeyPassword.convertMD5(message);
			try {
				Login.out.writeUTF(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setWin(Window win){
		this.win = win;
	}
	public void setGendaListener(GendaListener t){
		gendaListener = t;
	}
	public void setChengjiText(JTextArea t){
		chengji = t;
	}
	public void setDaziText(JTextArea t){
		dazi = t;
	}
	public void setWenbenText(JTextPane t){
		wenben = t;
	}
	public void setQQName(JLabel qqName2){
		qqName = qqName2;
	}
	public void setTable(TableAdd t){
		table = t;
	}
    public static void setClipboardString(String text) {
        // ��ȡϵͳ������
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // ��װ�ı�����
        Transferable trans = new StringSelection(text);
        // ���ı��������õ�ϵͳ������
        clipboard.setContents(trans, null);
        clipboard = null;
    }
    public static String getClipboardString() {
        // ��ȡϵͳ������
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // ��ȡ�������е�����
        Transferable trans = clipboard.getContents(null);
      
        if (trans != null) {
            // �жϼ������е������Ƿ�֧���ı�
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // ��ȡ�������е��ı�����
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    clipboard = null;
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        clipboard = null;
        return null;
    }
}
