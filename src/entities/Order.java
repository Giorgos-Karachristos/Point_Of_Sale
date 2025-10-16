package entities;

public class Order {
    private String orderID;
    private String name;
    private String subtotal;
    private String discount;
    private String tip;
    private String total;
    private String status;
    private String orderDate;
    private String type;

    public Order(String orderID, String status, String orderDate) {
        this.orderID = orderID;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Order(String orderID, String subtotal, String status, String type, String orderDate) {
        this.orderID = orderID;
        this.subtotal = subtotal;
        this.status = status;
        this.type = type;
        this.orderDate = orderDate;
    }

    public Order(String orderID, String name, String subtotal, String discount, String tip, String total, String orderDate, String status) {
        this.orderID = orderID;
        this.name = name;
        this.subtotal = subtotal;
        this.discount = discount;
        this.tip = tip;
        this.total = total;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getName() {
        return name;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public String getDiscount() {
        return discount;
    }

    public String getTip() {
        return tip;
    }

    public String getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getType() {
        return type;
    }
}