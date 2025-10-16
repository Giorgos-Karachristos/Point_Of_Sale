package controller.manager;

import model.manager.ManagerDAO;
import model.manager.ManagerModel;

public class ManagerController {
    private final ManagerDAO managerDAO;

    public ManagerController() {
        this.managerDAO = new ManagerModel();
    }

    public String[] getAttributeNames(String table) {
        return managerDAO.getAttributeNames(table);
    }
}
