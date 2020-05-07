package ru.scadarnull.gui.dialog;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.settings.Text;

import javax.swing.*;

public class ConfirmDialog {

    public static int show(MainFrame frame, String text, String title){
        String[] options = {Text.get("YES"), Text.get("NO")};
        int result = JOptionPane.showOptionDialog(frame, text, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        return result;
    }
}
