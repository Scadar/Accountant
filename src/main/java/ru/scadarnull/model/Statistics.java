package ru.scadarnull.model;

import ru.scadarnull.saveload.SaveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    public static double getBalanceCurrency(Currency currency){
        SaveData sd = SaveData.getInstance();
        double amount = 0;
        for(Account account : sd.getAccountList()){
            if(currency.equals(account.getCurrency())){
                amount += account.getAmount();
            }
        }
        return amount;
    }
    public static double getBalance(Currency currency){
        SaveData sd = SaveData.getInstance();
        double amount = 0;
        for(Account account : sd.getAccountList()){
            amount += account.getAmount() * account.getCurrency().getRateByCurrency(currency);
        }
        return amount;
    }

    public static Map<String ,Double> getDataForChartIncomeArticles(){
        return getDataForChartOnArticles(true);
    }

    public static Map<String ,Double> getDataForChartExpArticles(){
        return getDataForChartOnArticles(false);
    }

    private static Map<String , Double> getDataForChartOnArticles(boolean income){
        List<Transaction> transactions = SaveData.getInstance().getFilterTransactions();
         Map<String ,Double> data = new HashMap<>();
         for(Transaction t : transactions){
             if((income && t.getAmount() > 0) || (!income && t.getAmount() < 0)){
                 String key = t.getArticle().getTitle();
                 double sum = 0;
                 double amount = t.getAmount();
                 if(!income) {
                     amount *= -1;
                 }
                 if(data.containsKey(key)){
                     sum= data.get(key);
                 }
                 sum += amount * t.getAccount().getCurrency().getRateByCurrency(SaveData.getInstance().getBaseCurrency());
                 data.put(key, round(sum));
             }
         }
         return data;
    }

    private static double round(double x){
        return (double) (Math.round(x * 100) / 100);
    }
}
