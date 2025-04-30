package Advanced_Online_Shopping_System;

import java.util.HashMap;
import java.util.Map;

class ClothingItem extends ShoppingItem {
    private Map<String, Integer> sizeStock; // size -> quantity
    private boolean isSeasonal;
    private double seasonalDiscount;

    public ClothingItem(String itemId, String itemName, String itemDescription, double price, boolean isSeasonal) {
        super(itemId, itemName, itemDescription, price, 0);
        this.sizeStock = new HashMap<>();
        this.isSeasonal = isSeasonal;
        this.seasonalDiscount = 0.2; // 20% discount
    }

    public void addSizeStock(String size, int quantity) {
        sizeStock.put(size, sizeStock.getOrDefault(size, 0) + quantity);
        stockAvailable += quantity;
    }

    @Override
    public void updateStock(int quantity) {
        System.out.println("For clothing items, please use addSizeStock to update specific sizes");
    }

    @Override
    public boolean addToCart(Customer customer, int quantity) {
        System.out.println("Please select a size for " + itemName);
        System.out.println("Available sizes: " + sizeStock.keySet());
        System.out.print("Enter size: ");
        String size = System.console().readLine();

        if (!sizeStock.containsKey(size) || sizeStock.get(size) < quantity) {
            System.out.println("Error: Not enough stock available for size " + size);
            return false;
        }

        customer.getShoppingCart().addItem(this, quantity, size);
        return true;
    }

    @Override
    public String generateInvoice() {
        return String.format("Clothing Item: %s\nDescription: %s\nPrice: $%.2f\nSeasonal Discount: %s",
                itemName, itemDescription, getEffectivePrice(), isSeasonal ? "Yes (20%)" : "No");
    }

    @Override
    public boolean validateItem() {
        if (!validateCommonFields()) return false;
        if (sizeStock.isEmpty()) {
            System.out.println("Error: Clothing item must have size information");
            return false;
        }
        return true;
    }

    public double getEffectivePrice() {
        return isSeasonal ? price * (1 - seasonalDiscount) : price;
    }

    public Map<String, Integer> getSizeStock() { return sizeStock; }
    public boolean isSeasonal() { return isSeasonal; }
}
