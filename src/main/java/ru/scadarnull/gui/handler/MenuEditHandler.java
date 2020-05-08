package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class MenuEditHandler extends Handler {

    public MenuEditHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FunctionsHandler handler = frame.getRightPanel().getTableData().getFunctionsHandler();
        switch (e.getActionCommand()){
            case HandlerCode.MENU_EDIT_ADD:{
                handler.add();
                break;
            }
            case HandlerCode.MENU_EDIT_EDIT:{
                handler.edit();
                break;
            }
            case HandlerCode.MENU_EDIT_DELETE:{
                handler.delete();
                break;
            }
        }
        super.actionPerformed(e);
    }
}
