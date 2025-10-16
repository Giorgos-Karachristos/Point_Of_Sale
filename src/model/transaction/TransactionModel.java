package model.transaction;

import entities.TransactionItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionModel implements TransactionDAO {

    private static final Logger LOGGER = Logger.getLogger(TransactionModel.class.getName());
    private static final String ENDPOINT = "manager/transaction";

    @Override
    public void getTransactionTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        JSONArray transactions = fetchTransactionData("Table");
        if (transactions != null) {
            for (int i = 0; i < transactions.length(); i++) {
                try {
                    JSONObject transaction = transactions.getJSONObject(i);
                    Object[] rowData = new Object[9];
                    rowData[0] = transaction.getString("TRANSACTION_ID");
                    rowData[1] = transaction.getString("ITEM_ID");
                    rowData[2] = transaction.getString("ITEM_CODE");
                    rowData[3] = transaction.getString("NAME");
                    rowData[4] = transaction.getString("OLD_QUANTITY");
                    rowData[5] = transaction.getString("TYPE");
                    rowData[6] = transaction.getString("TRANSACTION_QUANTITY");
                    rowData[7] = transaction.getString("TRANSACTION_DATE");
                    rowData[8] = transaction.getString("STATUS");
                    tableModel.addRow(rowData);
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing transaction data for table", e);
                }
            }
        }
    }

    @Override
    public List<TransactionItem> getTransactionID(DefaultComboBoxModel<String> boxModel) {
        boxModel.removeAllElements();
        List<TransactionItem> rowData = new ArrayList<>();
        boxModel.addElement("");
        JSONArray transactions = fetchTransactionData("ID");
        if (transactions != null) {
            for (int i = 0; i < transactions.length(); i++) {
                try {
                    JSONObject transaction = transactions.getJSONObject(i);
                    boxModel.addElement(transaction.getString("TRANSACTION_ID"));
                    rowData.add(new TransactionItem(transaction.getString("TRANSACTION_ID"), transaction.getString("ITEM_ID"), transaction.getString("ITEM_CODE"), transaction.getString("NAME"), transaction.getString("OLD_QUANTITY"), transaction.getString("TYPE"), transaction.getString("TRANSACTION_QUANTITY"), transaction.getString("TRANSACTION_DATE"), transaction.getString("STATUS")));
                } catch (JSONException e) {
                    LOGGER.log(Level.WARNING, "Error parsing transaction data for list", e);
                }
            }
        }
        return rowData;
    }

    private JSONArray fetchTransactionData(String variable) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + "?variable=" + variable, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchTransactionData", e);
        }
        return null;
    }

    @Override
    public String insertTransaction(int p_item_id, int p_old_quantity, String p_type, int p_transaction_quantity, int p_new_quantity) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("TRANSACTION_ID", p_item_id);
        jsonInput.put("OLD_QUANTITY", p_old_quantity);
        jsonInput.put("TYPE", p_type);
        jsonInput.put("TRANSACTION_QUANTITY", p_transaction_quantity);
        jsonInput.put("NEW_QUANTITY", p_new_quantity);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "POST", jsonInput);
    }

    @Override
    public String deleteInventory(int id) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("TRANSACTION_ID", id);

        return HttpRequestUtil.sendHttpRequest(ENDPOINT, "DELETE", jsonInput);
    }
}
