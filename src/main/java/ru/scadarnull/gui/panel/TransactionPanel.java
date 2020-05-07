package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.table.TransactionTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Settings;
import ru.scadarnull.settings.Style;

public class TransactionPanel extends RightPanel{


    public TransactionPanel(MainFrame frame){
        super(frame, new TransactionTableData(), "TRANSACTIONS", Style.ICON_PANEL_TRANSACTIONS, new FunctionsTooBar());
    }
}
