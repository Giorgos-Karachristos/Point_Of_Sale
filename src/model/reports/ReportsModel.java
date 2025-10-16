package model.reports;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpRequestUtil;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportsModel implements ReportsDAO {

    private static final Logger LOGGER = Logger.getLogger(ReportsModel.class.getName());
    private static final String ENDPOINT = "manager/report/";

    public String[] getSalesLabels() {
        String[] labels = {"0", "0", "0", "0"};
        JSONArray jSONArray = fetchLabelData("getSalesLabels");
        if (jSONArray != null && !jSONArray.isEmpty()) {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            labels = new String[]{jSONObject.optString("total_subtotal", "0"), jSONObject.optString("total_discount", "0"), jSONObject.optString("net_subtotal", "0"), jSONObject.optString("total_net_profit", "0")};
        }
        return labels;
    }

    public String[] getRefundsLabel() {
        String[] label = {"0"};
        JSONArray jSONArray = fetchLabelData("getRefundsLabel");
        if (jSONArray != null && !jSONArray.isEmpty()) {
            label[0] = jSONArray.getJSONObject(0).optString("total_refunds", "0");
        }
        return label;
    }

    private JSONArray fetchLabelData(String endpoint) {
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                return new JSONArray(response);
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchLabelData for " + endpoint, e);
        }
        return null;
    }

    public String[] getGrossSalesGraph() {
        return fetchGraphData("getGrossSalesGraph", "DATE", "total_completed_subtotal");
    }

    public String[] getRefundsGraph() {
        return fetchGraphData("getRefundsGraph", "DATE", "total_refunt_total");
    }

    public String[] getDiscountsGraph() {
        return fetchGraphData("getDiscountsGraph", "DATE", "total_completed_discount");
    }

    public String[] getNetSalesGraph() {
        return fetchGraphData("getNetSalesGraph", "DATE", "total_net_revenue");
    }

    public String[] getGrossProfitGraph() {
        return fetchGraphData("getGrossProfitGraph", "DATE", "total_gross_profit");
    }

    public String[] getInventoryGraph() {
        return fetchGraphData("getInventoryGraph", "NAME", "QUANTITY");
    }

    public String[] getItemsGraph(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<String> graphData = new ArrayList<>();
        Object[] rowData = new Object[2];
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + "getItemsGraph", "GET", null);
            if (response != null) {
                JSONArray jSONArray = new JSONArray(response);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    rowData[0] = i + 1;
                    rowData[1] = jSONObject.getString("NAME");
                    graphData.add(jSONObject.optString("NAME"));
                    graphData.add(jSONObject.optString("order_count", "0"));
                    graphData.add(jSONObject.optString("profit_margin", "0"));
                    tableModel.addRow(rowData);
                }
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in getItemsGraph", e);
        }
        return get3ValuesAsString(graphData);
    }

    private String[] fetchGraphData(String endpoint, String key, String key1) {
        ArrayList<String> graphData = new ArrayList<>();
        try {
            String response = HttpRequestUtil.sendHttpRequest(ENDPOINT + endpoint, "GET", null);
            if (response != null) {
                JSONArray jSONArray = new JSONArray(response);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    graphData.add(jSONObject.getString(key));
                    graphData.add(jSONObject.optString(key1, "0"));
                }
            }
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, "Error parsing JSON in fetchGraphData for " + endpoint, e);
        }
        return get2ValuesAsString(graphData);
    }

    private String[] get2ValuesAsString(ArrayList<String> list) {
        String[] s = new String[2];

        StringBuilder yString = new StringBuilder();
        StringBuilder xString = new StringBuilder();

        for (int i = 0; i < list.size(); i += 2) {
            if (!xString.isEmpty()) {
                xString.append(",");
            }
            xString.append(list.get(i));

            if (!yString.isEmpty()) {
                yString.append(",");
            }
            yString.append(list.get(i + 1));
        }
        s[0] = xString.toString();
        s[1] = yString.toString();
        if (s[1].isEmpty()) {
            s[1] = "0";
        }
        return s;
    }

    private String[] get3ValuesAsString(ArrayList<String> list) {
        String[] s = new String[3];

        StringBuilder nameString = new StringBuilder();
        StringBuilder xString = new StringBuilder();
        StringBuilder yString = new StringBuilder();

        for (int i = 0; i < list.size(); i += 3) {
            if (!nameString.isEmpty()) {
                nameString.append(",");
            }
            nameString.append(list.get(i));

            if (!xString.isEmpty()) {
                xString.append(",");
            }
            xString.append(list.get(i + 1));

            if (!yString.isEmpty()) {
                yString.append(",");
            }
            yString.append(list.get(i + 2));
        }
        s[0] = nameString.toString();
        s[1] = xString.toString();
        s[2] = yString.toString();
        return s;
    }
}
