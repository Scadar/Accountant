package ru.scadarnull.gui.table;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.Refresh;
import ru.scadarnull.gui.table.model.MainTableModel;
import ru.scadarnull.gui.table.renderer.MainTableCellRenderer;
import ru.scadarnull.gui.table.renderer.TableHeaderIconRenderer;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.util.Arrays;

abstract public class TableData extends JTable implements Refresh {
    private final String[] columns;
    private final ImageIcon[] icons;


    protected TableData(MainTableModel model, String[] columns, ImageIcon[] icons) {
        super(model);
        this.icons = icons;
        this.columns = columns;

        getTableHeader().setFont(Style.FONT_TABLE_HEADER);
        setFont(Style.FONT_TABLE);

        setRowHeight(getRowHeight() + Style.TABLE_ROW_HEIGHT);
        setAutoCreateRowSorter(true);
        setPreferredScrollableViewportSize(Style.DIMENSION_TABLE_SHOW_SIZE);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        System.out.println(Arrays.toString(columns));
        System.out.println(Arrays.toString(icons));
        for(int i = 0; i < columns.length; ++i){
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        MainTableCellRenderer renderer = new MainTableCellRenderer();
        setDefaultRenderer(String.class, renderer);
    }



    @Override
    public void refresh() {

        int selectRow = getSelectedRow();
        ((MainTableModel)getModel()).refresh();
        /*for(int i = 0; i < columns.length; ++i){
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }*/
        if(selectRow != -1 && selectRow < getRowCount()){
            setRowSelectionInterval(selectRow, selectRow);
        }
        init();
    }

    protected void init(){}
}
