package Advanced_Online_Shopping_System;

abstract class ShoppingItem {
    protected String itemId;
    protected String itemName;
    protected String itemDescription;
    protected double price;
    protected int stockAvailable;

    public ShoppingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    // Abstract methods
    public abstract void updateStock(int quantity);
    public abstract  boolean addToCart(Customer customer, int quantity);
    public abstract String generateInvoice();
    public abstract boolean validateItem();

    // Common methods
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public String getItemDescription() { return itemDescription; }
    public double getPrice() { return price; }
    public int getStockAvailable() { return stockAvailable; }

    protected boolean validateCommonFields() {
        if (stockAvailable < 0) {
            System.out.println("Error: Stock quantity cannot be negative for " + itemName);
            return false;
        }
        if (price <= 0) {
            System.out.println("Error: Price must be positive for " + itemName);
            return false;
        }
        if (itemName == null || itemName.trim().isEmpty()) {
            System.out.println("Error: Item name cannot be empty");
            return false;
        }
        return true;
    }
}
