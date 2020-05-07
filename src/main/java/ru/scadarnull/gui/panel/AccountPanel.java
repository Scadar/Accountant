package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.table.AccountTableData;
import ru.scadarnull.gui.table.TransactionTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Settings;
import ru.scadarnull.settings.Style;

public class AccountPanel extends RightPanel{


    public AccountPanel(MainFrame frame){
        super(frame, new AccountTableData(), "ACCOUNTS", Style.ICON_PANEL_ACCOUNTS, new FunctionsTooBar());
    }
}
