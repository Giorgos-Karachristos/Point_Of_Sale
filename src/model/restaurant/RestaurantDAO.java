package model.restaurant;

public interface RestaurantDAO {
    String[] getRestaurant();

    String updateRestaurant(String name, String address, String phone, int tables);

    String getRestaurantName();
}
