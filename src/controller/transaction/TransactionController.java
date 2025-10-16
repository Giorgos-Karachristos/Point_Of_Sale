package controller.transaction;

import entities.TransactionItem;
import model.transaction.TransactionDAO;
import model.transaction.TransactionModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TransactionController {
    private final TransactionDAO transactionDAO;

    public TransactionController() {
        this.transactionDAO = new TransactionModel();
    }

    public void getTransactionTable(DefaultTableModel tableModel) {
        transactionDAO.getTransactionTable(tableModel);
    }

    public List<TransactionItem> getTransactionID(DefaultComboBoxModel<String> boxModel) {
        return transactionDAO.getTransactionID(boxModel);
    }

    public String insertTransaction(int p_item_id, int p_old_quantity, String p_type, int p_transaction_quantity, int p_new_quantity) {
        return transactionDAO.insertTransaction(p_item_id, p_old_quantity, p_type, p_transaction_quantity, p_new_quantity);
    }

    public String deleteInventory(int id) {
        return transactionDAO.deleteInventory(id);
    }
}
