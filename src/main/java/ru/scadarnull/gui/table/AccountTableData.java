package ru.scadarnull.gui.table;

import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.model.AccountTableModel;
import ru.scadarnull.settings.Style;

import javax.swing.*;

public class AccountTableData extends TableData {

    private static String[] columns = new String[]{"TITLE", "AMOUNT"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_TITLE, Style.ICON_AMOUNT};

    public AccountTableData(FunctionsHandler handler) {
        super(new AccountTableModel(columns), handler, columns, icons);
    }
}
