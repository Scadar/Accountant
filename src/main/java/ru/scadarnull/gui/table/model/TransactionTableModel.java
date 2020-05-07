package ru.scadarnull.gui.table.model;

import ru.scadarnull.model.Transaction;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.Format;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionTableModel extends MainTableModel {

    private static final int DATE = 0;
    private static final int ACCOUNT = 1;
    private static final int ARTICLE = 2;
    private static final int AMOUNT = 3;
    private static final int NOTICE = 4;

    private int count = -1;

    public TransactionTableModel(String[] columns) {
        super(SaveData.getInstance().getFilterTransactions(), columns);
    }

    public TransactionTableModel(String[] columns, int count) {
        super(SaveData.getInstance().getTransactionsOnCount(count), columns);
        this.count = count;
    }

    @Override
    protected void updateData() {
        if (count == -1) data = SaveData.getInstance().getFilterTransactions();
        else data = SaveData.getInstance().getTransactionsOnCount(count);
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) return null;
        Transaction transaction = (Transaction) data.get(row);
        return switch (column) {
            case DATE -> Format.date(transaction.getDate());
            case ACCOUNT -> transaction.getAccount().getTitle();
            case ARTICLE -> transaction.getArticle().getTitle();
            case AMOUNT -> transaction.getAmount();
            case NOTICE -> transaction.getNotice();
            default -> null;
        };
    }

}
