package ru.scadarnull.gui.table;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.Refresh;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.gui.handler.Handler;
import ru.scadarnull.gui.menu.TablePopUpMenu;
import ru.scadarnull.gui.table.model.MainTableModel;
import ru.scadarnull.gui.table.renderer.MainTableCellRenderer;
import ru.scadarnull.gui.table.renderer.TableHeaderIconRenderer;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

abstract public class TableData extends JTable implements Refresh {
    private final TablePopUpMenu popUp;
    private final String[] columns;
    private final ImageIcon[] icons;
    private final FunctionsHandler handler;


    protected TableData(MainTableModel model, FunctionsHandler handler, String[] columns, ImageIcon[] icons) {
        super(model);
        this.handler = handler;
        this.popUp = new TablePopUpMenu();
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

        addMouseListener(handler);
        addKeyListener(handler);

        for(int i = 0; i < columns.length; ++i){
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        MainTableCellRenderer renderer = new MainTableCellRenderer();
        setDefaultRenderer(String.class, renderer);
        setComponentPopupMenu(popUp);
    }

    @Override
    public JPopupMenu getComponentPopupMenu(){
        Point p = getMousePosition();
        if(p != null){
            int row = rowAtPoint(p);
            if(isRowSelected(row)){
                return super.getComponentPopupMenu();
            }else{
                return null;
            }
        }

        return super.getComponentPopupMenu();
    }


    @Override
    public void refresh() {

        int selectRow = getSelectedRow();
        ((MainTableModel)getModel()).refresh();
        for(int i = 0; i < columns.length; ++i){
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        if(selectRow != -1 && selectRow < getRowCount()){
            setRowSelectionInterval(selectRow, selectRow);
            requestFocus();
        }
        init();
    }

    protected void init(){}

    public FunctionsHandler getFunctionsHandler(){
        return handler;
    }
}
