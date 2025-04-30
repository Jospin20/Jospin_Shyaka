package Advanced_Online_Shopping_System;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class GroceriesItem extends ShoppingItem {
    private LocalDate expirationDate;
    private double bulkDiscount;
    private int bulkThreshold;

    public GroceriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String expirationDate) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.expirationDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.bulkDiscount = 0.1; // 10% discount
        this.bulkThreshold = 5;
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
        if (isExpired()) {
            System.out.println("Error: " + itemName + " has expired and cannot be purchased");
            return false;
        }
        customer.getShoppingCart().addItem(this, quantity);
        return true;
    }

    @Override
    public String generateInvoice() {
        return String.format("Grocery Item: %s\nDescription: %s\nPrice: $%.2f\nExpiration: %s\nBulk Discount: %s",
                itemName, itemDescription, getEffectivePrice(), expirationDate,
                quantityEligibleForDiscount() ? "Yes (10% for 5+ items)" : "No");
    }

    @Override
    public boolean validateItem() {
        if (!validateCommonFields()) return false;
        if (isExpired()) {
            System.out.println("Error: " + itemName + " has already expired!");
            return false;
        }
        return true;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    public boolean quantityEligibleForDiscount() {
        return stockAvailable >= bulkThreshold;
    }

    public double getEffectivePrice() {
        return quantityEligibleForDiscount() ? price * (1 - bulkDiscount) : price;
    }

    public LocalDate getExpirationDate() { return expirationDate; }
}
