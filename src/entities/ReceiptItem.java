package entities;

public class ReceiptItem {
    private String name;
    private int quantity;
    private String tax;
    private String totalPrice;

    public ReceiptItem(String name, int quantity, String tax, String totalPrice) {
        this.name = name;
        this.quantity = quantity;
        this.tax = tax;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTax() {
        return tax;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
