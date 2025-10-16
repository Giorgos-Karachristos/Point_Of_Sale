package entities;

public class TransactionItem {
    private String transactionId;
    private String itemId;
    private String itemCode;
    private String name;
    private String oldQuantity;
    private String type;
    private String transactionQuantity;
    private String transactionDate;
    private String status;

    public TransactionItem(String transactionId, String itemId, String itemCode, String name, String oldQuantity, String type, String transactionQuantity, String transactionDate, String status) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.itemCode = itemCode;
        this.name = name;
        this.oldQuantity = oldQuantity;
        this.type = type;
        this.transactionQuantity = transactionQuantity;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
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

    public String getOldQuantity() {
        return oldQuantity;
    }

    public String getType() {
        return type;
    }

    public String getTransactionQuantity() {
        return transactionQuantity;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getStatus() {
        return status;
    }
}
