package model.menu;

import entities.Category;
import entities.Item;

import java.util.List;

public interface MenuDAO {
    List<Category> getCategoryList();

    List<Item> getItemsList(String category);

    String insertOrder(String username, String subtotal, String type);

    String insertKitchen(int id, double quantity, double total);
}
