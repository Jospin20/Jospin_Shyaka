package Advanced_Stock_Management_Sytem;

class ElectronicsItem extends StockItem {
    private int warrantyPeriod; // in months
    private double discount;

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, int warrantyPeriod) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Electronics", supplier);
        this.warrantyPeriod = warrantyPeriod;
        this.discount = 0;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
        System.out.println("Stock updated for " + itemName + ". New quantity: " + quantityInStock);
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (1 - discount);
    }

    @Override
    public String generateStockReport() {
        return String.format("Electronics Item Report:\n" +
                        "ID: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nWarranty: %d months\nDiscount: %.1f%%\nStock Value: $%.2f",
                itemId, itemName, quantityInStock, pricePerUnit, warrantyPeriod, discount*100, calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        if (!validateCommonFields()) return false;
        if (warrantyPeriod < 0 || warrantyPeriod > 36) {
            System.out.println("Error: Warranty period must be between 0-36 months for " + itemName);
            return false;
        }
        return true;
    }

    public void applyDiscount(double discount) {
        if (discount < 0 || discount > 0.5) {
            System.out.println("Error: Discount must be between 0-50%");
            return;
        }
        this.discount = discount;
        System.out.printf("Applied %.1f%% discount to %s\n", discount*100, itemName);
    }

    public int getWarrantyPeriod() { return warrantyPeriod; }
    public double getDiscount() { return discount; }
}
