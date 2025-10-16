package controller.reports;

import model.reports.ReportsDAO;
import model.reports.ReportsModel;

import javax.swing.table.DefaultTableModel;

public class ReportsController {
    private final ReportsDAO reportsDAO;

    public ReportsController() {
        this.reportsDAO = new ReportsModel();
    }

    public String[] getSalesLabels() {
        return reportsDAO.getSalesLabels();
    }

    public String[] getRefundsLabel() {
        return reportsDAO.getRefundsLabel();
    }

    public String[] getGrossSalesGraph() {
        return reportsDAO.getGrossSalesGraph();
    }

    public String[] getRefundsGraph() {
        return reportsDAO.getRefundsGraph();
    }

    public String[] getDiscountsGraph() {
        return reportsDAO.getDiscountsGraph();
    }

    public String[] getNetSalesGraph() {
        return reportsDAO.getNetSalesGraph();
    }

    public String[] getGrossProfitGraph() {
        return reportsDAO.getGrossProfitGraph();
    }

    public String[] getInventoryGraph() {
        return reportsDAO.getInventoryGraph();
    }

    public String[] getItemsGraph(DefaultTableModel tableModel) {
        return reportsDAO.getItemsGraph(tableModel);
    }
}
