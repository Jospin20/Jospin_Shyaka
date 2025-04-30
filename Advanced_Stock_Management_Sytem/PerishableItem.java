package Advanced_Stock_Management_Sytem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class PerishableItem extends StockItem {
    private LocalDate expirationDate;
    private int shelfLife; // in days

    public PerishableItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, String expirationDate, int shelfLife) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Perishable", supplier);
        this.expirationDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.shelfLife = shelfLife;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
        System.out.println("Stock updated for " + itemName + ". New quantity: " + quantityInStock);
    }

    @Override
    public double calculateStockValue() {
        if (isExpired()) return 0;
        if (isNearExpiration()) return quantityInStock * pricePerUnit * 0.5;
        return quantityInStock * pricePerUnit;
    }

    @Override
    public String generateStockReport() {
        String status;
        if (isExpired()) status = "EXPIRED (to be disposed)";
        else if (isNearExpiration()) status = "NEAR EXPIRATION (50% discount)";
        else status = "Fresh";

        return String.format("Perishable Item Report:\n" +
                        "ID: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nExpiration: %s\nShelf Life: %d days\nStatus: %s\nStock Value: $%.2f",
                itemId, itemName, quantityInStock, pricePerUnit, expirationDate, shelfLife, status, calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        if (!validateCommonFields()) return false;
        if (shelfLife <= 0) {
            System.out.println("Error: Shelf life must be positive for " + itemName);
            return false;
        }
        return true;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    public boolean isNearExpiration() {
        LocalDate today = LocalDate.now();
        return expirationDate.isBefore(today.plusDays(3)) && !isExpired();
    }

    public LocalDate getExpirationDate() { return expirationDate; }
    public int getShelfLife() { return shelfLife; }
}
