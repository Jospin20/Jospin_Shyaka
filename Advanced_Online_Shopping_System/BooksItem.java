package Advanced_Online_Shopping_System;

class BooksItem extends ShoppingItem {
    private String isbn;
    private String edition;
    private String printQuality; // "Standard", "Premium"

    public BooksItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable,
                     String isbn, String edition, String printQuality) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.isbn = isbn;
        this.edition = edition;
        this.printQuality = printQuality;
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
        return String.format("Book: %s\nDescription: %s\nPrice: $%.2f\nISBN: %s\nEdition: %s\nQuality: %s",
                itemName, itemDescription, price, isbn, edition, printQuality);
    }

    @Override
    public boolean validateItem() {
        if (!validateCommonFields()) return false;
        if (isbn == null || isbn.length() != 13) {
            System.out.println("Error: Invalid ISBN for " + itemName);
            return false;
        }
        if (!printQuality.equals("Standard") && !printQuality.equals("Premium")) {
            System.out.println("Error: Invalid print quality for " + itemName);
            return false;
        }
        return true;
    }

    public String getIsbn() { return isbn; }
    public String getEdition() { return edition; }
    public String getPrintQuality() { return printQuality; }
}
