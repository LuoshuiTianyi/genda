package Acticle;
import genda1.*;
import gendaClient.battleClient;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import Login.Login;
import SetWin.SetFrameQianshuiListener;
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
						SendWenben.title = node.toString();
						if(SendWenben.title.equals("�������.txt")){
							readjindu();
						}
						open = new File("����//������",SendWenben.title);
						in = new RandomAccessFile(open,"r");
						
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
					wen = all.substring(fontweizhi,all.length());
				else
					wen = all.substring(fontweizhi, fontweizhi+fontnum);
				wenben.setText(wen);
				fontweizhi += fontnum;
				win.sendwen.setText(String.valueOf(fontweizhi)+"/"+String.valueOf(all.length())+":"+String.format("%.2f",(double)fontweizhi*100/all.length())+"%");
			} catch (IOException e) {}
		}
	}
	void readjindu() throws IOException{
		try {
			open = new File("����//������","�������.txt");
			Reader read = new FileReader(open);
			BufferedReader br = new BufferedReader(read);
			SendWenben.title = br.readLine();
			fontweizhi = Integer.valueOf(br.readLine());
			br.close();
			read.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					if(fontweizhi+fontnum>=all.length()){
						wen = all.substring(fontweizhi, all.length());
						fontweizhi = all.length();
					}
					else{
						wen = all.substring(fontweizhi, fontweizhi+fontnum);
						fontweizhi+= fontnum;
					}
					QQZaiwenListener.wenbenstr = wen;
					QQZaiwenListener.wenbenstr = RegexText.qukong(RegexText.huanfu(QQZaiwenListener.wenbenstr));
					Window.f3listener.F3();
					
					RegexText.duan1++;	//��������
					win.sendwen.setText(String.valueOf(fontweizhi)+"/"+String.valueOf(all.length())+":"+String.format("%.2f",(double)fontweizhi*100/all.length())+"%");
					try{
						DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
						out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+Window.wenben.getText()+"%0"+"%"+Login.zhanghao.getText());
					}
					catch(Exception ex){
						System.out.println("�޷������ı�����acticlelistner,121");
					}
					if(SetFrameQianshuiListener.qianshui==0)ShareListener.send();
				}
				catch(Exception ex){System.out.println("���Ĵ�ʧ��");}
			}
			else if(e.getActionCommand()=="����������"){
				try{
					open = new File("����//������","�������.txt");
					FileOutputStream testfile = new FileOutputStream(open);
					testfile.write(new String("").getBytes());
					byte baocun[] = (SendWenben.title+"\r\n"+String.valueOf(fontweizhi-fontnum)).getBytes();
					testfile.write(baocun);
					testfile.close();
					JOptionPane.showMessageDialog(new JTextArea(),"�ѱ��浱ǰ�������");
				}catch(Exception ex){JOptionPane.showMessageDialog(new JTextArea(),"�������ʧ��");}
			}
		}
		else 
			win.dazi.requestFocusInWindow();
	}
}
