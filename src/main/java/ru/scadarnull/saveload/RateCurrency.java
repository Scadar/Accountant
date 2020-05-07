package ru.scadarnull.saveload;

import org.decimal4j.util.DoubleRounder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.scadarnull.model.Currency;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RateCurrency {

    public static Map<String, Double> getRate(Currency base) throws IOException, ParserConfigurationException, SAXException {
        Map<String, NodeList> result = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateFormat.format(new Date());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().parse(new URL(url).openStream());

        NodeList nl = doc.getElementsByTagName("Valute");

        for(int i = 0; i < nl.getLength(); ++i){
            Node c = nl.item(i);
            NodeList nlChild = c.getChildNodes();

            for (int j = 0; j < nlChild.getLength(); j++) {
                if(nlChild.item(j).getNodeName().equalsIgnoreCase("CharCode")){
                    result.put(nlChild.item(j).getTextContent(), nlChild);
                }
            }
        }
        Map<String, Double> rates = new HashMap<>();

        for(Map.Entry<String, NodeList> m : result.entrySet()){
            NodeList temp = m.getValue();
            double value = 0;
            double nominal = 0;
            for (int i = 0; i < temp.getLength(); i++) {
                if(temp.item(i).getNodeName().equals("Value")){
                    value = Double.parseDouble(temp.item(i).getTextContent().replace(",", "."));
                }else if(temp.item(i).getNodeName().equals("Nominal")){
                    nominal = Integer.parseInt(temp.item(i).getTextContent().replace(",", "."));
                }
            }
            double amount = value / nominal;
            rates.put(m.getKey(), DoubleRounder.round(amount, 4));
        }
        rates.put("RUB", (double)1);
        double div = rates.get(base.getCode());
        for (Map.Entry<String, Double> entry: rates.entrySet()) {
            entry.setValue(entry.getValue() / div);
        }
        return rates;
    }
}
