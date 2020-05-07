package ru.scadarnull.gui.table;

import ru.scadarnull.gui.table.model.AccountTableModel;
import ru.scadarnull.gui.table.model.TransactionTableModel;
import ru.scadarnull.gui.table.renderer.MainTableCellRenderer;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.awt.*;

public class AccountTableData extends TableData {

    private static String[] columns = new String[]{"TITLE", "AMOUNT"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_TITLE, Style.ICON_AMOUNT};

    public AccountTableData() {
        super(new AccountTableModel(columns), columns, icons);
    }
}
