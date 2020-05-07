package ru.scadarnull.model;

import ru.scadarnull.exception.ModelException;
import ru.scadarnull.saveload.SaveData;

import java.util.List;
import java.util.Objects;

public class Account extends Common{
    private String title;
    private Currency currency;
    private double startAmount;
    private double amount;

    public Account(){}

    public Account(String имя_счета, Currency currency) {}

    public Account(String title, Currency currency, double startAmount) throws ModelException {
        if(title.length() == 0){
            throw new ModelException(ModelException.TITLE_EMPTY);
        }
        if(currency == null){
            throw new ModelException(ModelException.CURRENCY_EMPTY);
        }
        this.title = title;
        this.currency = currency;
        this.startAmount = startAmount;
    }

    public double getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(title, account.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String getValueForComboBox() {
        return title;
    }

    public void setAmountFromTransactionsAndTransfer(List<Transaction> transactionList, List<Transfer> transferList){
        this.amount = startAmount;
        for(Transaction transaction : transactionList){
            if(transaction.getAccount().equals(this)){
                this.amount += transaction.getAmount();
            }
        }
        for(Transfer transfer : transferList){
            if(transfer.getFromAccount().equals(this)){
                this.amount -= transfer.getFromAmount();
            }
            if(transfer.getToAccount().equals(this)){
                this.amount += transfer.getToAmount();
            }
        }
    }

    @Override
    public void postAdd(SaveData sd) {
        setAmountFromTransactionsAndTransfer(sd.getTransactionList(), sd.getTransferList());
    }

    @Override
    public void postEdit(SaveData sd) {
        for(Transaction t : sd.getTransactionList()){
            if(t.getAccount().equals(sd.getOldCommon())){
                t.setAccount(this);
            }
        }

        for(Transfer t : sd.getTransferList()){
            if(t.getFromAccount().equals(sd.getOldCommon())){
                t.setFromAccount(this);
            }
            if(t.getToAccount().equals(sd.getOldCommon())){
                t.setToAccount(this);
            }
        }
        setAmountFromTransactionsAndTransfer(sd.getTransactionList(), sd.getTransferList());
    }

}