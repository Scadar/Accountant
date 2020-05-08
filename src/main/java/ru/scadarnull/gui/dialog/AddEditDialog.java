package ru.scadarnull.gui.dialog;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import ru.scadarnull.exception.ModelException;
import ru.scadarnull.gui.MainButton;
import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.handler.AddEditDialogHandler;
import ru.scadarnull.model.Common;
import ru.scadarnull.settings.HandlerCode;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

abstract public class AddEditDialog extends JDialog {

    protected Map<String, JComponent> components = new LinkedHashMap<>();
    protected Map<String, ImageIcon> icons = new LinkedHashMap<>();
    protected Map<String, Object> values = new LinkedHashMap<>();

    protected Common common;
    private MainFrame frame;

    public AddEditDialog(MainFrame frame){
        super(frame, Text.get("ADD"), true);
        this.frame = frame;
        addWindowListener(new AddEditDialogHandler(frame, this));
        setResizable(false);
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public final void showDialog(){
        setDialog();
        setVisible(true);
    }

    public final void closeDialog(){
        setVisible(false);
        this.common = null;
        components.clear();
        icons.clear();
        values.clear();
        dispose();
    }

    public boolean isAdd(){
        return common == null;
    }

    abstract protected void init();

    abstract protected void setValues();

    public abstract Common getCommonFromForm() throws ModelException;

    private void setDialog() {
        init();
        if(isAdd()){
            setTitle(Text.get("ADD"));
            setIconImage(Style.ICON_ADD.getImage());
        }else{
            setValues();
            setTitle(Text.get("EDIT"));
            setIconImage(Style.ICON_EDIT.getImage());
        }
        getContentPane().removeAll();
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        ((JPanel)getContentPane()).setBorder(Style.BORDER_DIALOG);
        for(Map.Entry<String, JComponent> entry : components.entrySet()){
            String key = entry.getKey();
            JLabel label = new JLabel(Text.get(key));
            label.setIcon(icons.get(key));
            label.setFont(Style.FONT_DIALOG_LABEL);

            JComponent component = entry.getValue();
            if(component instanceof JTextField){
                component.setPreferredSize(Style.DIMENSION_DIALOG_TEXTFIELD_SIZE);
                if(values.containsKey(key)){
                    ((JTextField)component).setText("" + values.get(key));
                }
            }else if(component instanceof JComboBox){
                if(values.containsKey(key)){
                    ((JComboBox)component).setSelectedItem(values.get(key));
                }
            }else if(component instanceof JDatePickerImpl){
                if(values.containsKey(key)){
                    ((UtilDateModel)((JDatePickerImpl)component).getModel()).setValue((Date)values.get(key));
                }
            }
            component.addKeyListener(new AddEditDialogHandler(frame, this));
            component.setAlignmentX(JComponent.LEFT_ALIGNMENT);
            add(label);
            add(Box.createVerticalStrut(Style.PADDING_DIALOG));
            add(component);
            add(Box.createVerticalStrut(Style.PADDING_DIALOG));
        }
        MainButton ok = new MainButton(Text.get("ADD"), Style.ICON_OK, new AddEditDialogHandler(frame, this), HandlerCode.ADD);
        if(!isAdd()){
            ok.setActionCommand(HandlerCode.EDIT);
            ok.setText(Text.get("EDIT"));
        }

        MainButton cancel = new MainButton(Text.get("CANCEL"), Style.ICON_CANCEL, new AddEditDialogHandler(frame, this), HandlerCode.CANCEL);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BorderLayout());
        panelButtons.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panelButtons.add(ok, BorderLayout.WEST);
        panelButtons.add(Box.createRigidArea(Style.DIMENSION_DIALOG_PADDING_BUTTON), BorderLayout.CENTER);
        panelButtons.add(cancel, BorderLayout.EAST);

        add(panelButtons);
        pack();
        setLocationRelativeTo(null);
    }

    protected class CommonComboBox extends JComboBox{
        public CommonComboBox(Object[] objects){
            super(objects);
            setRenderer(new DefaultListCellRenderer(){
                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    DefaultListCellRenderer renderer = (DefaultListCellRenderer)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    Common c = (Common) value;
                    if(c != null)
                        renderer.setText(c.getValueForComboBox());
                    return renderer;
                }
            });
        }
    }
}
