package Tips;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class ChooseFile implements ActionListener{
	public static String cizufilename = "�����ļ�/���뷨����/������ʾ���.txt";
	@Override
	public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub 
		cizufilename = getFileName();
		if(cizufilename==null)
			return;
		Window.tipschange = new Tips(Window.tips);
	}
	public static String getFileName(){
		JFileChooser jfc;
		File file;
		jfc=new JFileChooser();  
	    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	    jfc.showDialog(new JLabel(), "ѡ��");  
	    file=jfc.getSelectedFile();
	    if(file!=null){
		    if(file.isDirectory()){
		    	System.out.println("�ļ���:"+file.getAbsolutePath());  
		    }else if(file.isFile()){  
		        System.out.println("�ļ�:"+file.getAbsolutePath());  
		    }
		    String str = file.getAbsolutePath();
		    str = str.replace("\\", "/");
		    System.out.println("ѡ������:"+str); 
			return str;
	    }
	    return null;
	}
}
