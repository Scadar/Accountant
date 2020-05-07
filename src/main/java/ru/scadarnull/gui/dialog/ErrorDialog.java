package ru.scadarnull.gui.dialog;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.settings.Text;

import javax.swing.*;

public class ErrorDialog {
    public static void show(MainFrame frame, String text){
        JOptionPane.showMessageDialog(frame, text, Text.get("ERROR"), JOptionPane.ERROR_MESSAGE);
    }
}
