package ru.scadarnull.gui.table.model;

import ru.scadarnull.model.Account;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.Format;

public class AccountTableModel extends MainTableModel {

    private static final int TITLE = 0;
    private static final int AMOUNT = 1;

    public AccountTableModel(String[] columns) {
        super(SaveData.getInstance().getAccountList(), columns);
    }


    @Override
    protected void updateData() {
        data = SaveData.getInstance().getAccountList();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) return null;
        Account account = (Account) data.get(row);
        return switch (column) {
            case TITLE -> account.getTitle();
            case AMOUNT -> Format.amount(account.getAmount(), account.getCurrency());
            default -> null;
        };
    }

}
