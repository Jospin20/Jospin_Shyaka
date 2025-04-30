package Advanced_Stock_Management_Sytem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class GroceryItem extends StockItem {
    private LocalDate expirationDate;
    private boolean nearExpiration;

    public GroceryItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, String expirationDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Groceries", supplier);
        this.expirationDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        checkExpirationStatus();
    }

    private void checkExpirationStatus() {
        LocalDate today = LocalDate.now();
        LocalDate warningDate = today.plusDays(7); // 1 week from now
        this.nearExpiration = expirationDate.isBefore(warningDate);
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
        System.out.println("Stock updated for " + itemName + ". New quantity: " + quantityInStock);
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (nearExpiration ? 0.7 : 1.0);
    }

    @Override
    public String generateStockReport() {
        return String.format("Grocery Item Report:\n" +
                        "ID: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nExpiration: %s\nStatus: %s\nStock Value: $%.2f",
                itemId, itemName, quantityInStock, pricePerUnit, expirationDate,
                nearExpiration ? "NEAR EXPIRATION (30% discount applied)" : "Fresh",
                calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        if (!validateCommonFields()) return false;
        if (expirationDate.isBefore(LocalDate.now())) {
            System.out.println("Error: " + itemName + " has already expired!");
            return false;
        }
        return true;
    }

    public LocalDate getExpirationDate() { return expirationDate; }
    public boolean isNearExpiration() { return nearExpiration; }
}