package model.transaction;

import entities.TransactionItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface TransactionDAO {
     void getTransactionTable(DefaultTableModel tableModel);

     List<TransactionItem> getTransactionID(DefaultComboBoxModel<String> boxModel);

     String insertTransaction(int p_item_id, int p_old_quantity, String p_type, int p_transaction_quantity, int p_new_quantity);

     String deleteInventory(int id);
}
