package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class FilterHandler extends Handler {

    public FilterHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HandlerCode.LEFT:{
                SaveData.getInstance().getFilter().prev();
                break;
            }
            case HandlerCode.RIGHT:{
                SaveData.getInstance().getFilter().next();
                break;
            }
            case HandlerCode.STEP:{
                SaveData.getInstance().getFilter().nextPeriod();
                break;
            }
        }
        super.actionPerformed(e);
    }
}
