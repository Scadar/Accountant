package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.TransactionAddEditDialog;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.TransactionTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Style;

import javax.swing.*;

public class TransactionPanel extends RightPanel{


    public TransactionPanel(MainFrame frame){
        super(frame, new TransactionTableData(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))),
                "TRANSACTIONS", Style.ICON_PANEL_TRANSACTIONS,
                new JPanel[]{new FunctionsTooBar(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))), new FilterPanel(frame)});
    }
}
