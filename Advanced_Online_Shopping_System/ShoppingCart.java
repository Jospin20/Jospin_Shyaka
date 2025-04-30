package Advanced_Online_Shopping_System;

import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private String cartId;
    private List<CartItem> cartItems;
    private Customer customer;
    private double totalPrice;

    public ShoppingCart(Customer customer) {
        this.cartId = "CART-" + System.currentTimeMillis();
        this.cartItems = new ArrayList<>();
        this.customer = customer;
        this.totalPrice = 0;
    }

    public void addItem(ShoppingItem item, int quantity) {
        CartItem cartItem = new CartItem(item, quantity);
        cartItems.add(cartItem);
        updateTotalPrice();
        System.out.println(quantity + " x " + item.getItemName() + " added to cart");
    }

    public void addItem(ShoppingItem item, int quantity, String variant) {
        CartItem cartItem = new CartItem(item, quantity, variant);
        cartItems.add(cartItem);
        updateTotalPrice();
        System.out.println(quantity + " x " + item.getItemName() + " (" + variant + ") added to cart");
    }

    public void removeItem(String itemId) {
        cartItems.removeIf(item -> item.getItem().getItemId().equals(itemId));
        updateTotalPrice();
        System.out.println("Item removed from cart");
    }

    public void updateQuantity(String itemId, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getItem().getItemId().equals(itemId)) {
                if (newQuantity > item.getItem().getStockAvailable()) {
                    System.out.println("Error: Not enough stock available");
                    return;
                }
                item.setQuantity(newQuantity);
                updateTotalPrice();
                System.out.println("Quantity updated");
                return;
            }
        }
        System.out.println("Item not found in cart");
    }

    public void updateTotalPrice() {
        totalPrice = 0;
        for (CartItem item : cartItems) {
            totalPrice += item.getTotalPrice();
        }
    }

    public String generateCartSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== Shopping Cart Summary ===\n");
        summary.append("Customer: ").append(customer.getCustomerName()).append("\n\n");

        for (CartItem item : cartItems) {
            summary.append(item.toString()).append("\n\n");
        }

        summary.append(String.format("Total Price: $%.2f", totalPrice));
        return summary.toString();
    }

    // Getters
    public String getCartId() { return cartId; }
    public List<CartItem> getCartItems() { return cartItems; }
    public Customer getCustomer() { return customer; }
    public double getTotalPrice() { return totalPrice; }
}

class CartItem {
    private ShoppingItem item;
    private int quantity;
    private String variant; // size, color, etc.

    public CartItem(ShoppingItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public CartItem(ShoppingItem item, int quantity, String variant) {
        this(item, quantity);
        this.variant = variant;
    }

    public double getTotalPrice() {
        if (item instanceof ClothingItem) {
            return quantity * ((ClothingItem)item).getEffectivePrice();
        } else if (item instanceof GroceriesItem) {
            return quantity * ((GroceriesItem)item).getEffectivePrice();
        }
        return quantity * item.getPrice();
    }

    @Override
    public String toString() {
        String base = String.format("%s x %d - %s\nPrice: $%.2f each\nSubtotal: $%.2f",
                item.getItemName(), quantity, item.getItemDescription(),
                item.getPrice(), getTotalPrice());

        if (variant != null) {
            base += "\nVariant: " + variant;
        }

        return base;
    }

    // Getters and Setters
    public ShoppingItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getVariant() { return variant; }
}