package SetWin;
import genda1.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SetFontSize implements ActionListener {
  JTextField FontSize;
  int fontSize;
  SetFontSize(JTextField FontSize) {
    this.FontSize = FontSize;
  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    try {
      Window.fontSize = Integer.parseInt(FontSize.getText());
      fontSize = Window.fontSize;
      JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
          Color.BLACK, Window.family, Window.mistakecolor);
      JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
          Color.BLACK, Window.family, Window.rightcolor);
      JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
          Color.BLACK, Window.family, Window.mistakecolor);

      JTextPaneChange.createStyle("����", JTextPaneChange.styledDoc, fontSize, 1, 0, 0,
          Window.smacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize, 0, 0, 0,
          Window.smacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("��б", JTextPaneChange.styledDoc, fontSize, 0, 1, 0,
          Window.smacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("����б", JTextPaneChange.styledDoc, fontSize, 1, 1, 0,
          Window.smacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("�۴�", JTextPaneChange.styledDoc, fontSize, 1, 0, 0,
          Window.emacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize, 0, 0, 0,
          Window.emacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("��б", JTextPaneChange.styledDoc, fontSize, 0, 1, 0,
          Window.emacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("�۴�б", JTextPaneChange.styledDoc, fontSize, 1, 1, 0,
          Window.emacicolor, Window.family, Window.rightcolor); // GRAY

      JTextPaneChange.createStyle("�̴�", JTextPaneChange.styledDoc, fontSize, 1, 0, 0,
          Window.qmacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize, 0, 0, 0,
          Window.qmacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("�̴�б", JTextPaneChange.styledDoc, fontSize, 1, 1, 0,
          Window.qmacicolor, Window.family, Window.rightcolor); // GRAY
      JTextPaneChange.createStyle("��б", JTextPaneChange.styledDoc, fontSize, 0, 1, 0,
          Window.qmacicolor, Window.family, Window.mistakecolor); // GRAY

      Window.dazi.setFont(new Font(Window.family, 0, fontSize));
      Window.accept.setFont(new Font(Window.family, 0, fontSize));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(new JTextArea(), "�����ֺ�ֻ��Ϊ����");
    }
  }
}