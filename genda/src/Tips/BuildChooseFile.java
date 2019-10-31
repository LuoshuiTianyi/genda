package Tips;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class BuildChooseFile implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    jfc.showDialog(new JLabel(), "ѡ��");
    File file = jfc.getSelectedFile();
    if (file.isDirectory()) {
      System.out.println("�ļ���:" + file.getAbsolutePath());
    } else if (file.isFile()) {
      System.out.println("�ļ�:" + file.getAbsolutePath());
    }
    String str = file.getAbsolutePath();
    System.out.println(jfc.getSelectedFile().getName());
    Changetxt change = new Changetxt(str);
    change.start();
  }
}