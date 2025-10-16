package entities;

public class Category {
    private String name;
    private String tax;
    private String color;
    private String isActive;
    private String countItems;

    public Category(String name, String color, String tax) {
        this.name = name;
        this.color = color;
        this.tax = tax;
    }

    public Category(String name, String tax, String color, String isActive, String countItems) {
        this.name = name;
        this.tax = tax;
        this.color = color;
        this.isActive = isActive;
        this.countItems = countItems;
    }

    public String getName() {
        return name;
    }

    public String getTax() {
        return tax;
    }

    public String getColor() {
        return color;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getCountItems() {
        return countItems;
    }
}
