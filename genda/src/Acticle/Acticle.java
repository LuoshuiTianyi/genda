package Acticle;

import genda1.Window;

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
	JTextField number;
	JButton send,next;
	int i;
	public Acticle(Window win){
		this.win = win;
		setTitle("����");
		setBounds(100,100,700,400);
		setLayout(null);
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
	}
	void addNext(){
		next = new JButton("��һ��");
		next.setBounds(170, 290, 70, 30);
//		add(next);
		next.addActionListener(treeListener);
		
	}
	void Sendwenben(){
		send = new JButton("ȷ��");
		send.setBounds(110, 290, 50, 30);
		add(send);
		SendWenben sendwenben = new SendWenben(wenben);
		sendwenben.setwin(win,this);
		send.addActionListener(sendwenben);
	}
	void addnumber(){
		number = new JTextField("100");
		number.setBounds(10,290,90,30);
		add(number);
	}
	void addinArea(){
		wenben = new JTextPane();
		wenben1 = new JScrollPane(wenben);
		wenben1.setBounds(210,10,400,270);
		add(wenben1);
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
		treeListener.setTree(tree);
		treeListener.setDanziFileName(danziFileName);
		treeListener.setWenzhangFileName(wenzhangFileName);
		treeListener.setWin(win);
		tree.addTreeSelectionListener(treeListener);
		tree1.setBounds(10,10,190,270);
		treeListener.setWenbenText(wenben);

		treeListener.setNumber(number);
		
		add(tree1);
	}
}
