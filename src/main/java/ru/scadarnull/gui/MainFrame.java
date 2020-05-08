package ru.scadarnull.gui;

import ru.scadarnull.gui.menu.MainMenu;
import ru.scadarnull.gui.panel.*;
import ru.scadarnull.gui.toolbar.MainToolBar;
import ru.scadarnull.settings.Style;
import ru.scadarnull.settings.Text;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Refresh{

    private GridBagConstraints constraints;
    private final MainToolBar toolBar;
    private final MainMenu mb;
    private final LeftPanel leftPanel;
    private RightPanel rightPanel;

    public MainFrame() throws HeadlessException {
        super(Text.get("PROGRAM_NAME"));

        //////////////
        /*CurrencyAddEditDialog temp = new CurrencyAddEditDialog(this);
        temp.setCommon(SaveData.getInstance().getBaseCurrency());
        temp.showDialog();*/

        //////////////
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Style.ICON_MAIN.getImage());

        mb = new MainMenu(this);
        setJMenuBar(mb);

        setLayout(new GridBagLayout());

        this.constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;

        toolBar = new MainToolBar();
        add(toolBar, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;

        leftPanel = new LeftPanel(this);
        add(leftPanel, constraints);

        setRightPanel(new TransactionPanel(this));

        pack();
        setLocationRelativeTo(null);
    }

    private void setRightPanel(RightPanel panel) {
        if(rightPanel != null){
            remove(rightPanel);
        }
        constraints.gridy = 1;
        constraints.gridx = 1;
        rightPanel = panel;
        panel.setBorder(Style.BORDER_PANEL);
        add(rightPanel, constraints);
        pack();
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
        mb.refresh();
        leftPanel.refresh();
        rightPanel.refresh();
        pack();
    }

    public MainMenu getMenu(){
        return mb;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }
}
