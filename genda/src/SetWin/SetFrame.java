package SetWin;

import genda1.*;
import genda1.Window;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.*;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import Tips.ChooseFile;
public class SetFrame extends JFrame {
	JRadioButton setjindutiaoON,setjindutiaoOFF;
	ButtonGroup setjindutiaogroup;
	SetFrameJinduListener setframeJindulistener = new SetFrameJinduListener();
	SetFrameKeyboardRecordListener keyRecordListener = new SetFrameKeyboardRecordListener();
	SetFramechangeListener tiplistener = new SetFramechangeListener();
	SetFrameSplitnum setframesplitenum;
	GendaListener gendalistener;
	JLabel qianshui;
	public static JTextField Splitenum,FontSize;
	JRadioButton qianshuiON;
	JRadioButton qianshuiOFF;
	JRadioButton changetxtON;
	JRadioButton changetxtOFF;
	
	ChooseFile chosefilelistener = new ChooseFile();
	
	ButtonGroup setqianshui ;
	ButtonGroup setchange;
	SetFrameQianshuiListener setframeQianshuilistener = new SetFrameQianshuiListener();
	Window win;
	JLabel setjindutiao = new JLabel("���ö�̬������",JLabel.LEFT);
	JLabel setchangetxt = new JLabel("������ʾ",JLabel.LEFT);
	public void SetFrame1(){
		setTitle("����");
		setBounds(100,100,700,400);
		setLayout(null);

		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		com.sun.awt.AWTUtilities.setWindowOpaque(this,true);//ȡ��͸��
		
		addJinduON_Off();
		addKeyboardRecord();
		addKeymistakeRecord();
		addQianshu();
		addChangetxt();
		SetBackground setbackgroundListener = new SetBackground();
		setbackgroundListener.setFrame(this);
		setbackgroundListener.setWin(win);
		JButton RightcolorSet = new JButton("�������ɫ");
		RightcolorSet.setBounds(10,180,100,30);
		add(RightcolorSet);
		
		JButton MistakecolorSet = new JButton("�������ɫ");
		MistakecolorSet.setBounds(RightcolorSet.getX()+RightcolorSet.getWidth()+10,RightcolorSet.getY(),100,30);
		add(MistakecolorSet);
		
		JButton WenbenBackgroundSet = new JButton("�ı��򱳾���ɫ");
		WenbenBackgroundSet.setBounds(MistakecolorSet.getX()+MistakecolorSet.getWidth()+10,RightcolorSet.getY(),140,30);
		add(WenbenBackgroundSet);
		
		JButton qmccolorset = new JButton("ȫ�����ɫ");
		qmccolorset.setBounds(WenbenBackgroundSet.getX()+WenbenBackgroundSet.getWidth()+10,RightcolorSet.getY(),140,30);
		add(qmccolorset);
		
		JButton BackgroundSet = new JButton("���������ɫ");
		BackgroundSet.setBounds(RightcolorSet.getX(),RightcolorSet.getY()+RightcolorSet.getHeight()+10,140,30);
		add(BackgroundSet);
		
		JButton DaziBackgroundSet = new JButton("���ֿ򱳾���ɫ");
		DaziBackgroundSet.setBounds(BackgroundSet.getX()+BackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,140,30);
		add(DaziBackgroundSet);
		
		JButton emccolorset = new JButton("�������ɫ");
		emccolorset.setBounds(DaziBackgroundSet.getX()+DaziBackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		add(emccolorset);
		
		JButton smccolorset = new JButton("�������ɫ");
		smccolorset.setBounds(emccolorset.getX()+emccolorset.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		add(smccolorset);
		
		FontSize = new JTextField("�����ֺ�:����");
		FontSize.setBounds(BackgroundSet.getX(),BackgroundSet.getY()+BackgroundSet.getHeight()+10,90,30);
		add(FontSize);
		
		JButton changeFontSize = new JButton("����");
		changeFontSize.setBounds(FontSize.getX()+FontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		add(changeFontSize);
		
		Splitenum = new JTextField("��ҳ����:����");
		Splitenum.setBounds(changeFontSize.getX()+changeFontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,90,30);
		add(Splitenum);
		
		JButton splitebutton = new JButton("����");
		splitebutton.setBounds(Splitenum.getX()+Splitenum.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		add(splitebutton);
		
		JButton mabiao = new JButton("ȫ���ѡ��");
		mabiao.setBounds(splitebutton.getX()+splitebutton.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,120,30);
		add(mabiao);
		
		SetFontSize setfontlistener = new SetFontSize(FontSize);
		setframesplitenum = new SetFrameSplitnum(Splitenum);
		
		WenbenBackgroundSet.addActionListener(setbackgroundListener);
		RightcolorSet.addActionListener(setbackgroundListener);
		MistakecolorSet.addActionListener(setbackgroundListener);
		DaziBackgroundSet.addActionListener(setbackgroundListener);
		BackgroundSet.addActionListener(setbackgroundListener);
		qmccolorset.addActionListener(setbackgroundListener);
		emccolorset.addActionListener(setbackgroundListener);
		smccolorset.addActionListener(setbackgroundListener);
		changeFontSize.addActionListener(setfontlistener);
		splitebutton.addActionListener(setframesplitenum);
		mabiao.addActionListener(chosefilelistener);
		
	}
	public void setGendaListener(GendaListener t){
		gendalistener = t;
	}
	public void setWin(Window win){
		this.win = win;
	}
	void addJinduON_Off(){
		setjindutiaoON = new JRadioButton("��");
		setjindutiaoOFF = new JRadioButton("��");
		setjindutiaogroup = new ButtonGroup();
		setjindutiaogroup.add(setjindutiaoON);
		setjindutiaogroup.add(setjindutiaoOFF);
		setjindutiaoON.setBounds(110,10,50,20);
		setjindutiaoOFF.setBounds(160,10,50,20);
		setjindutiao.setBounds(10,10,90,20);
		
		add(setjindutiao);
		add(setjindutiaoON);
		add(setjindutiaoOFF);
		setframeJindulistener.setButton1(setjindutiaoON);
		setjindutiaoON.addActionListener(setframeJindulistener);
		setjindutiaoOFF.addActionListener(setframeJindulistener);
	}
	void addChangetxt(){
		
		changetxtON = new JRadioButton("��");
		changetxtOFF = new JRadioButton("��");
		setchange = new ButtonGroup();
		setchange.add(changetxtON);
		setchange.add(changetxtOFF);
		setchangetxt.setBounds(410,10,50,20);
		changetxtON.setBounds(470,10,50,20);
		changetxtOFF.setBounds(520,10,50,20);
		add(setchangetxt);
		add(changetxtON);
		add(changetxtOFF);
		tiplistener.setButton1(changetxtON);
		changetxtON.addActionListener(tiplistener);
		changetxtOFF.addActionListener(tiplistener);
	}
	void addQianshu(){
		qianshui = new JLabel("Ǳˮ����",JLabel.LEFT);
		qianshuiON = new JRadioButton("��");
		qianshuiOFF = new JRadioButton("��");
		setqianshui = new ButtonGroup();
		setqianshui.add(qianshuiON);
		setqianshui.add(qianshuiOFF);
		qianshui.setBounds(220,10,50,20);
		qianshuiON.setBounds(280,10,50,20);
		qianshuiOFF.setBounds(330,10,50,20);
		add(qianshui);
		add(qianshuiON);
		add(qianshuiOFF);
		setframeQianshuilistener.setButton1(qianshuiON);
		qianshuiON.addActionListener(setframeQianshuilistener);
		qianshuiOFF.addActionListener(setframeQianshuilistener);
	}
	void addKeyboardRecord(){
		JButton KeyboardRecord = new JButton("��ȡ���θ��������¼");
		JTextArea Keyboarddisplay = new JTextArea("��ȡ�ı��ڴ���ʾ");
		JScrollPane Keyboarddisplay1 = new JScrollPane(Keyboarddisplay);
		
		
		keyRecordListener.setKeyboarddisplay(Keyboarddisplay);
		
		keyRecordListener.setGendaListener(gendalistener);
		
		KeyboardRecord.addActionListener(keyRecordListener);
		Keyboarddisplay.setLineWrap(true);
		Keyboarddisplay.setFont(new Font("΢���ź�",0,20));
		KeyboardRecord.setBounds(10,40,250,20);
		Keyboarddisplay1.setBounds(10,70,510,100);
		add(KeyboardRecord);
		add(Keyboarddisplay1);
	}
	void addKeymistakeRecord(){
		JButton KeymistakeRecord = new JButton("��ȡ���θ�����ּ�¼");
//		JTextArea Keymistakedisplay = new JTextArea("��ȡ�ı��ڴ���ʾ");
//		JScrollPane Keymistakedisplay1 = new JScrollPane(Keymistakedisplay);
//		keyRecordListener.setKeymistakedisplay( Keymistakedisplay);
		
		KeymistakeRecord.addActionListener(keyRecordListener);
//		Keymistakedisplay.setLineWrap(true);
//		Keymistakedisplay.setFont(new Font("΢���ź�",0,20));
		KeymistakeRecord.setBounds(270,40,250,20);
//		Keymistakedisplay1.setBounds(10,210,500,100);
		add(KeymistakeRecord);
//		add(Keymistakedisplay1);
	}
}
