package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainButton;
import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.handler.FilterHandler;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.Format;
import ru.scadarnull.settings.HandlerCode;
import ru.scadarnull.settings.Style;

import java.awt.*;

public class FilterPanel extends AbstractPanel{

    public FilterPanel(MainFrame frame) {
        super(frame);
        init();
    }

    @Override
    protected void init() {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        setLayout(layout);
        MainButton left = new MainButton(Style.ICON_LEFT, new FilterHandler(frame), HandlerCode.LEFT);
        MainButton step = null;
        try {
            step = new MainButton(Format.getTitleFilter(SaveData.getInstance().getFilter()), new FilterHandler(frame), HandlerCode.STEP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainButton right = new MainButton(Style.ICON_RIGHT, new FilterHandler(frame), HandlerCode.RIGHT);

        setBorder(Style.BORDER_FILTER_PANEl);

        assert step != null;
        step.setFont(Style.FONT_BUTTON_FILTER);
        step.setPreferredSize(new Dimension(Style.WIDTH_FILTER_BUTTON, left.getPreferredSize().height));

        add(left);
        add(step);
        add(right);
    }
}
