package ru.scadarnull.gui.toolbar;

import ru.scadarnull.gui.EnableEditDelete;
import ru.scadarnull.gui.MainButton;
import ru.scadarnull.gui.handler.Handler;
import ru.scadarnull.settings.HandlerCode;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

public final class FunctionsTooBar extends AbstractToolBar implements EnableEditDelete {

    private MainButton editButton;
    private MainButton deleteButton;

    public FunctionsTooBar(Handler handler) {
        super(Style.BORDER_FUNCTIONS_TOOLBAR, handler);
        init();
    }

    @Override
    protected void init() {
        addButton(Text.get("ADD"), Style.ICON_ADD, HandlerCode.ADD, false);
        editButton = addButton(Text.get("EDIT"), Style.ICON_EDIT, HandlerCode.EDIT, false);
        deleteButton = addButton(Text.get("DELETE"), Style.ICON_DELETE, HandlerCode.DELETE, false);
    }

    @Override
    public void setEnableEditDelete(boolean enable) {
        editButton.setEnabled(enable);
        deleteButton.setEnabled(enable);
    }
}
