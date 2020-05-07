package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.table.ArticleTableData;
import ru.scadarnull.gui.table.CurrencyTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Style;

public class CurrencyPanel extends RightPanel{


    public CurrencyPanel(MainFrame frame){
        super(frame, new CurrencyTableData(), "CURRENCIES", Style.ICON_PANEL_CURRENCIES, new FunctionsTooBar());
    }
}
