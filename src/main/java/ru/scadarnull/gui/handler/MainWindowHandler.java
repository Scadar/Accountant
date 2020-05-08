package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.ConfirmDialog;
import ru.scadarnull.saveload.SaveData;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        if(SaveData.getInstance().isSaved()){
            System.exit(0);
        }else{
            int result = ConfirmDialog.show((MainFrame)e.getWindow(), "CONFIRM_EXIT_TEXT", "CONFIRM_EXIT_TITLE");
            if(result == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
}
