package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.TransactionAddEditDialog;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.TransactionTableData;
import ru.scadarnull.settings.Settings;
import ru.scadarnull.settings.Style;

public class OverViewPanel extends RightPanel{


    public OverViewPanel(MainFrame frame){
        super(frame,
                new TransactionTableData(new FunctionsHandler(frame, new TransactionAddEditDialog(frame)), Settings.COUNT_OVERVIEW_ROWS),
                "LAST_TRANSACTIONS",
                Style.ICON_PANEL_OVERVIEW);
    }
}
