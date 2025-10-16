package controller.restaurant;

import model.restaurant.RestaurantDAO;
import model.restaurant.RestaurantModel;

public class RestaurantController {
    private final RestaurantDAO restaurantDAO;

    public RestaurantController() {
        this.restaurantDAO = new RestaurantModel();
    }

    public String[] getRestaurant() {
        return restaurantDAO.getRestaurant();
    }

    public String updateRestaurant(String name, String address, String phone, int tables) {
        return restaurantDAO.updateRestaurant(name, address, phone, tables);
    }

    public String getRestaurantName() {
        return restaurantDAO.getRestaurantName();
    }
}
