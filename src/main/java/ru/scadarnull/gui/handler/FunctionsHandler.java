package ru.scadarnull.gui.handler;

import ru.scadarnull.gui.MainFrame;
import ru.scadarnull.gui.dialog.AddEditDialog;
import ru.scadarnull.gui.dialog.ConfirmDialog;
import ru.scadarnull.gui.table.TableData;
import ru.scadarnull.gui.table.model.MainTableModel;
import ru.scadarnull.model.Common;
import ru.scadarnull.saveload.SaveData;
import ru.scadarnull.settings.HandlerCode;

import javax.swing.*;
import java.awt.event.*;

public class FunctionsHandler extends Handler implements MouseListener, KeyListener {

    private final AddEditDialog dialog;

    public FunctionsHandler(MainFrame frame, AddEditDialog dialog) {
        super(frame);
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HandlerCode.ADD:{
                add();
                break;
            }
            case HandlerCode.EDIT:{
                edit();
                break;
            }
            case HandlerCode.DELETE:{
                delete();
                break;
            }
        }
        super.actionPerformed(e);
    }

    public void add() {
        showAddEditDialog(null);
    }

    public void edit() {
        showAddEditDialog(getSelectedCommon());
    }

    private Common getSelectedCommon(){
        TableData td = frame.getRightPanel().getTableData();
        Common common = ((MainTableModel) td.getModel()).getCommonByRow(td.getSelectedRow());
        return common;
    }

    private void showAddEditDialog(Common common){
        dialog.setCommon(common);
        dialog.showDialog();
    }

    public void delete() {
        Common common = getSelectedCommon();
        if(common != null){
            int result = ConfirmDialog.show(frame, "CONFIRM_DELETE_TEXT", "CONFIRM_DELETE_TITLE");
            if(result == JOptionPane.YES_OPTION){
                SaveData.getInstance().remove(common);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            delete();
        }
        frame.refresh();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof TableData){
            if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
                showAddEditDialog(getSelectedCommon());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() instanceof TableData){
            if(e.getButton() == MouseEvent.BUTTON3){
                TableData td = frame.getRightPanel().getTableData();
                int row = td.rowAtPoint(e.getPoint());
                td.setRowSelectionInterval(row, row);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
