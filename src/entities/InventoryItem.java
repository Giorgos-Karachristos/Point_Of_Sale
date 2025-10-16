package entities;

public class InventoryItem {
    private String itemId;
    private String itemCode;
    private String name;
    private String location;
    private String quantity;
    private String minQuantity;
    private String maxQuantity;
    private String lastUpdated;

    public InventoryItem(String itemId, String itemCode, String name, String location, String quantity, String minQuantity, String maxQuantity, String lastUpdated) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.name = name;
        this.location = location;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.lastUpdated = lastUpdated;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public String getMaxQuantity() {
        return maxQuantity;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
}
