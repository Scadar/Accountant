package ru.scadarnull.saveload;

import org.xml.sax.SAXException;
import ru.scadarnull.exception.ModelException;
import ru.scadarnull.model.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.spi.CurrencyNameProvider;

public class SaveData {
    private static SaveData instance;

    private List<Article> articleList = new ArrayList<>();
    private List<Currency> currencyList = new ArrayList<>();
    private List<Account> accountList = new ArrayList<>();
    private List<Transaction> transactionList = new ArrayList<>();
    private List<Transfer> transferList = new ArrayList<>();

    private Common oldCommon;
    private boolean isSave = true;
    private final Filter filter;

    private SaveData(){
        load();
        this.filter = new Filter();
    }

    public void load() {
        SaveLoad.load(this);
        sort();
        for(Account account : accountList){
            account.setAmountFromTransactionsAndTransfer(transactionList, transferList);
        }
    }

    public boolean isSaved() {
        return isSave;
    }

    private void sort() {
        this.articleList.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
        this.accountList.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
        this.transactionList.sort((a,b) -> (int)a.getDate().compareTo(b.getDate()));
        this.transferList.sort((a,b) -> (int)a.getDate().compareTo(b.getDate()));
        this.currencyList.sort((a,b) -> {
            if(a.isBase()){
                return -1;
            }
            if(b.isBase()){
                return 1;
            }
            if(a.isOn() ^ b.isOn()){
                if(b.isOn()){
                    return 1;
                }else{
                    return -1;
                }
            }
            return a.getTitle().compareToIgnoreCase(b.getTitle());
        });
    }

    public void save(){
        SaveLoad.save(this);
        isSave = true;
    }

    public Filter getFilter() {
        return filter;
    }

    public static SaveData getInstance(){
        if(instance == null){
            instance = new SaveData();
        }
        return instance;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

    public Currency getBaseCurrency() {
        for(Currency currency : currencyList){
            if(currency.isBase()){
                return  currency;
            }
        }
        return new Currency();
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void setTransferList(List<Transfer> transferList) {
        this.transferList = transferList;
    }

    public List<Currency> getEnableCurrencies(){
        List<Currency> result = new ArrayList<>();
        for(Currency currency : currencyList){
            if(currency.isOn()){
                result.add(currency);
            }
        }
        return result;
    }

    public List<Transaction> getFilterTransactions(){
        List<Transaction> result = new ArrayList<>();
        for(Transaction t : transactionList){
            if(filter.check(t.getDate())){
                result.add(t);
            }
        }
        return result;
    }

    public List<Transfer> getFilterTransfers(){
        List<Transfer> result = new ArrayList<>();
        for(Transfer t : transferList){
            if(filter.check(t.getDate())){
                result.add(t);
            }
        }
        return result;
    }

    public List<Transaction> getTransactionsOnCount(int count){
        return new ArrayList<>(transactionList.subList(0, Math.min(count, transactionList.size())));
    }

    public Common getOldCommon() {
        return oldCommon;
    }

    public void add(Common c) throws ModelException {
        List ref = getRef(c);
        if(ref.contains(c)){
            throw new ModelException(ModelException.IS_EXISTS);
        }
        ref.add(c);
        c.postAdd(this);
        sort();
        isSave = false;
    }

    //Исправить эту дичь!
    private List getRef(Common c) {
        if(c instanceof Account){
            return accountList;
        }else if(c instanceof Article){
            return articleList;
        }else if(c instanceof Currency){
            return currencyList;
        }else if(c instanceof Transaction){
            return transactionList;
        }else if(c instanceof Transfer){
            return transferList;
        }
        throw new RuntimeException("Неправильный класс");
    }

    public void edit(Common oldC, Common newC) throws ModelException {
        List ref = getRef(oldC);
        if(ref.contains(newC) && oldC != ref.get(ref.indexOf(newC))){
            throw new ModelException(ModelException.IS_EXISTS);
        }
        ref.set(ref.indexOf(oldC), newC);
        oldCommon = oldC;
        newC.postEdit(this);
        sort();
        isSave = false;
    }

    public void remove(Common c){
        getRef(c).remove(c);
        c.postDelete(this);
        isSave = false;
    }

    @Override
    public String toString() {
        return "SaveData{" +
                "articleList=" + articleList +
                ", currencyList=" + currencyList +
                ", accountList=" + accountList +
                ", transactionList=" + transactionList +
                ", transferList=" + transferList +
                '}';
    }

    public void updateCurrencies() throws ParserConfigurationException, SAXException, IOException {
        Map<String, Double> rates = RateCurrency.getRate(getBaseCurrency());

        for(Currency c : currencyList){
                c.setRate(rates.get(c.getCode()));
        }
        for(Account a: accountList){
                a.getCurrency().setRate(rates.get(a.getCurrency().getCode()));
        }
    }
}

