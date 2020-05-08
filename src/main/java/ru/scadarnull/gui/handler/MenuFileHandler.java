package ru.scadarnull.gui.handler;

import org.xml.sax.SAXException;
import ru.scadarnull.gui.MainFileChooser;
import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.ConfirmDialog;
import ru.scadarnull.gui.dialog.ErrorDialog;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.HandlerCode;
import ru.scadarnull.settings.Settings;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MenuFileHandler extends Handler {

    private final MainFileChooser fileChooser;
    public MenuFileHandler(MainFrame frame) {
        super(frame);
        this.fileChooser = new MainFileChooser(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HandlerCode.MENU_FILE_NEW:{
                Settings.setFileSave(null);
                SaveData.getInstance().clear();
                break;
            }
            case HandlerCode.MENU_FILE_OPEN:{
                int result = fileChooser.open();
                if(result == JFileChooser.APPROVE_OPTION){
                    Settings.setFileSave(fileChooser.getSelectedFile());
                    SaveData.getInstance().clear();
                    SaveData.getInstance().load();
                }
                break;
            }
            case HandlerCode.MENU_FILE_SAVE:{
                if(Settings.getFileSave() == null){
                    int result = fileChooser.save();
                    if(result == JFileChooser.APPROVE_OPTION){
                        String path = fileChooser.getSelectedFile().getAbsolutePath();
                        String ext = path.substring(path.lastIndexOf(".") + 1, path.length());
                        if(ext.equals(Settings.SAVE_FILE_EXT)){
                            Settings.setFileSave(new File(path));
                        }else{
                            Settings.setFileSave(new File(path + "." + Settings.SAVE_FILE_EXT));
                        }
                    }
                }
                if(Settings.getFileSave() != null){
                    SaveData.getInstance().save();
                }
                break;
            }
            case HandlerCode.MENU_FILE_UPDATE_CURRENCIES:{
                try {
                    SaveData.getInstance().updateCurrencies();
                } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
                    ErrorDialog.show(frame, "ERROR_UPDATE_CURRENCIES");
                }
                break;
            }
            case HandlerCode.MENU_FILE_EXIT:{
                if(SaveData.getInstance().isSaved()){
                    System.exit(0);
                }else{
                    int result = ConfirmDialog.show(frame, "CONFIRM_EXIT_TEXT", "CONFIRM_EXIT_TITLE");
                    if(result == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
                }
                break;
            }
        }
        super.actionPerformed(e);
    }
}
