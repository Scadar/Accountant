package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.panel.StatisticsPanel;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class ChartHandler extends Handler {

    public ChartHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HandlerCode.TYPE:{
                ((StatisticsPanel)frame.getRightPanel()).nextType();
                break;
            }
        }
        super.actionPerformed(e);
    }
}
