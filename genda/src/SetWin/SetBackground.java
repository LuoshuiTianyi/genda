package SetWin;
import genda1.*;
import genda1.JTextPaneChange;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
public class SetBackground implements ActionListener {
	Frame win;
	Window win1;
	Color tempColor;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand()=="���ֿ򱳾���ɫ"){
			tempColor = JColorChooser.showDialog(win, "��ɫ��", Color.white);
			if(tempColor!=null)
				Window.dazi.setBackground(tempColor);
		}
		else if(e.getActionCommand()=="�ı��򱳾���ɫ"){
			tempColor = JColorChooser.showDialog(win, "��ɫ��", Color.white);
			if(tempColor!=null)
				Window.wenben.setBackground(tempColor);
		}
		else if(e.getActionCommand()=="�������ɫ"){
			Window.rightcolor = JColorChooser.showDialog(win, "��ɫ��", Color.gray);
			if(Window.rightcolor!=null)
				JTextPaneChange.createStyle("��",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,"΢���ź�",Window.rightcolor);
		}
		else if(e.getActionCommand()=="�������ɫ"){
			Window.mistakecolor = JColorChooser.showDialog(win, "��ɫ��", Color.red);
			if(Window.mistakecolor!=null)
				JTextPaneChange.createStyle("��",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.mistakecolor,"΢���ź�",Window.mistakecolor);
		}
		else if(e.getActionCommand()=="���������ɫ"){
			tempColor = JColorChooser.showDialog(win, "��ɫ��", Color.red);
			if(tempColor!=null)
				win1.getContentPane().setBackground(tempColor);
		}
		else if(e.getActionCommand()=="ȫ�����ɫ"){
			Window.qmacicolor = JColorChooser.showDialog(win, "��ɫ��",new Color(128,138,135));
			if(Window.qmacicolor!=null){
				JTextPaneChange.createStyle("�̴�",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.qmacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("��",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.qmacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("��б",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.qmacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("�̴�б",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.qmacicolor,"΢���ź�",Window.mistakecolor);//GRAY
			}	
		}
		else if(e.getActionCommand()=="�������ɫ"){
			Window.emacicolor = JColorChooser.showDialog(win, "��ɫ��", Color.ORANGE);
			if(Window.emacicolor!=null){
				JTextPaneChange.createStyle("�۴�",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.emacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("��",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.emacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("��б",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.emacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("�۴�б",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.emacicolor,"΢���ź�",Window.mistakecolor);//GRAY
			}
				
		}
		else if(e.getActionCommand()=="�������ɫ"){
			Window.smacicolor = JColorChooser.showDialog(win, "��ɫ��",Color.BLUE);
			if(Window.smacicolor!=null){
				JTextPaneChange.createStyle("����",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.smacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("��",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.smacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("��б",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.smacicolor,"΢���ź�",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("����б",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.smacicolor,"΢���ź�",Window.mistakecolor);//GRAY
			}
		}
	}
	public void setFrame(Frame win){
		this.win = win;
	}
	public void setWin(Window win1){
		this.win1 = win1;
	}
	
}