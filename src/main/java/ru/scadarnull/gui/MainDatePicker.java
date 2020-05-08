package ru.scadarnull.gui;

import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import ru.scadarnull.settings.Style;

import javax.swing.*;
import java.util.Date;

public class MainDatePicker {
    private final JDatePickerImpl datePicker;

    public MainDatePicker() {
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);


        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        JButton button = (JButton)datePicker.getComponent(1);
        button.setIcon(Style.ICON_DATE);
        button.setText("");
        model.setValue(new Date());
    }

    public JDatePickerImpl getDatePicker(){
        return datePicker;
    }

    public void setValue(Date date){
        ((UtilDateModel)datePicker.getModel()).setValue(date);
    }
}
