package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.AccountAddEditDialog;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.AccountTableData;
import ru.scadarnull.gui.table.TransactionTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Settings;
import ru.scadarnull.settings.Style;

public class AccountPanel extends RightPanel{


    public AccountPanel(MainFrame frame){
        super(frame, new AccountTableData(new FunctionsHandler(frame, new AccountAddEditDialog(frame))),
                "ACCOUNTS", Style.ICON_PANEL_ACCOUNTS,
                new FunctionsTooBar(new FunctionsHandler(frame, new AccountAddEditDialog(frame))));
    }
}
