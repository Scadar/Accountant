package ru.scadarnull.gui.toolbar;

import ru.scadarnull.gui.MainButton;
import ru.scadarnull.gui.Refresh;
import ru.scadarnull.gui.handler.Handler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

abstract public class AbstractToolBar extends JPanel implements Refresh {
    private final Handler handler;

    public AbstractToolBar(EmptyBorder border, Handler handler) {
        super();
        setBorder(border);
        this.handler = handler;
    }

    abstract protected void init();

    protected MainButton addButton(String title, ImageIcon icon, String action, boolean topIcon){
        MainButton button = new MainButton(title, icon, handler, action);
        if(topIcon){
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
        }else{
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setVerticalTextPosition(SwingConstants.CENTER);
        }
        add(button);
        return button;
    }

    @Override
    public void refresh() {
        removeAll();
        init();
    }
}
