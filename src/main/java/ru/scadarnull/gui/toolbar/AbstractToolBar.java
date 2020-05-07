package ru.scadarnull.gui.toolbar;

import ru.scadarnull.gui.MainButton;
import ru.scadarnull.gui.Refresh;

import javax.swing.*;
import java.awt.font.ImageGraphicAttribute;

abstract public class AbstractToolBar extends JPanel implements Refresh {
    public AbstractToolBar() {
    }

    abstract protected void init();

    protected MainButton addButton(String title, ImageIcon icon, String action, boolean topIcon){
        MainButton button = new MainButton(title, icon, null, action);
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
