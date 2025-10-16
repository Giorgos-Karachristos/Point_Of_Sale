package controller.menu;

import entities.Category;
import entities.Item;
import model.menu.MenuDAO;
import model.menu.MenuModel;

import java.util.List;


public class MenuController {
    private final MenuDAO model;

    public MenuController() {
        this.model = new MenuModel();
    }

    public List<Category> getCategoryList() {
        return model.getCategoryList();
    }

    public List<Item> getItemsList(String category) {
        return model.getItemsList(category);
    }

    public String insertOrder(String username, String subtotal, String type) {
        return model.insertOrder(username, subtotal, type);
    }

    public String insertKitchen(int id, double quantity, double total) {
        return model.insertKitchen(id, quantity, total);
    }
}
