package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.TransferAddEditDialog;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.TransferTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Style;

import javax.swing.*;

public class TransferPanel extends RightPanel{


    public TransferPanel(MainFrame frame){
        super(frame, new TransferTableData(new FunctionsHandler(frame, new TransferAddEditDialog(frame))),
                "TRANSFERS", Style.ICON_PANEL_TRANSFERS,
                new JPanel[]{new FunctionsTooBar(new FunctionsHandler(frame, new TransferAddEditDialog(frame))), new FilterPanel(frame)});
    }
}
