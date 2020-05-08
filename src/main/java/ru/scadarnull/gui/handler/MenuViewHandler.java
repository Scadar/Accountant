package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.panel.*;
import ru.scadarnull.settings.HandlerCode;

import java.awt.event.ActionEvent;
import java.awt.geom.Arc2D;

public class MenuViewHandler extends Handler {

    public MenuViewHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HandlerCode.MENU_VIEW_OVERVIEW:{
                showOverViewPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_ACCOUNTS:{
                showAccountPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_ARTICLES:{
                showArticlePanel();
                break;
            }
            case HandlerCode.MENU_VIEW_CURRENCIES:{
                showCurrencyPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_TRANSACTIONS:{
                showTransactionPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_TRANSFERS:{
                showTransferPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_STATISTICS:{
                showStatisticPanel();
                break;
            }
        }
        super.actionPerformed(e);
    }

    protected void showOverViewPanel(){
        frame.setRightPanel(new OverViewPanel(frame));
    }

    protected void showAccountPanel(){
        frame.setRightPanel(new AccountPanel(frame));
    }

    protected void showArticlePanel(){
        frame.setRightPanel(new ArticlePanel(frame));
    }

    protected void showCurrencyPanel(){
        frame.setRightPanel(new CurrencyPanel(frame));
    }

    protected void showTransactionPanel(){
        frame.setRightPanel(new TransactionPanel(frame));
    }

    protected void showTransferPanel(){
        frame.setRightPanel(new TransferPanel(frame));
    }

    protected void showStatisticPanel(){
        frame.setRightPanel(new StatisticsPanel(frame));
    }
}
