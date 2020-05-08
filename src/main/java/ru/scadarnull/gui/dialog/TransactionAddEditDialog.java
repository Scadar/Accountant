package ru.scadarnull.gui.dialog;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import ru.scadarnull.exception.ModelException;
import ru.scadarnull.gui.MainDatePicker;
import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.model.*;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.Format;
import ru.scadarnull.settings.Style;

import javax.swing.*;
import java.util.Date;

public class TransactionAddEditDialog extends AddEditDialog{
    public TransactionAddEditDialog(MainFrame frame) {
        super(frame);
    }

    @Override
    protected void init() {
        components.put("LABEL_DATE", new MainDatePicker().getDatePicker());
        components.put("LABEL_ACCOUNT", new CommonComboBox(SaveData.getInstance().getAccountList().toArray()));
        components.put("LABEL_ARTICLES", new CommonComboBox(SaveData.getInstance().getArticleList().toArray()));
        components.put("LABEL_AMOUNT", new JTextField());
        components.put("LABEL_NOTICE", new JTextField());

        icons.put("LABEL_DATE", Style.ICON_DATE);
        icons.put("LABEL_ACCOUNT", Style.ICON_ACCOUNT);
        icons.put("LABEL_ARTICLES", Style.ICON_ARTICLE);
        icons.put("LABEL_AMOUNT", Style.ICON_AMOUNT);
        icons.put("LABEL_NOTICE", Style.ICON_NOTICE);

        values.put("LABEL_DATE", new Date());
        values.put("LABEL_AMOUNT", Format.amount(0));
    }

    @Override
    protected void setValues() {
        Transaction transaction = (Transaction)common;
        values.put("LABEL_DATE", transaction.getDate());
        values.put("LABEL_ACCOUNT", transaction.getAccount());
        values.put("LABEL_ARTICLES", transaction.getArticle());
        values.put("LABEL_AMOUNT", transaction.getAmount());
        values.put("LABEL_NOTICE", transaction.getNotice());
    }

    @Override
    public Common getCommonFromForm() throws ModelException {
        try{
            Account account = (Account) ((CommonComboBox) components.get("LABEL_ACCOUNT")).getSelectedItem();
            Article article = (Article) ((CommonComboBox) components.get("LABEL_ARTICLES")).getSelectedItem();
            String amount = ((JTextField) components.get("LABEL_AMOUNT")).getText();
            String notice = ((JTextField) components.get("LABEL_NOTICE")).getText();
            Date date = (Date) ((JDatePickerImpl) components.get("LABEL_DATE")).getModel().getValue();
            return new Transaction(account, article, Format.fromAmountToNumber(amount), notice,date);
        }catch (NumberFormatException ex){
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }

    }
}
