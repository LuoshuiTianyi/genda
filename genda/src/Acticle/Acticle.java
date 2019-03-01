package Acticle;

import genda1.Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Acticle extends JFrame{
	public static ActicleListener treeListener;
	JTree tree;
	JScrollPane tree1;
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode danzilei,wenzhanglei;
	Window win;
	JTextPane wenben;
	JScrollPane wenben1;
	JTextField number,cikuchouqucanshu;
	JButton send,next,mix,chouqu,cikuchouqu;
	JPanel p = new JPanel();
	int i;
	public Acticle(Window win){
		this.win = win;
		setTitle("����");
		setBounds(100,100,605,340);
//		setLayout(null);
		p.setLayout(null);
		add(p);
		init();
		setVisible(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	void init(){
		
		addinArea();
		addnumber();
		Sendwenben();
		Articlelist();
		addNext();
		addluanxu();
		addchouqu();
		addcikuchouqu();
	}
	void addcikuchouqu(){
		cikuchouqu = new JButton("�ʿ���ϰ");
		cikuchouqucanshu = new JTextField("x:y:z");
		cikuchouqu.setBounds(390,270,70,30);
		cikuchouqucanshu.setBounds(465,270,60,30);
		cikuchouqu.addActionListener(treeListener);
		p.add(cikuchouqu);
		p.add(cikuchouqucanshu);
	}
	void addchouqu(){
		chouqu = new JButton("��ȡģʽ����");
		chouqu.setBounds(285,270,100,30);
		p.add(chouqu);
		chouqu.addActionListener(treeListener);
	}
	void addluanxu(){
		mix = new JButton("ȫ������");
		mix.setBounds(210, 270, 70, 30);
		p.add(mix);
		mix.addActionListener(win.mixlistener);
	}
	void addNext(){
		next = new JButton("��һ��");
		next.setBounds(240, 270, 70, 30);
//		add(next);
		next.addActionListener(treeListener);
		
	}
	void Sendwenben(){
		send = new JButton("˳��ģʽ����");
		send.setBounds(105, 270, 100, 30);
		p.add(send);
		SendWenben sendwenben = new SendWenben(wenben);
		sendwenben.setwin(win,this);
		send.addActionListener(sendwenben);
	}
	void addnumber(){
		number = new JTextField("100");
		number.setBounds(5,270,90,30);
		p.add(number);
	}
	void addinArea(){
		wenben = new JTextPane();
		wenben1 = new JScrollPane(wenben);
		wenben1.setBounds(191,0,400,270);
		p.add(wenben1);
	}
	void Articlelist(){
		root = new DefaultMutableTreeNode("��ϰ");
		danzilei = new DefaultMutableTreeNode("������");
		wenzhanglei = new DefaultMutableTreeNode("������");
		File danziDir = new File("����//������"),
				wenzhangDir = new File("����//������");
		File []danziFile = danziDir.listFiles(),
				wenzhangFile = wenzhangDir.listFiles();
		String[]danziFileName = danziDir.list(),
				wenzhangFileName = wenzhangDir.list();
		for(i=0;i<danziFileName.length;i++)
			 if(danziFile[i].isFile()) {
				 danzilei.add(new DefaultMutableTreeNode(danziFileName[i]));
			 }
		for(i=0;i<wenzhangFileName.length;i++)
			if(wenzhangFile[i].isFile())
				wenzhanglei.add(new DefaultMutableTreeNode(wenzhangFileName[i]));
		root.add(danzilei);
		root.add(wenzhanglei);

		tree = new JTree(root);
		tree1 = new JScrollPane(tree);
		 	
		treeListener = new ActicleListener();
		treeListener.setActicle(this);
		treeListener.setTree(tree);
		treeListener.setDanziFileName(danziFileName);
		treeListener.setWenzhangFileName(wenzhangFileName);
		treeListener.setWin(win);
		tree.addTreeSelectionListener(treeListener);
		tree1.setBounds(0,0,190,270);
		treeListener.setWenbenText(wenben);

		treeListener.setNumber(number);
		
		p.add(tree1);
	}
}
