package ru.scadarnull.gui.table;

import ru.scadarnull.gui.table.model.TransactionTableModel;
import ru.scadarnull.gui.table.model.TransferTableModel;
import ru.scadarnull.gui.table.renderer.MainTableCellRenderer;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.awt.*;

public class TransferTableData extends TableData {

    private static String[] columns = new String[]{"DATE", "FROM_ACCOUNT", "TO_ACCOUNT", "FROM_AMOUNT","TO_AMOUNT", "NOTICE"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_DATE, Style.ICON_ACCOUNT, Style.ICON_ACCOUNT, Style.ICON_AMOUNT, Style.ICON_AMOUNT, Style.ICON_NOTICE};

    public TransferTableData() {
        super(new TransferTableModel(columns), columns, icons);
        init();
    }

    @Override
    protected final void init() {
        getColumn(Text.get("FROM_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(Style.COLOR_EXP));
        getColumn(Text.get("TO_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(Style.COLOR_INCOME));
    }

    @Override
    public void refresh(){
        super.refresh();
        init();
    }

    private static class TableCellAmountRenderer extends MainTableCellRenderer {

        private final Color color;

        public TableCellAmountRenderer(Color color) {
            this.color = color;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            renderer.setForeground(color);
            renderer.setFont(Style.FONT_TRANSACTIONS);

            return renderer;
        }

    }

}
