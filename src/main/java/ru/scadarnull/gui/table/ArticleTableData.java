package ru.scadarnull.gui.table;

import ru.scadarnull.gui.table.model.AccountTableModel;
import ru.scadarnull.gui.table.model.ArticleTableModel;
import ru.scadarnull.settings.Style;

import javax.swing.*;

public class ArticleTableData extends TableData {

    private static String[] columns = new String[]{"TITLE"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_TITLE};

    public ArticleTableData() {
        super(new ArticleTableModel(columns), columns, icons);
    }
}
