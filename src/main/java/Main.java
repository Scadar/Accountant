import org.xml.sax.SAXException;
import ru.scadarnull.exception.ModelException;
import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.model.*;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.Settings;
import ru.scadarnull.settings.Text;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ModelException, IOException, SAXException, ParserConfigurationException {
        init();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    private static void testMode() throws ModelException {
        Currency c1 = new Currency("Рубль", "RUB", 1, true, true);
        Currency c2 = new Currency("Доллар", "USD", 65, true, false);
        Currency c3 = new Currency("Евро", "EUR", 75, false, false);
        Currency c4 = new Currency("Гривна", "UAH", 3, false, false);

        Account ac1 = new Account("Кошель", c1, 10000);
        Account ac2 = new Account("Карта Visa", c1, 0);
        Account ac3 = new Account("Депозит в банке(РУБ)", c1, 100000);
        Account ac4 = new Account("Депозит в банке(ДОЛ)", c2, 0);

        Article ar1 = new Article("Продукты");
        Article ar2 = new Article("ЖКХ");
        Article ar3 = new Article("Зарплата");
        Article ar4 = new Article("Столовая");
        Article ar5 = new Article("Проценты по депозиту");

        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(c1);
        currencies.add(c2);
        currencies.add(c3);
        currencies.add(c4);

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(ac1);
        accounts.add(ac2);
        accounts.add(ac3);
        accounts.add(ac4);

        ArrayList<Article> articles = new ArrayList<>();
        articles.add(ar1);
        articles.add(ar2);
        articles.add(ar3);
        articles.add(ar4);
        articles.add(ar5);

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(ac2, ar3, 30000));
        transactions.add(new Transaction(ac2, ar1, -1500, "Продукты на выходные"));
        transactions.add(new Transaction(ac1, ar2, -4000, "За основую хату"));
        transactions.add(new Transaction(ac1, ar2, -2000, "За вторую хату"));
        transactions.add(new Transaction(ac3, ar5, 1000));
        transactions.add(new Transaction(ac2, ar3, 25000, new Date((new Date().getTime() - (long)86400000*30))));
        transactions.add(new Transaction(ac3, ar5, 2000, new Date((new Date().getTime() - (long)86400000*30))));

        for(int i = 0; i < 50; ++i){
            Article tempArticle;
            Account tempAccount;
            if(Math.random() < 0.5){
                tempArticle = ar1;
            }else{
                tempArticle = ar4;
            }
            if(Math.random() < 0.5){
                tempAccount = ac1;
            }else{
                tempAccount = ac2;
            }
            double tempAmount = Math.round(Math.random() * (-1000));

            Date tempDate = new Date((long)(new Date().getTime() - (long) 86400000 * 30* Math.random()));

            transactions.add(new Transaction(tempAccount, tempArticle, tempAmount, tempDate));
        }

        ArrayList<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(ac2, ac1, 25000, 25000));
        transfers.add(new Transfer(ac2, ac3, 3000, 3000));
        transfers.add(new Transfer(ac2, ac4, 6000, 90));

        for(Account account : accounts){
            account.setAmountFromTransactionsAndTransfer(transactions, transfers);
        }

        SaveData sd = SaveData.getInstance();
        sd.setArticleList(articles);
        sd.setAccountList(accounts);
        sd.setCurrencyList(currencies);
        sd.setTransferList(transfers);
        sd.setTransactionList(transactions);
        sd.save();
        //sd.load();
    }

    private static void init(){
        //Языковые константы
        Text.init();
        Settings.init();
    }
}
