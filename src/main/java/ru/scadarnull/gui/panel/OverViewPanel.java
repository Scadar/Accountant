package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.table.TransactionTableData;
import ru.scadarnull.settings.Settings;
import ru.scadarnull.settings.Style;

public class OverViewPanel extends RightPanel{


    public OverViewPanel(MainFrame frame){
        super(frame, new TransactionTableData(Settings.COUNT_OVERVIEW_ROWS), "LAST_TRANSACTIONS", Style.ICON_PANEL_OVERVIEW);
    }
}
