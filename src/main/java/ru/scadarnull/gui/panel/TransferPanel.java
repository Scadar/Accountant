package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.table.TransactionTableData;
import ru.scadarnull.gui.table.TransferTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Style;

public class TransferPanel extends RightPanel{


    public TransferPanel(MainFrame frame){
        super(frame, new TransferTableData(), "TRANSFERS", Style.ICON_PANEL_TRANSFERS, new FunctionsTooBar());
    }
}
