package ru.scadarnull.gui.table;

import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.model.TransactionTableModel;
import ru.scadarnull.gui.table.renderer.MainTableCellRenderer;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.awt.*;

public class TransactionTableData extends TableData {

    private static String[] columns = new String[]{"DATE", "ACCOUNT", "ARTICLE", "AMOUNT", "NOTICE"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_DATE, Style.ICON_ACCOUNT, Style.ICON_ARTICLE, Style.ICON_AMOUNT, Style.ICON_NOTICE};

    public TransactionTableData(FunctionsHandler handler) {
        super(new TransactionTableModel(columns), handler, columns, icons);
        init();
    }

    public TransactionTableData(FunctionsHandler handler, int count) {
        super(new TransactionTableModel(columns, count), handler, columns, icons);
        init();
    }



    @Override
    protected final void init() {
        getColumn(Text.get("AMOUNT")).setCellRenderer(new TableCellAmountRenderer());
    }

    private class TableCellAmountRenderer extends MainTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ((value.toString()).contains("-")) {
                renderer.setForeground(Style.COLOR_EXP);
            }
            else {
                renderer.setForeground(Style.COLOR_INCOME);
            }
            renderer.setFont(Style.FONT_TRANSACTIONS);
            return renderer;
        }

    }

}
