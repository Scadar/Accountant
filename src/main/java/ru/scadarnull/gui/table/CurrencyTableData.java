package ru.scadarnull.gui.table;

import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.table.model.ArticleTableModel;
import ru.scadarnull.gui.table.model.CurrencyTableModel;
import ru.scadarnull.gui.table.renderer.MainTableCellRenderer;
import ru.scadarnull.model.Currency;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.awt.*;

public class CurrencyTableData extends TableData {

    private static String[] columns = new String[]{"TITLE", "CODE", "RATE", "ON", "BASE"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_TITLE, Style.ICON_CODE,Style.ICON_RATE,Style.ICON_ON,Style.ICON_BASE};

    public CurrencyTableData(FunctionsHandler handler) {
        super(new CurrencyTableModel(columns), handler, columns, icons);
        init();
    }

    @Override
    protected void init() {
        for(String column : columns){
            getColumn(Text.get(column)).setCellRenderer(new TableCellOnOffRenderer());
        }
    }

    private static class TableCellOnOffRenderer extends MainTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (((Currency)((CurrencyTableModel)table.getModel()).getObjectByRow(row)).isOn()){
                renderer.setForeground(Style.COLOR_ON);
            }else{
                renderer.setForeground(Style.COLOR_OFF);
            }

            return renderer;
        }
    }
}
