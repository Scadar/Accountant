package ru.scadarnull.gui.menu;

import ru.scadarnull.gui.Refresh;
import ru.scadarnull.gui.handler.FunctionsHandler;
import ru.scadarnull.settings.HandlerCode;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;

public class TablePopUpMenu extends JPopupMenu implements Refresh {
    private final FunctionsHandler handler;

    public TablePopUpMenu(FunctionsHandler handler){
        super();
        this.handler = handler;
        init();
    }

    private void init() {
        JMenuItem editItem = new JMenuItem(Text.get("EDIT"));
        JMenuItem deleteItem = new JMenuItem(Text.get("DELETE"));

        editItem.setActionCommand(HandlerCode.EDIT);
        deleteItem.setActionCommand(HandlerCode.DELETE);

        editItem.addActionListener(handler);
        deleteItem.addActionListener(handler);

        editItem.setIcon(Style.ICON_MENU_POP_UP_EDIT);
        deleteItem.setIcon(Style.ICON_MENU_POP_UP_DELETE);

        add(editItem);
        add(deleteItem);

    }

    @Override
    public void refresh() {

    }
}
