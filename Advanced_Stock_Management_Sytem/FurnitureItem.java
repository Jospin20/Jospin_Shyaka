package Advanced_Stock_Management_Sytem;

class FurnitureItem extends StockItem {
    private double weight; // in kg
    private boolean isPacked;

    public FurnitureItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, double weight) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Furniture", supplier);
        this.weight = weight;
        this.isPacked = false;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
        System.out.println("Stock updated for " + itemName + ". New quantity: " + quantityInStock);
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit;
    }

    @Override
    public String generateStockReport() {
        return String.format("Furniture Item Report:\n" +
                        "ID: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nWeight: %.2f kg\nPacked: %s\nStock Value: $%.2f",
                itemId, itemName, quantityInStock, pricePerUnit, weight,
                isPacked ? "Yes" : "No", calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        if (!validateCommonFields()) return false;
        if (weight <= 0) {
            System.out.println("Error: Weight must be positive for " + itemName);
            return false;
        }
        return true;
    }

    public void markAsPacked() {
        this.isPacked = true;
        System.out.println(itemName + " marked as packed and ready for delivery");
    }

    public double getWeight() { return weight; }
    public boolean isPacked() { return isPacked; }
}
