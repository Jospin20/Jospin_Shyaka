package Advanced_Online_Shopping_System;

class ElectronicsItem extends ShoppingItem {
    private int warrantyPeriod; // in months
    private boolean isRegistered;

    public ElectronicsItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, int warrantyPeriod) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.warrantyPeriod = warrantyPeriod;
        this.isRegistered = false;
    }

    @Override
    public void updateStock(int quantity) {
        this.stockAvailable += quantity;
        System.out.println("Stock updated for " + itemName + ". New quantity: " + stockAvailable);
    }

    @Override
    public boolean addToCart(Customer customer, int quantity) {
        if (quantity > stockAvailable) {
            System.out.println("Error: Not enough stock available for " + itemName);
            return false;
        }
        customer.getShoppingCart().addItem(this, quantity);
        return true;
    }

    @Override
    public String generateInvoice() {
        return String.format("Electronics Item: %s\nDescription: %s\nPrice: $%.2f\nWarranty: %d months\nRegistered: %s",
                itemName, itemDescription, price, warrantyPeriod, isRegistered ? "Yes" : "No");
    }

    @Override
    public boolean validateItem() {
        if (!validateCommonFields()) return false;
        if (warrantyPeriod < 0 || warrantyPeriod > 36) {
            System.out.println("Error: Warranty period must be between 0-36 months for " + itemName);
            return false;
        }
        return true;
    }

    public void registerProduct() {
        this.isRegistered = true;
        System.out.println(itemName + " has been registered for warranty.");
    }

    public int getWarrantyPeriod() { return warrantyPeriod; }
    public boolean isRegistered() { return isRegistered; }
}