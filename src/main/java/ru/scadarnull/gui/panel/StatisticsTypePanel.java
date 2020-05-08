package ru.scadarnull.gui.panel;

import ru.scadarnull.gui.MainButton;
import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.handler.ChartHandler;
import ru.scadarnull.settings.HandlerCode;
import ru.scadarnull.settings.Text;

public class StatisticsTypePanel extends AbstractPanel{
    private final String title;
    public StatisticsTypePanel(MainFrame frame, String title) {
        super(frame);
        this.title = Text.get(title);
        init();
    }

    @Override
    protected void init() {
        MainButton type = new MainButton(title, new ChartHandler(frame), HandlerCode.TYPE);
        add(type);
    }
}
