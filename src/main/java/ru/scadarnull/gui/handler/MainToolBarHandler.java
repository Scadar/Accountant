package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.settings.HandlerCode;

import java.awt.event.ActionEvent;

public class MainToolBarHandler extends MenuViewHandler {

    public MainToolBarHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HandlerCode.TOOLBAR_OVERVIEW:{
                showOverViewPanel();
                break;
            }
            case HandlerCode.TOOLBAR_ACCOUNTS:{
                showAccountPanel();
                break;
            }
            case HandlerCode.TOOLBAR_ARTICLES:{
                showArticlePanel();
                break;
            }
            case HandlerCode.TOOLBAR_CURRENCIES:{
                showCurrencyPanel();
                break;
            }
            case HandlerCode.TOOLBAR_TRANSACTIONS:{
                showTransactionPanel();
                break;
            }
            case HandlerCode.TOOLBAR_TRANSFERS:{
                showTransferPanel();
                break;
            }
            case HandlerCode.TOOLBAR_STATISTICS:{
                showStatisticPanel();
                break;
            }
        }
        super.actionPerformed(e);
    }

}
