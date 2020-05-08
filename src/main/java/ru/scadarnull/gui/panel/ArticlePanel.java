package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.ArticleAddEditDialog;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.ArticleTableData;
import ru.scadarnull.gui.toolbar.FunctionsTooBar;
import ru.scadarnull.settings.Style;

public class ArticlePanel extends RightPanel{


    public ArticlePanel(MainFrame frame){
        super(frame, new ArticleTableData(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))),
                "ARTICLES", Style.ICON_PANEL_ARTICLES,
                new FunctionsTooBar(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))));
    }
}
