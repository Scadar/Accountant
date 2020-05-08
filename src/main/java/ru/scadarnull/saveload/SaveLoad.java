package ru.scadarnull.saveload;

import ru.scadarnull.settings.Settings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class SaveLoad {

    public static void save(SaveData sd){
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Wrapper wrapper = new Wrapper();
            wrapper.setAccounts(sd.getAccountList());
            wrapper.setArticles(sd.getArticleList());
            wrapper.setCurrencies(sd.getCurrencyList());
            wrapper.setTransactions(sd.getTransactionList());
            wrapper.setTransfers(sd.getTransferList());

            m.marshal(wrapper, Settings.getFileSave());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void load(SaveData sd){
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            Wrapper wp = (Wrapper) um.unmarshal(Settings.getFileSave());
            sd.setAccountList(wp.getAccounts());
            sd.setArticleList(wp.getArticles());
            sd.setCurrencyList(wp.getCurrencies());
            sd.setTransactionList(wp.getTransactions());
            sd.setTransferList(wp.getTransfers());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
