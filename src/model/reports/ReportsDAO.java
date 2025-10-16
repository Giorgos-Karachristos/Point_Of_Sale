package model.reports;

import javax.swing.table.DefaultTableModel;

public interface ReportsDAO {
    String[] getSalesLabels();

    String[] getRefundsLabel();

    String[] getGrossSalesGraph();

    String[] getRefundsGraph();

    String[] getDiscountsGraph();

    String[] getNetSalesGraph();

    String[] getGrossProfitGraph();

    String[] getInventoryGraph();

    String[] getItemsGraph(DefaultTableModel tableModel);

}
