package ru.scadarnull.settings;

import ru.scadarnull.model.Currency;
import ru.scadarnull.model.Filter;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {

    public static String amount(double money) {
        return String.format(Settings.FORMAT_AMOUNT, money);
    }

    public static String amount(double money, Currency currency) {
        return amount(money) + " " + currency.getCode();
    }

    public static String rate(double rate) {
        return String.format(Settings.FORMAT_RATE, rate);
    }

    public static String rate(double rate, Currency currency) {
        return rate(rate) + " " + currency.getCode();
    }

    public static String date(Date date){
        return dateFormat(date, Settings.FORMAT_DATE);
    }

    public static String dateMonth(Date date){
        String result = dateFormat(date, Settings.FORMAT_DATE_MONTH);
        result = result.substring(0,1).toUpperCase() + result.substring(1);
        return result;
    }

    public static String dateYear(Date date){
        return dateFormat(date, Settings.FORMAT_DATE_YEAR);
    }

    private static String dateFormat(Date date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, new MainDateFormatSymbols());
        return simpleDateFormat.format(date);
    }

    public static double fromAmountToNumber(String amount) throws NumberFormatException{
        amount = amount.replace(",", ".");
        return Double.parseDouble(amount);
    }

    public static String yesNo(boolean yes){
        if(yes){
            return Text.get("YES");
        }else{
            return Text.get("NO");
        }
    }

    public static String getTitleFilter(Filter filter) throws Exception {
        Date time = filter.getTo();
        switch (filter.getStep()){
            case Filter.STEP_DAY -> { return date(time); }
            case Filter.STEP_MONTH -> { return dateMonth(time); }
            case Filter.STEP_YEAR -> { return dateYear(time); }
        }
        throw new Exception("Задан неправильный формат даты " + time.toString());
    }

    private static class MainDateFormatSymbols extends DateFormatSymbols {
        @Override
        public String[] getMonths() {
            return Text.getMonths();
        }
    }
}
