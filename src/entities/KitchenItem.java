package entities;

public class KitchenItem {
    private int itemID;
    private int quantity;
    private String status;
    private String name;

    public KitchenItem(String name, int quantity, int itemID, String status) {
        this.name = name;
        this.quantity = quantity;
        this.itemID = itemID;
        this.status = status;
    }

    public int getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
