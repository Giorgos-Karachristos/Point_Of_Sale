package entities;

public class Item {
    private String itemId;
    private String itemCode;
    private String name;
    private String description;
    private String price;
    private String cost;
    private String category;
    private String createdAt;
    private String color;
    private String isActive;

    public Item(String name, String color, String price, String itemId, String description) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.itemId = itemId;
        this.description = description;
    }

    public Item(String itemId, String itemCode, String name, String description, String price, String cost, String category, String createdAt, String color, String isActive) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cost = cost;
        this.category = category;
        this.createdAt = createdAt;
        this.color = color;
        this.isActive = isActive;
    }

    public Item(String itemId, String itemCode, String name) {
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getColor() {
        return color;
    }

    public String getIsActive() {
        return isActive;
    }
}
