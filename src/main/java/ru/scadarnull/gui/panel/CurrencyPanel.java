package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.CurrencyAddEditDialog;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.CurrencyTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Style;

public class CurrencyPanel extends RightPanel{


    public CurrencyPanel(MainFrame frame){
        super(frame, new CurrencyTableData(new FunctionsHandler(frame, new CurrencyAddEditDialog(frame))),
                "CURRENCIES", Style.ICON_PANEL_CURRENCIES,
                new FunctionsTooBar(new FunctionsHandler(frame, new CurrencyAddEditDialog(frame))));
    }
}
