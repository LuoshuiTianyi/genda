package Acticle;
import genda1.*;
import gendaClient.battleClient;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import Login.Login;
public class ActicleListener implements TreeSelectionListener ,ActionListener{
	JTextArea dazi;
	JTextPane wenben;
	JTree tree;
	GendaListener gendalistener;
	File open;
	RandomAccessFile in = null,out=null;
	String []danziFileName,wenzhangFileName;
	byte s[] ;
	String all,wen;
	int i;
	public static long length = 0;
	JTextField number;
	public static int fontnum=0,fontweizhi=0;
	Window win;
//	String s;
	public void setWenbenText(JTextPane t){
		wenben = t;
	}
	public void setTree(JTree t){
		tree = t;
	}
	public void setDanziFileName(String []t) {
		danziFileName = t;
	}
	public void setWenzhangFileName(String []t){
		wenzhangFileName = t;
	}

	public void setNumber(JTextField number){
		this.number = number;
	}
	public void setWin(Window win){
		this.win = win;
	}
	void getNumber(){
		try{
			System.out.println(number.getText());
			fontnum = Integer.parseInt(number.getText());}
		catch(Exception e){
			JOptionPane.showMessageDialog(new JTextArea(),"��������������");
		}	
	}
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		if(node.isLeaf()){
			try {
				in=null;
				fontweizhi = 0;
				for(i=0;i<danziFileName.length;i++)	
					if(node.toString().equals(danziFileName[i])){
						open = new File("����//������",node.toString());
						in = new RandomAccessFile(open,"r");
						SendWenben.title = node.toString();
						break;
					}
				for(i=0;i<wenzhangFileName.length;i++){
					if(in!=null)break;
					if(node.toString().equals(wenzhangFileName[i])){
						open = new File("����//������",node.toString());
						in = new RandomAccessFile(open,"r");
						SendWenben.title = node.toString();
						break;
					}
				}
			}catch (Exception e) {}
			try {
				getNumber();
				length = in.length();
				s = new byte[(int)length];
				in.readFully(s);
				all = new String(s);
				all = all.replaceAll("\\s*", ""); 
				if(fontnum>all.length())
					wen = all.substring(fontweizhi,fontnum);
				else
					wen = all.substring(fontweizhi, fontweizhi+fontnum);
				
				wenben.setText(wen);
				fontweizhi += fontnum;
			} catch (IOException e) {}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(SendWenben.sendwenSign==1){
			if(e.getActionCommand()=="��һ��"){
				try{
					if(fontweizhi>=all.length()){
						JOptionPane.showMessageDialog(new JTextArea(),"���Ľ���");
						win.sendwen.setVisible(false);
						SendWenben.sendwenSign = 0;
						return;
					}
					if(fontweizhi+fontnum>all.length())
						wen = all.substring(fontweizhi, all.length());
					else
						wen = all.substring(fontweizhi, fontweizhi+fontnum);
					QQZaiwenListener.wenbenstr = wen;
					win.dazi.setText("");
					win.tipschange.changecolortip();
					win.wenben.setText(QQZaiwenListener.wenbenstr);
					win.gendaListener.ChangeFontColor();
					win.wenben.setCaretPosition(0);//������ɫ
					fontweizhi+= fontnum;
				}
				catch(Exception ex){System.out.println("���Ĵ�ʧ��");}
				win.dazi.setText(null);
				win.dazi.setEditable(true);
				win.gendaListener.sign = 0;
				win.gendaListener.deleteNumber = 0;
				win.gendaListener.deleteTextNumber = 0;
				win.gendaListener.KeyNumber = 0;
				win.gendaListener.space = 0;
				win.gendaListener.left = 0;
				win.gendaListener.right = 0;
				
				win.wenben.setCaretPosition(0);
				QQZaiwenListener.lilun = 1.0*win.tipschange.compalllength()/QQZaiwenListener.wenbenstr.length();
				win.lilunma.setText("�����볤:"+String.format("%.2f", QQZaiwenListener.lilun));
				
				//�������
				win.gendaListener.daciall = 0;
				win.gendaListener.daci = 0;
				
				win.dazi.setEditable(true);
				win.dazi.requestFocusInWindow();
				win.setGendajindu.open(wenben.getText().length());
				RegexText.duan1++;	//��������
				win.sendwen.setText(String.valueOf(fontweizhi/2)+"/"+String.valueOf(length/2)+":"+String.format("%.2f",(double)fontweizhi*100/length)+"%");
				try{
					DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
					out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+Window.wenben.getText()+"%0"+"%"+Login.zhanghao.getText());
				}
				catch(Exception ex){
					System.out.println("�޷������ı�����");
				}
			}
			else if(e.getActionCommand()=="����������"){
				try{
					open = new File("����//������","�������.txt");
					out = new RandomAccessFile(open,"rw");
					wen = all.substring(fontweizhi-fontnum, all.length());
					byte baocun[] = wen.getBytes();
					out.write(baocun);
					out.close();
					JOptionPane.showMessageDialog(new JTextArea(),"�ѱ��浱ǰ�������");
				}catch(Exception ex){JOptionPane.showMessageDialog(new JTextArea(),"����ʧ��");}
			}
		}
		else 
			win.dazi.requestFocusInWindow();
	}
}
