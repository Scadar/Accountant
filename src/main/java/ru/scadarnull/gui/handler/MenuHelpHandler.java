package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.AboutDialog;
import ru.scadarnull.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class MenuHelpHandler extends Handler {

    public MenuHelpHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HandlerCode.MENU_HELP_ABOUT:{
                new AboutDialog().setVisible(true);
                break;
            }
        }
        super.actionPerformed(e);
    }


}
