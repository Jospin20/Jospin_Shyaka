package Advanced_Online_Shopping_System;

import java.util.ArrayList;
import java.util.List;

class AccessoriesItem extends ShoppingItem {
    private List<String> colorsAvailable;
    private List<Review> reviews;
    private double averageRating;

    public AccessoriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.colorsAvailable = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.averageRating = 0;
    }

    public void addColor(String color) {
        colorsAvailable.add(color);
    }

    public void addReview(Review review) {
        reviews.add(review);
        updateAverageRating();
    }

    private void updateAverageRating() {
        if (reviews.isEmpty()) {
            averageRating = 0;
            return;
        }
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        averageRating = sum / reviews.size();
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
        if (colorsAvailable.isEmpty()) {
            System.out.println("Error: No colors available for " + itemName);
            return false;
        }

        System.out.println("Available colors: " + colorsAvailable);
        System.out.print("Select color: ");
        String color = System.console().readLine();

        if (!colorsAvailable.contains(color)) {
            System.out.println("Error: Selected color not available");
            return false;
        }

        customer.getShoppingCart().addItem(this, quantity, color);
        return true;
    }

    @Override
    public String generateInvoice() {
        return String.format("Accessory: %s\nDescription: %s\nPrice: $%.2f\nColors: %s\nRating: %.1f/5",
                itemName, itemDescription, price, colorsAvailable, averageRating);
    }

    @Override
    public boolean validateItem() {
        if (!validateCommonFields()) return false;
        if (colorsAvailable.isEmpty()) {
            System.out.println("Error: Accessory must have at least one color option");
            return false;
        }
        return true;
    }

    public List<String> getColorsAvailable() { return colorsAvailable; }
    public List<Review> getReviews() { return reviews; }
    public double getAverageRating() { return averageRating; }
}

class Review {
    private String customerName;
    private String comment;
    private int rating; // 1-5

    public Review(String customerName, String comment, int rating) {
        this.customerName = customerName;
        this.comment = comment;
        this.rating = rating;
    }

    public String getCustomerName() { return customerName; }
    public String getComment() { return comment; }
    public int getRating() { return rating; }
}